package com.ejp.golf.league.legacy;

import com.ejp.golf.league.domain.Course;
import com.ejp.golf.league.legacy.domain.FlightsRoot;
import com.ejp.golf.league.legacy.domain.PlayersRoot;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;
import java.io.IOException;

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
            JAXBContext context = JAXBContext.newInstance(PlayersRoot.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            PlayersRoot playersRoot = (PlayersRoot) unmarshaller.unmarshal(new File("src/test/resources/legacy/data/Players.xml"));
            playersRoot.getPlayers().forEach(System.out::println);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
