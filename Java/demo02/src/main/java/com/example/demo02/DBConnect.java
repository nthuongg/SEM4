package com.example.demo02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static Connection getMySQLConnection() throws SQLException {
        Connection conn = null;
        String hostName = "localhost"; //127.0.0.1
        String dbName = "account";
        String userName = "root";
        String password = "";
        //Connection String: chuỗi kết nối( thông tin) đến csdl
        //String connURL = "jdbc:mysql://localhost:3306/account";
        String connURL = "jdbc:mysql://"+hostName+":3306/"+dbName;
        //conn = DriverManager.getConnection(connURL, "root", "");
        conn = DriverManager.getConnection(connURL, userName, password);
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        if(getMySQLConnection() != null) {
            System.out.println("Kết nối thành công!");
        }
    }
}
