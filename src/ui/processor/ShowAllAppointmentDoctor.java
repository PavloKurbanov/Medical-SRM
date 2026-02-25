package ui.processor;

import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.inputReader.InputReader;
import util.AppointmentViewMapper;
import util.ConsolePrinter;
import util.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

public record ShowAllAppointmentDoctor(AppointmentService appointmentService,
                                       DoctorService doctorService, PatientService patientService,
                                       InputReader inputReader) implements Processor {
    @Override
    public String choice() {
        return "2";
    }

    @Override
    public void process() {
        try {
            List<Doctor> allDoctors = doctorService.findAll();
            if (ConsolePrinter.checkIfEmpty(allDoctors, "Не має жодного лікаря!")) {
                return;
            }
            ConsolePrinter.showList(allDoctors, "--- ЛІКАРІ ---");

            Integer doctorID = inputReader.readInt("Введіть ID лікаря: ");
            Doctor doctorRepositoryById = doctorService.findById(doctorID);

            List<Appointment> allByDoctorId = appointmentService.findAllByDoctorId(doctorID);
            if (ConsolePrinter.checkIfEmpty(allByDoctorId, "В лікаря " + doctorRepositoryById.getName() + " немає записів!")) {
                return;
            }

            List<String> formattedList = AppointmentViewMapper.toFormattedList(allByDoctorId, doctorService, patientService);

            ConsolePrinter.showList(formattedList, "--- Записи лікаря " + doctorRepositoryById.getName() + " ---");

        } catch (IllegalArgumentException e) {
            System.err.println("ПОМИЛКА: " + e.getMessage());
        }
    }
}
