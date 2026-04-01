import repository.AppointmentRepository;
import repository.DoctorRepository;
import repository.PatientRepository;
import repository.fileImpl.FilePatientRepository;
import repository.fileImpl.FileAppointmentRepository;
import repository.fileImpl.FileDoctorRepository;
import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import service.impl.AppointmentServiceImpl;
import service.impl.DoctorServiceImpl;
import service.impl.LiveQueueService;
import service.impl.PatientServiceImpl;
import ui.inputReader.InputReader;
import ui.menu.MainMenu;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path appointmentFine = Paths.get("appointments.cvc");
        Path doctorFile = Paths.get("doctors.csv");
        Path patientFile = Paths.get("patients.csv");

        InputReader inputReader = new InputReader();

        AppointmentRepository appointmentRepository = new FileAppointmentRepository(appointmentFine);
        PatientRepository patientRepository = new FilePatientRepository(patientFile);
        DoctorRepository doctorRepository = new FileDoctorRepository(doctorFile);

        PatientService patientService = new PatientServiceImpl(patientRepository);
        DoctorService doctorService = new DoctorServiceImpl(doctorRepository);
        AppointmentService appointmentService = new AppointmentServiceImpl(appointmentRepository, doctorRepository, patientRepository);
        LiveQueueService liveQueueService = new LiveQueueService();

        MainMenu mainMenu = new MainMenu(inputReader, appointmentService, doctorService, patientService, liveQueueService);
        mainMenu.start();
    }
}