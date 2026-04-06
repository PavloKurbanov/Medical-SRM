package service.impl;

import entity.Patient;
import repository.PatientRepository;
import service.PatientService;
import ui.annotation.validationAnnotation.validator.Validator;

import java.util.List;

public record PatientServiceImpl(PatientRepository patientRepository) implements PatientService {

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
