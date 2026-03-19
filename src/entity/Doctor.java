package entity;

import java.util.Objects;

public class Doctor implements Comparable<Doctor> {
    private Integer id;
    private final String name;
    private final Specialization specialization;

    public Doctor(Integer id, String name, Specialization specialization) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Введіть ім'я лікаря");
        }
        this.id = id;
        this.name = name;
        this.specialization = specialization;
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