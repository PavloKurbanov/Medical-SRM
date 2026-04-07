package repository;

import entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    List<Appointment> findByDateTime(LocalDateTime date);

    // Шукаємо за об'єктом лікаря або за його ID (в Hibernate це гнучко)
    List<Appointment> findByDoctorId(Integer doctorId);

    // Шукаємо за ID пацієнта
    List<Appointment> findByPatientId(Integer patientId);
}
