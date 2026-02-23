package reposytory;

import entity.Appointment;
import util.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    List<Appointment> appointmentFindByDateTime(LocalDateTime date);

    List<Appointment> findByDoctorId(Integer doctorId);

    List<Appointment> findByPatientId(Integer patientId);
}
