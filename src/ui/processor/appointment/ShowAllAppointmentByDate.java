package ui.processor.appointment;

import entity.Appointment;
import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.inputReader.InputReader;
import ui.processor.Processor;
import util.AppointmentViewMapper;
import util.ConsolePrinter;
import util.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public record ShowAllAppointmentByDate(InputReader inputReader, AppointmentService appointmentService,
                                       DoctorService doctorService,
                                       PatientService patientService) implements Processor {

    @Override
    public String choice() {
        return "4";
    }

    @Override
    public void process() {
        try {
            LocalDateTime localDateTime = inputReader.readDateTime("Введіть дату та час: ");
            List<Appointment> appointmentsByDate = appointmentService.appointmentFindByDateTime(localDateTime);

            if (ConsolePrinter.checkIfEmpty(appointmentsByDate, "Не має запису на час (" + DateTimeFormat.format(localDateTime) + ")")) {
                return;
            }

            List<String> formattedList = AppointmentViewMapper.toFormattedList(appointmentsByDate, doctorService, patientService);

            ConsolePrinter.showList(formattedList, "--- ЗАПИСИ НА ДАТУ (" + DateTimeFormat.format(localDateTime) + ") ---");
        } catch (IllegalArgumentException e) {
            System.err.println("ПОМИЛКА: " + e.getMessage());
        }
    }
}
