package com.Library;

import com.sun.awt.AWTUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;

public class RegisterPatron {
    JTextField FnameField,LnameField, address, phoneField, emailField;
    JLabel FnameLabel,LnameLabel, addressLabel, phoneLabel, emailLabel,Department;
    JButton registerButton, cancelButton;
    JComboBox<String> DepartmentComboBox;
    int mouseX, mouseY;
    JFrame frame;

    public RegisterPatron() {
        frame = new JFrame("");
        frame.setUndecorated(true);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


        int arcWidth = 20;
        int arcHeight = 20;
        RoundRectangle2D shape = new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), arcWidth, arcHeight);
        AWTUtilities.setWindowShape(frame, shape);

        JPanel upper = new JPanel(new GridBagLayout());
        frame.add(upper, BorderLayout.NORTH);

        GridBagConstraints gb = new GridBagConstraints();
        gb.insets = new Insets(25, 5, 0, 5);
        upper.setBackground(new java.awt.Color(79, 102, 102));

        JLabel label = new JLabel("<html><u>PATRON REGISTRATION FORM</u></html>");
        label.setFont(new Font("Arial", Font.PLAIN, 25));
        label.setForeground(new Color(186, 135, 89));
        gb.gridx = 0;
        gb.gridy = 0;
        upper.add(label, gb);

        JPanel down = new JPanel(new GridBagLayout());
        frame.add(down, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        down.setBackground(new java.awt.Color(79, 102, 102));

        FnameLabel = new JLabel("FIRST NAME:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        FnameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        FnameLabel.setForeground(new Color(74, 93, 35));
        down.add(FnameLabel, gbc);

        FnameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        FnameField.setFont(new Font("Arial", Font.PLAIN, 20));
        FnameField.setForeground(new Color(225, 169, 95));
        down.add(FnameField, gbc);

        LnameLabel = new JLabel("LAST NAME:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        LnameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        LnameLabel.setForeground(new Color(74, 93, 35));
        down.add(LnameLabel, gbc);

        LnameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        LnameField.setFont(new Font("Arial", Font.PLAIN, 20));
        LnameField.setForeground(new Color(225, 169, 95));
        down.add(LnameField, gbc);

        phoneLabel = new JLabel("PHONE NO:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        phoneLabel.setForeground(new Color(74, 93, 35));
        down.add(phoneLabel, gbc);

        phoneField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        phoneField.setFont(new Font("Arial", Font.PLAIN, 20));
        phoneField.setForeground(new Color(225, 169, 95));
        down.add(phoneField, gbc);

        emailLabel = new JLabel("EMAIL:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        emailLabel.setForeground(new Color(74, 93, 35));
        down.add(emailLabel, gbc);

        emailField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        emailField.setForeground(new Color(225, 169, 95));
        down.add(emailField, gbc);

        Department = new JLabel("DEPARTMENT:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        Department.setFont(new Font("Arial", Font.PLAIN, 20));
        Department.setForeground(new Color(74, 93, 35));
        down.add(Department, gbc);

        DepartmentComboBox = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 4;
        DepartmentComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        DepartmentComboBox.setForeground(new Color(225, 169, 95));
        String[] departments = {"Counter Service", "Shelf Management"};
        for (String department : departments) {
            DepartmentComboBox.addItem(department);
        }
        down.add(DepartmentComboBox, gbc);

        addressLabel= new JLabel("ADDRESS:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        addressLabel.setForeground(new Color(74, 93, 35));
        down.add(addressLabel, gbc);

        address = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 5;
        address.setFont(new Font("Arial", Font.PLAIN, 20));
        address.setForeground(new Color(225, 169, 95));
        down.add(address, gbc);

// Add courses to the combo box

        registerButton = new JButton("REGISTER");
        gbc.gridx = 0;
        gbc.gridy = 6;
        registerButton.setFont(new Font("Arial", Font.PLAIN, 20));
        registerButton.setForeground(new Color(150, 113, 23));
        registerButton.setBackground(new Color(224, 254, 200));
        down.add(registerButton, gbc);

        cancelButton = new JButton("CANCEL");
        gbc.gridx = 1;
        gbc.gridy = 6;
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));
        cancelButton.setForeground(new Color(150, 113, 23));
        cancelButton.setBackground(new Color(224, 254, 200));
        down.add(cancelButton, gbc);

        frame.setVisible(true);

        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        frame.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - mouseX;
                int y = e.getYOnScreen() - mouseY;
                frame.setLocation(x, y);
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateAllFields() && validatePhoneNumber(phoneField.getText())) {
                    String message = "DETAILS VERIFICATION";

                    int choice;
                    choice = JOptionPane.showConfirmDialog(frame, message, "REGISTRATION CONFIRMATION", JOptionPane.OK_CANCEL_OPTION);

                    if (choice == JOptionPane.OK_OPTION) {
                        savePatronToDatabase();

                        clearTextFields();
                    }
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
            }
        });
    }
    private void savePatronToDatabase() {
        String url = "jdbc:mysql://localhost:3306/library";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO patron (first_name, last_name, phone_no, email_address, adress, department_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, FnameField.getText());
            preparedStatement.setString(2, LnameField.getText());
            preparedStatement.setString(3, phoneField.getText());
            preparedStatement.setString(4, emailField.getText());
            preparedStatement.setString(5, address.getText());

            // Assuming you have a method to get the department id based on the selected department name
            int departmentId = getDepartmentIdFromDatabase(connection, (String) DepartmentComboBox.getSelectedItem());
            preparedStatement.setInt(6, departmentId);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Success:patron data saved to the database.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Could not save patron data to the database.");
        }
    }

    private int getDepartmentIdFromDatabase(Connection connection, String departmentName) throws SQLException {
        String query = "SELECT id FROM department WHERE department_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, departmentName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        }
        return -1;
    }
    private boolean validateAllFields() {
        if (FnameField.getText().isEmpty()||LnameField.getText().isEmpty() || address.getText().isEmpty() ||
                phoneField.getText().isEmpty() || emailField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "All fields must be filled.", "Missing Information", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 10 || !phoneNumber.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid 10-digit phone number.", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void clearTextFields() {
        FnameField.setText("");
        LnameField.setText("");
        address.setText("");
        phoneField.setText("");
        emailField.setText("");
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegisterPatron register= new RegisterPatron();
        });
    }
}
