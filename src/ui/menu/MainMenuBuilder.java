package ui.menu;

import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.inputReader.InputReader;
import ui.processor.BookAppointmentProcessor;
import ui.processor.Processor;
import ui.processor.RecordingMenuProcessor;

import java.util.HashMap;
import java.util.Map;

public record MainMenuBuilder(InputReader inputReader, AppointmentService appointmentService,
                              DoctorService doctorService, PatientService patientService) {

    public Map<String, Processor> showMenu() {
        Map<String, Processor> menu = new HashMap<>();

        Processor bookAppointment = new BookAppointmentProcessor(appointmentService, doctorService, patientService, inputReader);
        Processor recordingShowMenu = new RecordingMenuProcessor(inputReader, appointmentService, doctorService, patientService);

        menu.put(bookAppointment.choice(), bookAppointment);
        menu.put(recordingShowMenu.choice(), recordingShowMenu);

        return menu;
    }
}