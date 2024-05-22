package com.example.model;

import com.example.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {
    public void addEmployee(Employee employee) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnect.getMySQLConnection();
            String sql = "INSERT INTO Employees (FullName, Birthday, Address, Position, Department) VALUES (?, ?, ?, ?, ?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, employee.getFullName());
            pstmt.setDate(2, employee.getBirthday());
            pstmt.setString(3, employee.getAddress());
            pstmt.setString(4, employee.getPosition());
            pstmt.setString(5, employee.getDepartment());
            pstmt.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            try {
                if ( pstmt != null ) pstmt.close();
                if (conn != null ) conn.close();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }

    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.getMySQLConnection();
            String sql = "SELECT * FROM Employees";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while ( rs.next() ) {
                int id = rs.getInt("Id");
                String fullName = rs.getString("FullName");
                Date birthday = rs.getDate("Birthday");
                String address = rs.getString("Address");
                String position = rs.getString("Position");
                String department = rs.getString("Department");

                Employee employee = new Employee(fullName, birthday, address, position, department);
                employee.setId(id);
                employees.add(employee);
            }
        } catch ( SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if ( rs != null ) rs.close();
                if ( pstmt != null ) pstmt.close();
                if (conn != null ) conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employees;
    }
}