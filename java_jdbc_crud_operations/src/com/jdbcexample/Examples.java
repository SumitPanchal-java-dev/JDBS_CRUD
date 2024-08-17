package com.jdbcexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Examples {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        // Load the MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a connection to the database
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/example", "root", "sumit@123");

        // Queries for CRUD operations
        String createQuery = "INSERT INTO Employees (name, position, salary) VALUES (?, ?, ?)";
        String readQuery = "SELECT * FROM Employees";
        String updateQuery = "UPDATE Employees SET position = ?, salary = ? WHERE id = ?";
        String deleteQuery = "DELETE FROM Employees WHERE id = ?";
        
        // 1. Create Operation
        PreparedStatement createStmt = con.prepareStatement(createQuery);
        createStmt.setString(1, "Smith");
        createStmt.setString(2, "Python Developer");
        createStmt.setFloat(3, 50000.90f);
        int createResult = createStmt.executeUpdate();
        if (createResult > 0) {
            System.out.println("Record Inserted Successfully");
        } else {
            System.out.println("Insertion Failed");
        }

        // 2. Read Operation
        PreparedStatement readStmt = con.prepareStatement(readQuery);
        ResultSet rs = readStmt.executeQuery();
        System.out.println("Employees:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                    ", Position: " + rs.getString("position") + ", Salary: " + rs.getFloat("salary"));
            
            
        }

        // 3. Update Operation
        PreparedStatement updateStmt = con.prepareStatement(updateQuery);
        updateStmt.setString(1, "Senior Java Developer");
        updateStmt.setFloat(2, 6000.50f);
        updateStmt.setInt(3, 1); // Assuming the ID of the record to update is 1
        int updateResult = updateStmt.executeUpdate();
        if (updateResult > 0) {
            System.out.println("Record Updated Successfully");
        } else {
            System.out.println("Update Failed");
        }

        // 4. Delete Operation
        PreparedStatement deleteStmt = con.prepareStatement(deleteQuery);
        deleteStmt.setInt(1, 1); // Assuming the ID of the record to delete is 1
        int deleteResult = deleteStmt.executeUpdate();
        if (deleteResult > 0) {
            System.out.println("Record Deleted Successfully");
        } else {
            System.out.println("Deletion Failed");
        }

        // Close all statements and the connection
        createStmt.close();
        readStmt.close();
        updateStmt.close();
        deleteStmt.close();
        con.close();
    }
}
