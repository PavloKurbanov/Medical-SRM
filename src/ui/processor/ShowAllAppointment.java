package ui.processor;

import entity.Appointment;
import service.AppointmentService;
import util.ConsolePrinter;

import java.util.List;

public class ShowAllAppointment implements Processor {
    private final AppointmentService appointmentService;

    public ShowAllAppointment(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public String choice() {
        return "1";
    }

    @Override
    public void process() {
        List<Appointment> allAppointments = appointmentService.getAllAppointments();
        if(ConsolePrinter.checkIfEmpty(allAppointments, "Не має жодного запису")){
            return;
        }
        ConsolePrinter.showList(allAppointments, "--- ЗАПИСИ ---");
    }
}
