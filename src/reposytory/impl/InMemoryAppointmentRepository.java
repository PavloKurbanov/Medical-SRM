package reposytory.impl;

import entity.Appointment;
import reposytory.AppointmentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryAppointmentRepository implements AppointmentRepository {
    private final Map<Integer, Appointment> appointments;
    private Integer appointmentId = 1;

    public InMemoryAppointmentRepository() {
        this.appointments = new HashMap<>();
    }

    @Override
    public void save(Appointment appointment) {
        if(appointment.getId() == null) {
            appointment.setId(appointmentId++);
        }
        appointments.put(appointment.getId(), appointment);
    }

    @Override
    public Appointment findById(Integer appointmentId) {
        return appointments.get(appointmentId);
    }

    @Override
    public List<Appointment> appointmentFindByDateTime(LocalDateTime date) {
        List<Appointment> appointmentDataTimeList = new ArrayList<>();
        List<Appointment> all = findAll();

        for (Appointment appointment : all) {
            if(appointment.getDateTime().equals(date)) {
                appointmentDataTimeList.add(appointment);
            }
        }
        return appointmentDataTimeList;
    }

    @Override
    public List<Appointment> findByDoctorId(Integer doctorId) {
        List<Appointment> appointmentDoctorId = new ArrayList<>();
        List<Appointment> all = findAll();
        for (Appointment appointment : all) {
            if(appointment.getDoctorId().equals(doctorId)) {
                appointmentDoctorId.add(appointment);
            }
        }
        return appointmentDoctorId;
    }

    @Override
    public List<Appointment> findByPatientId(Integer patientId) {
        List<Appointment> appointmentPatientId = new ArrayList<>();
        List<Appointment> all = findAll();

        for (Appointment appointment : all) {
            if(appointment.getPatientId().equals(patientId)) {
                appointmentPatientId.add(appointment);
            }
        }
        return appointmentPatientId;
    }

    @Override
    public List<Appointment> findAll() {
        return new ArrayList<>(appointments.values());
    }
}
