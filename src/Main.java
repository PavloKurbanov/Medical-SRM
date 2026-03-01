import repository.AppointmentRepository;
import repository.DoctorRepository;
import repository.PatientRepository;
import repository.fileImpl.FIlePatientRepository;
import repository.fileImpl.FileAppointmentRepository;
import repository.fileImpl.FileDoctorRepository;
import repository.impl.InMemoryAppointmentRepository;
import repository.impl.InMemoryDoctorRepository;
import repository.impl.InMemoryPatientRepository;
import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import service.impl.AppointmentServiceImpl;
import service.impl.DoctorServiceImpl;
import service.impl.PatientServiceImpl;
import ui.inputReader.InputReader;
import ui.menu.MainMenu;

import java.nio.file.Path;
import java.nio.file.Paths;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Path appointmentFine = Paths.get("appointments.cvc");
        Path doctorFile = Paths.get("doctors.csv");
        Path patientFile = Paths.get("patients.csv");

        InputReader inputReader = new InputReader();

        AppointmentRepository appointmentRepository = new FileAppointmentRepository(appointmentFine);
        PatientRepository patientRepository = new FIlePatientRepository(patientFile);
        DoctorRepository doctorRepository = new FileDoctorRepository(doctorFile);

        PatientService patientService = new PatientServiceImpl(patientRepository);
        DoctorService doctorService = new DoctorServiceImpl(doctorRepository);
        AppointmentService appointmentService = new AppointmentServiceImpl(appointmentRepository, doctorRepository, patientRepository);

        MainMenu mainMenu = new MainMenu(inputReader, appointmentService, doctorService, patientService);
        mainMenu.start();
    }
}