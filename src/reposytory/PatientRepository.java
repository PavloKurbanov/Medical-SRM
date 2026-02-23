package reposytory;

import entity.Patient;
import util.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
}