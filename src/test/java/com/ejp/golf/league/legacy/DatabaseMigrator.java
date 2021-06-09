package com.ejp.golf.league.legacy;

import com.ejp.golf.league.domain.*;
import com.ejp.golf.league.legacy.domain.*;
import org.apache.commons.lang3.tuple.Pair;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

public class DatabaseMigrator {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final List<Integer> teamIds = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    private final EntityManagerFactory entityManagerFactory;
    private final League league;
    private final EventType eventType;
    public final Map<Integer, Integer> teamSlotMap = new HashMap<>();

    public DatabaseMigrator() {
        entityManagerFactory = Persistence.createEntityManagerFactory("golf_league");
        League league = new League();
        league.setId(1);
        league.setName("Territory Wednesday Mens League");
        eventType = new EventType();
        eventType.setName("league");
        eventType.setDescription("league play");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //TODO ADD TO DB SCRIPT TODO ADD COURSE TO THIS LIST
        //TODO add clearing job
//        entityManager.getTransaction().begin();
//        entityManager.persist(league);
//        entityManager.persist(eventType);
//        entityManager.getTransaction().commit();
//        entityManager.close();

//        IntStream.range(2002, 2022)
//                .mapToObj(year -> {
//                    Season season = new Season();
//                    season.setId(year);
//                    season.setYear(year);
//                    season.setLeagueId(1);
//                    season.setCourseId(2);
//                    return season;
//                }).forEach(season -> {
//                    entityManager.getTransaction().begin();
//                    entityManager.persist(season);
//                    entityManager.getTransaction().commit();
//                });


        this.league = league;
    }

