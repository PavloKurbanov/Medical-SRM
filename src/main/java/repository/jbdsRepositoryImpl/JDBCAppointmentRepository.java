package repository.jbdsRepositoryImpl;

import entity.Appointment;
import repository.AppointmentRepository;
import util.ConnectionManager;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public record JDBCAppointmentRepository(ConnectionManager connectionManager) implements AppointmentRepository {

    @Override
    public void save(Appointment entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Запис не може бути null!");
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
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }

    }

    @Override
    public Appointment findById(Integer integer) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            return entityManager.find(Appointment.class, integer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Appointment> findAll() {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            return entityManager.createQuery("select a from Appointment a", Appointment.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Appointment> findByDateTime(LocalDateTime date) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            return entityManager.createQuery("select a from Appointment a where a.dateTime = :date", Appointment.class)
                    .setParameter("date", date).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Appointment> findByDoctorId(Integer doctorId) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            return entityManager.createQuery("select a from Appointment a where a.doctor.id = :doctorId", Appointment.class)
                    .setParameter("doctorId", doctorId).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Appointment> findByPatientId(Integer patientId) {
        EntityManager entityManager = null;
        try {
            entityManager = connectionManager.getEntityManager();
            return entityManager.createQuery("select a from Appointment a where a.patient.id = :patientId", Appointment.class)
                    .setParameter("patientId", patientId).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
