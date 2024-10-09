package com.Library;

import com.sun.awt.AWTUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;

public class UpdatePatron {
    JTextField FnameField,LnameField, address, phoneField, emailField,idF;
    JLabel FnameLabel,LnameLabel, addressLabel, phoneLabel, emailLabel,Department,id;
    JButton registerButton, cancelButton,searchButton;
    JComboBox<String> DepartmentComboBox;
    int mouseX, mouseY;
    JFrame frame;

    public UpdatePatron() {
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

        JLabel label = new JLabel("<html><u>PATRON INFORMATION UPDATE FORM</u></html>");
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

        id = new JLabel("PATRON ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        id.setFont(new Font("Arial", Font.PLAIN, 20));
        id.setForeground(new Color(74, 93, 35));
        down.add(id, gbc);

        idF = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        idF.setFont(new Font("Arial", Font.PLAIN, 20));
        idF.setForeground(new Color(225, 169, 95));
        down.add(idF, gbc);

        FnameLabel = new JLabel("FIRST NAME:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        FnameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        FnameLabel.setForeground(new Color(74, 93, 35));
        down.add(FnameLabel, gbc);

        FnameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        FnameField.setFont(new Font("Arial", Font.PLAIN, 20));
        FnameField.setForeground(new Color(225, 169, 95));
        down.add(FnameField, gbc);

        LnameLabel = new JLabel("LAST NAME:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        LnameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        LnameLabel.setForeground(new Color(74, 93, 35));
        down.add(LnameLabel, gbc);

        LnameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        LnameField.setFont(new Font("Arial", Font.PLAIN, 20));
        LnameField.setForeground(new Color(225, 169, 95));
        down.add(LnameField, gbc);

        phoneLabel = new JLabel("PHONE NO:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        phoneLabel.setForeground(new Color(74, 93, 35));
        down.add(phoneLabel, gbc);

        phoneField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        phoneField.setFont(new Font("Arial", Font.PLAIN, 20));
        phoneField.setForeground(new Color(225, 169, 95));
        down.add(phoneField, gbc);

        emailLabel = new JLabel("EMAIL:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        emailLabel.setForeground(new Color(74, 93, 35));
        down.add(emailLabel, gbc);

        emailField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 5;
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        emailField.setForeground(new Color(225, 169, 95));
        down.add(emailField, gbc);

        Department = new JLabel("DEPARTMENT:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        Department.setFont(new Font("Arial", Font.PLAIN, 20));
        Department.setForeground(new Color(74, 93, 35));
        down.add(Department, gbc);

        DepartmentComboBox = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 6;
        DepartmentComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        DepartmentComboBox.setForeground(new Color(225, 169, 95));
        String[] departments = {"Counter Service", "Shelf Management"};
        for (String department : departments) {
            DepartmentComboBox.addItem(department);
        }
        down.add(DepartmentComboBox, gbc);

        addressLabel= new JLabel("ADDRESS:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        addressLabel.setForeground(new Color(74, 93, 35));
        down.add(addressLabel, gbc);

        address = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 7;
        address.setFont(new Font("Arial", Font.PLAIN, 20));
        address.setForeground(new Color(225, 169, 95));
        down.add(address, gbc);

// Add courses to the combo box

        registerButton = new JButton("UPDATE");
        gbc.gridx = 0;
        gbc.gridy = 8;
        registerButton.setFont(new Font("Arial", Font.PLAIN, 20));
        registerButton.setForeground(new Color(150, 113, 23));
        registerButton.setBackground(new Color(224, 254, 200));
        down.add(registerButton, gbc);

        cancelButton = new JButton("CANCEL");
        gbc.gridx = 1;
        gbc.gridy = 8;
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));
        cancelButton.setForeground(new Color(150, 113, 23));
        cancelButton.setBackground(new Color(224, 254, 200));
        down.add(cancelButton, gbc);

        searchButton = new JButton("SEARCH");
        gbc.gridx = 1;
        gbc.gridy = 1;
        searchButton.setFont(new Font("Arial", Font.PLAIN, 20));
        searchButton.setForeground(new Color(150, 113, 23));
        searchButton.setBackground(new Color(224, 254, 200));
        down.add(searchButton, gbc);

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
                    String message = "UPDATE VERIFICATION";

                    int choice;
                    choice = JOptionPane.showConfirmDialog(frame, message, "UPDATE CONFIRMATION", JOptionPane.OK_CANCEL_OPTION);

                    if (choice == JOptionPane.OK_OPTION) {
                        try {
                          int   id = Integer.parseInt(idF.getText());
                            updatePatronInDatabase(id);
                            // Use 'id' as needed
                        } catch (NumberFormatException ed) {
                      // ed.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer for ID.", "Error", JOptionPane.ERROR_MESSAGE);
                        }

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
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchPatronInformation();
            }
        });
    }
    String url = "jdbc:mysql://localhost:3306/library";
    String username = "root";
    String password = "";

    private void updatePatronInDatabase(int patronId) {

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE patron SET first_name = ?, last_name = ?, phone_no = ?, email_address = ?, adress = ?, department_id = ? WHERE patron_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, FnameField.getText());
                preparedStatement.setString(2, LnameField.getText());
                preparedStatement.setString(3, phoneField.getText());
                preparedStatement.setString(4, emailField.getText());
                preparedStatement.setString(5, address.getText());

                // Assuming you have a method to get the department id based on the selected department name
                int departmentId = getDepartmentIdFromDatabase(connection, (String) DepartmentComboBox.getSelectedItem());
                preparedStatement.setInt(6, departmentId);

                preparedStatement.setInt(7, patronId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Success: Patron data updated in the database.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Patron not found or update failed.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Could not update patron data in the database.");
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
    private void fetchPatronInformation() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM patron WHERE patron_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Assuming idF is a JTextField for entering patron ID
            int patronId = Integer.parseInt(idF.getText());
            preparedStatement.setInt(1, patronId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Assuming FnameField, LnameField, phoneField, emailField, address are JTextFields
                FnameField.setText(resultSet.getString("first_name"));
                LnameField.setText(resultSet.getString("last_name"));
                phoneField.setText(resultSet.getString("phone_no"));
                emailField.setText(resultSet.getString("email_address"));
                address.setText(resultSet.getString("adress"));

                // Assuming DepartmentComboBox is a JComboBox
                int departmentId = resultSet.getInt("department_id");
                String departmentName = getDepartmentNameFromDatabase(connection, departmentId);
                DepartmentComboBox.setSelectedItem(departmentName);
            } else {
                // Patron with the given ID not found
                JOptionPane.showMessageDialog(null, "Patron not found with ID: " + patronId, "Not Found", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching patron information: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getDepartmentNameFromDatabase(Connection connection, int departmentId) throws SQLException {
        String query = "SELECT department_name FROM department WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, departmentId);

        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next() ? resultSet.getString("department_name") : "Unknown Department";
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
            UpdatePatron update= new UpdatePatron();
        });
    }
}
