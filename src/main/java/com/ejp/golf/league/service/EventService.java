package com.ejp.golf.league.service;

import com.ejp.golf.league.domain.Event;

public class EventService extends AbstractCrudService<Event>{

    public EventService() {
        super();
    }

    @Override
    protected String getTableName() {
        return "event";
    }

    @Override
    protected Class<Event> getEntityClass() {
        return Event.class;
    }

    @Override
    protected String getFilterColumn() {
        return "day";
    }
}
