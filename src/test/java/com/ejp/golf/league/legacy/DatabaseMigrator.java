package com.ejp.golf.league.legacy;

import com.ejp.golf.league.domain.*;
import com.ejp.golf.league.legacy.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

/**
 * A program to migrate the legacy data to the new database.
 * THIS WILL DELETE THE CURRENT DATA IN THE DATABASE.
 * Only run this program if the consequences are acceptable.
 */
public class DatabaseMigrator {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final EntityManagerFactory entityManagerFactory;
    private final League league;
    private final EventType eventType;
    private final int currentYear;
    private final List<Event> events = new ArrayList<>();
    private final List<EventMatch> matches = new ArrayList<>();

    public DatabaseMigrator() {
        // Set field values.
        entityManagerFactory = Persistence.createEntityManagerFactory("golf_league");
        this.league = new League();
        league.setId(1);
        league.setName("Territory Wednesday Mens League");
        eventType = new EventType();
        eventType.setName("league");
        eventType.setDescription("league play");
        this.currentYear = LocalDate.now().getYear();
    }

    /**
     * A program to migrate data from the legacy database to the new schema.
     *
     * @param args NONE
     */
    public static void main(String[] args) {
        DatabaseMigrator databaseMigrator = new DatabaseMigrator();
        if (args.length == 0) args = new String[]{"ALL"};
        if (args[0].equals("ALL")) {
            databaseMigrator.deleteExistingData();
            databaseMigrator.insertStaticData();
        }
        Arrays.stream(args)
                .map(LegacyData::getByName)
                .forEach(databaseMigrator::migrateData);
        databaseMigrator.addAdmin();
    }

    public void deleteExistingData() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List.of(
                "admin",
                "score",
                "round",
                "team_match",
                "event_match",
                "tee_time",
                "flight",
                "hole",
                "nine",
                "golfer_handicap",
                "event",
                "season",
                "course",
                "event_type",
                "team_member",
                "team",
                "league",
                "golfer"
        ).forEach(table -> {
            Query deleteQuery = entityManager.createQuery("DELETE FROM " + table);
            deleteQuery.executeUpdate();
        });
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void insertStaticData() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(this.league);
        entityManager.persist(this.eventType);

