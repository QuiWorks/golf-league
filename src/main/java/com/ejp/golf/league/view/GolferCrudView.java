package com.ejp.golf.league.view;

import com.ejp.golf.league.component.CrudForm;
import com.ejp.golf.league.domain.Golfer;
import com.ejp.golf.league.layout.MainLayout;
import com.ejp.golf.league.service.CrudService;
import com.ejp.golf.league.service.GolferService;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;
import java.util.Arrays;

@Route(value = "admin/golfer", layout = MainLayout.class)
public class GolferCrudView extends CrudView<Golfer> {

    private final GolferForm form;

    public GolferCrudView() {
        super(Golfer.class, new GolferService());
        form = new GolferForm(this, service);
        this.filters = new Component[]{getFilter()};
    }

    @Override
    protected CrudForm<Golfer> getForm() {
        return form;
    }

    @Override
    protected String[] getColumnNames() {
        return Arrays.stream(super.getColumnNames()).filter(name -> !name.equals("teams")).toArray(String[]::new);
    }

    private TextField getFilter() {
        TextField textFilter = new TextField();
        textFilter.setPlaceholder("Filter by last name...");
        textFilter.setClearButtonVisible(true);
        textFilter.setValueChangeMode(ValueChangeMode.EAGER);
        textFilter.addValueChangeListener(getFilterChangeHandler(textFilter));
        return textFilter;
    }

    private HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<TextField, String>> getFilterChangeHandler(TextField textFilter) {
        return e -> getGrid().setItems(
                (textFilter.getValue() == null || "".equals(textFilter.getValue()))
                        ? service.findAll()
                        : service.findAll(textFilter.getValue() + "%"));
    }

    public static class GolferForm extends CrudForm<Golfer> {

        private final TextField firstName = new TextField("First Name");
        private final TextField lastName = new TextField("Last Name");
        private final TextField middleName = new TextField("Middle Name");
        private final TextField email = new TextField("Email");
        private final TextField city = new TextField("City");
        private final TextField state = new TextField("State");
        private final TextField zip = new TextField("Zip");
        private final TextField homePhone = new TextField("Home Phone");
        private final TextField workPhone = new TextField("Work Phone");
        private final TextField notes = new TextField("Notes");
        private final Binder<Golfer> binder = new Binder<>(Golfer.class);

        public GolferForm(CrudView<Golfer> view, CrudService<Golfer> service) {
            super(view, service);
            init(this);
        }

        @Override
        protected Binder<Golfer> getBinder() {
            return binder;
        }

        @Override
        public Component[] getInputFields() {
            return new Component[]{firstName, lastName, middleName, email, city, state, zip, homePhone, workPhone, notes};
        }

        @Override
        protected void save() {
            getBinder().getBean().setDateAdded(LocalDateTime.now());
            super.save();
        }
    }
}