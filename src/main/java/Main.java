import repository.AppointmentRepository;
import repository.DoctorRepository;
import repository.PatientRepository;
import repository.jbdsRepositoryImpl.JBDCDoctorsRepository;
import repository.jbdsRepositoryImpl.JBDCPatientRepository;
import repository.jbdsRepositoryImpl.JDBCAppointmentRepository;
import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import service.impl.AppointmentServiceImpl;
import service.impl.DoctorServiceImpl;
import service.impl.LiveQueueService;
import service.impl.PatientServiceImpl;
import ui.inputReader.InputReader;
import ui.menu.MainMenu;
import util.ConnectionManager;

public class Main {
    public static void main(String[] args) {
        try{
            ConnectionManager connectionManager = new ConnectionManager();
            InputReader inputReader = new InputReader();

            AppointmentRepository appointmentRepository = new JDBCAppointmentRepository(connectionManager);
            PatientRepository patientRepository = new JBDCPatientRepository(connectionManager);
            DoctorRepository doctorRepository = new JBDCDoctorsRepository(connectionManager);

            PatientService patientService = new PatientServiceImpl(patientRepository);
            DoctorService doctorService = new DoctorServiceImpl(doctorRepository);
            AppointmentService appointmentService = new AppointmentServiceImpl(appointmentRepository, doctorRepository, patientRepository);
            LiveQueueService liveQueueService = new LiveQueueService();

            MainMenu mainMenu = new MainMenu(inputReader, appointmentService, doctorService, patientService, liveQueueService);
            mainMenu.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}