package ui.processor;

import entity.Appointment;
import entity.Patient;
import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.inputReader.InputReader;
import util.ConsolePrinter;

import java.util.List;

public record ShowAllAppointmentPatient(AppointmentService appointmentService, PatientService patientService,
                                        InputReader inputReader) implements Processor {

    @Override
    public String choice() {
        return "3";
    }

    @Override
    public void process() {
        try {
            List<Patient> allPatient = patientService.findAll();
            if(ConsolePrinter.checkIfEmpty(allPatient, "Не має жодного пацієнта!")){
                return;
            }
            ConsolePrinter.showList(allPatient, "--- ПАЦІЄНТИ ---");

            Integer patientId = inputReader.readInt("Введіть ID пацієнта: ");
            Patient patient = patientService.findById(patientId);

            List<Appointment> allByPatientId = appointmentService.findAllByPatientId(patientId);
            if(ConsolePrinter.checkIfEmpty(allByPatientId, "В пацієнта " + patient.getName() + " не має записів!")){
                return;
            }
            ConsolePrinter.showList(allByPatientId, "--- Записи пацієнта " + patient.getName() + " ---");
        } catch (IllegalArgumentException e) {
            System.err.println("ПОИМЛКА:" + e.getMessage());
        }
    }
}
