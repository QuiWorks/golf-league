package com.ejp.golf.league.view;

import com.ejp.golf.league.component.CrudForm;
import com.ejp.golf.league.domain.Event;
import com.ejp.golf.league.layout.MainLayout;
import com.ejp.golf.league.service.CrudService;
import com.ejp.golf.league.service.EventService;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

@Route(value = "admin/event", layout = MainLayout.class)
public class EventCrudView extends CrudView<Event> {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final EventForm form;

    public EventCrudView() {
        super(Event.class, new EventService());
        form = new EventForm(this, service);
        DatePicker filter = getFilter();
        this.filters = new Component[]{filter};
    }

    @Override
    protected CrudForm<Event> getForm() {
        return form;
    }

    private DatePicker getFilter() {
        DatePicker datePicker = new DatePicker();
        datePicker.setPlaceholder("Filter by date...");
        datePicker.setClearButtonVisible(true);
        datePicker.addValueChangeListener(getDateChangeEventHandler(datePicker));
        return datePicker;
    }

    private HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<DatePicker, LocalDate>> getDateChangeEventHandler(DatePicker datePicker) {
        return e -> getGrid().setItems(datePicker.getValue() == null
                ? service.findAll()
                : service.findAll(sdf.format(Date.from(datePicker.getValue().atTime(12, 0).toInstant(ZoneOffset.UTC)))));
    }

    public static class EventForm extends CrudForm<Event> {

        private final IntegerField seasonId = new IntegerField("Season ID");
        private final DatePicker day = new DatePicker("Day");
        private final IntegerField week = new IntegerField("Week");
        private final ComboBox<String> eventType = new ComboBox<>("Event Type");
        private final TextField notes = new TextField("Notes");
        private final Binder<Event> binder = new Binder<>(Event.class);

        public EventForm(CrudView<Event> view, CrudService<Event> service) {
            super(view, service);
            eventType.setItems("league", "playoff");
            init(this);
        }

        @Override
        protected Binder<Event> getBinder() {
            return binder;
        }

        @Override
        public Component[] getInputFields() {
            return new Component[]{seasonId, day, week, eventType, notes};
        }
    }
}