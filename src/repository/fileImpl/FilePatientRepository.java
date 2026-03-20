package repository.fileImpl;

import entity.Patient;
import repository.PatientRepository;
import repository.annotation.CsvMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FilePatientRepository implements PatientRepository {
    private final Path filePath;
    private final Set<String> uniquePatientNames;
    private final Map<Integer, Patient> patients;
    private Integer patientId = 1;

    public FilePatientRepository(Path filePath) {
        this.patients = new HashMap<>();
        this.uniquePatientNames = new HashSet<>();
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
        if (patient == null) {
            throw new IllegalArgumentException("Не може бути null");
        }
        if (patient.getId() == null) {
            patient.setId(patientId++);
        }
        if (!uniquePatientNames.add(patient.getName())) {
            throw new IllegalArgumentException("Пацієнт з таким іменем вже існує!");
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
            String line = CsvMapper.toCsvLine(patient);
            lines.add(line);
        }
        Files.write(filePath, lines);
    }

    private void loadFile() throws IOException {
        if (!Files.exists(filePath)) {
            return;
        }

        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            if (line.isBlank()) continue; // пропускаємо порожні рядки

            // МАГІЯ ТУТ: Мапер сам створює Пацієнта з рядка!
            Patient patient = CsvMapper.fromCsvLine(line, Patient.class);

            // Зберігаємо його в нашу мапу
            patients.put(patient.getId(), patient);
        }
    }
}
