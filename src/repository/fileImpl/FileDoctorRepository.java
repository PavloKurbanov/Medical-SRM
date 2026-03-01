package repository.fileImpl;

import entity.Doctor;
import entity.Specialization;
import repository.DoctorRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileDoctorRepository implements DoctorRepository {
    private final Path filePath;
    private final Map<Integer, Doctor> doctors;
    private Integer doctorId = 1;

    public FileDoctorRepository(Path filePath) {
        this.doctors = new HashMap<>();
        this.filePath = filePath;

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
    public void save(Doctor doctor) {
        if (doctor.getId() == null) {
            doctor.setId(doctorId++);
        }
        doctors.put(doctor.getId(), doctor);

        try {
            saveFile();
        } catch (IOException e) {
            throw new RuntimeException("Не вдалось зберегти доктора у файл", e);
        }
    }

    @Override
    public Doctor findById(Integer doctorId) {
        return doctors.get(doctorId);
    }

    @Override
    public List<Doctor> findAll() {
        return new ArrayList<>(doctors.values());
    }

    private void saveFile() throws IOException {
        List<String> lines = new ArrayList<>();
        for (Doctor doctor : doctors.values()) {
            String line = doctor.getId() + "," + doctor.getName() + "," + doctor.getSpecialization().name();
            lines.add(line);
        }
        Files.write(filePath, lines);
    }

    private void loadFile() throws IOException {
        boolean exists = Files.exists(filePath);
        if (!exists) {
            return;
        }

        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            String[] parse = line.split(",");

            int doctorId = Integer.parseInt(parse[0]);
            String doctorName = parse[1];
            Specialization specialization = Specialization.valueOf(parse[2]);

            Doctor doctor = new Doctor(doctorId, doctorName, specialization);

            doctors.put(doctor.getId(), doctor);
            if (doctorId >= this.doctorId) {
                this.doctorId = doctorId + 1;
            }
        }
    }
}
