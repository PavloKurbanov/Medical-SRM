package service.impl;

import entity.Doctor;
import repository.DoctorRepository;
import service.DoctorService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void save(Doctor doctor) {
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
