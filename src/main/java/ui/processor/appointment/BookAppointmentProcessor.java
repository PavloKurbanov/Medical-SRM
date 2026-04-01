package ui.processor.appointment;

import entity.Doctor;
import entity.Patient;
import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import ui.annotation.menuAnnotation.MenuItem;
import ui.inputReader.InputReader;
import ui.processor.Processor;
import util.ConsolePrinter;
import util.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
@MenuItem(action = "2", description = "Записатись до лікаря")
public record BookAppointmentProcessor(AppointmentService appointmentService, DoctorService doctorService,
                                       PatientService patientService, InputReader inputReader) implements Processor {

    @Override
    public void process() {

        List<Patient> allPatient = patientService.findAll();
        List<Doctor> allDoctor = doctorService.findAll();

        if (ConsolePrinter.checkIfEmpty(allPatient, "Зареєструйте пацієнта!")) {
            return;
        }

        if (ConsolePrinter.checkIfEmpty(allDoctor, "Зареєструйте доктора!")) {
            return;
        }

        ConsolePrinter.showList(allPatient, "--- ПАЦІЄНТИ ---");
        Integer patientId = inputReader.readInt("Введіть ID пацієнта: ");
        Patient patient = patientService.findById(patientId);

        ConsolePrinter.showList(allDoctor, "--- ЛІКАРІ ---");
        Integer doctorId = inputReader.readInt("Введіть ID лікаря: ");
        Doctor doctor = doctorService.findById(doctorId);

        LocalDateTime localDateTime = inputReader.readDateTime("Введіть дату через '-', та час через ':' :");

        try {
            appointmentService.save(doctorId, patientId, localDateTime);
            System.out.println("Пацієнт " + patient.getName() + " записний до лікаря " + doctor.getName() + " на (" + DateTimeFormat.format(localDateTime) + ").");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}