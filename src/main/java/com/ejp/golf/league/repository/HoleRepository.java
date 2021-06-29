package com.ejp.golf.league.repository;

import com.ejp.golf.league.domain.Hole;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class HoleRepository extends AbstractRepository{
    public List<Hole> getHoles(EntityManager entityManager, int courseId, String nine) {
        TypedQuery<Hole> holeQuery = entityManager.createQuery(
                "SELECT h FROM hole h " +
                        "WHERE h.courseId = :courseId" +
                        " AND h.nineName = :nine",
                Hole.class);
        holeQuery.setParameter("courseId", courseId);
        holeQuery.setParameter("nine", nine);
        return holeQuery.getResultList();
    }
}
