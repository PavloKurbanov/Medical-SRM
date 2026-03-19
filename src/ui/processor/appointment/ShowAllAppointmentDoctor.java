package ui.processor.appointment;

import entity.Appointment;
import entity.Doctor;
import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.annotation.MenuGroup;
import ui.annotation.MenuItem;
import ui.inputReader.InputReader;
import ui.processor.Processor;
import util.AppointmentViewMapper;
import util.ConsolePrinter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@MenuItem(action = "2", description = "Показати всі записи лікаря", group = MenuGroup.RECORDING)
public record ShowAllAppointmentDoctor(AppointmentService appointmentService,
                                       DoctorService doctorService, PatientService patientService,
                                       InputReader inputReader) implements Processor {

    @Override
    public void process() {
        try {
            List<Doctor> allDoctors = doctorService.findAll();
            if (ConsolePrinter.checkIfEmpty(allDoctors, "Не має жодного лікаря!")) {
                return;
            }
            ConsolePrinter.showList(allDoctors, "--- ЛІКАРІ ---");

            Integer doctorID = inputReader.readInt("Введіть ID лікаря: ");
            if (doctorID == null) {
                throw new IllegalArgumentException("Лікаря з таким ID не знайдено!");
            }

            Doctor doctorRepositoryById = doctorService.findById(doctorID);

            List<Appointment> allByDoctorId = appointmentService.findAllByDoctorId(doctorID).stream().sorted().collect(Collectors.toList());
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