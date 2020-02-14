package ru.Gridasov.Haulmont.ui;

import com.vaadin.ui.Composite;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;
import ru.Gridasov.Haulmont.Users.Doctor;
import ru.Gridasov.Haulmont.Users.DoctorsRepository;

public class Doctors extends Composite {

    private GridCrud<Doctor> crud = new GridCrud<>(Doctor.class, new HorizontalSplitCrudLayout());

    public Doctors() {
        initLayout();
        initBehavior();
    }

    private void initLayout() {
        crud.getGrid().setColumns("firstName", "lastName", "middleName", "specialization");
        crud.getCrudFormFactory().setVisibleProperties("firstName", "lastName", "middleName", "specialization");

        VerticalLayout layout = new VerticalLayout(crud);
        setCompositionRoot(layout);
        setSizeFull();
    }

    private void initBehavior() {
        crud.setFindAllOperation(DoctorsRepository::findAll);
        crud.setAddOperation(DoctorsRepository::save);
        crud.setUpdateOperation(DoctorsRepository::save);
        crud.setDeleteOperation(DoctorsRepository::delete);
        crud.getCrudFormFactory().setUseBeanValidation(true);
    }


}
