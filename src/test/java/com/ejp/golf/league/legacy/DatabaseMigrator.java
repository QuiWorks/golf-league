package com.ejp.golf.league.legacy;

import com.ejp.golf.league.domain.*;
import com.ejp.golf.league.legacy.domain.*;

import javax.persistence.*;
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
import java.util.*;
import java.util.stream.Collectors;
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
                "score",
                "round",
                "team_event",
                "event_match",
                "tee_time",
                "flight",
                "hole",
                "nine",
                "player_handicap",
                "event",
                "season",
                "course",
                "event_type",
                "team_member",
                "team",
                "league",
                "user",
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
            case SCORE_CARD:
                ScoreCardList scoreCardList = getLegacyList(LegacyData.SCORE_CARD.getUrl(), ScoreCardList.class);
                migrateToNewDomain(scoreCardList, entityManager);
                if (shouldBreak) break;
        }
        entityManager.close();
    }

    public void addAdmin() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        User user = new User();
        user.setGolferId(1);
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

        if (course.getId() == 2) {
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
        PlayerHandicap playerHandicap = new PlayerHandicap();
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
        playerHandicap.setGolferId(legacyPlayer.getGolfer());
        playerHandicap.setHandicap(legacyPlayer.getCurrentHdcp());
        playerHandicap.setCreated(created);


        Team team = entityManager.find(Team.class, (int) legacyPlayer.getTeam());
        if (team == null) {
            team = new Team();
            team.setId(legacyPlayer.getTeam());
            team.setLeagueId(league.getId());
            entityManager.persist(team);
        }

        teamMember.setGolferId(legacyPlayer.getGolfer());
        teamMember.setTeamId(legacyPlayer.getTeam());


        entityManager.persist(golfer);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        entityManager.persist(playerHandicap);
        entityManager.persist(teamMember);
        entityManager.getTransaction().commit();
    }

    private void migrateToNewDomain(ScoreCardList scoreCardList, EntityManager entityManager) {
        List<Event> events = scoreCardList.getScoreCard().stream()
                .distinct()
                .map(scoreCard -> {
                    Event event = new Event();
                    event.setWeek(scoreCard.getWeek());
                    event.setEventType(eventType.getName());
                    LocalDate day = convertLegacyDate(scoreCard.getGameDate()).toLocalDate();
                    event.setDay(day);
                    event.setSeasonId(day.getYear());
                    return event;
                }).peek(event -> {
                    entityManager.getTransaction().begin();
                    entityManager.persist(event);
                    entityManager.flush();
                    entityManager.getTransaction().commit();
                }).collect(Collectors.toList());

        List<EventMatch> matches = scoreCardList.getScoreCard().stream()
                .map(scoreCard -> {
                    EventMatch eventMatch = new EventMatch();
                    eventMatch.setEventId(getEventId(events, scoreCard));
                    eventMatch.setNine(scoreCard.isBack9() ? "back" : "front");
                    eventMatch.setSlot(scoreCard.getSlot());
                    eventMatch.setFlightId(scoreCard.getFlight());
                    eventMatch.setCourseId(2); //hard coded.
                    return eventMatch;
                })
                .peek(eventMatch -> {
                    entityManager.getTransaction().begin();
                    entityManager.persist(eventMatch);
                    entityManager.getTransaction().commit();
                })
                .collect(Collectors.toList());

        List<TeamEvent> teamEvents = scoreCardList.getScoreCard().stream()
                .map(scoreCard -> {
                    int matchId = getMatchId(matches, scoreCard);
                    TeamEvent teamEvent = new TeamEvent();
                    teamEvent.setTeamId(scoreCard.getTeam1());
                    teamEvent.setMatchId(matchId);
                    teamEvent.setHome(true);
                    return teamEvent;
                })
                .collect(Collectors.toList());
        teamEvents.addAll(scoreCardList.getScoreCard().stream()
                .map(scoreCard -> {
                    int matchId = getMatchId(matches, scoreCard);
                    TeamEvent teamEvent = new TeamEvent();
                    teamEvent.setTeamId(scoreCard.getTeam2());
                    teamEvent.setMatchId(matchId);
                    teamEvent.setHome(false);
                    return teamEvent;
                })
                .collect(Collectors.toList()));

        entityManager.getTransaction().begin();
        teamEvents.stream()
                .filter(teamEvent -> entityManager.find(TeamEvent.class, new TeamEventPK(teamEvent.getMatchId(), teamEvent.getTeamId())) == null)
                .forEach(entityManager::persist);
        entityManager.getTransaction().commit();

        scoreCardList.getScoreCard().stream()
                .filter(scoreCard -> scoreCard.getA1() != null)
                .forEach(scoreCard -> {

                    Round roundA = new Round();
                    roundA.setMatchId(getMatchId(matches, scoreCard));
                    roundA.setGolferId(scoreCard.getGolfer1());

                    entityManager.getTransaction().begin();
                    entityManager.persist(roundA);
                    entityManager.flush();
                    entityManager.getTransaction().commit();

                    Score scoreA1 = new Score();
                    scoreA1.setRoundId(roundA.getId());
                    scoreA1.setHoleId(scoreCard.isBack9() ? 10 : 1);
                    scoreA1.setScore(scoreCard.getA1());
                    Score scoreA2 = new Score();
                    scoreA2.setRoundId(roundA.getId());
                    scoreA2.setHoleId(scoreCard.isBack9() ? 11 : 2);
                    scoreA2.setScore(scoreCard.getA2());
                    Score scoreA3 = new Score();
                    scoreA3.setRoundId(roundA.getId());
                    scoreA3.setHoleId(scoreCard.isBack9() ? 12 : 3);
                    scoreA3.setScore(scoreCard.getA3());
                    Score scoreA4 = new Score();
                    scoreA4.setRoundId(roundA.getId());
                    scoreA4.setHoleId(scoreCard.isBack9() ? 13 : 4);
                    scoreA4.setScore(scoreCard.getA4());
                    Score scoreA5 = new Score();
                    scoreA5.setRoundId(roundA.getId());
                    scoreA5.setHoleId(scoreCard.isBack9() ? 14 : 5);
                    scoreA5.setScore(scoreCard.getA5());
                    Score scoreA6 = new Score();
                    scoreA6.setRoundId(roundA.getId());
                    scoreA6.setHoleId(scoreCard.isBack9() ? 15 : 6);
                    scoreA6.setScore(scoreCard.getA6());
                    Score scoreA7 = new Score();
                    scoreA7.setRoundId(roundA.getId());
                    scoreA7.setHoleId(scoreCard.isBack9() ? 16 : 7);
                    scoreA7.setScore(scoreCard.getA7());
                    Score scoreA8 = new Score();
                    scoreA8.setRoundId(roundA.getId());
                    scoreA8.setHoleId(scoreCard.isBack9() ? 17 : 8);
                    scoreA8.setScore(scoreCard.getA8());
                    Score scoreA9 = new Score();
                    scoreA9.setRoundId(roundA.getId());
                    scoreA9.setHoleId(scoreCard.isBack9() ? 18 : 9);
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
                    roundB.setMatchId(getMatchId(matches, scoreCard));
                    roundB.setGolferId(scoreCard.getGolfer1());

                    entityManager.getTransaction().begin();
                    entityManager.persist(roundB);
                    entityManager.flush();
                    entityManager.getTransaction().commit();

                    Score scoreB1 = new Score();
                    scoreB1.setRoundId(roundB.getId());
                    scoreB1.setHoleId(scoreCard.isBack9() ? 10 : 1);
                    scoreB1.setScore(scoreCard.getB1());
                    Score scoreB2 = new Score();
                    scoreB2.setRoundId(roundB.getId());
                    scoreB2.setHoleId(scoreCard.isBack9() ? 11 : 2);
                    scoreB2.setScore(scoreCard.getB2());
                    Score scoreB3 = new Score();
                    scoreB3.setRoundId(roundB.getId());
                    scoreB3.setHoleId(scoreCard.isBack9() ? 12 : 3);
                    scoreB3.setScore(scoreCard.getB3());
                    Score scoreB4 = new Score();
                    scoreB4.setRoundId(roundB.getId());
                    scoreB4.setHoleId(scoreCard.isBack9() ? 13 : 4);
                    scoreB4.setScore(scoreCard.getB4());
                    Score scoreB5 = new Score();
                    scoreB5.setRoundId(roundB.getId());
                    scoreB5.setHoleId(scoreCard.isBack9() ? 14 : 5);
                    scoreB5.setScore(scoreCard.getB5());
                    Score scoreB6 = new Score();
                    scoreB6.setRoundId(roundB.getId());
                    scoreB6.setHoleId(scoreCard.isBack9() ? 15 : 6);
                    scoreB6.setScore(scoreCard.getB6());
                    Score scoreB7 = new Score();
                    scoreB7.setRoundId(roundB.getId());
                    scoreB7.setHoleId(scoreCard.isBack9() ? 16 : 7);
                    scoreB7.setScore(scoreCard.getB7());
                    Score scoreB8 = new Score();
                    scoreB8.setRoundId(roundB.getId());
                    scoreB8.setHoleId(scoreCard.isBack9() ? 17 : 8);
                    scoreB8.setScore(scoreCard.getB8());
                    Score scoreB9 = new Score();
                    scoreB9.setRoundId(roundB.getId());
                    scoreB9.setHoleId(scoreCard.isBack9() ? 18 : 9);
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
                    roundC.setMatchId(getMatchId(matches, scoreCard));
                    roundC.setGolferId(scoreCard.getGolfer1());

                    entityManager.getTransaction().begin();
                    entityManager.persist(roundC);
                    entityManager.flush();
                    entityManager.getTransaction().commit();

                    Score scoreC1 = new Score();
                    scoreC1.setRoundId(roundC.getId());
                    scoreC1.setHoleId(scoreCard.isBack9() ? 10 : 1);
                    scoreC1.setScore(scoreCard.getC1());
                    Score scoreC2 = new Score();
                    scoreC2.setRoundId(roundC.getId());
                    scoreC2.setHoleId(scoreCard.isBack9() ? 11 : 2);
                    scoreC2.setScore(scoreCard.getC2());
                    Score scoreC3 = new Score();
                    scoreC3.setRoundId(roundC.getId());
                    scoreC3.setHoleId(scoreCard.isBack9() ? 12 : 3);
                    scoreC3.setScore(scoreCard.getC3());
                    Score scoreC4 = new Score();
                    scoreC4.setRoundId(roundC.getId());
                    scoreC4.setHoleId(scoreCard.isBack9() ? 13 : 4);
                    scoreC4.setScore(scoreCard.getC4());
                    Score scoreC5 = new Score();
                    scoreC5.setRoundId(roundC.getId());
                    scoreC5.setHoleId(scoreCard.isBack9() ? 14 : 5);
                    scoreC5.setScore(scoreCard.getC5());
                    Score scoreC6 = new Score();
                    scoreC6.setRoundId(roundC.getId());
                    scoreC6.setHoleId(scoreCard.isBack9() ? 15 : 6);
                    scoreC6.setScore(scoreCard.getC6());
                    Score scoreC7 = new Score();
                    scoreC7.setRoundId(roundC.getId());
                    scoreC7.setHoleId(scoreCard.isBack9() ? 16 : 7);
                    scoreC7.setScore(scoreCard.getC7());
                    Score scoreC8 = new Score();
                    scoreC8.setRoundId(roundC.getId());
                    scoreC8.setHoleId(scoreCard.isBack9() ? 17 : 8);
                    scoreC8.setScore(scoreCard.getC8());
                    Score scoreC9 = new Score();
                    scoreC9.setRoundId(roundC.getId());
                    scoreC9.setHoleId(scoreCard.isBack9() ? 18 : 9);
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
                    roundD.setMatchId(getMatchId(matches, scoreCard));
                    roundD.setGolferId(scoreCard.getGolfer1());

                    entityManager.getTransaction().begin();
                    entityManager.persist(roundD);
                    entityManager.flush();
                    entityManager.getTransaction().commit();

                    Score scoreD1 = new Score();
                    scoreD1.setRoundId(roundD.getId());
                    scoreD1.setHoleId(scoreCard.isBack9() ? 10 : 1);
                    scoreD1.setScore(scoreCard.getD1());
                    Score scoreD2 = new Score();
                    scoreD2.setRoundId(roundD.getId());
                    scoreD2.setHoleId(scoreCard.isBack9() ? 11 : 2);
                    scoreD2.setScore(scoreCard.getD2());
                    Score scoreD3 = new Score();
                    scoreD3.setRoundId(roundD.getId());
                    scoreD3.setHoleId(scoreCard.isBack9() ? 12 : 3);
                    scoreD3.setScore(scoreCard.getD3());
                    Score scoreD4 = new Score();
                    scoreD4.setRoundId(roundD.getId());
                    scoreD4.setHoleId(scoreCard.isBack9() ? 13 : 4);
                    scoreD4.setScore(scoreCard.getD4());
                    Score scoreD5 = new Score();
                    scoreD5.setRoundId(roundD.getId());
                    scoreD5.setHoleId(scoreCard.isBack9() ? 14 : 5);
                    scoreD5.setScore(scoreCard.getD5());
                    Score scoreD6 = new Score();
                    scoreD6.setRoundId(roundD.getId());
                    scoreD6.setHoleId(scoreCard.isBack9() ? 15 : 6);
                    scoreD6.setScore(scoreCard.getD6());
                    Score scoreD7 = new Score();
                    scoreD7.setRoundId(roundD.getId());
                    scoreD7.setHoleId(scoreCard.isBack9() ? 16 : 7);
                    scoreD7.setScore(scoreCard.getD7());
                    Score scoreD8 = new Score();
                    scoreD8.setRoundId(roundD.getId());
                    scoreD8.setHoleId(scoreCard.isBack9() ? 17 : 8);
                    scoreD8.setScore(scoreCard.getD8());
                    Score scoreD9 = new Score();
                    scoreD9.setRoundId(roundD.getId());
                    scoreD9.setHoleId(scoreCard.isBack9() ? 18 : 9);
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
                });
    }

    private Integer getMatchId(List<EventMatch> matches, ScoreCard scoreCard) {
        return matches.stream()
                .filter(match -> match.getFlightId() == scoreCard.getFlight() &&
                        match.getNine().equals(scoreCard.isBack9() ? "back" : "front") &&
                        match.getSlot() == scoreCard.getSlot())
                .map(EventMatch::getId)
                .findAny().orElse(0);
    }

    private Integer getEventId(List<Event> events, ScoreCard scoreCard) {
        return events.stream().filter(e -> e.getDay().equals(convertLegacyDate(scoreCard.getGameDate()).toLocalDate())).map(Event::getId).findAny().orElse(1);
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

    private enum LegacyData {
        ALL("src/test/resources/legacy/data/*.xml"),
        COURSES("src/test/resources/legacy/data/Courses.xml"),
        FLIGHTS("src/test/resources/legacy/data/Flights.xml"),
        HOLES("src/test/resources/legacy/data/Holes.xml"),
        NINES("src/test/resources/legacy/data/Nines.xml"),
        PLAYERS("src/test/resources/legacy/data/Players.xml"),
        SCORE_CARD("src/test/resources/legacy/data/ScoreCard.xml"),
        TEE_TIMES("src/test/resources/legacy/data/TeeTimes.xml");

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
