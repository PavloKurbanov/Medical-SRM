package ui.processor.navigation;

import service.DoctorService;
import service.PatientService;
import ui.annotation.menuAnnotation.MenuGroup;
import ui.annotation.menuAnnotation.MenuItem;
import ui.inputReader.InputReader;
import ui.menu.RegistrationMenu;
import ui.processor.Processor;
@MenuItem(action = "1", description = "Адміністрація лікарні", group = MenuGroup.MAIN)
public class RegistrationMenuProcessor implements Processor {
    private final RegistrationMenu registrationMenu;

    public RegistrationMenuProcessor(InputReader inputReader, DoctorService doctorService, PatientService patientService){
        this.registrationMenu = new RegistrationMenu(inputReader, doctorService, patientService);

    }

    @Override
    public void process() {
        registrationMenu.show();
    }
}