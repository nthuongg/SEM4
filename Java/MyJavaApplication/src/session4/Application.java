package session4;

import session4.view.LoginConsoleUI;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args)  throws SQLException{
        LoginConsoleUI loginConsoleUI = new LoginConsoleUI();
        loginConsoleUI.start();
    }
}
