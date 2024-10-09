package com.Library;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class LoginUpdateUser extends JDialog implements ActionListener {

    JLabel lblUser, lblPass,lblAdm;
    JPasswordField txtPass;

    public LoginUpdateUser() {
        setTitle("LOGIN");
        setSize(450, 340);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Use BoxLayout with Y_AXIS orientation
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        lblAdm = new JLabel("USER");
        lblAdm .setAlignmentX(Component.CENTER_ALIGNMENT); // Center the component
        lblAdm.setFont(new Font("Arial", Font.BOLD, 20));
        add( lblAdm );

        lblUser = new JLabel(new ImageIcon(scaleOurImage("src\\com\\Library\\OIP.jpeg")));
        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the component
        add(lblUser);

        txtPass = new JPasswordField();
        txtPass.setEchoChar('*');
        txtPass.setFont(new Font("Arial", Font.BOLD, 70));
        txtPass.setBorder(new EmptyBorder(0, 20, 0, 20));
        txtPass.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the component
        txtPass.add(Box.createVerticalStrut(5));
        add(txtPass);

        lblPass = new JLabel("ENTER YOUR FOUR DIGIT PIN");
        lblPass.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the component
        lblPass.add(Box.createVerticalStrut(5));
        lblPass.setHorizontalAlignment(JLabel.CENTER);
        lblPass.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblPass);

        // Add a DocumentListener to the JPasswordField
        txtPass.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkPIN();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkPIN();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Plain text components don't fire these events
            }
        });

        setVisible(true);
    }

    private void checkPIN() {
        char[] enteredChars = txtPass.getPassword();

        // Check if the PIN length is 4
        if (enteredChars.length == 4) {
            // Check if all characters are digits
            if (areAllDigits(enteredChars)) {
                // Trigger login action
                login();
            } else {
                // Clear the password field if not all characters are digits

                JOptionPane.showMessageDialog(this, "Please enter a valid 4-digit PIN.", "Invalid PIN", JOptionPane.ERROR_MESSAGE);
                txtPass.setText("");
            }
        }
    }

    private boolean areAllDigits(char[] chars) {
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private void login() {
        // Perform login action, e.g., check against stored password in the database
        String enteredPIN = new String(txtPass.getPassword());

        // Add your database check logic here
        String url = "jdbc:mysql://localhost:3306/library";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM users";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                boolean loggedIn = false;

                while (resultSet.next()) {
                    // Check the entered password against each stored password
                    String storedPassword = resultSet.getString("password");
                    if (enteredPIN.equals(storedPassword)) {
                        // Passwords match, login successful
                        String welcomeMessage = "Welcome, " + resultSet.getString("username") + "!";
                       UserManagement user = new UserManagement();
                       user.setVisible(true);
                        dispose();
                        loggedIn = true;
                        break; // Exit the loop if login is successful
                    }
                }

                if (!loggedIn) {
                    txtPass.setText("");
                    JOptionPane.showMessageDialog(this, "Invalid PIN. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    txtPass.setText("");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Could not connect to the database.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent arg0) {
        // Action event handling
    }

    public static void main(String[] args) {
        new LoginUpdateUser();
    }

    private Image scaleOurImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH);
        return image;
    }
}
