package com.pazukdev.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MainLayout extends VerticalLayout implements View {

    Button bearingsButton = new Button("Bearings");

    public MainLayout(Navigator navigator, Button backButton) {
        setSizeFull();
        addComponents(bearingsButton);
        setComponentAlignment(bearingsButton, Alignment.MIDDLE_CENTER);
        bearingsButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        //bearingsButton.setStyleName(ValoTheme.BUTTON_HUGE);
        bearingsButton.setWidth("320px");
        bearingsButton.setHeight("80px");
        bearingsButton.addClickListener(event -> {
            backButton.setEnabled(true);
            backButton.setIcon(VaadinIcons.ARROW_LEFT);
            backButton.setVisible(true);
            navigator.navigateTo("bearings");
        });
    }

}
