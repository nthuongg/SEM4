package com.example.demo02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginBean {
    public boolean checkLogin(String usr, String pwd) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs= null;

        try {
            // Kết nối đến cơ sở dữ liệu
            conn = DBConnect.getMySQLConnection();
            String query = "SELECT * from accounts WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, usr);
            stmt.setString(2, pwd);
            rs = stmt.executeQuery();
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if(rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
