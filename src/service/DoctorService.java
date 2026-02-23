package service;

import entity.Doctor;

import java.util.List;

public interface DoctorService {
    void save(Doctor doctor);

    Doctor findById(Integer id);

    List<Doctor> findAll();
}