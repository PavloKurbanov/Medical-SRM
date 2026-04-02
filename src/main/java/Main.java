import repository.AppointmentRepository;
import repository.DoctorRepository;
import repository.PatientRepository;
import repository.fileImpl.FilePatientRepository;
import repository.fileImpl.FileAppointmentRepository;
import repository.fileImpl.FileDoctorRepository;
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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private final static String URL = "jdbc:mysql://localhost:3306/study_db";
    private final static String USER = "root";
    private final static String PASS = "270119Pavlo";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            InputReader inputReader = new InputReader();

            AppointmentRepository appointmentRepository = new JDBCAppointmentRepository(connection);
            PatientRepository patientRepository = new JBDCPatientRepository(connection);
            DoctorRepository doctorRepository = new JBDCDoctorsRepository(connection);

            PatientService patientService = new PatientServiceImpl(patientRepository);
            DoctorService doctorService = new DoctorServiceImpl(doctorRepository);
            AppointmentService appointmentService = new AppointmentServiceImpl(appointmentRepository, doctorRepository, patientRepository);
            LiveQueueService liveQueueService = new LiveQueueService();

            MainMenu mainMenu = new MainMenu(inputReader, appointmentService, doctorService, patientService, liveQueueService);
            mainMenu.start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}