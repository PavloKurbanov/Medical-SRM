package ui.menu;

import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.inputReader.InputReader;
import ui.processor.Processor;
import ui.processor.ShowAllAppointment;

import java.util.HashMap;
import java.util.Map;

public class RecordingMenuBuilder {
    private final InputReader inputReader;
    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public RecordingMenuBuilder(InputReader inputReader, AppointmentService appointmentService, DoctorService doctorService, PatientService patientService) {
        this.inputReader = inputReader;
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    public Map<String, Processor> showMenu() {
        Map<String, Processor> menu = new HashMap<>();

        Processor showAllAppointment = new ShowAllAppointment(appointmentService);

        menu.put(showAllAppointment.choice(), showAllAppointment);

        return  menu;
    }

}
