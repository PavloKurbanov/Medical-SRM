package entity;


import repository.annotation.CsvColum;
import repository.annotation.CsvTable;
import ui.annotation.validationAnnotation.NoBlank;

import java.util.Objects;

@CsvTable(fileName = "patients.cvc")
public class Patient implements Comparable<Patient> {

    @CsvColum(index = 0)
    private Integer id;

    @CsvColum(index = 1)
    @NoBlank(message = "Введіть ім'я пацієнта!")
    private String name;

    public Patient(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Patient(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(name, patient.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(Patient o) {
        int nameCompare = name.compareTo(o.name);
        if (nameCompare != 0) {
            return nameCompare;
        }
        return this.id.compareTo(o.id);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s", id, name);
    }
}