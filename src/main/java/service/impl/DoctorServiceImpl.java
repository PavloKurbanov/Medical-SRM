package service.impl;

import entity.Doctor;
import repository.DoctorRepository;
import service.DoctorService;
import ui.annotation.validationAnnotation.validator.Validator;

import java.util.List;

public record DoctorServiceImpl(DoctorRepository doctorRepository) implements DoctorService {

    @Override
    public void save(Doctor doctor) {
        Validator.validator(doctor);
        doctorRepository.save(doctor);
    }

    @Override
    public Doctor findById(Integer id) {
        return doctorRepository.findById(id);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }
}
