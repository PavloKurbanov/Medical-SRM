package repository.jbdsRepositoryImpl;

import entity.Patient;
import repository.PatientRepository;
import util.ConnectionManager;

import javax.persistence.EntityManager;
import java.util.List;

public record JBDCPatientRepository(ConnectionManager connectionManager) implements PatientRepository {

    @Override
    public void save(Patient entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Пацієнт не може бути null!");
        }

        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Не вдалось зберегти пацієнта у базу", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public Patient findById(Integer integer) {
        EntityManager entityManager = null;
        try{
            entityManager = connectionManager.getEntityManager();
            return entityManager.find(Patient.class, integer);
        } catch (Exception e) {
            throw new RuntimeException("Помилка при пошуку пацієнта з ID: " + integer, e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) entityManager.close();
        }
    }

    @Override
    public List<Patient> findAll() {
        EntityManager entityManager = null;
        try {
        entityManager = connectionManager.getEntityManager();
        return entityManager.createQuery("Select p from Patient p ",  Patient.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }  finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}