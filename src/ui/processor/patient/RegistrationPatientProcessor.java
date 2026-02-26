package ui.processor.patient;

import entity.Patient;
import service.PatientService;
import ui.inputReader.InputReader;
import ui.processor.Processor;

public record RegistrationPatientProcessor(InputReader inputReader,
                                           PatientService patientService) implements Processor {

    @Override
    public String choice() {
        return "2";
    }

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
