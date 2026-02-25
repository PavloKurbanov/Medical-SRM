package ui.menu;

import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.inputReader.InputReader;
import ui.processor.*;

import java.util.HashMap;
import java.util.Map;

public record RecordingMenuBuilder(InputReader inputReader, AppointmentService appointmentService,
                                   DoctorService doctorService, PatientService patientService) {

    public Map<String, Processor> showMenu() {
        Map<String, Processor> menu = new HashMap<>();

        Processor showAllAppointment = new ShowAllAppointment(appointmentService, doctorService, patientService);
        Processor showAllAppointmentDoctor = new ShowAllAppointmentDoctor(appointmentService, doctorService, patientService, inputReader);
        Processor showAllAppointmentPatient = new ShowAllAppointmentPatient(appointmentService, doctorService, patientService, inputReader);
        Processor showAllAppointmentByDate = new ShowAllAppointmentByDate(inputReader, appointmentService, doctorService, patientService);

        menu.put(showAllAppointment.choice(), showAllAppointment);
        menu.put(showAllAppointmentDoctor.choice(), showAllAppointmentDoctor);
        menu.put(showAllAppointmentPatient.choice(), showAllAppointmentPatient);
        menu.put(showAllAppointmentByDate.choice(), showAllAppointmentByDate);

        return menu;
    }
}