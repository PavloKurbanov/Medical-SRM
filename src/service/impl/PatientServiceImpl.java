package service.impl;

import entity.Patient;
import reposytory.PatientRepository;
import service.PatientService;

import java.util.List;

public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;

    public  PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(Integer id) {
        return patientRepository.findById(id);
    }

    @Override
    public void save(Patient patient) {
        patientRepository.save(patient);
    }
}
