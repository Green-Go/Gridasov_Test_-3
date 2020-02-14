package ru.Gridasov.Haulmont.ui;

import com.vaadin.ui.Composite;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;
import ru.Gridasov.Haulmont.Users.Patient;
import ru.Gridasov.Haulmont.Users.PatientsRepository;

public class Patients extends Composite {
    private GridCrud<Patient> crud = new GridCrud<>(Patient.class, new HorizontalSplitCrudLayout());

    public Patients() {
        initLayout();
        initBehavior();
    }

    private void initLayout() {
        crud.getGrid().setColumns("firstName", "lastName", "middleName", "phoneNumber");
        crud.getCrudFormFactory().setVisibleProperties("firstName", "lastName", "middleName", "phoneNumber");

        VerticalLayout layout = new VerticalLayout(crud);
        setCompositionRoot(layout);
        setSizeFull();
    }

    private void initBehavior() {
        crud.setFindAllOperation(PatientsRepository::findAll);
        crud.setAddOperation(PatientsRepository::save);
        crud.setUpdateOperation(PatientsRepository::save);
        crud.setDeleteOperation(PatientsRepository::delete);
        crud.getCrudFormFactory().setUseBeanValidation(true);
    }
}
