package service.impl;

import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import repository.AppointmentRepository;
import repository.DoctorRepository;
import repository.PatientRepository;
import service.AppointmentService;
import util.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void save(Integer doctorId, Integer patientId, LocalDateTime dateTime) {
        Doctor doctorById = doctorRepository.findById(doctorId);
        Patient patientById = patientRepository.findById(patientId);

        if (doctorById == null) {
            throw new IllegalArgumentException("Доктора з ID " + doctorId + " не знайдено!");
        }
        if (patientById == null) {
            throw new IllegalArgumentException("Пацієнта з ID " + patientId + " не знайдено!");
        }
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Запис не можу бути до " + DateTimeFormat.format(LocalDateTime.now()));
        }

        List<Appointment> appointmentsDoctorId = appointmentRepository.findByDoctorId(doctorId);
        for (Appointment appointment : appointmentsDoctorId) {
            if (isTimeOverlap(appointment.getDateTime(), dateTime)) {
                throw new IllegalArgumentException("Лікар " + doctorById.getName() + " вже має запис на час (" + DateTimeFormat.format(dateTime) + ")");
            }
        }

        List<Appointment> appointmentsPatientId = appointmentRepository.findByPatientId(patientId);
        for (Appointment appointment : appointmentsPatientId) {
            if (isTimeOverlap(appointment.getDateTime(), dateTime)) {
                throw new IllegalArgumentException("Пацієнт " + patientById.getName() + " вже має запис на час (" + DateTimeFormat.format(dateTime) + ")");
            }
        }

        appointmentRepository.save(new Appointment(null, doctorId, patientId, dateTime));
    }

    @Override
    public List<Appointment> findAllByDoctorId(Integer doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> findAllByPatientId(Integer patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public Appointment findById(Integer id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public List<Appointment> appointmentFindByDateTime(LocalDateTime date) {
        return appointmentRepository.appointmentFindByDateTime(date);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    private boolean isTimeOverlap(LocalDateTime existingTime, LocalDateTime newTime) {
        LocalDateTime existingEnd = existingTime.plusMinutes(30);
        LocalDateTime newEnd = newTime.plusMinutes(30);

        return existingTime.isBefore(newEnd) && newTime.isBefore(existingEnd);
    }
}
