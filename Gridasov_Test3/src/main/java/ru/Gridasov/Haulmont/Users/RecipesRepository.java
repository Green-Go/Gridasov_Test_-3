package ru.Gridasov.Haulmont.Users;

import ru.Gridasov.Haulmont.Cofiguration.JPA;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RecipesRepository {

    public static List<Recipe> findAll(){
        return JPA.runInTransaction(em ->
                em.createQuery("select r from Recipe r").getResultList());
    }

    public static Recipe findById (Long id) {return JPA.runInTransaction(entityManager -> getById(id, entityManager));}

    public static Recipe getById (Long id, EntityManager entityManager) {
        Query query = entityManager.createQuery("select r from Recipe r where r.id=:id");
        query.setParameter("id", id);
        return (Recipe) query.getResultList().stream().findFirst().orElse(null);
    }

    public static Recipe save (Recipe recipe) {return JPA.runInTransaction(em -> em.merge(recipe));}

    public static void delete (Recipe recipe) {
        JPA.runInTransaction (em -> {em.remove(getById(recipe.getId(), em));
        return null;
        });
    }
}
