import repository.AppointmentRepository;
import repository.DoctorRepository;
import repository.PatientRepository;
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

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader();
        AppointmentRepository appointmentRepository = new InMemoryAppointmentRepository();
        PatientRepository patientRepository = new InMemoryPatientRepository();
        DoctorRepository doctorRepository = new InMemoryDoctorRepository();

        PatientService patientService = new PatientServiceImpl(patientRepository);
        DoctorService doctorService = new DoctorServiceImpl(doctorRepository);
        AppointmentService appointmentService = new AppointmentServiceImpl(appointmentRepository, doctorRepository, patientRepository);

        MainMenu mainMenu = new MainMenu(inputReader, appointmentService, doctorService, patientService);
        mainMenu.start();
    }
}