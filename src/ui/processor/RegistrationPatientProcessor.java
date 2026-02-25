package ui.processor;

import entity.Patient;
import service.DoctorService;
import service.PatientService;
import ui.inputReader.InputReader;

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
            throw new IllegalArgumentException("ПОМИКА: " + e.getMessage());
        }
    }
}
