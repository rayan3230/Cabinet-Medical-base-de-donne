package database;

import Cabinet.Personnels.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    public void addPatient(Client patient) {
        String sql = "INSERT INTO patients (full_name, phone_number) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, patient.FullName);
            pstmt.setString(2, patient.PhoneNum);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getAllPatients() {
        List<Client> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Client patient = new Client(
                    rs.getString("full_name"),
                    rs.getString("phone_number")
                );
                patients.add(patient);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return patients;
    }
} 