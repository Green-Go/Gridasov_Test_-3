package ru.Gridasov.Haulmont.ui;

import com.vaadin.ui.Composite;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;
import ru.Gridasov.Haulmont.Users.Recipe;
import ru.Gridasov.Haulmont.Users.RecipesRepository;

import java.util.ArrayList;

public class Recipes extends Composite {
    private GridCrud<Recipe> crud = new GridCrud<>(Recipe.class, new HorizontalSplitCrudLayout());

    public Recipes() {
        initLayout();
        initBehavior();
    }

    private void initLayout() {
        crud.getGrid().setColumns("description", "patient", "doctor", "date", "expDate", "priority");
        crud.getCrudFormFactory().setVisibleProperties("description", "patient", "doctor", "date", "expDate", "priority");
        ArrayList<String> list = new ArrayList<String>(3);
        list.add("Normal");
        list.add("Cito");
        list.add("Statim");
        crud.getCrudFormFactory().setFieldProvider("priority", new ComboBoxProvider<>("Priority", list));

        VerticalLayout layout = new VerticalLayout(crud);
        setCompositionRoot(layout);
        setSizeFull();
    }

    private void initBehavior() {
        crud.setFindAllOperation(RecipesRepository::findAll);
        crud.setAddOperation(RecipesRepository::save);
        crud.setUpdateOperation(RecipesRepository::save);
        crud.setDeleteOperation(RecipesRepository::delete);
        crud.getCrudFormFactory().setUseBeanValidation(true);
    }
}
