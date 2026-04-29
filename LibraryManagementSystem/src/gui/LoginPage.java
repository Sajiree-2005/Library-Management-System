package gui;

import service.LibraryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {

    JTextField userField;
    JPasswordField passField;
    JButton loginBtn;

    LibraryService service = new LibraryService();

    public LoginPage() {

        setTitle("Library Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        userField = new JTextField();
        passField = new JPasswordField();
        loginBtn = new JButton("Login");

        panel.add(new JLabel("Username"));
        panel.add(userField);
        panel.add(new JLabel("Password"));
        panel.add(passField);
        panel.add(loginBtn);

        add(panel);

        loginBtn.addActionListener(e -> login());

        setVisible(true);
    }

    void login() {
        try {
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (service.login(user, pass)) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                new Dashboard();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}