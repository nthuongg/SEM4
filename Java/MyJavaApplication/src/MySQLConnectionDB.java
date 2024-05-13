import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionDB {
    public static Connection getMySQLConnection() throws SQLException {
        Connection conn = null;
        String hostName = "localhost"; //127.0.0.1
        String dbName = "fptaptech2024";
        String userName = "root";
        String password = "";
        //Connection String: chuỗi kết nối( thông tin) đến csdl
        //String connURL = "jdbc:mysql://localhost:3306/fptaptech2024";
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
