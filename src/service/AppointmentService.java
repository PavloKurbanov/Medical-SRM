package service;

import entity.Appointment;
import service.callback.AppointmentCallback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

public interface AppointmentService {
    void save(Integer doctorId, Integer patientId, LocalDateTime dateTime, AppointmentCallback appointmentCallback);

    Appointment findById(Integer id);

    List<Appointment> appointmentFindByDateTime(LocalDateTime date);

    List<Appointment> getAllAppointments();

    List<Appointment> findAllByDoctorId(Integer doctorId);

    List<Appointment> findAllByPatientId(Integer patientId);
}