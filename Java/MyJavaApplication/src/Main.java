import java.sql.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        Customer customer = new Customer();
        int choose=0;
        Scanner input=new Scanner(System.in);
        try {
            do {
                System.out.println("Menu choose :");
                System.out.println("1. Create new customer");
                System.out.println("2. Show all customer");
                System.out.println("3. Update customer");
                System.out.println("4. Delete customer");
                System.out.print("Enter your choice: ");

                choose=input.nextInt();
                switch (choose){
                    case 1:{
                        Customer cus = new Customer();
                        cus.inputData();
                        createCustomers(cus);
                    }
                    break;
                    case 2:{
                        getAllCustomer();
                    }
                    break;
                    case 3:{
                        System.out.println("Enter customer id to update:");
                        int id = input.nextInt();
                        updateCustomers(id);
                    }
                    break;
                    case 4: {
                        System.out.println("Enter customer id to delete:");
                        int id = input.nextInt();
                        deleteCustomers(id);
                    }
                    break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }while (true);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public  static void getAllCustomer() throws SQLException {
        //Goi DT connection tu lop khac
        Connection connection = MySQLConnectionDB.getMySQLConnection();
        //Tao Statement de thuc thi truy van
        Statement stm = connection.createStatement();
        //Cau lenh truy van csdl(sql engine)
        String query = "select * from customers";
        //Thuc thi truy van, ket qua tra ve ResultSet
        ResultSet rs = stm.executeQuery(query);
        //Doc ban ghi cho den het

        while (rs.next()) {
            System.out.println("==========================");
            System.out.println("Customer ID: " + rs.getInt(1));
            System.out.println("First Name: " + rs.getString(2));
            System.out.println("Last Name: " + rs.getString(3));
            System.out.println("email: " + rs.getString(4));
        }
        connection.close();
    }

    public static void createCustomers(Customer customer) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = MySQLConnectionDB.getMySQLConnection();
            String query = "INSERT INTO customers(customer_id, first_name, last_name, email) VALUES (?,?,?,?)";
            pstmt = connection.prepareStatement(query);

            // Thiết lập các tham số cho preparedStatement từ đối tượng Customer
            pstmt.setInt(1, customer.getId());
            pstmt.setString(2, customer.getFirstName());
            pstmt.setString(3, customer.getLastName());
            pstmt.setString(4, customer.getEmail());

            // Thực thi câu lệnh SQL
            pstmt.executeUpdate(); // Thực thi INSERT

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối sau khi hoàn thành
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void updateCustomers(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {

            Customer updateCus = Main.findCustomerById(id);
            if (updateCus != null) {
                connection = MySQLConnectionDB.getMySQLConnection();
                pstmt = connection.prepareStatement(
                        "UPDATE Customers SET first_name = ?, last_name = ?, email = ? WHERE id = ?"
                );
                pstmt.setString(1, updateCus.getFirstName());
                pstmt.setString(2, updateCus.getLastName());
                pstmt.setString(3, updateCus.getEmail());
                pstmt.setInt(4, updateCus.getId());

                int updated = pstmt.executeUpdate();
                if (updated > 0) {
                    System.out.println("Update successfully !!!!!!");
                }
                pstmt.close();
                connection.close();

            } else {
                System.out.println("Customer not found !!!!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomers(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = MySQLConnectionDB.getMySQLConnection();
            String query = "DELETE FROM customers WHERE id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            int deleted = pstmt.executeUpdate(query);
            if (deleted > 0) {
                System.out.println("Delete successfully !!!!!!");
            } else {
                System.out.println("Customer not found !!!!!!");
            }
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }

    }

    public static Customer findCustomerById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = pstmt.executeQuery();
        Customer customer = null;

        try {
            connection = MySQLConnectionDB.getMySQLConnection();
            String query = "select * from customers where customer_id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            while (rs.next()) {
                customer = new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}