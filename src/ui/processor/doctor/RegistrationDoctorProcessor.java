package ui.processor.doctor;

import entity.Doctor;
import entity.Specialization;
import service.DoctorService;
import ui.inputReader.InputReader;
import ui.processor.Processor;

public record RegistrationDoctorProcessor(InputReader inputReader, DoctorService doctorService) implements Processor {

    @Override
    public String choice() {
        return "1";
    }

    @Override
    public void process() {
        try {
            String doctorName = inputReader.readString("Введіть ім'я лікаря: ");
            Specialization specialization = inputReader.readSpecialization("Оберіть спеціальність");

            doctorService.save(new Doctor(null, doctorName, specialization));
            System.out.println("Лікар " + doctorName + " | Спеціальність: " + specialization.getSpecialization() + " успішно доданий");
        } catch (IllegalArgumentException e) {
            System.err.println("ПОМИЛКА: " + e.getMessage());
        }
    }
}