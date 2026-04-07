package repository.jbdsRepositoryImpl;

import entity.Doctor;
import repository.DoctorRepository;
import util.ConnectionManager;

import javax.persistence.EntityManager;
import java.util.List;

public record JBDCDoctorsRepository(ConnectionManager connectionManager) implements DoctorRepository {

    @Override
    public void save(Doctor doctor) {
        EntityManager em = null;
        try {
            em = connectionManager.getEntityManager();
            em.getTransaction().begin();
            em.merge(doctor);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Помилка збереження лікаря: " + doctor.getName(), e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    @Override
    public Doctor findById(Integer id) {
        EntityManager em = null;
        try {
            em = connectionManager.getEntityManager();
            return em.find(Doctor.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Помилка при пошуку за ID: " + id, e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    @Override
    public List<Doctor> findAll() {
        EntityManager em = null;
        try {
            em = connectionManager.getEntityManager();
            return em.createQuery("select d from Doctor d", Doctor.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Помилка при отриманні всіх лікарів", e);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
}