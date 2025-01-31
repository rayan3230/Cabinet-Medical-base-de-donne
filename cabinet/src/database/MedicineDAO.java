package database;

import Cabinet.Management.Medicines;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO {
    public void addMedicine(Medicines medicine) {
        String sql = "INSERT INTO medicines (name, quantity, price, times_per_day) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, medicine.Name);
            pstmt.setInt(2, medicine.Quantity);
            pstmt.setDouble(3, medicine.Price);
            pstmt.setInt(4, medicine.TimesPerDay);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Medicines> getAllMedicines() {
        List<Medicines> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicines";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Medicines medicine = new Medicines(
                    rs.getString("name"),
                    rs.getInt("quantity"),
                    rs.getInt("price"),
                    rs.getInt("times_per_day")
                );
                medicines.add(medicine);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return medicines;
    }
} 