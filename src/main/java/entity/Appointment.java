package entity;

import repository.annotation.CsvColum;
import repository.annotation.CsvTable;
import ui.annotation.validationAnnotation.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@CsvTable(fileName = "appointments.cvc")
public class Appointment implements Comparable<Appointment> {

    @CsvColum(index = 0)
    private Integer id;

    @CsvColum(index = 1)
    @NotNull(message = "Введіть ID доктора!")
    private Integer doctorId;

    @CsvColum(index = 2)
    @NotNull(message = "Введіть ID пацієнта!")
    private Integer patientId;

    @CsvColum(index = 3)
    @NotNull(message = "Введіть дату!")
    private LocalDateTime dateTime;

    public Appointment(Integer id, Integer doctorId, Integer patientId, LocalDateTime dateTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.dateTime = dateTime;
    }

    public Appointment(){

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