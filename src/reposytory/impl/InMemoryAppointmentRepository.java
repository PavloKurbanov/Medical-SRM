package reposytory.impl;

import entity.Appointment;
import reposytory.AppointmentRepository;

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
        appointment.setId(appointmentId++);
        appointments.put(appointment.getId(), appointment);
    }

    @Override
    public Appointment findById(Integer appointmentId) {
        return appointments.get(appointmentId);
    }

    @Override
    public List<Appointment> findAll() {
        return new ArrayList<>(appointments.values());
    }
}
