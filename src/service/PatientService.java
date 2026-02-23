package service;

import entity.Patient;

import java.util.List;

public interface PatientService {
    void save(Patient patient);
    Patient findById(Integer id);
    List<Patient> findAll();
}