package repository.jbdsRepositoryImpl;

import entity.Patient;
import repository.PatientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JBDCPatientRepository implements PatientRepository {
    private final Connection connection;

    public JBDCPatientRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Patient entity) {
        if(entity == null){
            throw new IllegalArgumentException("Пацієнт не може бути null!");
        }

        String sql = "INSERT INTO patients (id, name) VALUES (?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            int i = preparedStatement.executeUpdate();

            if(i == 0){
                throw new SQLException("Збереження пацієнта не вдалося, жодного рядка не додано.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не вдалось зберегти пацієнта у базу", e);
        }
    }

    @Override
    public Patient findById(Integer integer) {
        String sql = "select id, name from patients where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, integer);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    return new Patient(id, name);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при пошуку пацієнта з ID: " + integer, e);
        }
        return null;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Patient patient = new Patient(id, name);
                patients.add(patient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patients;
    }
}
