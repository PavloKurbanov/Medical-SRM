package ui.processor;

import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.inputReader.InputReader;
import ui.menu.RecordingMenu;

import java.util.Map;

public class RecordingMenuProcessor implements Processor {

    private final RecordingMenu recording;

    public RecordingMenuProcessor(InputReader inputReader, AppointmentService appointmentService, DoctorService doctorService, PatientService patientService) {
        RecordingMenu recordingMenu = new RecordingMenu(inputReader, appointmentService, doctorService, patientService);
        this.recording = recordingMenu;
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
