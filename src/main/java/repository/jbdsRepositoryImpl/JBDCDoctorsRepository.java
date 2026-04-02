package repository.jbdsRepositoryImpl;

import entity.Doctor;
import entity.Specialization;
import repository.DoctorRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public record JBDCDoctorsRepository(Connection connection) implements DoctorRepository {

    @Override
    public void save(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Не може бути null");
        }
        String sql = "insert into doctors (name, specialization) values (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getSpecialization().getSpecialization());
            int executeUpdate = preparedStatement.executeUpdate();

            if (executeUpdate == 0) {
                throw new SQLException("Збереження лікаря не вдалося, жодного рядка не додано.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не вдалось зберегти доктора у базу", e);
        }
    }

    @Override
    public Doctor findById(Integer integer) {
        String sql = "select id, name, specialization from doctors where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, integer);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Specialization specialization = Specialization.getSpecialization(resultSet.getString("specialization"));
                    return new Doctor(id, name, specialization);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при пошуку лікаря з ID: " + integer, e);
        }
        return null;
    }

    @Override
    public List<Doctor> findAll() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        String sql = "select * from doctors";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Specialization specialization = Specialization.getSpecialization(resultSet.getString("specialization"));
                Doctor doctor = new Doctor(id, name, specialization);
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не має жодного лікаря!", e);
        }
        return doctors;
    }
}
