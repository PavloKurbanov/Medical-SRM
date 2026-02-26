package repository.impl;

import entity.Patient;
import repository.PatientRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryPatientRepository implements PatientRepository {
    private final Map<Integer, Patient> patients;
    private Integer patientId = 1;

    public InMemoryPatientRepository() {
        this.patients = new HashMap<>();
    }

    @Override
    public void save(Patient patient) {
        if(patient.getId() == null) {
            patient.setId(patientId++);
        }
        patients.put(patient.getId(), patient);
    }

    @Override
    public Patient findById(Integer patientId) {
        return patients.get(patientId);
    }

    @Override
    public List<Patient> findAll() {
        return new ArrayList<>(patients.values());
    }
}