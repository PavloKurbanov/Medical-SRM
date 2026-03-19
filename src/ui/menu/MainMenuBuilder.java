package ui.menu;

import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import service.impl.LiveQueueService;
import ui.annotation.MenuGroup;
import ui.inputReader.InputReader;
import ui.menuRegistry.MenuRegistry;
import ui.processor.appointment.BookAppointmentProcessor;
import ui.processor.Processor;
import ui.processor.liveQueue.LiveQueueProcessor;
import ui.processor.navigation.RecordingMenuProcessor;
import ui.processor.navigation.RegistrationMenuProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record MainMenuBuilder(InputReader inputReader, AppointmentService appointmentService,
                              DoctorService doctorService, PatientService patientService,
                              LiveQueueService liveQueueService) {

    public Map<String, Processor> showMenu() {

        Processor bookAppointment = new BookAppointmentProcessor(appointmentService, doctorService, patientService, inputReader);
        Processor recordingShowMenu = new RecordingMenuProcessor(inputReader, appointmentService, doctorService, patientService);
        Processor registrationMenu = new RegistrationMenuProcessor(inputReader, doctorService, patientService);
        Processor liveQueue = new LiveQueueProcessor(liveQueueService, inputReader);

        List<Processor> bookAppointment1 = List.of(
                bookAppointment, recordingShowMenu, registrationMenu, liveQueue);

        return MenuRegistry.buildMenu(bookAppointment1, MenuGroup.MAIN);
    }
}