    public EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }


    /**
     * A program to migrate data from the legacy database to the new schema.
     *
     * @param args NONE
     */
    public static void main(String[] args) {
//        try {
//            JAXBContext context = JAXBContext.newInstance(FlightsList.class);
//            Unmarshaller unmarshaller = context.createUnmarshaller();
//            FlightsList cast = FlightsList.class.cast(unmarshaller.unmarshal(new File("src/test/resources/legacy/data/Flights.xml")));
//            System.out.println(cast);
//        } catch (JAXBException e) {
//            throw new RuntimeException("Could not read legacy data: " + FlightsList.class + " from file: " + "src/test/resources/legacy/data/Flights.xml", e);
//        }
        DatabaseMigrator databaseMigrator = new DatabaseMigrator();

//        HolesList holesList = databaseMigrator.getLegacyList(LegacyData.HOLES.getUrl(), HolesList.class);
//        holesList.getHoles().forEach(hole -> databaseMigrator.migrateToNewDomain(hole, databaseMigrator.getEntityManager()));
//        TeamSumTranList teamSumTranList = databaseMigrator.getLegacyList(LegacyData.TEAM_SUMMARY_TRANSACTION.getUrl(), TeamSumTranList.class);
//        teamSumTranList.getTeamSumTran().stream().distinct().forEach(teamSumTran -> {
//            databaseMigrator.teamSlotMap.put(teamSumTran.getTeam1(), (int) teamSumTran.getSlot());
//            databaseMigrator.teamSlotMap.put(teamSumTran.getTeam2(), (int) teamSumTran.getSlot());
//        });
//        PlayerTranList scoreCardList = databaseMigrator.getLegacyList(LegacyData.PLAYER_TRANSACTION.getUrl(), PlayerTranList.class);
//        databaseMigrator.migrateToNewDomain(scoreCardList, databaseMigrator.getEntityManager());
        FlightsList flightsList = databaseMigrator.getLegacyList(LegacyData.FLIGHTS.getUrl(), FlightsList.class);
        flightsList.getFlights().forEach(flight -> databaseMigrator.migrateToNewDomain(flight, databaseMigrator.getEntityManager()));

//        if (args.length == 0) args = new String[]{"ALL"};
//        Arrays.stream(args)
//                .map(LegacyData::getByName)
//                .forEach(databaseMigrator::migrateData);
    }

    public void migrateData(LegacyData data) {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        boolean shouldBreak = !LegacyData.ALL.equals(data);
        switch (data) {
            case ALL:
//            case COURSES:
//                CoursesList coursesList = getLegacyList(LegacyData.COURSES.getUrl(), CoursesList.class);
//                coursesList.getCourses().forEach(course -> migrateToNewDomain(course, entityManager));
//                if (shouldBreak) break;
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
            case TEAM_SUMMARY_TRANSACTION:
                TeamSumTranList teamSumTranList = getLegacyList(LegacyData.TEAM_SUMMARY_TRANSACTION.getUrl(), TeamSumTranList.class);
                teamSumTranList.getTeamSumTran().stream().distinct().forEach(teamSumTran -> {
                    teamSlotMap.put(teamSumTran.getTeam1(), (int) teamSumTran.getSlot());
                    teamSlotMap.put(teamSumTran.getTeam2(), (int) teamSumTran.getSlot());
                });
//            case TEES:
//                TeesList teesList = getLegacyList(LegacyData.TEES.getUrl(), TeesList.class);
//                teesList.getTees().forEach(tee -> migrateToNewDomain(tee, entityManager));
//                if (shouldBreak) break;
            case PLAYER_TRANSACTION:
                PlayerTranList scoreCardList = getLegacyList(LegacyData.PLAYER_TRANSACTION.getUrl(), PlayerTranList.class);
                migrateToNewDomain(scoreCardList, entityManager);
                if (shouldBreak) break;
        }
        entityManager.close();
    }

    private static class TeamSlot {
        int teamId, slot;

        public TeamSlot(int teamId, int slot) {
            this.teamId = teamId;
            this.slot = slot;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TeamSlot teamSlot = (TeamSlot) o;
            return teamId == teamSlot.teamId && slot == teamSlot.slot;
        }

        @Override
        public int hashCode() {
            return Objects.hash(teamId, slot);
        }
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

    private Date parseDate(LocalDateTime time) {
        try {
            return simpleDateFormat.parse(time.getYear() + "-" + time.getMonthValue() + "-" + time.getDayOfMonth() + " " + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
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

        // Save hole first to generate ID.
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

    private void migrateToNewDomain(Tees legacyObj, EntityManager entityManager) {
        Tee tee = new Tee();
        tee.setId(legacyObj.getTees());
        entityManager.getTransaction().begin();
        entityManager.persist(tee);
        entityManager.getTransaction().commit();
    }

    private void migrateToNewDomain(PlayerTranList playerTranList, EntityManager entityManager) {
        List<Event> events = playerTranList.getPlayerTran().stream()
                .distinct()
                .filter(playerTran -> teamIds.contains(playerTran.getTeam()))
                .map(playerTran -> {
                    Event event = new Event();
                    event.setWeek(playerTran.getWeek());
                    event.setEventType(eventType.getName());
                    LocalDate day = convertLegacyDate(playerTran.getGameDate()).toLocalDate();
                    event.setDay(day);
                    event.setSeasonId(day.getYear());
                    return event;
                }).peek(event -> {
                    entityManager.getTransaction().begin();
                    entityManager.persist(event);
                    entityManager.getTransaction().commit();
                }).collect(Collectors.toList());

        List<EventMatch> matches = playerTranList.getPlayerTran().stream()
                .filter(playerTran -> playerTran.getTeam() != null)
                .filter(playerTran -> teamIds.contains(playerTran.getTeam()))
                .map(playerTran -> {
                    EventMatch eventMatch = new EventMatch();
                    eventMatch.setEventId(getEventId(events, playerTran));
                    eventMatch.setNine(playerTran.isBack9() ? "back" : "front");
                    eventMatch.setSlot(teamSlotMap.get(playerTran.getTeam()));
                    eventMatch.setFlightId(playerTran.getFlight());
                    eventMatch.setCourseId(2); //hard coded.
                    return eventMatch;
                })
                .peek(eventMatch -> {
                    if (entityManager.find(TeeTime.class, new TeeTimePK(eventMatch.getFlightId(), eventMatch.getSlot())) != null) {
                        entityManager.getTransaction().begin();
                        entityManager.persist(eventMatch);
                        entityManager.getTransaction().commit();
                    }
                })
                .collect(Collectors.toList());

        playerTranList.getPlayerTran().stream()
                .filter(playerTran -> teamIds.contains(playerTran.getTeam()))
                .forEach(playerTran -> {
                    int matchId = getMatchId(matches, playerTran);
                    TeamEvent teamEvent = entityManager.find(TeamEvent.class, new TeamEventPK(matchId, playerTran.getTeam()));
                    EventMatch eventMatch = entityManager.find(EventMatch.class, matchId);

                    if (teamEvent == null && eventMatch != null) {
                        teamEvent = new TeamEvent();
                        teamEvent.setMatchId(matchId);
                        teamEvent.setTeamId(playerTran.getTeam());
                        entityManager.getTransaction().begin();
                        entityManager.persist(teamEvent);
                        entityManager.getTransaction().commit();
                    }

                });

        List<Pair<Integer, Integer>> integerIntegerPair = new ArrayList<>();
        playerTranList.getPlayerTran().stream()
                .filter(playerTran -> teamIds.contains(playerTran.getTeam()))
                .filter(playerTran -> !integerIntegerPair.contains(Pair.of(getEventId(events, playerTran), playerTran.getGolfer())))
                .peek(playerTran -> integerIntegerPair.add(Pair.of(getEventId(events, playerTran), playerTran.getGolfer())))
                .forEach(playerTran -> {

                    Round round = new Round();
                    round.setEventId(getEventId(events, playerTran));
                    round.setGolferId(playerTran.getGolfer());

                    entityManager.getTransaction().begin();
                    entityManager.persist(round);
                    entityManager.getTransaction().commit();

                    Score scoreH1 = new Score();
                    scoreH1.setRoundId(round.getId());
                    scoreH1.setHoleId(playerTran.isBack9() ? 10 : 1);
                    scoreH1.setScore(playerTran.getH1());
                    Score scoreH2 = new Score();
                    scoreH2.setRoundId(round.getId());
                    scoreH2.setHoleId(playerTran.isBack9() ? 11 : 2);
                    scoreH2.setScore(playerTran.getH2());
                    Score scoreH3 = new Score();
                    scoreH3.setRoundId(round.getId());
                    scoreH3.setHoleId(playerTran.isBack9() ? 12 : 3);
                    scoreH3.setScore(playerTran.getH3());
                    Score scoreH4 = new Score();
                    scoreH4.setRoundId(round.getId());
                    scoreH4.setHoleId(playerTran.isBack9() ? 13 : 4);
                    scoreH4.setScore(playerTran.getH4());
                    Score scoreH5 = new Score();
                    scoreH5.setRoundId(round.getId());
                    scoreH5.setHoleId(playerTran.isBack9() ? 14 : 5);
                    scoreH5.setScore(playerTran.getH5());
                    Score scoreH6 = new Score();
                    scoreH6.setRoundId(round.getId());
                    scoreH6.setHoleId(playerTran.isBack9() ? 15 : 6);
                    scoreH6.setScore(playerTran.getH6());
                    Score scoreH7 = new Score();
                    scoreH7.setRoundId(round.getId());
                    scoreH7.setHoleId(playerTran.isBack9() ? 16 : 7);
                    scoreH7.setScore(playerTran.getH7());
                    Score scoreH8 = new Score();
                    scoreH8.setRoundId(round.getId());
                    scoreH8.setHoleId(playerTran.isBack9() ? 17 : 8);
                    scoreH8.setScore(playerTran.getH8());
                    Score scoreH9 = new Score();
                    scoreH9.setRoundId(round.getId());
                    scoreH9.setHoleId(playerTran.isBack9() ? 18 : 9);
                    scoreH9.setScore(playerTran.getH9());

                    entityManager.getTransaction().begin();
                    entityManager.persist(scoreH1);
                    entityManager.persist(scoreH2);
                    entityManager.persist(scoreH3);
                    entityManager.persist(scoreH4);
                    entityManager.persist(scoreH5);
                    entityManager.persist(scoreH6);
                    entityManager.persist(scoreH7);
                    entityManager.persist(scoreH8);
                    entityManager.persist(scoreH9);
                    entityManager.getTransaction().commit();
                });
    }

    private Integer getMatchId(List<EventMatch> matches, PlayerTran playerTran) {
        return matches.stream()
                .filter(match -> match.getFlightId() == playerTran.getFlight() &&
                        match.getNine().equals(playerTran.isBack9() ? "back" : "front") &&
                        match.getSlot() == teamSlotMap.get(playerTran.getTeam()))
                .map(EventMatch::getId)
                .findAny().orElse(0);
    }

    private Integer getEventId(List<Event> events, PlayerTran playerTran) {
        return events.stream().filter(e -> e.getDay().equals(convertLegacyDate(playerTran.getGameDate()).toLocalDate())).map(Event::getId).findAny().orElse(1);
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
        //        SCORE_CARD("src/test/resources/legacy/data/ScoreCard.xml"),
        PLAYER_TRANSACTION("src/test/resources/legacy/data/PlayerTran.xml"),
        TEAM_SUMMARY_TRANSACTION("src/test/resources/legacy/data/TeamSumTran.xml"),
        //        TEES("src/test/resources/legacy/data/Tees.xml"),
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
