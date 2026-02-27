package ui.processor.appointment;

import entity.Appointment;
import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.processor.Processor;
import util.AppointmentViewMapper;
import util.ConsolePrinter;

import java.util.List;

public record ShowAllAppointment(AppointmentService appointmentService, DoctorService doctorService,
                                 PatientService patientService) implements Processor {

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
        List<String> formattedList = AppointmentViewMapper.toFormattedList(allAppointments, doctorService, patientService);
        ConsolePrinter.showList(formattedList, "--- ЗАПИСИ ---");
    }
}