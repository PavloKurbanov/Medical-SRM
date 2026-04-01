package service.impl;

import entity.Patient;
import repository.PatientRepository;
import service.PatientService;
import ui.annotation.validationAnnotation.validator.Validator;

import java.util.List;

public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
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
        Validator.validator(patient);
        patientRepository.save(patient);
    }
}
