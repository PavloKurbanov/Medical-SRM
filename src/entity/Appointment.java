package entity;

import util.DataTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public class Appointment {
    private Integer id;
    private  final Integer doctorId;
    private final Integer patientId;
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
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("Пацієнт: %s записаний до лікаря %s на (%s)", patientId, doctorId, DataTimeFormat.format(dateTime));
    }
}