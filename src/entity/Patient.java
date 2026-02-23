package entity;

import java.util.Objects;

public class Patient {
    private Integer id;
    private final String name;

    public Patient(Integer id, String name) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Введіть ім'я пацієнта");
        }
        this.id = id;
        this.name = name;
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
        return Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s", id, name);
    }
}