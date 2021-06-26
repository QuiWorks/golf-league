package com.ejp.golf.league.service;

import com.ejp.golf.league.domain.Golfer;

public class GolferService extends AbstractCrudService<Golfer>{

    public GolferService() {
        super();
    }

    @Override
    protected String getTableName() {
        return "golfer";
    }

    @Override
    protected Class<Golfer> getEntityClass() {
        return Golfer.class;
    }

    @Override
    protected String getFilterColumn() {
        return "last_name";
    }

    @Override
    protected String getFilterComparator() {
        return "like";
    }
}
