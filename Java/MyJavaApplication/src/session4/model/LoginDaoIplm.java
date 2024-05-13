package session4.model;

import session4.entity.Users;

import java.sql.*;

public class LoginDaoIplm implements LoginDAO {
    private static final Connection conn;

    static {
        try {
            conn = MySQLConnectionDB.getMySQLConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Statement stmt = null;
    private PreparedStatement pstmt = null;

    @Override
    public String checkLoginStatement(Users user) throws SQLException {
        String username = user.getUsername();
        String password = user.getPassword();
        // query
        String query = "SELECT * FROM users WHERE username like '"+username+"' AND password like '"+password+"'";
        //Tạo statement mỗi lần thực thi
        stmt = conn.createStatement();
        // Tạo đối tượng ResultSet để nhận kết quả từ db trả về
        ResultSet rs = stmt.executeQuery(query);
        // duyệt trong toàn bộ bản ghi trong csdl
        while (rs.next()) {
            System.out.println("Username is: "+ rs.getString("username"));
            return rs.getString("username");
        }
        return "Not in the database";
    }

    @Override
    public String checkLoginPreparedStatement(Users user) throws SQLException {
        //Query
        String query = "SELECT username from users WHERE username like ? AND password like ?";
        //Gọi prepareStatement
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        //Tạo resultSet để lưu dữ liệu
        ResultSet rs = pstmt.executeQuery();
        //Duyệt trong toàn b data lưu trong ResultSet
        while (rs.next()) {
            System.out.println("Username is: "+ rs.getString("username"));
            return rs.getString("username");
        }
        return "Not in the database";
    }
}
