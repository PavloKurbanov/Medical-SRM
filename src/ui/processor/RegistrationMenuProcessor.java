package ui.processor;

import service.DoctorService;
import service.PatientService;
import ui.inputReader.InputReader;
import ui.menu.RegistrationMenu;

public class RegistrationMenuProcessor implements Processor {
    private final RegistrationMenu registrationMenu;

    public RegistrationMenuProcessor(InputReader inputReader, DoctorService doctorService, PatientService patientService){
        this.registrationMenu = new RegistrationMenu(inputReader, doctorService, patientService);

    }

    @Override
    public String choice() {
        return "1";
    }

    @Override
    public void process() {
        registrationMenu.show();
    }
}
