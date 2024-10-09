package com.Library;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

//import org.mindrot.jbcrypt.BCrypt;

public class RegisterUser extends JDialog implements ActionListener {

    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    Connection con;
    PreparedStatement preparedStatement;
    String url = "jdbc:mysql://localhost:3306/library";

    public RegisterUser() {
        setTitle("User Registration");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Initialize components
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);

        // Set layout
        setLayout(new GridLayout(3, 2, 5, 5));

        // Add components to the frame
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Empty label for spacing
        add(registerButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            registerUser();
        }
    }

    private void registerUser() {
        try {
            con = DriverManager.getConnection(url, "root", "");

            // Use a PreparedStatement to prevent SQL injection
            String query = "INSERT INTO users(username, password) VALUES (?, ?)";
            preparedStatement = con.prepareStatement(query);

            // Set parameters
            preparedStatement.setString(1, usernameField.getText());

            // Hash the password using BCrypt
          //  String hashedPassword = BCrypt.hashpw(new String(passwordField.getPassword()), BCrypt.gensalt());
            preparedStatement.setString(2,passwordField.getText());

            // Execute the update
            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                JOptionPane.showMessageDialog(this, "User registered successfully!");
                dispose(); // Close the registration window after successful registration
            } else {
                JOptionPane.showMessageDialog(this, "Error: Registration failed.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close resources in a finally block
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterUser());
    }
}
