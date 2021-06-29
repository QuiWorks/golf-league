package com.ejp.golf.league.service;

import com.ejp.golf.league.domain.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AbstractCrudServiceTest {

    private CrudService<Course> courseCrudService;

    @BeforeEach
    void setUp() {
        this.courseCrudService = new AbstractCrudService<>() {
            @Override
            protected String getTableName() {
                return "course";
            }

            @Override
            protected Class<Course> getEntityClass() {
                return Course.class;
            }

            @Override
            protected String getFilterColumn() {
                return "name";
            }

            @Override
            protected String getFilterComparator() {
                return "like";
            }
        };
    }

    @Test
    void findAll() {
        List<Course> all = courseCrudService.findAll().stream()
                .sorted(Comparator.comparing(Course::getId))
                .collect(Collectors.toList());
        assertEquals(2, all.size());
        assertEquals(1, all.get(0).getId());
        assertEquals("The  Fairways", all.get(0).getName());
        assertEquals(2, all.get(1).getId());
        assertEquals("Territory Golf", all.get(1).getName());
    }

    @Test
    void filterFindAll() {
        List<Course> matching = courseCrudService.findAll("The%").stream()
                .sorted(Comparator.comparing(Course::getId))
                .collect(Collectors.toList());
        assertEquals(1, matching.size());
        assertEquals(1, matching.get(0).getId());
        assertEquals("The  Fairways", matching.get(0).getName());
    }

}