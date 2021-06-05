package com.ejp.golf.league.legacy;

import com.ejp.golf.league.domain.*;
import com.ejp.golf.league.legacy.domain.*;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;

public class DatabaseMigrator {

    private final EntityManagerFactory entityManagerFactory;

    public DatabaseMigrator() {
        entityManagerFactory = Persistence.createEntityManagerFactory("golf_league");
    }

    @Test
    void deleteme() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("golf_league");
        Course course = new Course();
        course.setName("DELETEME");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /**
     * A program to migrate data from the legacy database to the new schema.
     * @param args NONE
     */
    public static void main(String[] args) {
        DatabaseMigrator databaseMigrator = new DatabaseMigrator();
        if(args.length == 0) args = new String[]{"ALL"};
        Arrays.stream(args)
                .map(LegacyData::getByName)
                .forEach(databaseMigrator::migrateData);
    }

    public void migrateData(LegacyData data)
    {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        boolean shouldBreak = !LegacyData.ALL.equals(data);
        switch (data){
            case ALL:
            case COURSES:
                CoursesList coursesList = getLegacyList(LegacyData.COURSES.getUrl(), CoursesList.class);
                coursesList.getCourses().forEach(course -> migrateToNewDomain(course, entityManager));
                if(shouldBreak) break;
            case FLIGHTS:
                FlightsList flightsList = getLegacyList(LegacyData.FLIGHTS.getUrl(), FlightsList.class);
                flightsList.getFlights().forEach(flight -> migrateToNewDomain(flight, entityManager));
                if(shouldBreak) break;
            case HOLES:

                if(shouldBreak) break;
            case NINES:

                if(shouldBreak) break;
            case PLAYERS:

                if(shouldBreak) break;
            case SCHEDULE:

                if(shouldBreak) break;
            case SCHEDULE_MASTER:

                if(shouldBreak) break;
            case SCORE_CARD:

                if(shouldBreak) break;
            case TEES:

                if(shouldBreak) break;
            case TEE_TIMES:

                if(shouldBreak) break;
            case WEEK_DATES:

                if(shouldBreak) break;
        }
        entityManager.close();
    }

    private <LEGACY_LIST> LEGACY_LIST getLegacyList(String url, Class<LEGACY_LIST> legacyListClass)
    {
        try {
            JAXBContext context = JAXBContext.newInstance(CoursesList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return legacyListClass.cast(unmarshaller.unmarshal(new File(url)));
        } catch (JAXBException e) {
            throw new RuntimeException("Could not read legacy data: " + legacyListClass + " from file: " + url, e);
        }
    }

    private void migrateToNewDomain(Courses legacyCourse, EntityManager entityManager) {
        Course course = new Course();
        course.setId(legacyCourse.getCourseId());
        course.setName(legacyCourse.getCourseName());
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
    }

    private void migrateToNewDomain(Flights legacyFlight, EntityManager entityManager) {
//        Flight flight = new Flight();
//        flight.setId(legacyFlight.getFlight());
////        flight.setStart(legacyFlight.);
//        entityManager.getTransaction().begin();
//        entityManager.persist(flight);
//        entityManager.getTransaction().commit();
    }

    private void migratePlayerData() {
        try {
            JAXBContext context = JAXBContext.newInstance(PlayersList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            PlayersList playersList = (PlayersList) unmarshaller.unmarshal(new File("src/test/resources/legacy/data/Players.xml"));
            playersList.getPlayers().forEach(this::migratePlayerToNewDomain);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private void migratePlayerToNewDomain(Players player) {
        Golfer golfer = new Golfer();
        PlayerHandicap playerHandicap = new PlayerHandicap();
        TeamMember teamMember = new TeamMember();

        golfer.setId(player.getGolfer());
        golfer.setFirstName(player.getFirstName());
        golfer.setMiddleName(player.getMiddleInt());
        golfer.setLastName(player.getLastName());
        golfer.setEmail(player.getEmail());
        golfer.setCity(player.getCity());
        golfer.setState(player.getState());
        golfer.setZip(player.getZipCode());
        golfer.setHomePhone(player.getHomePhone());
        golfer.setWorkPhone(player.getWorkPhone());
        golfer.setNotes(player.getNotes());
        golfer.setActive(player.isActive());
        golfer.setDateAdded(convertLegacyDate(player.getDateAdded()));

        playerHandicap.setGolferId(player.getGolfer());
        playerHandicap.setHandicap(player.getCurrentHdcp());

        //  TODO need to insert team first
        teamMember.setGolferId(player.getGolfer());
        teamMember.setTeamId(player.getTeam());

    }

    private LocalDateTime convertLegacyDate(XMLGregorianCalendar date) {
        return LocalDateTime.of(date.getYear(), date.getMonth(), date.getDay(), date.getHour(), date.getMinute(), date.getSecond());
    }

    private enum LegacyData {
        ALL("src/test/resources/legacy/data/*.xml"),
        COURSES("src/test/resources/legacy/data/Courses.xml"),
        FLIGHTS("src/test/resources/legacy/data/Flights.xml"),
        HOLES("src/test/resources/legacy/data/Holes.xml"),
        NINES("src/test/resources/legacy/data/Nines.xml"),
        PLAYERS("src/test/resources/legacy/data/Players.xml"),
        SCHEDULE("src/test/resources/legacy/data/Schedule.xml"),
        SCHEDULE_MASTER("src/test/resources/legacy/data/ScheduleMaster.xml"),
        SCORE_CARD("src/test/resources/legacy/data/ScoreCard.xml"),
        TEES("src/test/resources/legacy/data/Tees.xml"),
        TEE_TIMES("src/test/resources/legacy/data/TeeTimes.xml"),
        WEEK_DATES("src/test/resources/legacy/data/WeekDates.xml");

        private final String url;

        LegacyData(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public static LegacyData getByName(String name)
        {
            return Arrays.stream(LegacyData.values())
                    .filter(data -> data.name().equalsIgnoreCase(name))
                    .findAny().orElse(LegacyData.ALL);
        }
    }

}
