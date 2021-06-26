package com.ejp.golf.league.view;

import com.ejp.golf.league.component.CrudForm;
import com.ejp.golf.league.domain.Hole;
import com.ejp.golf.league.layout.MainLayout;
import com.ejp.golf.league.service.CrudService;
import com.ejp.golf.league.service.HoleService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import java.sql.Timestamp;

@Route(value = "admin/hole", layout = MainLayout.class)
public class HoleCrudView extends CrudView<Hole> {

    private final HoleForm form;

    public HoleCrudView() {
        super(Hole.class, new HoleService());
        form = new HoleForm(this, service);
        this.filters = new Component[]{};
    }

    @Override
    protected CrudForm<Hole> getForm() {
        return form;
    }

    public static class HoleForm extends CrudForm<Hole> {

        private final ComboBox<String> nineName = new ComboBox<>("Nine");
        private final IntegerField holeNumber = new IntegerField("Hole Number");
        private final IntegerField handicap = new IntegerField("Handicap");
        private final IntegerField par = new IntegerField("Par");
        private final IntegerField yardage = new IntegerField("Yardage");
        private final Binder<Hole> binder = new Binder<>(Hole.class);

        public HoleForm(CrudView<Hole> view, CrudService<Hole> service) {
            super(view, service);
            nineName.setItems("Back","Front");
            init(this);
        }

        @Override
        protected Binder<Hole> getBinder() {
            return binder;
        }

        @Override
        public Component[] getInputFields() {
            return new Component[]{nineName, holeNumber, handicap, par, yardage};
        }

        @Override
        protected void save() {
            Hole bean = getBinder().getBean();
            bean.setCreated(new Timestamp(System.currentTimeMillis()));
            bean.setCourseId(1);
            super.save();
        }
    }
}