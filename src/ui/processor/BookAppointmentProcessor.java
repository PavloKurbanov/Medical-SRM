package ui.processor;

import entity.Doctor;
import entity.Patient;
import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.inputReader.InputReader;
import util.ConsolePrinter;
import util.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public record BookAppointmentProcessor(AppointmentService appointmentService, DoctorService doctorService,
                                       PatientService patientService, InputReader inputReader) implements Processor {

    @Override
    public String choice() {
        return "2";
    }

    @Override
    public void process() {
        try {
            List<Patient> allPatient = patientService.findAll();
            List<Doctor> allDoctor = doctorService.findAll();

            if (ConsolePrinter.checkIfEmpty(allPatient, "Зареєструйте пацієнта!")) {
                return;
            }

            if (ConsolePrinter.checkIfEmpty(allDoctor, "Зареєструйте доктора!")) {
                return;
            }

            ConsolePrinter.showList(allPatient, "--- ПАЦІЄНТИ ---");
            Integer patientId = inputReader.readInt("Введіть ID пацієнта: ");
            Patient patient = patientService.findById(patientId);

            ConsolePrinter.showList(allDoctor, "--- ЛІКАРІ ---");
            Integer doctorId = inputReader.readInt("Введіть ID лікаря: ");
            Doctor doctor = doctorService.findById(doctorId);

            LocalDateTime localDateTime = inputReader.readDateTime("Введіть дату через '-', та час через ':' :");

            appointmentService.save(doctorId, patientId, localDateTime);
            System.out.println("Пацієнт " + patient.getName() + " записний до лікаря " + doctor.getName() + " на (" + DateTimeFormat.format(localDateTime) + ").");
        } catch (IllegalArgumentException e) {
            System.err.println("ПОМИЛКА: " + e.getMessage());
        }
    }
}