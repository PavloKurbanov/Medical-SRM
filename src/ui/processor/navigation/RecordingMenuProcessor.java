package ui.processor.navigation;

import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.annotation.menuAnnotation.MenuGroup;
import ui.annotation.menuAnnotation.MenuItem;
import ui.inputReader.InputReader;
import ui.menu.RecordingMenu;
import ui.processor.Processor;
@MenuItem(action = "3", description = "Показати всі записи", group = MenuGroup.MAIN)
public class RecordingMenuProcessor implements Processor {

    private final RecordingMenu recording;

    public RecordingMenuProcessor(InputReader inputReader, AppointmentService appointmentService, DoctorService doctorService, PatientService patientService) {
        this.recording = new RecordingMenu(inputReader, appointmentService, doctorService, patientService);
    }

    @Override
    public void process() {
        recording.showMenu();
    }
}