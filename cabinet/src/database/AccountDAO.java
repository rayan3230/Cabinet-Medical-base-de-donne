package database;

import Cabinet.Personnels.Doctor;
import Cabinet.Personnels.Secretary;
import java.sql.*;

public class AccountDAO {
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

    public void addSecretary(Secretary secretary) {
        String sql = "INSERT INTO secretaries (full_name, password) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, secretary.FullName);
            pstmt.setString(2, secretary.PassWord);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Doctor getDoctorByCredentials(String username, String password) {
        String sql = "SELECT * FROM doctors WHERE full_name = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Doctor(
                    rs.getString("full_name"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("specialization"),
                    rs.getString("phone_number")
                );
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Secretary getSecretaryByCredentials(String username, String password) {
        String sql = "SELECT * FROM secretaries WHERE full_name = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Secretary(
                    rs.getString("full_name"),
                    rs.getString("password")
                );
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
} 