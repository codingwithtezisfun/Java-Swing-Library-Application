package com.Library;

import com.sun.awt.AWTUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;

public class RegisterStudent {
    JTextField nameField, admissionNoField, phoneField, emailField;
    JLabel nameLabel, admissionNoLabel, phoneLabel, emailLabel,course;
    JButton registerButton, cancelButton;
    JComboBox<String> courseComboBox;
    int mouseX, mouseY;
    JFrame frame;

    public RegisterStudent() {
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

        JLabel label = new JLabel("<html><u>REGISTRATION FORM</u></html>");
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

        nameLabel = new JLabel("STUDENT NAME:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nameLabel.setForeground(new Color(74, 93, 35));
        down.add(nameLabel, gbc);

        nameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        nameField.setFont(new Font("Arial", Font.PLAIN, 20));
        nameField.setForeground(new Color(225, 169, 95));
        down.add(nameField, gbc);

        admissionNoLabel = new JLabel("ADMISSION NO:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        admissionNoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        admissionNoLabel.setForeground(new Color(74, 93, 35));
        down.add(admissionNoLabel, gbc);

        admissionNoField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        admissionNoField.setFont(new Font("Arial", Font.PLAIN, 20));
        admissionNoField.setForeground(new Color(225, 169, 95));
        down.add(admissionNoField, gbc);

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

        course = new JLabel("COURSE NAME:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        course.setFont(new Font("Arial", Font.PLAIN, 20));
        course.setForeground(new Color(74, 93, 35));
        down.add(course, gbc);

        courseComboBox = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 4;
        courseComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        courseComboBox.setForeground(new Color(225, 169, 95));

// Add courses to the combo box
        String[] courses = {"Software Engineering", "Electrical Engineering", "Drawing and Design", "Architecture", "Catering"};
        for (String course : courses) {
            courseComboBox.addItem(course);
        }

        down.add(courseComboBox, gbc);


        registerButton = new JButton("REGISTER");
        gbc.gridx = 0;
        gbc.gridy = 5;
        registerButton.setFont(new Font("Arial", Font.PLAIN, 20));
        registerButton.setForeground(new Color(150, 113, 23));
        registerButton.setBackground(new Color(224, 254, 200));
        down.add(registerButton, gbc);

        cancelButton = new JButton("CANCEL");
        gbc.gridx = 1;
        gbc.gridy = 5;
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
                    String message = "STUDENT HAS BEEN REGISTERED";

                    int choice;
                    choice = JOptionPane.showConfirmDialog(frame, message, "REGISTRATION CONFIRMATION", JOptionPane.OK_CANCEL_OPTION);

                    if (choice == JOptionPane.OK_OPTION) {
                        saveStudentToDatabase();

                        clearTextFields();
                        frame.dispose();

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
    private void saveStudentToDatabase() {
        String url = "jdbc:mysql://localhost:3306/library"; // Change YourDatabaseName to your actual database name
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Fetch the course id based on the selected course name from the combo box
            String selectedCourse = (String) courseComboBox.getSelectedItem();
            int courseId = getCourseIdFromDatabase(connection, selectedCourse);

            String query = "INSERT INTO student (names, course_id, admission_number, phone_number, email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameField.getText());
            preparedStatement.setInt(2, courseId);
            preparedStatement.setString(3, admissionNoField.getText());
            preparedStatement.setString(4, phoneField.getText());
            preparedStatement.setString(5, emailField.getText());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Could not save student data to the database.");
        }
    }
    private int getCourseIdFromDatabase(Connection connection, String courseName) throws SQLException {
        String query = "SELECT course_id FROM course WHERE course_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, courseName);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("course_id");
        }
        return -1;
    }
    private boolean validateAllFields() {
        if (nameField.getText().isEmpty() || admissionNoField.getText().isEmpty() || phoneField.getText().isEmpty() || emailField.getText().isEmpty()) {
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
        nameField.setText("");
        admissionNoField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegisterStudent registerStudent= new RegisterStudent();
        });
    }
}
