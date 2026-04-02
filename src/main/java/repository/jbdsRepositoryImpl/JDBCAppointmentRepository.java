package repository.jbdsRepositoryImpl;

import entity.Appointment;
import repository.AppointmentRepository;
import util.DateTimeFormat;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JDBCAppointmentRepository implements AppointmentRepository {
    private final Connection connection;

    public JDBCAppointmentRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Appointment entity) {
        if(entity == null){
            throw new IllegalArgumentException("Запис не може бути null!");
        }

        String sql = "INSERT INTO appointments (id, doctor_id, patient_id, visit_date) values (?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getDoctorId());
            preparedStatement.setInt(3, entity.getPatientId());
            preparedStatement.setDate(4, Timestamp.valueOf(entity.getDateTime());

            int i = preparedStatement.executeUpdate();

            if(i == 0){
                throw new SQLException("Збереження запису не вдалося, жодного рядка не додано.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Appointment findById(Integer integer) {
        return null;
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int doctorId = resultSet.getInt("doctor_id");
                int patientId = resultSet.getInt("patient_id");
                LocalDateTime date = resultSet.getObject("visit_date", LocalDateTime.class);
,
                Appointment appointment = new Appointment(id, doctorId, patientId, date);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка завантаження записів", e);        }
        return appointments;
    }
}
