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
        System.out.println("Tên người dùng: " + usr);
        System.out.println("Mật khẩu: " + pwd);

        try {
            conn = DBConnect.getMySQLConnection();
            if (conn == null) {
                System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
                return false;
            }

            String query = "SELECT * FROM accounts WHERE username like ? AND password like ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, usr);
            stmt.setString(2, pwd);

            System.out.println("Thực thi câu lệnh: " + query);
            System.out.println("Tên người dùng: " + usr);
            System.out.println("Mật khẩu: " + pwd);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                System.out.println("Đăng nhập không hợp lệ");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void main(String[] args) throws SQLException {
        LoginBean bean = new LoginBean();

        // Thay đổi giá trị username và password thành giá trị thực tế
        String username = "phuczoo";  // Đảm bảo giá trị này không phải null
        String password = "122456789";  // Đảm bảo giá trị này không phải null

        boolean loginStatus = bean.checkLogin(username, password);

        if (loginStatus) {
            System.out.println("Đăng nhập thành công!");
        } else {
            System.out.println("Tên người dùng hoặc mật khẩu không đúng.");
        }
    }
}
