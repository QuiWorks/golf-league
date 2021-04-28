package com.ejp.golf.league;

import com.ejp.golf.league.domain.Course;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

    }
}
