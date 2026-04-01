package service;

import entity.Appointment;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface AppointmentService {
    void save(Integer doctorId, Integer patientId, LocalDateTime dateTime);

    Appointment findById(Integer id);

    List<Appointment> appointmentFindByDateTime(LocalDateTime date);

    List<Appointment> getAllAppointments();

    List<Appointment> findAllByDoctorId(Integer doctorId);

    List<Appointment> findAllByPatientId(Integer patientId);

    List<Appointment> getSortAppointments(Comparator<Appointment> comparator);
}