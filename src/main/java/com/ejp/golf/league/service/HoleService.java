package com.ejp.golf.league.service;

import com.ejp.golf.league.domain.Hole;

public class HoleService extends AbstractCrudService<Hole>{

    public HoleService() {
        super();
    }

    @Override
    protected String getTableName() {
        return "hole";
    }

    @Override
    protected Class<Hole> getEntityClass() {
        return Hole.class;
    }
}
