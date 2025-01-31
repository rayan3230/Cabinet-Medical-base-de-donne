package database;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/medical_cabinet";
    private static final String USER = "root";
    private static final String PASSWORD = "rayan-555";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to database", e);
        }
    }

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306",
                USER, PASSWORD)) {
            
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS medical_cabinet");
            stmt.executeUpdate("USE medical_cabinet");
            
            // Create all tables from your .txt file
            String[] tableCreationQueries = {
                "CREATE TABLE IF NOT EXISTS doctors (id INT PRIMARY KEY AUTO_INCREMENT, full_name VARCHAR(100) NOT NULL, specialization VARCHAR(100), email VARCHAR(100), phone_number VARCHAR(20), password VARCHAR(100) NOT NULL)",
                "CREATE TABLE IF NOT EXISTS patients (id INT PRIMARY KEY AUTO_INCREMENT, full_name VARCHAR(100) NOT NULL, phone_number VARCHAR(20), password VARCHAR(100))",
                "CREATE TABLE IF NOT EXISTS appointments (id INT PRIMARY KEY AUTO_INCREMENT, patient_id INT, doctor_id INT, appointment_date DATETIME, status VARCHAR(20), FOREIGN KEY (patient_id) REFERENCES patients(id), FOREIGN KEY (doctor_id) REFERENCES doctors(id))",
                "CREATE TABLE IF NOT EXISTS medicines (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL, quantity INT, price DECIMAL(10,2), times_per_day INT)",
                "CREATE TABLE IF NOT EXISTS patient_sheets (id INT PRIMARY KEY AUTO_INCREMENT, patient_id INT, weight INT, height INT, medical_observations TEXT, surgical_observations TEXT, creation_date DATETIME DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (patient_id) REFERENCES patients(id))",
                "CREATE TABLE IF NOT EXISTS bills (id INT PRIMARY KEY AUTO_INCREMENT, patient_id INT, appointment_id INT, amount DECIMAL(10,2), bill_date DATETIME DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (patient_id) REFERENCES patients(id), FOREIGN KEY (appointment_id) REFERENCES appointments(id))"
            };
            
            for (String query : tableCreationQueries) {
                stmt.executeUpdate(query);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 