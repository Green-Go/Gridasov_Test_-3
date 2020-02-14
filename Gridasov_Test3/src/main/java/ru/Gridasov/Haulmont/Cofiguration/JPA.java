package ru.Gridasov.Haulmont.Cofiguration;

import ru.Gridasov.Haulmont.Users.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Function;

public class JPA {

    private static EntityManagerFactory managerFactory;

    public static void init() {

        if (managerFactory == null) {
            managerFactory = Persistence.createEntityManagerFactory("item-manager-pu");
            createData();
        }
    }

    private static void createData() {
        for (int i = 0; i <= 4; i++) {
            Doctor doctor = new Doctor();
            doctor.setFirstName("Doctor" + i);
            doctor.setLastName("lDoctor" + i);
            doctor.setMiddleName("mDoctor" + i);
            doctor.setSpecialization("sDoctor" + i);
            DoctorsRepository.save(doctor);
        }

        for (int i = 0; i <= 4; i++) {
            Patient patient = new Patient();
            patient.setFirstName("Patient" + i);
            patient.setLastName("lPatient" + i);
            patient.setMiddleName("mPatient" + i);
            patient.setPhoneNumber("+7" + i);
            PatientsRepository.save(patient);
        }

        for (int i = 0; i <= 4; i++) {
            Recipe recipe = new Recipe();
            recipe.setDescription("Desk" + i);
            RecipesRepository.save(recipe);
        }
    }

    public static void close() {managerFactory.close();}

    public static EntityManagerFactory getManagerFactory () {
        init();
        return managerFactory;}

    public static <T> T runInTransaction(Function<EntityManager, T> function) {
        EntityManager entityManager = null;

        try{
            entityManager = JPA.getManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();

            T result = function.apply(entityManager);

            entityManager.getTransaction().commit();
            return result;
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
