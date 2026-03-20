package ui.processor.doctor;

import entity.Doctor;
import entity.Specialization;
import service.DoctorService;
import ui.annotation.menuAnnotation.MenuGroup;
import ui.annotation.menuAnnotation.MenuItem;
import ui.inputReader.InputReader;
import ui.processor.Processor;
@MenuItem(action = "1", description = "Додати лікаря", group = MenuGroup.REGISTRATION)
public record RegistrationDoctorProcessor(InputReader inputReader, DoctorService doctorService) implements Processor {

    @Override
    public void process() {
        try {
            String doctorName = inputReader.readString("Введіть ім'я лікаря: ");
            Specialization specialization = inputReader.readSpecialization("Оберіть спеціальність");

            doctorService.save(new Doctor(null, doctorName, specialization));
            System.out.println("Лікар " + doctorName + " | Спеціальність: " + specialization.getSpecialization() + " успішно доданий");
        } catch (IllegalArgumentException e) {
            System.out.println("ПОМИЛКА: " + e.getMessage());
        }
    }
}