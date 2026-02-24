package ui.menu;

import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.inputReader.InputReader;
import ui.processor.Processor;
import ui.processor.ShowAllAppointment;
import ui.processor.ShowAllAppointmentDoctor;
import ui.processor.ShowAllAppointmentPatient;

import java.util.HashMap;
import java.util.Map;

public record RecordingMenuBuilder(InputReader inputReader, AppointmentService appointmentService,
                                   DoctorService doctorService, PatientService patientService) {

    public Map<String, Processor> showMenu() {
        Map<String, Processor> menu = new HashMap<>();

        Processor showAllAppointment = new ShowAllAppointment(appointmentService);
        Processor showAllAppointmentDoctor = new ShowAllAppointmentDoctor(appointmentService, doctorService, inputReader);
        Processor showAllAppointmentPatient = new ShowAllAppointmentPatient(appointmentService, patientService, inputReader);

        menu.put(showAllAppointment.choice(), showAllAppointment);
        menu.put(showAllAppointmentDoctor.choice(), showAllAppointmentDoctor);
        menu.put(showAllAppointmentPatient.choice(), showAllAppointmentPatient);

        return menu;
    }
}