        entityManager.getTransaction().commit();
        entityManager.close();
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
            case PLAYERS:
                PlayersList playersList = getLegacyList(LegacyData.PLAYERS.getUrl(), PlayersList.class);
                playersList.getPlayers().forEach(nine -> migrateToNewDomain(nine, entityManager));
                if (shouldBreak) break;
            case WEEK_DATES:
                WeekDatesList weekDatesList = getLegacyList(LegacyData.WEEK_DATES.getUrl(), WeekDatesList.class);
                weekDatesList.getWeekDates().forEach(weekDate -> migrateToNewDomain(weekDate, entityManager));
                if (shouldBreak) break;
            case SCHEDULE:
                ScheduleList ScheduleList = getLegacyList(LegacyData.SCHEDULE.getUrl(), ScheduleList.class);
                ScheduleList.getSchedule().forEach(event -> migrateToNewDomain(event, entityManager));
                if (shouldBreak) break;
            case SCORE_CARD:
                ScoreCardList scoreCardList = getLegacyList(LegacyData.SCORE_CARD.getUrl(), ScoreCardList.class);
                scoreCardList.getScoreCard().stream()
                        .filter(scoreCard -> scoreCard.getA1() != null)
                        .forEach(scoreCard -> migrateToNewDomain(scoreCard, entityManager));
                if (shouldBreak) break;
        }
        entityManager.close();
    }

    public void addAdmin() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Admin user = new Admin();
        user.setGolferId(1);
        user.setLeagueId(1);
        user.setUsername("gary");
        user.setPassword("password");
        user.setDateAdded(Timestamp.valueOf(LocalDateTime.now()));
        entityManager.persist(user);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private void migrateToNewDomain(Courses legacyCourse, EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Course course = new Course();
        course.setId(legacyCourse.getCourseId());
        course.setName(legacyCourse.getCourseName());
        entityManager.persist(course);

        if (course.getId() == 1) {
            Season season = new Season();
            season.setCourseId(course.getId());
            season.setLeagueId(this.league.getId());
            season.setId(this.currentYear);
            season.setYear(this.currentYear);
            entityManager.persist(season);
        }

        entityManager.getTransaction().commit();
    }

    private void migrateToNewDomain(Flights legacyFlight, EntityManager entityManager) {
        Flight flight = new Flight();
        flight.setId(legacyFlight.getFlight());
        flight.setLeagueId(1);
        // Legacy flight info needs to be parsed from a string. ex: Flight 1 - 4:30 - 5:07
        String[] split = legacyFlight.getFDesc().split(" - ");
        LocalDateTime start = LocalTime.of(Integer.parseInt(split[1].split(":")[0]), Integer.parseInt(split[1].split(":")[1]), 0).atDate(LocalDate.now());
        LocalDateTime end = LocalTime.of(Integer.parseInt(split[2].split(":")[0]), Integer.parseInt(split[2].split(":")[1]), 0).atDate(LocalDate.now());
        flight.setStart(parseDate(start));
        flight.setEnd(parseDate(end));
        entityManager.getTransaction().begin();
        entityManager.persist(flight);
        IntStream.range(1, 7).forEach(slot -> {
            TeeTime teeTime = new TeeTime();
            teeTime.setFlightId(legacyFlight.getFlight());
            teeTime.setSlot(slot);
            teeTime.setTime(parseDate(start.plus(8L * slot, ChronoUnit.MINUTES)));
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
        hole.setId(legacyHole.getHoleNo());
        hole.setHoleNumber(legacyHole.getHoleNo());
        hole.setCourseId(legacyHole.getCourseId());
        hole.setNineName(legacyHole.getNineName());
        hole.setHandicap(legacyHole.getHdcp());
        hole.setPar(legacyHole.getPar());
        hole.setYardage(legacyHole.getYardage());
        Timestamp created = Timestamp.valueOf(LocalDateTime.now());
        hole.setCreated(created);

        entityManager.getTransaction().begin();
        entityManager.persist(hole);
        entityManager.getTransaction().commit();
    }

    private void migrateToNewDomain(Players legacyPlayer, EntityManager entityManager) {
        Golfer golfer = new Golfer();
        GolferHandicap golferHandicap = new GolferHandicap();
        TeamMember teamMember = new TeamMember();

        entityManager.getTransaction().begin();

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

        Timestamp created = Timestamp.valueOf(LocalDateTime.now());
        golferHandicap.setGolferId(legacyPlayer.getGolfer());
        golferHandicap.setHandicap(legacyPlayer.getCurrentHdcp());
        golferHandicap.setCreated(created);

        entityManager.persist(golfer);
        entityManager.persist(golferHandicap);

        String teamNumber = legacyPlayer.getTeam().toString();
        int teamId = Integer.parseInt(legacyPlayer.getFlight().toString() + (teamNumber.length() == 1 ? "0" + teamNumber : teamNumber));
        Team team = entityManager.find(Team.class, teamId);
        if (team == null) {
            team = new Team();
            team.setId(teamId);
            team.setTeamId(legacyPlayer.getTeam());
            team.setLeagueId(league.getId());
            team.setFlightId(legacyPlayer.getFlight());
            entityManager.persist(team);
        }

        if(!legacyPlayer.isSubstitute())
        {
            teamMember.setGolferId(legacyPlayer.getGolfer());
            teamMember.setTeamId(teamId);
            entityManager.persist(teamMember);
        }

        entityManager.getTransaction().commit();
    }

    private void migrateToNewDomain(WeekDates legacyWeekDate, EntityManager entityManager) {
        Event event = new Event();
        event.setWeek(legacyWeekDate.getWeek());
        event.setEventType("league");
        LocalDateTime day = convertLegacyDate(legacyWeekDate.getWeekDate());
        event.setDay(day.toLocalDate());
        event.setSeasonId(day.getYear());
        events.add(event);

        entityManager.getTransaction().begin();
        entityManager.persist(event);
        entityManager.getTransaction().commit();
    }

    private void migrateToNewDomain(Schedule legacyEvent, EntityManager entityManager) {

        EventMatch eventMatch = new EventMatch();
        Event event = new Event();
        event.setId(getEventId(events, legacyEvent.getWeek()));
        eventMatch.setEvent(event);
        eventMatch.setFlightId(legacyEvent.getFlight());
        eventMatch.setSlot(legacyEvent.getSlot());
        eventMatch.setNine(legacyEvent.isBack9() ? "back" : "front");
        eventMatch.setCourseId(1);
        entityManager.getTransaction().begin();
        entityManager.persist(eventMatch);
        entityManager.getTransaction().commit();
        matches.add(eventMatch);

        TeamMatch teamMatch1 = new TeamMatch();
        teamMatch1.setMatchId(eventMatch.getId());
        String team1Number = legacyEvent.getTeam1().toString();
        teamMatch1.setTeamId(Integer.parseInt(legacyEvent.getFlight().toString() + (team1Number.length() == 1 ? "0" + team1Number : team1Number)));
        teamMatch1.setHome(true);

        TeamMatch teamMatch2 = new TeamMatch();
        teamMatch2.setMatchId(eventMatch.getId());
        String team2Number = legacyEvent.getTeam2().toString();
        teamMatch2.setTeamId(Integer.parseInt(legacyEvent.getFlight2().toString() + (team2Number.length() == 1 ? "0" + team2Number : team2Number)));
        teamMatch2.setHome(false);

        entityManager.getTransaction().begin();
        entityManager.persist(teamMatch1);
        entityManager.persist(teamMatch2);
        entityManager.getTransaction().commit();
    }

    private void migrateToNewDomain(ScoreCard scoreCard, EntityManager entityManager) {
        int matchId = getMatchId(matches, events, scoreCard);

        Round roundA = new Round();
        EventMatch eventMatch = new EventMatch();
        eventMatch.setId(matchId);
        roundA.setEventMatch(eventMatch);
        roundA.setFlightId(scoreCard.getFlight());
        roundA.setSlot(scoreCard.getSlot());
        roundA.setNine(scoreCard.isBack9() ? "back" : "front");
        Golfer golfer1 = new Golfer();
        golfer1.setId(scoreCard.getGolfer1());
        roundA.setGolfer(golfer1);
        roundA.setHandicap(scoreCard.getHdcp1());
        roundA.setHome(true);
        roundA.setDatePlayed(Timestamp.valueOf(LocalDateTime.now()));

        entityManager.getTransaction().begin();
        entityManager.persist(roundA);
        entityManager.flush();
        entityManager.getTransaction().commit();

        Score scoreA1 = new Score();
        scoreA1.getRound().setId(roundA.getId());
        scoreA1.getHole().setId(scoreCard.isBack9() ? 10 : 1);
        scoreA1.setScore(scoreCard.getA1());
        Score scoreA2 = new Score();
        scoreA2.getRound().setId(roundA.getId());
        scoreA2.getHole().setId(scoreCard.isBack9() ? 11 : 2);
        scoreA2.setScore(scoreCard.getA2());
        Score scoreA3 = new Score();
        scoreA3.getRound().setId(roundA.getId());
        scoreA3.getHole().setId(scoreCard.isBack9() ? 12 : 3);
        scoreA3.setScore(scoreCard.getA3());
        Score scoreA4 = new Score();
        scoreA4.getRound().setId(roundA.getId());
        scoreA4.getHole().setId(scoreCard.isBack9() ? 13 : 4);
        scoreA4.setScore(scoreCard.getA4());
        Score scoreA5 = new Score();
        scoreA5.getRound().setId(roundA.getId());
        scoreA5.getHole().setId(scoreCard.isBack9() ? 14 : 5);
        scoreA5.setScore(scoreCard.getA5());
        Score scoreA6 = new Score();
        scoreA6.getRound().setId(roundA.getId());
        scoreA6.getHole().setId(scoreCard.isBack9() ? 15 : 6);
        scoreA6.setScore(scoreCard.getA6());
        Score scoreA7 = new Score();
        scoreA7.getRound().setId(roundA.getId());
        scoreA7.getHole().setId(scoreCard.isBack9() ? 16 : 7);
        scoreA7.setScore(scoreCard.getA7());
        Score scoreA8 = new Score();
        scoreA8.getRound().setId(roundA.getId());
        scoreA8.getHole().setId(scoreCard.isBack9() ? 17 : 8);
        scoreA8.setScore(scoreCard.getA8());
        Score scoreA9 = new Score();
        scoreA9.getRound().setId(roundA.getId());
        scoreA9.getHole().setId(scoreCard.isBack9() ? 18 : 9);
        scoreA9.setScore(scoreCard.getA9());

        entityManager.getTransaction().begin();
        entityManager.persist(scoreA1);
        entityManager.persist(scoreA2);
        entityManager.persist(scoreA3);
        entityManager.persist(scoreA4);
        entityManager.persist(scoreA5);
        entityManager.persist(scoreA6);
        entityManager.persist(scoreA7);
        entityManager.persist(scoreA8);
        entityManager.persist(scoreA9);
        entityManager.getTransaction().commit();

        Round roundB = new Round();
        roundB.setEventMatch(eventMatch);
        roundB.setFlightId(scoreCard.getFlight());
        roundB.setSlot(scoreCard.getSlot());
        roundB.setNine(scoreCard.isBack9() ? "back" : "front");
        Golfer golfer2 = new Golfer();
        golfer2.setId(scoreCard.getGolfer2());
        roundB.setGolfer(golfer2);
        roundB.setHandicap(scoreCard.getHdcp2());
        roundB.setHome(true);
        roundB.setDatePlayed(Timestamp.valueOf(LocalDateTime.now()));

        entityManager.getTransaction().begin();
        entityManager.persist(roundB);
        entityManager.flush();
        entityManager.getTransaction().commit();

        Score scoreB1 = new Score();
        scoreB1.getRound().setId(roundB.getId());
        scoreB1.getHole().setId(scoreCard.isBack9() ? 10 : 1);
        scoreB1.setScore(scoreCard.getB1());
        Score scoreB2 = new Score();
        scoreB2.getRound().setId(roundB.getId());
        scoreB2.getHole().setId(scoreCard.isBack9() ? 11 : 2);
        scoreB2.setScore(scoreCard.getB2());
        Score scoreB3 = new Score();
        scoreB3.getRound().setId(roundB.getId());
        scoreB3.getHole().setId(scoreCard.isBack9() ? 12 : 3);
        scoreB3.setScore(scoreCard.getB3());
        Score scoreB4 = new Score();
        scoreB4.getRound().setId(roundB.getId());
        scoreB4.getHole().setId(scoreCard.isBack9() ? 13 : 4);
        scoreB4.setScore(scoreCard.getB4());
        Score scoreB5 = new Score();
        scoreB5.getRound().setId(roundB.getId());
        scoreB5.getHole().setId(scoreCard.isBack9() ? 14 : 5);
        scoreB5.setScore(scoreCard.getB5());
        Score scoreB6 = new Score();
        scoreB6.getRound().setId(roundB.getId());
        scoreB6.getHole().setId(scoreCard.isBack9() ? 15 : 6);
        scoreB6.setScore(scoreCard.getB6());
        Score scoreB7 = new Score();
        scoreB7.getRound().setId(roundB.getId());
        scoreB7.getHole().setId(scoreCard.isBack9() ? 16 : 7);
        scoreB7.setScore(scoreCard.getB7());
        Score scoreB8 = new Score();
        scoreB8.getRound().setId(roundB.getId());
        scoreB8.getHole().setId(scoreCard.isBack9() ? 17 : 8);
        scoreB8.setScore(scoreCard.getB8());
        Score scoreB9 = new Score();
        scoreB9.getRound().setId(roundB.getId());
        scoreB9.getHole().setId(scoreCard.isBack9() ? 18 : 9);
        scoreB9.setScore(scoreCard.getB9());

        entityManager.getTransaction().begin();
        entityManager.persist(scoreB1);
        entityManager.persist(scoreB2);
        entityManager.persist(scoreB3);
        entityManager.persist(scoreB4);
        entityManager.persist(scoreB5);
        entityManager.persist(scoreB6);
        entityManager.persist(scoreB7);
        entityManager.persist(scoreB8);
        entityManager.persist(scoreB9);
        entityManager.getTransaction().commit();


        Round roundC = new Round();
        roundC.setEventMatch(eventMatch);
        roundC.setFlightId(scoreCard.getFlight());
        roundC.setSlot(scoreCard.getSlot());
        roundC.setNine(scoreCard.isBack9() ? "back" : "front");
        Golfer golfer3 = new Golfer();
        golfer3.setId(scoreCard.getGolfer3());
        roundC.setGolfer(golfer3);
        roundC.setHandicap(scoreCard.getHdcp3());
        roundC.setHome(false);
        roundC.setDatePlayed(Timestamp.valueOf(LocalDateTime.now()));

        entityManager.getTransaction().begin();
        entityManager.persist(roundC);
        entityManager.flush();
        entityManager.getTransaction().commit();

        Score scoreC1 = new Score();
        scoreC1.getRound().setId(roundC.getId());
        scoreC1.getHole().setId(scoreCard.isBack9() ? 10 : 1);
        scoreC1.setScore(scoreCard.getC1());
        Score scoreC2 = new Score();
        scoreC2.getRound().setId(roundC.getId());
        scoreC2.getHole().setId(scoreCard.isBack9() ? 11 : 2);
        scoreC2.setScore(scoreCard.getC2());
        Score scoreC3 = new Score();
        scoreC3.getRound().setId(roundC.getId());
        scoreC3.getHole().setId(scoreCard.isBack9() ? 12 : 3);
        scoreC3.setScore(scoreCard.getC3());
        Score scoreC4 = new Score();
        scoreC4.getRound().setId(roundC.getId());
        scoreC4.getHole().setId(scoreCard.isBack9() ? 13 : 4);
        scoreC4.setScore(scoreCard.getC4());
        Score scoreC5 = new Score();
        scoreC5.getRound().setId(roundC.getId());
        scoreC5.getHole().setId(scoreCard.isBack9() ? 14 : 5);
        scoreC5.setScore(scoreCard.getC5());
        Score scoreC6 = new Score();
        scoreC6.getRound().setId(roundC.getId());
        scoreC6.getHole().setId(scoreCard.isBack9() ? 15 : 6);
        scoreC6.setScore(scoreCard.getC6());
        Score scoreC7 = new Score();
        scoreC7.getRound().setId(roundC.getId());
        scoreC7.getHole().setId(scoreCard.isBack9() ? 16 : 7);
        scoreC7.setScore(scoreCard.getC7());
        Score scoreC8 = new Score();
        scoreC8.getRound().setId(roundC.getId());
        scoreC8.getHole().setId(scoreCard.isBack9() ? 17 : 8);
        scoreC8.setScore(scoreCard.getC8());
        Score scoreC9 = new Score();
        scoreC9.getRound().setId(roundC.getId());
        scoreC9.getHole().setId(scoreCard.isBack9() ? 18 : 9);
        scoreC9.setScore(scoreCard.getC9());

        entityManager.getTransaction().begin();
        entityManager.persist(scoreC1);
        entityManager.persist(scoreC2);
        entityManager.persist(scoreC3);
        entityManager.persist(scoreC4);
        entityManager.persist(scoreC5);
        entityManager.persist(scoreC6);
        entityManager.persist(scoreC7);
        entityManager.persist(scoreC8);
        entityManager.persist(scoreC9);
        entityManager.getTransaction().commit();

        Round roundD = new Round();
        roundD.setEventMatch(eventMatch);
        roundD.setFlightId(scoreCard.getFlight());
        roundD.setSlot(scoreCard.getSlot());
        roundD.setNine(scoreCard.isBack9() ? "back" : "front");
        Golfer golfer4 = new Golfer();
        golfer4.setId(scoreCard.getGolfer4());
        roundD.setGolfer(golfer4);
        roundD.setHandicap(scoreCard.getHdcp4());
        roundD.setHome(false);
        roundD.setDatePlayed(Timestamp.valueOf(LocalDateTime.now()));

        entityManager.getTransaction().begin();
        entityManager.persist(roundD);
        entityManager.flush();
        entityManager.getTransaction().commit();

        Score scoreD1 = new Score();
        scoreD1.getRound().setId(roundD.getId());
        scoreD1.getHole().setId(scoreCard.isBack9() ? 10 : 1);
        scoreD1.setScore(scoreCard.getD1());
        Score scoreD2 = new Score();
        scoreD2.getRound().setId(roundD.getId());
        scoreD2.getHole().setId(scoreCard.isBack9() ? 11 : 2);
        scoreD2.setScore(scoreCard.getD2());
        Score scoreD3 = new Score();
        scoreD3.getRound().setId(roundD.getId());
        scoreD3.getHole().setId(scoreCard.isBack9() ? 12 : 3);
        scoreD3.setScore(scoreCard.getD3());
        Score scoreD4 = new Score();
        scoreD4.getRound().setId(roundD.getId());
        scoreD4.getHole().setId(scoreCard.isBack9() ? 13 : 4);
        scoreD4.setScore(scoreCard.getD4());
        Score scoreD5 = new Score();
        scoreD5.getRound().setId(roundD.getId());
        scoreD5.getHole().setId(scoreCard.isBack9() ? 14 : 5);
        scoreD5.setScore(scoreCard.getD5());
        Score scoreD6 = new Score();
        scoreD6.getRound().setId(roundD.getId());
        scoreD6.getHole().setId(scoreCard.isBack9() ? 15 : 6);
        scoreD6.setScore(scoreCard.getD6());
        Score scoreD7 = new Score();
        scoreD7.getRound().setId(roundD.getId());
        scoreD7.getHole().setId(scoreCard.isBack9() ? 16 : 7);
        scoreD7.setScore(scoreCard.getD7());
        Score scoreD8 = new Score();
        scoreD8.getRound().setId(roundD.getId());
        scoreD8.getHole().setId(scoreCard.isBack9() ? 17 : 8);
        scoreD8.setScore(scoreCard.getD8());
        Score scoreD9 = new Score();
        scoreD9.getRound().setId(roundD.getId());
        scoreD9.getHole().setId(scoreCard.isBack9() ? 18 : 9);
        scoreD9.setScore(scoreCard.getD9());

        entityManager.getTransaction().begin();
        entityManager.persist(scoreD1);
        entityManager.persist(scoreD2);
        entityManager.persist(scoreD3);
        entityManager.persist(scoreD4);
        entityManager.persist(scoreD5);
        entityManager.persist(scoreD6);
        entityManager.persist(scoreD7);
        entityManager.persist(scoreD8);
        entityManager.persist(scoreD9);
        entityManager.getTransaction().commit();
    }

    private Integer getMatchId(List<EventMatch> matches, List<Event> events, ScoreCard scoreCard) {
        return matches.stream()
                .filter(match -> match.getFlightId() == scoreCard.getFlight() &&
                        match.getEvent().getId() == getEventId(events, scoreCard) &&
                        match.getNine().equals(scoreCard.isBack9() ? "back" : "front") &&
                        match.getSlot() == scoreCard.getSlot())
                .map(EventMatch::getId)
                .findAny().orElse(0);
    }

    private Integer getEventId(List<Event> events, ScoreCard scoreCard) {
        return events.stream().filter(e -> e.getDay().equals(convertLegacyDate(scoreCard.getGameDate()).toLocalDate())).map(Event::getId).findAny().orElse(1);
    }

    private Integer getEventId(List<Event> events, int week) {
        return events.stream()
                .filter(e -> e.getWeek() == week)
                .map(Event::getId).
                findAny()
                .orElse(1);
    }

    private <LEGACY_LIST> LEGACY_LIST getLegacyList(String url, Class<LEGACY_LIST> legacyListClass) {
        try {
            JAXBContext context = JAXBContext.newInstance(legacyListClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return legacyListClass.cast(unmarshaller.unmarshal(new File(url)));
        } catch (JAXBException e) {
            throw new RuntimeException("Could not read legacy data: " + legacyListClass + " from file: " + url, e);
        }
    }

    private Date parseDate(LocalDateTime time) {
        try {
            return simpleDateFormat.parse(time.getYear() + "-" + time.getMonthValue() + "-" + time.getDayOfMonth() + " " + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private LocalDateTime convertLegacyDate(XMLGregorianCalendar date) {
        return LocalDateTime.of(date.getYear(), date.getMonth(), date.getDay(), date.getHour(), date.getMinute(), date.getSecond());
    }

    public enum LegacyData {
        ALL("src/test/resources/legacy/data/*.xml"),
        COURSES("src/test/resources/legacy/data/Courses.xml"),
        FLIGHTS("src/test/resources/legacy/data/Flights.xml"),
        HOLES("src/test/resources/legacy/data/Holes.xml"),
        NINES("src/test/resources/legacy/data/Nines.xml"),
        PLAYERS("src/test/resources/legacy/data/Players.xml"),
        WEEK_DATES("src/test/resources/legacy/data/WeekDates.xml"),
        SCHEDULE("src/test/resources/legacy/data/Schedule.xml"),
        SCORE_CARD("src/test/resources/legacy/data/ScoreCard.xml");

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
