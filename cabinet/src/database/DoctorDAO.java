package database;

import Cabinet.Personnels.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    public void addDoctor(Doctor doctor) {
        String sql = "INSERT INTO doctors (full_name, specialization, email, phone_number, password) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, doctor.FullName);
            pstmt.setString(2, doctor.Profession);
            pstmt.setString(3, doctor.Mail);
            pstmt.setString(4, doctor.PhoneNum);
            pstmt.setString(5, doctor.PassWord);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Doctor doctor = new Doctor(
                    rs.getString("full_name"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("specialization"),
                    rs.getString("phone_number")
                );
                doctors.add(doctor);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return doctors;
    }
} 