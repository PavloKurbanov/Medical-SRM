package ui.processor.navigation;

import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.inputReader.InputReader;
import ui.menu.RecordingMenu;
import ui.processor.Processor;

public class RecordingMenuProcessor implements Processor {

    private final RecordingMenu recording;

    public RecordingMenuProcessor(InputReader inputReader, AppointmentService appointmentService, DoctorService doctorService, PatientService patientService) {
        this.recording = new RecordingMenu(inputReader, appointmentService, doctorService, patientService);
    }

    @Override
    public String choice() {
        return "3";
    }

    @Override
    public void process() {
        recording.showMenu();
    }
}
