import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import entity.Specialization;
import reposytory.AppointmentRepository;
import reposytory.DoctorRepository;
import reposytory.PatientRepository;
import reposytory.impl.InMemoryAppointmentRepository;
import reposytory.impl.InMemoryDoctorRepository;
import reposytory.impl.InMemoryPatientRepository;
import util.DataTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoctorRepository doctorRepository = new InMemoryDoctorRepository();
        PatientRepository patientRepository = new InMemoryPatientRepository();
        AppointmentRepository appointmentRepository = new InMemoryAppointmentRepository();

        Doctor doctor = new Doctor(null, "Павло", Specialization.GYNECOLOGIST);
        Doctor doctor1 = new Doctor(null, "Роман", Specialization.GYNECOLOGIST);

        Patient patient = new Patient(null, "Василина");
        Patient patient2 = new Patient(null, "Уляна");

        doctorRepository.save(doctor);
        doctorRepository.save(doctor1);


        patientRepository.save(patient);
        patientRepository.save(patient2);
        patientRepository.findAll().forEach(System.out::println);
        System.out.println();

        System.out.println("Оберіть лікаря::");
        doctorRepository.findAll().forEach(System.out::println);
        int doctorId = Integer.parseInt(sc.nextLine());
        Doctor doctorById = doctorRepository.findById(doctorId);

        System.out.println("Оберіть пацієнта: ");
        patientRepository.findAll().forEach(System.out::println);
        int patientById = Integer.parseInt(sc.nextLine());
        Patient patientRepositoryById = patientRepository.findById(patientById);
        LocalDateTime now = LocalDateTime.now();

        appointmentRepository.save(new Appointment(0, doctorId, patientById, now));
        List<Appointment> all = appointmentRepository.findAll();
        for (Appointment appointment : all) {
            Doctor doctorName = doctorRepository.findById(appointment.getDoctorId());
            Patient patientName = patientRepository.findById(appointment.getPatientId());

            System.out.println("Пацієнт: " + patientName.getName() + " записаний до лікаря: " + doctorName.getName() + " на ("
                    + DataTimeFormat.format(appointment.getDateTime()) + ")");
        }

        Specialization specialization = getSpecialization("Введіть цифру");
        System.out.println(specialization.getSpecialization());
    }


    public static Specialization getSpecialization(String prompt) {
        Specialization[] specializations = Specialization.values();
        Specialization specialization = null;

        do {
            for (int i = 0; i < specializations.length; i++) {
                System.out.println((i + 1) + ". " + specializations[i].getSpecialization());
            }
            Scanner sc = new Scanner(System.in);
            int readInt = Integer.parseInt(sc.nextLine());
            if (readInt >= 1 && readInt <= specializations.length) {
                specialization = specializations[readInt - 1];
            }
        } while (specialization == null);
        return specialization;
    }
}