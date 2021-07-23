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

    public int getHoleScore(EntityManager entityManager, int holeId, int golferId, int week)
    {
        TypedQuery<Integer> scoreQuery = entityManager.createQuery(
                "SELECT s.score FROM score s " +
                        "JOIN round r on r.id = s.round.id " +
                        "JOIN event_match em on em.id = r.eventMatch.id " +
                        "JOIN event e on e.id = em.event.id " +
                        "WHERE s.hole.id = :holeId" +
                        " AND s.round.golfer.id = :golferId" +
                        " AND e.week = :week",
                Integer.class);
        scoreQuery.setParameter("holeId", holeId);
        scoreQuery.setParameter("golferId", golferId);
        scoreQuery.setParameter("week", week);
        List<Integer> resultList = scoreQuery.setMaxResults(1).getResultList();
        if(resultList.size() > 0)
        {
            return resultList.get(0);
        }else{
            return 0;
        }
    }
}
