package repository.fileImpl;

import entity.Patient;
import repository.PatientRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FIlePatientRepository implements PatientRepository {
    private final Path filePath;
    private final Map<Integer, Patient> patients;
    private Integer patientId = 1;

    public FIlePatientRepository(Path filePath) {
        this.patients = new HashMap<>();
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
    public void save(Patient patient) {
        if (patient.getId() == null) {
            patient.setId(patientId++);
        }
        patients.put(patient.getId(), patient);

        try {
            saveFile();
        } catch (IOException e) {
            throw new RuntimeException("Не вдалось зберегти пацієнта у файл", e);
        }
    }

    @Override
    public Patient findById(Integer patientId) {
        return patients.get(patientId);
    }

    @Override
    public List<Patient> findAll() {
        return new ArrayList<>(patients.values());
    }

    private void saveFile() throws IOException {
        List<String> lines = new ArrayList<>();
        for (Patient patient : patients.values()) {
            String line = patient.getId() + "," + patient.getName();
            lines.add(line);
        }
        Files.write(filePath, lines);
    }

    private void loadFile() throws IOException {
        boolean exists = Files.exists(filePath);
        if (!exists) {
            return;
        }

        List<String> list = Files.readAllLines(filePath);
        for (String line : list) {
            String[] split = line.split(",");

            int patientId = Integer.parseInt(split[0]);
            String patientName = split[1];

            Patient patient = new Patient(patientId, patientName);

            patients.put(patientId, patient);
            if (patientId >= this.patientId) {
                this.patientId = patientId + 1;
            }
        }
    }
}
