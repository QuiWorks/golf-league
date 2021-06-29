package com.ejp.golf.league.repository;

import javax.persistence.EntityManager;

public class GolferHandicapRepository extends AbstractRepository{
    public Integer getHandicap(EntityManager entityManager, int golferId) {
        return entityManager.createQuery(
                "SELECT gh.handicap FROM golfer_handicap gh " +
                        "WHERE gh.golferId = " + golferId,
                Integer.class).getResultList().get(0);
    }
}
