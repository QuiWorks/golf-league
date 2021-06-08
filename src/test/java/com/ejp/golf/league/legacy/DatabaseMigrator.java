package com.ejp.golf.league.legacy;

import com.ejp.golf.league.domain.*;
import com.ejp.golf.league.legacy.domain.*;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import java.io.File;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DatabaseMigrator {

    private final EntityManagerFactory entityManagerFactory;
    private final League league;
    private final EventType eventType;

    public DatabaseMigrator() {
        entityManagerFactory = Persistence.createEntityManagerFactory("golf_league");
        League league = new League();
        league.setId(1);
        league.setName("Territory Wednesday Mens League");
        eventType = new EventType();
        eventType.setName("league");
        eventType.setDescription("league play");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(league);
        entityManager.persist(eventType);
        entityManager.getTransaction().commit();
        entityManager.close();
        this.league = league;
    }

    /**
     * A program to migrate data from the legacy database to the new schema.
     *
     * @param args NONE
     */
    public static void main(String[] args) {
        DatabaseMigrator databaseMigrator = new DatabaseMigrator();
        if (args.length == 0) args = new String[]{"ALL"};
        Arrays.stream(args)
                .map(LegacyData::getByName)
                .forEach(databaseMigrator::migrateData);
    }

    public void migrateData(LegacyData data) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        boolean shouldBreak = !LegacyData.ALL.equals(data);
        switch (data) {
            case ALL:
            case COURSES:
                CoursesList coursesList = getLegacyList(LegacyData.COURSES.getUrl(), CoursesList.class);
                coursesList.getCourses().forEach(course -> migrateToNewDomain(course, entityManager));
                if (shouldBreak) break;
            case FLIGHTS:
                FlightsList flightsList = getLegacyList(LegacyData.FLIGHTS.getUrl(), FlightsList.class);
                flightsList.getFlights().forEach(flight -> migrateToNewDomain(flight, entityManager));
                if (shouldBreak) break;
            case NINES:
                NinesList ninesList = getLegacyList(LegacyData.NINES.getUrl(), NinesList.class);
                ninesList.getNines().forEach(nine -> migrateToNewDomain(nine, entityManager));
                if (shouldBreak) break;
            case HOLES:
                HolesList holesList = getLegacyList(LegacyData.HOLES.getUrl(), HolesList.class);
                holesList.getHoles().forEach(hole -> migrateToNewDomain(hole, entityManager));
                if (shouldBreak) break;
            case TEAMS:
                TeamSumList teamsList = getLegacyList(LegacyData.TEAMS.getUrl(), TeamSumList.class);
                teamsList.getTeamSum().forEach(team -> migrateToNewDomain(team, entityManager));
                if (shouldBreak) break;
            case PLAYERS:
                PlayersList playersList = getLegacyList(LegacyData.PLAYERS.getUrl(), PlayersList.class);
                playersList.getPlayers().forEach(nine -> migrateToNewDomain(nine, entityManager));
                if (shouldBreak) break;
            case TEES:
                TeesList teesList = getLegacyList(LegacyData.TEES.getUrl(), TeesList.class);
                teesList.getTees().forEach(tee -> migrateToNewDomain(tee, entityManager));
                if (shouldBreak) break;
            case WEEK_DATES:
            case SCHEDULE:
            case SCHEDULE_MASTER:
                WeekDatesList weekDatesList = getLegacyList(LegacyData.WEEK_DATES.getUrl(), WeekDatesList.class);
                ScheduleList scheduleList = getLegacyList(LegacyData.SCHEDULE.getUrl(), ScheduleList.class);
                ScheduleMasterList scheduleMasterList = getLegacyList(LegacyData.SCHEDULE.getUrl(), ScheduleMasterList.class);
                migrateToNewDomain(weekDatesList, scheduleList, scheduleMasterList, entityManager);


                if (shouldBreak) break;
            case SCORE_CARD:
//                ScoreCardList scoreCardList = getLegacyList(LegacyData.SCHEDULE.getUrl(), ScoreCardList.class);
//                scoreCardList.getScoreCard().forEach(scoreCard -> migrateToNewDomain(scoreCard, entityManager));
                if (shouldBreak) break;
        }
        entityManager.close();
    }

    private <LEGACY_LIST> LEGACY_LIST getLegacyList(String url, Class<LEGACY_LIST> legacyListClass) {
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
        Flight flight = new Flight();
        flight.setId(legacyFlight.getFlight());
        flight.setLeagueId(league.getId());
        // Legacy flight info needs to be parsed from a string. ex: Flight 1 - 4:30 - 5:07
        String[] split = legacyFlight.getFDesc().split(" - ");
        LocalTime start = LocalTime.of(Integer.parseInt(split[1].split(":")[0]), Integer.parseInt(split[1].split(":")[1]));
        LocalTime end = LocalTime.of(Integer.parseInt(split[2].split(":")[0]), Integer.parseInt(split[2].split(":")[1]));
        flight.setStart(start);
        flight.setEnd(end);
        entityManager.getTransaction().begin();
        entityManager.persist(flight);
        IntStream.range(1,7).forEach(slot -> {
            TeeTime teeTime = new TeeTime();
            teeTime.setFlightId(legacyFlight.getFlight());
            teeTime.setSlot(slot);
            teeTime.setTime(start.plus(8L * slot, ChronoUnit.MINUTES));
            entityManager.persist(teeTime);
        });
        entityManager.getTransaction().commit();
    }

    private void migrateToNewDomain(Nines legacyNine, EntityManager entityManager) {
        Nine nine = new Nine();
        nine.setCourseId(legacyNine.getCourseId());
        nine.setName(legacyNine.getNineName());
        entityManager.getTransaction().begin();
        entityManager.persist(nine);
        entityManager.getTransaction().commit();
    }

    private void migrateToNewDomain(Holes legacyHole, EntityManager entityManager) {
        Hole hole = new Hole();
        hole.setHoleNumber(legacyHole.getHoleNo());
        hole.setCourseId(legacyHole.getCourseId());
        hole.setNineName(legacyHole.getNineName());

        // Save hole first to generate ID.
        entityManager.getTransaction().begin();
        entityManager.persist(hole);
        entityManager.getTransaction().commit();
        entityManager.flush();

        HoleHandicap holeHandicap = new HoleHandicap();
        holeHandicap.setHoleId(hole.getId());
        holeHandicap.setHandicap(legacyHole.getHdcp());

        HolePar holePar = new HolePar();
        holePar.setHoleId(hole.getId());
        holePar.setPar(legacyHole.getPar());

        HoleYardage holeYardage = new HoleYardage();
        holeYardage.setHoleId(hole.getId());
        holeYardage.setYardage(legacyHole.getYardage());

        entityManager.getTransaction().begin();
        entityManager.persist(holeHandicap);
        entityManager.persist(holePar);
        entityManager.persist(holeYardage);
        entityManager.getTransaction().commit();
    }

    private void migrateToNewDomain(TeamSum legacyTeam, EntityManager entityManager) {
        Team team = new Team();
        team.setId(legacyTeam.getTeam());
        team.setLeagueId(league.getId());
        team.setName(legacyTeam.getTeamName());
        entityManager.getTransaction().begin();
        entityManager.persist(team);
        entityManager.getTransaction().commit();
    }

    private void migrateToNewDomain(Players legacyPlayer, EntityManager entityManager) {
        Golfer golfer = new Golfer();
        PlayerHandicap playerHandicap = new PlayerHandicap();
        TeamMember teamMember = new TeamMember();

        golfer.setId(legacyPlayer.getGolfer());
        golfer.setFirstName(legacyPlayer.getFirstName());
        golfer.setMiddleName(legacyPlayer.getMiddleInt());
        golfer.setLastName(legacyPlayer.getLastName());
        golfer.setEmail(legacyPlayer.getEmail());
        golfer.setCity(legacyPlayer.getCity());
        golfer.setState(legacyPlayer.getState());
        golfer.setZip(legacyPlayer.getZipCode());
        golfer.setHomePhone(legacyPlayer.getHomePhone());
        golfer.setWorkPhone(legacyPlayer.getWorkPhone());
        golfer.setNotes(legacyPlayer.getNotes());
        golfer.setActive(legacyPlayer.isActive());
        golfer.setDateAdded(convertLegacyDate(legacyPlayer.getDateAdded()));

        playerHandicap.setGolferId(legacyPlayer.getGolfer());
        playerHandicap.setHandicap(legacyPlayer.getCurrentHdcp());

        teamMember.setGolferId(legacyPlayer.getGolfer());
        teamMember.setTeamId(legacyPlayer.getTeam());

        entityManager.getTransaction().begin();
        entityManager.persist(golfer);
        entityManager.persist(playerHandicap);
        entityManager.persist(teamMember);
        entityManager.getTransaction().commit();
    }

    private void migrateToNewDomain(Tees legacyObj, EntityManager entityManager) {
        Tee tee = new Tee();
        tee.setId(legacyObj.getTees());
        entityManager.getTransaction().begin();
        entityManager.persist(tee);
        entityManager.getTransaction().commit();
    }


    private void migrateToNewDomain(WeekDatesList weekDatesList, ScheduleList scheduleList, ScheduleMasterList scheduleMasterList, EntityManager entityManager) {
        EventMatch eventMatch = new EventMatch();
        TeamEvent teamEvent = new TeamEvent();

        List<Season> seasons = IntStream.range(2002, 2022).mapToObj(year -> {
            Season season = new Season();
            season.setCourseId(2);
            season.setLeagueId(league.getId());
            season.setYear(year);
            return season;
        }).collect(Collectors.toList());
        entityManager.getTransaction().begin();
        seasons.forEach(entityManager::persist);
        entityManager.getTransaction().commit();
        entityManager.flush();

//        seasons.forEach(season -> {
//            IntStream.range(1, 17).mapToObj(week -> {
//                Event event = new Event();
//                event.setWeek(week);
//
//            });
//        });


//        List<Event> events = weekDatesList.getWeekDates().stream()
//                .filter(weekDate -> weekDate.getEventType() == 1)
//                .sorted(Comparator.comparing(WeekDates::getWeek))
//                .map(weekDate -> {
//                    Event event = new Event();
//                    event.setEventType(eventType.getName());
//                    event.setDay(convertLegacyDate(weekDate.getWeekDate()).toLocalDate());
//                    event.setWeek(weekDate.getWeek());
//                    return event;})
//                .collect(Collectors.toList());



//        event.setEventType();
//        event.setDay(legacyObj.getWeekDate());
    }

    private void migrateToNewDomain(Schedule legacyObj, EntityManager entityManager) {}

    private void migrateToNewDomain(ScheduleMaster legacyObj, EntityManager entityManager) {}

    private void migrateToNewDomain(ScoreCard legacyObj, EntityManager entityManager) {
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
        TEAMS("src/test/resources/legacy/data/TeamSum.xml"),
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

        public static LegacyData getByName(String name) {
            return Arrays.stream(LegacyData.values())
                    .filter(data -> data.name().equalsIgnoreCase(name))
                    .findAny().orElse(LegacyData.ALL);
        }
    }

}
