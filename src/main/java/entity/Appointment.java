package entity;

import ui.annotation.validationAnnotation.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

public class Appointment implements Comparable<Appointment> {

    private Integer id;

    @NotNull(message = "Введіть ID доктора!")
    private final Integer doctorId;

    @NotNull(message = "Введіть ID пацієнта!")
    private final Integer patientId;

    @NotNull(message = "Введіть дату!")
    private final LocalDateTime dateTime;

    public Appointment(Integer id, Integer doctorId, Integer patientId, LocalDateTime dateTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(doctorId, that.doctorId) && Objects.equals(patientId, that.patientId) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, patientId, dateTime);
    }

    @Override
    public int compareTo(Appointment o) {
        return this.dateTime.compareTo(o.dateTime);
    }
}