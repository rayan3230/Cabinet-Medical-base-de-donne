package database;

import Cabinet.Management.PatientSheet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientSheetDAO {
    public void addPatientSheet(PatientSheet sheet) {
        String sql = "INSERT INTO patient_sheets (patient_id, weight, height, medical_observations, surgical_observations) VALUES ((SELECT id FROM patients WHERE full_name = ?), ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, sheet.FullName);
            pstmt.setInt(2, sheet.Weight);
            pstmt.setInt(3, sheet.Height);
            pstmt.setString(4, sheet.MedicalObservations);
            pstmt.setString(5, sheet.SurgicalObservations);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PatientSheet> getAllPatientSheets() {
        List<PatientSheet> sheets = new ArrayList<>();
        String sql = "SELECT ps.*, p.full_name, p.phone_number FROM patient_sheets ps JOIN patients p ON ps.patient_id = p.id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                PatientSheet sheet = new PatientSheet(
                    rs.getString("full_name"),
                    rs.getString("phone_number"),
                    rs.getString("medical_observations"),
                    rs.getString("surgical_observations"),
                    rs.getInt("weight"),
                    rs.getInt("height")
                );
                sheets.add(sheet);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return sheets;
    }
} 