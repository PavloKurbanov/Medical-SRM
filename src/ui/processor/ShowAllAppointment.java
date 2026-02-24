package ui.processor;

import entity.Appointment;
import service.AppointmentService;
import util.ConsolePrinter;

import java.util.List;

public record ShowAllAppointment(AppointmentService appointmentService) implements Processor {

    @Override
    public String choice() {
        return "1";
    }

    @Override
    public void process() {
        List<Appointment> allAppointments = appointmentService.getAllAppointments();
        if (ConsolePrinter.checkIfEmpty(allAppointments, "Не має жодного запису")) {
            return;
        }
        ConsolePrinter.showList(allAppointments, "--- ЗАПИСИ ---");
    }
}
