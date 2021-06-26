package com.ejp.golf.league.view;

import com.ejp.golf.league.component.CrudForm;
import com.ejp.golf.league.service.CrudService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Abstraction of a C.R.U.D View for a given database entity class
 *
 * @param <E> The database entity
 */
public abstract class CrudView<E> extends VerticalLayout {
    protected final Class<E> entityClass;
    protected CrudService<E> service;
    protected Component[] filters;
    private Grid<E> grid;
    private Button addEntityButton;

    public CrudView(Class<E> entityClass, CrudService<E> crudService) {
        this.entityClass = entityClass;
        service = crudService;
        setSizeFull();
        setupGrid(entityClass);
        setupNewEntityButton(entityClass);
        updateList();
    }

    @PostConstruct
    public void addComponents() {
        HorizontalLayout mainContent = new HorizontalLayout(grid, getForm());
        mainContent.setSizeFull();
        HorizontalLayout controls = new HorizontalLayout(filters);
        controls.add(addEntityButton);
        add(controls, mainContent);
    }

    public Grid<E> getGrid() {
        return grid;
    }

    public void updateList() {
        grid.setItems(service.findAll());
    }

    protected String[] getColumnNames() {
        return Arrays.stream(entityClass.getDeclaredFields())
                .map(Field::getName)
                .toArray(String[]::new);
    }

    protected abstract CrudForm<E> getForm();

    private void setupGrid(Class<E> entityClass) {
        grid = new Grid<>(entityClass);
        grid.setColumns(getColumnNames());
        grid.setSizeFull();
        grid.asSingleSelect().addValueChangeListener(event -> {
            getForm().setNewInstance(false);
            getForm().setEntity(grid.asSingleSelect().getValue());
        });
    }

    private void setupNewEntityButton(Class<E> entityClass) {
        addEntityButton = new Button("Add new " + entityClass.getSimpleName());
        addEntityButton.addClickListener(e -> {
            grid.asSingleSelect().clear();
            try {
                getForm().setNewInstance(true);
                getForm().setEntity(entityClass.getConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        });
    }
}