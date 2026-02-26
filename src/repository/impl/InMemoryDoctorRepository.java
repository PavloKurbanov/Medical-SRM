package repository.impl;

import entity.Doctor;
import repository.DoctorRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDoctorRepository implements DoctorRepository {
    private final Map<Integer, Doctor> doctors;
    private Integer doctorId = 1;

    public InMemoryDoctorRepository() {
        doctors = new HashMap<>();
    }

    @Override
    public void save(Doctor doctor) {
        if(doctor.getId() == null) {
            doctor.setId(doctorId++);
        }
        doctors.put(doctor.getId(), doctor);
    }

    @Override
    public Doctor findById(Integer doctorId) {
        return doctors.get(doctorId);
    }

    @Override
    public List<Doctor> findAll() {
        return new ArrayList<>(doctors.values());
    }
}