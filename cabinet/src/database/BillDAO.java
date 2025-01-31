package database;

import Cabinet.Management.Bill;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    public void addBill(Bill bill) {
        String sql = "INSERT INTO bills (patient_id, appointment_id, amount, hours_spent) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            int patientId = getPatientIdByName(bill.PatientName);
            
            pstmt.setInt(1, patientId);
            pstmt.setInt(2, bill.AppId);
            pstmt.setDouble(3, bill.Price);
            pstmt.setDouble(4, bill.hoursSpent);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getPatientIdByName(String patientName) throws SQLException {
        String sql = "SELECT id FROM patients WHERE full_name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, patientName);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1;
    }

    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT b.*, p.full_name as patient_name FROM bills b " +
                    "JOIN patients p ON b.patient_id = p.id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Bill bill = new Bill(
                    rs.getString("patient_name"),
                    rs.getInt("appointment_id"),
                    rs.getDouble("hours_spent")
                );
                bill.Price = rs.getDouble("amount");
                bills.add(bill);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bills;
    }
} 