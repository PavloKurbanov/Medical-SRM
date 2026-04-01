package entity;

import repository.annotation.CsvColum;
import repository.annotation.CsvTable;
import ui.annotation.validationAnnotation.NoBlank;
import ui.annotation.validationAnnotation.NotNull;

import java.util.Objects;

@CsvTable(fileName = "doctors.csv")
public class Doctor implements Comparable<Doctor> {

    @CsvColum(index = 0)
    private Integer id;

    @CsvColum(index = 1)
    @NoBlank(message = "Введіть ім'я лікаря")
    private String name;

    @CsvColum(index = 2)
    @NotNull(message = "Оберіть спеціальність")
    private Specialization specialization;

    public Doctor(Integer id, String name, Specialization specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public Doctor(){

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

    public Specialization getSpecialization() {
        return specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(name, doctor.name) && specialization == doctor.specialization;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, specialization);
    }

    @Override
    public int compareTo(Doctor o) {
        int nameCompare = name.compareTo(o.name);
        if (nameCompare != 0) {
            return nameCompare;
        }
        return this.id.compareTo(o.id);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | Спеціалізація: %s", id, name, specialization.getSpecialization());
    }
}