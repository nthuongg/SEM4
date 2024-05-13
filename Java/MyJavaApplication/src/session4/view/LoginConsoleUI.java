package session4.view;

import session4.controller.LoginController;
import session4.entity.Users;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginConsoleUI {
    private final Scanner sc;

    public LoginConsoleUI() {
        this.sc = new Scanner(System.in);
    }
    // Step 1. Call Controller
    LoginController loginController = new LoginController();
    Users user = new Users();
    private int menu() {
        System.out.println("============Admin=============");
        System.out.println("1. Login Statement");
        System.out.println("2. Login PreparedStatement");
        System.out.println("0. Exit");

        int choice = Integer.parseInt(sc.nextLine());
        return choice;
    }
    public void loginStatementUi() throws SQLException {
        // input data from user
        System.out.println("Enter username:");
        String username = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();
        user.setUsername(username);
        user.setPassword(password);
        //Step 1. Call Controller
        String result = loginController.loginStatementController(user);
        // Step 5
        System.out.println(result);
    }

    public void loginPreparedStatementUi() throws SQLException {
        // input data from user
        System.out.println("Enter username:");
        String username = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();
        user.setUsername(username);
        user.setPassword(password);
        //Step 1. Call Controller
        String result = loginController.loginPreparedStatement(user);
        // Step 5
        System.out.println(result);
    }

    public void start() throws SQLException {
        while (true) {
            int choice = menu();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1: loginStatementUi(); break;
                case 2: loginPreparedStatementUi(); break;
                default: throw  new AssertionError();
            }
        }
    }

}
