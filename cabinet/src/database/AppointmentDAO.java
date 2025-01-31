package database;

import Cabinet.Management.VisitDates;
import Cabinet.Personnels.Client;
import Cabinet.Personnels.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    public void addAppointment(VisitDates appointment) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, status) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // First get or create patient_id and doctor_id
            int patientId = getPatientId(appointment.patient);
            int doctorId = getDoctorId(appointment.doctor);
            
            pstmt.setInt(1, patientId);
            pstmt.setInt(2, doctorId);
            pstmt.setTimestamp(3, Timestamp.valueOf(appointment.Date));
            pstmt.setString(4, "Scheduled");
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getPatientId(Client patient) throws SQLException {
        String sql = "SELECT id FROM patients WHERE full_name = ? AND phone_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, patient.FullName);
            pstmt.setString(2, patient.PhoneNum);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }

    private int getDoctorId(Doctor doctor) throws SQLException {
        String sql = "SELECT id FROM doctors WHERE full_name = ? AND email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, doctor.FullName);
            pstmt.setString(2, doctor.Mail);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }

    public List<VisitDates> getAllAppointments() {
        List<VisitDates> appointments = new ArrayList<>();
        String sql = "SELECT a.*, p.full_name as patient_name, p.phone_number, " +
                    "d.full_name as doctor_name, d.email, d.specialization, d.phone_number as doctor_phone " +
                    "FROM appointments a " +
                    "JOIN patients p ON a.patient_id = p.id " +
                    "JOIN doctors d ON a.doctor_id = d.id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Client patient = new Client(
                    rs.getString("patient_name"),
                    rs.getString("phone_number")
                );
                
                Doctor doctor = new Doctor(
                    rs.getString("doctor_name"),
                    "",  // password not needed
                    rs.getString("email"),
                    rs.getString("specialization"),
                    rs.getString("doctor_phone")
                );
                
                VisitDates appointment = new VisitDates(
                    patient,
                    doctor,
                    rs.getTimestamp("appointment_date").toLocalDateTime()
                );
                appointments.add(appointment);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return appointments;
    }
} 