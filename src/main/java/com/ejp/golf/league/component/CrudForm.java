package com.ejp.golf.league.component;

import com.ejp.golf.league.service.CrudService;
import com.ejp.golf.league.view.CrudView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;

public abstract class CrudForm<E> extends FormLayout {

    private CrudView<E> view;
    private CrudService<E> service;
    private boolean newInstance = true;

    public CrudForm(CrudView<E> view, CrudService<E> service) {
        this.view = view;
        this.service = service;
    }

    public boolean isNewInstance() {
        return newInstance;
    }

    public void setNewInstance(boolean newInstance) {
        this.newInstance = newInstance;
    }

    public void setEntity(E entity) {
        getBinder().setBean(entity);

        if (entity == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    protected void init(CrudForm<E> implementation){
        getBinder().bindInstanceFields(implementation);
        setEntity(null);
        add(getInputFields());
        add(getButtons());
    }

    protected abstract Binder<E> getBinder();

    protected abstract Component[] getInputFields();

    private HorizontalLayout getButtons() {
        Button save = new Button("Save");
        Button delete = new Button("Delete");
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        return buttons;
    }

    private void save() {
        E entity = getBinder().getBean();
        if(newInstance){
            service.save(entity);
        }else{
            service.update(entity);
        }
        view.updateList();
        setEntity(null);
    }

    private void delete() {
        E entity = getBinder().getBean();
        service.delete(entity);
        view.updateList();
        setEntity(null);
    }

}