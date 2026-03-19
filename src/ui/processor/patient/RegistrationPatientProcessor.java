package ui.processor.patient;

import entity.Patient;
import service.PatientService;
import ui.annotation.MenuGroup;
import ui.annotation.MenuItem;
import ui.inputReader.InputReader;
import ui.processor.Processor;
@MenuItem(action = "2", description = "Додати пацієнта", group = MenuGroup.REGISTRATION)
public record RegistrationPatientProcessor(InputReader inputReader,
                                           PatientService patientService) implements Processor {

    @Override
    public void process() {
        try {
            String patientName = inputReader.readString("Введіть ім'я пацієнта: ");
            patientService.save(new Patient(null, patientName));
            System.out.println("Пацієнт " + patientName + " успішно доданий!");
        } catch (IllegalArgumentException e) {
            System.err.println("ПОМИЛКА: " + e.getMessage());
        }
    }
}