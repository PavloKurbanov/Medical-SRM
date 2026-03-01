package repository;

import entity.Appointment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    default List<Appointment> appointmentFindByDateTime(LocalDateTime date){
        List<Appointment> appointmentDataTimeList = new ArrayList<>();
        List<Appointment> all = findAll();

        for (Appointment appointment : all) {
            if(appointment.getDateTime().equals(date)) {
                appointmentDataTimeList.add(appointment);
            }
        }
        return appointmentDataTimeList;
    }

    default List<Appointment> findByDoctorId(Integer doctorId){
        List<Appointment> appointmentDoctorId = new ArrayList<>();
        List<Appointment> all = findAll();
        for (Appointment appointment : all) {
            if(appointment.getDoctorId().equals(doctorId)) {
                appointmentDoctorId.add(appointment);
            }
        }
        return appointmentDoctorId;
    }

    default List<Appointment> findByPatientId(Integer patientId){
        List<Appointment> appointmentPatientId = new ArrayList<>();
        List<Appointment> all = findAll();

        for (Appointment appointment : all) {
            if(appointment.getPatientId().equals(patientId)) {
                appointmentPatientId.add(appointment);
            }
        }
        return appointmentPatientId;
    }
}
