package ru.Gridasov.Haulmont.Users;

import ru.Gridasov.Haulmont.Cofiguration.JPA;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PatientsRepository {
    public static List<Patient> findAll( ){
        return JPA.runInTransaction(em ->
                em.createQuery("select p from Patient p").getResultList());
    }

    public static Patient findById(Long id) {return JPA.runInTransaction(entityManager -> getById(id, entityManager));}

    public static Patient getById (Long id, EntityManager entityManager) {
        Query query = entityManager.createQuery("select p from Recipe p where p.id=:id");
        query.setParameter("id", id);
        return (Patient) query.getResultList().stream().findFirst().orElse(null);
    }

    public static Patient save (Patient patient) {return JPA.runInTransaction(em -> em.merge(patient));}

    public static void delete (Patient patient) {
        JPA.runInTransaction (em -> {em.remove(getById(patient.getId(), em));
        return null;
        });
    }
}
