package com.pazukdev.ui;

import com.pazukdev.entities.Bearing;
import com.pazukdev.services.BearingsService;

import com.vaadin.navigator.View;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;


public class BearingsLayout extends VerticalLayout implements View {

    private BearingsService bearingsService = BearingsService.getInstance();
    private Grid<Bearing> bearingsGrid =new Grid<>(Bearing.class);
    //private Grid<Bearing> bearingsGrid =new Grid<>();


    public BearingsLayout() {
        setGrid();
        updateBearingsList();
        addComponent(bearingsGrid);
        setComponentAlignment(bearingsGrid, Alignment.MIDDLE_CENTER);
        setMargin(false);
        setSpacing(false);
    }


    private void setGrid() {
        bearingsGrid.setSelectionMode(Grid.SelectionMode.NONE);
        bearingsGrid.setWidth("480px");
        bearingsGrid.setHeight("640px");
        bearingsGrid.setBodyRowHeight(34);
        bearingsGrid.sort(bearingsGrid.getColumn("majorLocation"), SortDirection.ASCENDING);
        bearingsGrid.setColumns(
                //"id",
                "numberOfOriginal",
                "type",
                "majorLocation",
                "quantity"
        );
        bearingsGrid.getColumn("quantity").setCaption("Pcs");
    }


    public void updateBearingsList() {
        List<Bearing> bearings = bearingsService.findAll();
        bearingsGrid.setItems(bearings);
    }


    public Grid<Bearing> getBearingsGrid() {
        return bearingsGrid;
    }

}


























