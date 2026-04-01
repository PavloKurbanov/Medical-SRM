package repository;

import entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    default List<Appointment> appointmentFindByDateTime(LocalDateTime date){
        if(date == null){
            throw new IllegalArgumentException("Дата не може бути null");
        }
        return findAll().stream().filter(appointment -> Objects.equals(appointment.getDateTime(), date)).collect(Collectors.toList());
    }

    default List<Appointment> findByDoctorId(Integer doctorId){
        if (doctorId == null) {
            throw new IllegalArgumentException("ID доктора не може бути null!");
        }
        return findAll().stream().filter(appointment -> Objects.equals(appointment.getDoctorId(), doctorId)).collect(Collectors.toList());
    }

    default List<Appointment> findByPatientId(Integer patientId){
        if (patientId == null) {
            throw new IllegalArgumentException("ID пацієнта не може бути null!");
        }
        return findAll().stream().filter(appointment -> Objects.equals(appointment.getPatientId(),patientId)).collect(Collectors.toList());
    }
}
