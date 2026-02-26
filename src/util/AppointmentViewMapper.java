package util;

import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import service.DoctorService;
import service.PatientService;

import java.util.ArrayList;
import java.util.List;

public final class AppointmentViewMapper {
    private AppointmentViewMapper() {
        throw new IllegalStateException("Клас не можна створити!");
    }

    public static List<String> toFormattedList(List<Appointment> appointments, DoctorService doctorService, PatientService patientService) {
        List<String> formattedList = new ArrayList<>();

        for (Appointment appointment : appointments) {
            Doctor doctor = doctorService.findById(appointment.getDoctorId());
            Patient patient = patientService.findById(appointment.getPatientId());

            String info = "Пацієнт " + patient.getName() + " записаний до лікаря " + doctor.getName() + " на (" + DateTimeFormat.format(appointment.getDateTime()) + ")";
            formattedList.add(info);
        }
        return formattedList;
    }
}
