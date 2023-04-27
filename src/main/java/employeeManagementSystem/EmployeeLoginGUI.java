package employeeManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeLoginGUI {
    private final JFrame frame;
    private final JTextField txtUsername;
    private final JPasswordField txtPassword;

    public EmployeeLoginGUI() {
        frame = new JFrame("Employee Login");
        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Login");
        JButton btnCancel = new JButton("Cancel");

        // Set layout manager
        frame.setLayout(new GridLayout(3, 2));

        // Add components to frame
        frame.add(lblUsername);
        frame.add(txtUsername);
        frame.add(lblPassword);
        frame.add(txtPassword);
        frame.add(btnLogin);
        frame.add(btnCancel);

        // Add action listeners
        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            // Call the login() method to check for login success
            boolean loginSuccess = login(username, password);

            if (loginSuccess) {
                // Display login success message
                JOptionPane.showMessageDialog(frame, "Login Successful!");
            } else {
                // Display login failure message
                JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
            }
        });

        btnCancel.addActionListener(e -> System.exit(0));

        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Login authentication logic
    // compares username and password with hardcoded values
    protected boolean login(String username, String password) {
        return username.equals("employee") && password.equals("password123");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeLoginGUI();
            }
        });
    }

    protected void showLogin() {
    }

    protected boolean isLoggedIn() {
        return false;
    }
}
