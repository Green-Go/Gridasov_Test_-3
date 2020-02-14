package ru.Gridasov.Haulmont.Users;

import ru.Gridasov.Haulmont.Cofiguration.JPA;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DoctorsRepository {

    public static List<Doctor> findAll(){
        return JPA.runInTransaction(em ->
                em.createQuery("select d from Doctor d").getResultList());
    }

    public static Doctor findById (Long id) {return JPA.runInTransaction(entityManager -> getById(id, entityManager));}

    public static Doctor getById (Long id, EntityManager entityManager) {
        Query query = entityManager.createQuery("select d from Recipe d where d.id=:id");
        query.setParameter("id", id);
        return (Doctor) query.getResultList().stream().findFirst().orElse(null);
    }

    public static Doctor save (Doctor doctor) {return JPA.runInTransaction(em -> em.merge(doctor));}

    public static void delete (Doctor doctor) {
        JPA.runInTransaction (em -> {em.remove(getById(doctor.getId(), em));
        return null;
        });
    }
}
