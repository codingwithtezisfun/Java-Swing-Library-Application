package com.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManagement extends JDialog {

    private JTextField userIdField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public UserManagement() {


        setLayout(new GridLayout(7, 2, 10, 10)); // 6 rows, 2 columns, with gaps

        add(new JLabel("Enter User ID:"));
        userIdField = new JTextField();
        add(userIdField);
        add(new JLabel());

        JButton fetchButton = new JButton("Fetch");
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchUserData();
            }
        });
        add(fetchButton);

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        add(confirmPasswordField);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });
        add(updateButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        add(cancelButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void fetchUserData() {
        int userId = Integer.parseInt(userIdField.getText());
        // Fetch user data from the database
        UserData userData = fetchUserDataFromDatabase(userId);

        if (userData != null) {
            usernameField.setText(userData.getUsername());
            passwordField.setText(userData.getPassword());
            confirmPasswordField.setText(userData.getPassword());
        } else {
            JOptionPane.showMessageDialog(this, "No user found for the given User ID.");
        }
    }

    private void updateUser() {
        int userId = Integer.parseInt(userIdField.getText());
        String newUsername = usernameField.getText();
        char[] newPassword = passwordField.getPassword();
        char[] confirmPassword = confirmPasswordField.getPassword();

        if (validatePassword(newPassword, confirmPassword)) {
            // Update user data in the database
            updateUserDataInDatabase(userId, newUsername, newPassword);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Passwords do not match. Please enter matching passwords.");
        }
    }

    private UserData fetchUserDataFromDatabase(int userId) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "")) {
            String query = "SELECT username, password FROM users WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    return new UserData(username, password);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updateUserDataInDatabase(int userId, String newUsername, char[] newPassword) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "")) {
            String query = "UPDATE users SET username = ?, password = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newUsername);
                preparedStatement.setString(2, new String(newPassword));
                preparedStatement.setInt(3, userId);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Password updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "No user found for the given User ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean validatePassword(char[] newPassword, char[] confirmPassword) {
        return new String(newPassword).equals(new String(confirmPassword));
    }

    private void clearFields() {
        userIdField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    private static class UserData {
        private String username;
        private String password;

        public UserData(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserManagement());
    }
}
