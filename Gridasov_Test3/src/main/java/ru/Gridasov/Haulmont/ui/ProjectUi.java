package ru.Gridasov.Haulmont.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;

public class ProjectUi extends UI {
    private TabSheet tabSheet = new TabSheet();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        tabSheet.setSizeFull();
        setContent(tabSheet);

        addTab(new Doctors());
        addTab(new Patients());
        addTab(new Recipes());
    }

    private void addTab(Component component) {tabSheet.addTab(component, component.getClass().getSimpleName());}
}
