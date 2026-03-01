package repository.fileImpl;

import entity.Appointment;
import repository.AppointmentRepository;
import repository.impl.InMemoryAppointmentRepository;
import util.DateTimeFormat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

public class FileAppointmentRepository implements AppointmentRepository {
    private final Path filePath;
    private final Map<Integer, Appointment> appointments;
    private Integer appointmentId = 1;

    public FileAppointmentRepository(Path filePath) {
        this.filePath = filePath;
        this.appointments = new HashMap<>();
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            } else {
                loadFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Критична помилка ініціалізації бази даних", e);
        }
    }

    @Override
    public void save(Appointment appointment) {
        if (appointment.getId() == null) {
            appointment.setId(appointmentId++);
        }
        appointments.put(appointment.getId(), appointment);

        try {
            saveFile();
        } catch (IOException e) {
            throw new RuntimeException("Не вдалося зберегти запис у файл", e);
        }
    }

    @Override
    public Appointment findById(Integer appointmentId) {
        return appointments.get(appointmentId);
    }

    @Override
    public List<Appointment> findAll() {
        return new ArrayList<>(appointments.values());
    }

    private void saveFile() throws IOException {
        List<String> linesAppointment = new ArrayList<>();
        for (Appointment appointment : appointments.values()) {
            String line = appointment.getId() + "," + appointment.getDoctorId() + "," + appointment.getPatientId() + "," + appointment.getDateTime();
            linesAppointment.add(line);
        }
        Files.write(filePath, linesAppointment);
    }

    private void loadFile() throws IOException {
        boolean exists = Files.exists(filePath);
        if (!exists) {
            return;
        }

        List<String> linesAppointment = Files.readAllLines(filePath);

        for (String appointment : linesAppointment) {
            String[] line = appointment.split(",");

            int id = Integer.parseInt(line[0]);
            int doctorId = Integer.parseInt(line[1]);
            int patientId = Integer.parseInt(line[2]);
            LocalDateTime dateTime = LocalDateTime.parse(line[3]);

            Appointment newAppointment = new Appointment(id, doctorId, patientId, dateTime);
            appointments.put(id, newAppointment);
        }
    }
}
