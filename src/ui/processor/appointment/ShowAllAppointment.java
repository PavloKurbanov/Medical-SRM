package ui.processor.appointment;

import entity.Appointment;
import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.annotation.menuAnnotation.MenuGroup;
import ui.annotation.menuAnnotation.MenuItem;
import ui.processor.Processor;
import util.AppointmentViewMapper;
import util.ConsolePrinter;

import java.util.Comparator;
import java.util.List;
@MenuItem(action = "1", description = "Показати всі записи", group = MenuGroup.RECORDING)
public record ShowAllAppointment(AppointmentService appointmentService, DoctorService doctorService,
                                 PatientService patientService) implements Processor {

    @Override
    public void process() {
        List<Appointment> sortAppointments = appointmentService.getSortAppointments(Comparator.comparing(Appointment::getId));
        if (ConsolePrinter.checkIfEmpty(sortAppointments, "Не має жодного запису")) {
            return;
        }
        List<String> formattedList = AppointmentViewMapper.toFormattedList(sortAppointments, doctorService, patientService);
        ConsolePrinter.showList(formattedList, "--- ЗАПИСИ ---");
    }
}