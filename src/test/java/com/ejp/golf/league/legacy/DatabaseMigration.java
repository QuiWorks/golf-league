//package com.ejp.golf.league.legacy;
//
//import com.ejp.golf.league.domain.Course;
//import com.ejp.golf.league.domain.Golfer;
//import com.ejp.golf.league.domain.PlayerHandicap;
//import com.ejp.golf.league.domain.TeamMember;
//import org.junit.jupiter.api.Test;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//import javax.xml.datatype.XMLGregorianCalendar;
//
//import java.io.File;
//import java.time.LocalDateTime;
//
//public class DatabaseMigration {
//
//    @Test
//    void deleteme() {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("golf_league");
//        Course course = new Course();
//        course.setName("DELETEME");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(course);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
////    @Test
////    void migratePlayerData() {
////        try {
////            JAXBContext context = JAXBContext.newInstance(PlayersRoot.class);
////            Unmarshaller unmarshaller = context.createUnmarshaller();
////            PlayersRoot playersRoot = (PlayersRoot) unmarshaller.unmarshal(new File("src/test/resources/legacy/data/Players.xml"));
////            playersRoot.getPlayers().forEach(this::migratePlayerToNewDomain);
////        } catch (JAXBException e) {
////            e.printStackTrace();
////        }
////
////    }
////
////    private void migratePlayerToNewDomain(Players player)
////    {
////        Golfer golfer = new Golfer();
////        PlayerHandicap playerHandicap = new PlayerHandicap();
////        TeamMember teamMember = new TeamMember();
////
////        golfer.setId(player.getGolfer());
////        golfer.setFirstName(player.getFirstName());
////        golfer.setMiddleName(player.getMiddleInt());
////        golfer.setLastName(player.getLastName());
////        golfer.setEmail(player.getEmail());
////        golfer.setCity(player.getCity());
////        golfer.setState(player.getState());
////        golfer.setZip(player.getZipCode());
////        golfer.setHomePhone(player.getHomePhone());
////        golfer.setWorkPhone(player.getWorkPhone());
////        golfer.setNotes(player.getNotes());
////        golfer.setActive(player.isActive());
////        golfer.setDateAdded(convertLegacyDate(player.getDateAdded()));
////
////        playerHandicap.setGolferId(player.getGolfer());
////        playerHandicap.setHandicap(player.getCurrentHdcp());
////
////        //TODO need to insert team first
////        teamMember.setGolferId(player.getGolfer());
////        teamMember.setTeamId(player.getTeam());
////
////    }
//
//    private LocalDateTime convertLegacyDate(XMLGregorianCalendar date) {
//        return LocalDateTime.of(date.getYear(), date.getMonth(), date.getDay(), date.getHour(), date.getMinute(), date.getSecond());
//    }
//
//}
package com.ejp.golf.league.legacy;

import com.ejp.golf.league.domain.Course;
import com.ejp.golf.league.domain.Golfer;
import com.ejp.golf.league.domain.PlayerHandicap;
import com.ejp.golf.league.domain.TeamMember;
import com.ejp.golf.league.legacy.domain.Players;
import com.ejp.golf.league.legacy.domain.PlayersList;
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

public class DatabaseMigration {

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
      @Test
      void migratePlayerData() {
          try {
              JAXBContext context = JAXBContext.newInstance(PlayersList.class);
              Unmarshaller unmarshaller = context.createUnmarshaller();
              PlayersList playersList = (PlayersList) unmarshaller.unmarshal(new File("src/test/resources/legacy/data/Players.xml"));
              playersList.getPlayers().forEach(this::migratePlayerToNewDomain);
          } catch (JAXBException e) {
              e.printStackTrace();
          }

      }

      private void migratePlayerToNewDomain(Players player)
      {
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

}
