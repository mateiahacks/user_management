package jdbc_learning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GUI {
    private static JPanel panel;
    private static JFrame frame;
    private static JButton loginButton, registerButton, login, register, back;
    private static JLabel repeatPasswordLabel, registerPasswordLabel, registerUsernameLabel, usernameLabel, passwordLabel, matchErrorLabel, registered, loggedIn, userNameError, loginError;
    private static JTextField usernameField, registerUsernameField;
    private static JPasswordField passwordField, registerPasswordField, repeatPasswordField;
    private static String url = "jdbc:mysql://localhost:3306/Users";
    private static String uname = "root";
    private static String password = "%iOgWA2=";

    public static void main(String[] args) {
        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.setTitle("User management");

        // login or register
        loginButton = new JButton("Login");
        loginButton.setBounds(165, 150, 150, 50);
        loginButton.addActionListener(new loginListener());
        panel.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(165, 250, 150, 50);
        registerButton.addActionListener(new registerListener());
        panel.add(registerButton);

        // login
        //username
        usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        usernameLabel.setBounds(80, 100, 200, 150);
        usernameLabel.setVisible(false);
        panel.add(usernameLabel);

        // password
        passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        passwordLabel.setBounds(80, 180, 200, 150);
        passwordLabel.setVisible(false);
        panel.add(passwordLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(180, 167, 150, 25);
        usernameField.setVisible(false);
        panel.add(usernameField);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(180, 245, 150, 25);
        passwordField.setVisible(false);
        panel.add(passwordField);

        login = new JButton("Login");
        login.setBounds(180, 320, 150, 40);
        login.setVisible(false);
        login.addActionListener(new Login());
        panel.add(login);


        //register interface
        registerUsernameLabel = new JLabel("Username: ");
        registerUsernameLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        registerUsernameLabel.setBounds(80, 80, 200, 150);
        registerUsernameLabel.setVisible(false);
        panel.add(registerUsernameLabel);

        registerPasswordLabel = new JLabel("Password: ");
        registerPasswordLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        registerPasswordLabel.setBounds(80, 160, 200, 150);
        registerPasswordLabel.setVisible(false);
        panel.add(registerPasswordLabel);

        repeatPasswordLabel = new JLabel("Repeat pass: ");
        repeatPasswordLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        repeatPasswordLabel.setBounds(80, 240, 200, 150);
        repeatPasswordLabel.setVisible(false);
        panel.add(repeatPasswordLabel);

        registerUsernameField = new JTextField(20);
        registerUsernameField.setBounds(180, 147, 150, 25);
        registerUsernameField.setVisible(false);
        panel.add(registerUsernameField);

        registerPasswordField = new JPasswordField(20);
        registerPasswordField.setBounds(180, 225, 150, 25);
        registerPasswordField.setVisible(false);
        panel.add(registerPasswordField);

        repeatPasswordField = new JPasswordField(20);
        repeatPasswordField.setBounds(180, 305, 150, 25);
        repeatPasswordField.setVisible(false);
        panel.add(repeatPasswordField);

        register = new JButton("Register");
        register.setBounds(180, 380, 150, 40);
        register.setVisible(false);
        register.addActionListener(new Register());
        panel.add(register);

        back = new JButton("<=  Back");
        back.setBounds(20, 20, 120, 40);
        back.setVisible(false);
        back.addActionListener(new backListener());
        panel.add(back);


        //errors

        //match password error
        matchErrorLabel = new JLabel("Passwords don't match!");
        matchErrorLabel.setBounds(180, 330, 150,25);
        matchErrorLabel.setForeground(Color.RED);
        matchErrorLabel.setVisible(false);
        panel.add(matchErrorLabel);

        // username used error
        userNameError = new JLabel("Try different username!");
        userNameError.setBounds(180, 170, 150, 25);
        userNameError.setForeground(Color.RED);
        userNameError.setVisible(false);
        panel.add(userNameError);

        // login error
        loginError = new JLabel("Incorrect username or password!");
        loginError.setBounds(180, 270, 200, 25);
        loginError.setForeground(Color.RED);
        loginError.setVisible(false);
        panel.add(loginError);

        //success register
        registered = new JLabel("Successfully registered!");
        registered.setBounds(180, 230, 150, 30);
        registered.setVisible(false);
        panel.add(registered);

        //success login
        loggedIn = new JLabel("Successfully logged in!");
        loggedIn.setBounds(180, 230, 150, 30);
        loggedIn.setVisible(false);
        panel.add(loggedIn);

        panel.setLayout(null);
        frame.setVisible(true);


    }

    public static void turnVisibleRegister(boolean b) {
        register.setVisible(b);
        registerUsernameLabel.setVisible(b);
        registerPasswordLabel.setVisible(b);
        registerUsernameField.setVisible(b);
        registerPasswordField.setVisible(b);
        repeatPasswordLabel.setVisible(b);
        repeatPasswordField.setVisible(b);
    }

    public static void turnVisibleLogin(boolean b) {
        login.setVisible(b);
        usernameLabel.setVisible(b);
        passwordLabel.setVisible(b);
        usernameField.setVisible(b);
        passwordField.setVisible(b);
    }

    static class backListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            back.setVisible(false);
            turnVisibleRegister(false);
            turnVisibleLogin(false);
            registerButton.setVisible(true);
            loginButton.setVisible(true);
            registered.setVisible(false);
            loggedIn.setVisible(false);
            loginError.setVisible(false);
            matchErrorLabel.setVisible(false);
        }
    }

    static class loginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            back.setVisible(true);
            loginButton.setVisible(false);
            registerButton.setVisible(false);
            turnVisibleLogin(true);
        }
    }

    static class registerListener implements ActionListener {

        @Override

        public void actionPerformed(ActionEvent e) {
            back.setVisible(true);
            loginButton.setVisible(false);
            registerButton.setVisible(false);
            turnVisibleRegister(true);
        }
    }

    static class Login implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String uName = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            String query = "select userName from user where user.userName=" + " '" + uName + "' and user.userPassword=" + "'" + pass + "'";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            try {
                Connection con = DriverManager.getConnection(url, uname, password);
                Statement stm = con.createStatement();
                ResultSet res = stm.executeQuery(query);
                if(!res.next()) {
                    loginError.setVisible(true);
                    return;
                }
                loginError.setVisible(false);
                loggedIn.setVisible(true);
                turnVisibleLogin(false);


            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        }
    }

    static class Register implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            matchErrorLabel.setVisible(false);
            String uName = registerUsernameField.getText();
            String pass = new String(registerPasswordField.getPassword());
            String repeatPass = new String(repeatPasswordField.getPassword());
            if(pass.equals(repeatPass)) {
                String mainQuery = "insert into user(userName, userPassword) values(" + "'" + uName + "', " + "'" + pass +"'" + ")";
                String checkNameQuery = "select userName from user where user.userName=" + " '" + uName + "'";
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                try {
                    Connection con = DriverManager.getConnection(url, uname, password);
                    Statement statement1 = con.createStatement();
                    Statement statement2 = con.createStatement();
                    ResultSet result = statement2.executeQuery(checkNameQuery);
                    if (result.next()) {
                        userNameError.setVisible(true);
                        return;
                    }
                    statement1.executeUpdate(mainQuery);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                userNameError.setVisible(false);
                turnVisibleRegister(false);
                registered.setVisible(true);

            } else {
                matchErrorLabel.setVisible(true);
            }
        }
    }
}
