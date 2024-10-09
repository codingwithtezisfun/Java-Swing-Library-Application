package com.Library;

import com.sun.awt.AWTUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;

public class DeleteStudent {
    JTextField nameField, admissionNoField, phoneField, emailField,FetchnameField,AdmissionField,courseComboBox;
    JLabel nameLabel, admissionNoLabel, phoneLabel, emailLabel,course,FetchnameLabel,Admission;
    JButton registerButton, cancelButton,fetchButton;
    int mouseX, mouseY;

    public DeleteStudent() {
       JFrame frame = new JFrame("");
        frame.setUndecorated(true);
        frame.setSize(550, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


        int arcWidth = 20;
        int arcHeight = 20;
        RoundRectangle2D shape = new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), arcWidth, arcHeight);
        AWTUtilities.setWindowShape(frame, shape);

        JPanel upper = new JPanel(new GridBagLayout());
        frame.add(upper, BorderLayout.NORTH);

        GridBagConstraints gb = new GridBagConstraints();
        gb.insets = new Insets(5, 5, 5, 5);
        upper.setBackground(new Color(224, 255, 255));

        JLabel label = new JLabel("<html><u>DELETION FORM</u></html>");
        label.setFont(new Font("Arial", Font.PLAIN, 25));
        label.setForeground(new Color(186, 135, 89));
        gb.gridx = 0;
        gb.gridy = 0;
        gb.insets = new Insets(2, 5, 2, 5);
        upper.add(label, gb);

        JPanel down = new JPanel(new GridBagLayout());
        frame.add(down, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        down.setBackground(new Color(224, 255, 255));
        FetchnameLabel = new JLabel("STUDENT LIBRARY ID");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2, 5, 2, 5);
        FetchnameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        FetchnameLabel.setForeground(new Color(74, 93, 35));
        down.add(FetchnameLabel, gbc);

        FetchnameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Ensure horizontal fill
        gbc.gridwidth = 2;gbc.insets = new Insets(2, 5, 2, 5);
        FetchnameField.setFont(new Font("Arial", Font.PLAIN, 20));
        FetchnameField.setForeground(new Color(225, 169, 95));
        down.add(FetchnameField, gbc);

        Admission = new JLabel("ADMISSION NO");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(2, 5, 2, 5);
        Admission.setFont(new Font("Arial", Font.PLAIN, 20));
        Admission.setForeground(new Color(74, 93, 35));
        down.add(Admission, gbc);

        AdmissionField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Ensure horizontal fill
        gbc.gridwidth = 2;
        gbc.insets = new Insets(2, 5, 2, 5);
        AdmissionField.setFont(new Font("Arial", Font.PLAIN, 20));
        AdmissionField.setForeground(new Color(225, 169, 95));
        down.add(AdmissionField, gbc);

        fetchButton = new JButton("FETCH");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(2, 5, 2, 5);
        fetchButton.setFont(new Font("Arial", Font.PLAIN, 20));
        fetchButton.setForeground(new Color(150, 113, 23));
        fetchButton.setBackground(new Color(224, 254, 200));
        down.add(fetchButton, gbc);

        nameLabel = new JLabel("STUDENT NAME:");
        gbc.gridx = 0;
        gbc.gridy = 3;

        gbc.insets = new Insets(2, 5, 2, 5);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nameLabel.setForeground(new Color(74, 93, 35));
        down.add(nameLabel, gbc);

        nameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Ensure horizontal fill
        gbc.gridwidth = 2;
        gbc.insets = new Insets(2, 5, 2, 5);
        nameField.setFont(new Font("Arial", Font.PLAIN, 20));
        nameField.setForeground(new Color(225, 169, 95));
        down.add(nameField, gbc);

        admissionNoLabel = new JLabel("ADMISSION NO:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(2, 5, 2, 5);
        admissionNoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        admissionNoLabel.setForeground(new Color(74, 93, 35));
        down.add(admissionNoLabel, gbc);

        admissionNoField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Ensure horizontal fill
        gbc.gridwidth = 2;
        gbc.insets = new Insets(2, 5, 2, 5);
        admissionNoField.setFont(new Font("Arial", Font.PLAIN, 20));
        admissionNoField.setForeground(new Color(225, 169, 95));
        down.add(admissionNoField, gbc);

        phoneLabel = new JLabel("PHONE NO:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(2, 5, 2, 5);
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        phoneLabel.setForeground(new Color(74, 93, 35));
        down.add(phoneLabel, gbc);

        phoneField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Ensure horizontal fill
        gbc.gridwidth = 2;
        gbc.insets = new Insets(2, 5, 2, 5);
        phoneField.setFont(new Font("Arial", Font.PLAIN, 20));
        phoneField.setForeground(new Color(225, 169, 95));
        down.add(phoneField, gbc);

        emailLabel = new JLabel("EMAIL:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(2, 5, 2, 5);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        emailLabel.setForeground(new Color(74, 93, 35));
        down.add(emailLabel, gbc);

        emailField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Ensure horizontal fill
        gbc.gridwidth = 2;
        gbc.insets = new Insets(2, 5, 2, 5);
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        emailField.setForeground(new Color(225, 169, 95));
        down.add(emailField, gbc);

        course = new JLabel("COURSE NAME:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(2, 5, 2, 5);
        course.setFont(new Font("Arial", Font.PLAIN, 20));
        course.setForeground(new Color(74, 93, 35));
        down.add(course, gbc);

        courseComboBox = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Ensure horizontal fill
        gbc.gridwidth = 2;
        gbc.insets = new Insets(2, 5, 2, 5);
        courseComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        courseComboBox.setForeground(new Color(225, 169, 95));
        down.add(courseComboBox,gbc);


        registerButton = new JButton("REGISTER");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.insets = new Insets(2, 5, 2, 5);
        registerButton.setFont(new Font("Arial", Font.PLAIN, 20));
        registerButton.setForeground(new Color(150, 113, 23));
        registerButton.setBackground(new Color(224, 254, 200));
        down.add(registerButton, gbc);

        cancelButton = new JButton("CANCEL");
        gbc.gridx = 1;
        gbc.gridy = 8;gbc.insets = new Insets(2, 5, 2, 5);
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
                int bookId = Integer.parseInt(FetchnameField.getText());
                deleteStudent(bookId);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
            }
        });
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String idText = FetchnameField.getText();
                String admissionText = AdmissionField.getText();

                if (!idText.isEmpty() && admissionText.isEmpty()) {
                    int id = Integer.parseInt(idText);
                    fetchStudentDetails(id);
                } else if (idText.isEmpty() && !admissionText.isEmpty()) {
                    int admission = Integer.parseInt(admissionText);
                    fetchStudentDetailsById(admission);
                } else if (!idText.isEmpty() && !admissionText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fill only one field (ID or Admission Number).", "Multiple Fields Filled", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Fill in at least one field (ID or Admission Number).", "No Fields Filled", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
    String url = "jdbc:mysql://localhost:3306/library"; // Change YourDatabaseName to your actual database name
    String username = "root";
    String password = "";

    private void deleteStudent(int studentLibraryId) {
        // Check if the student ID is being used in the books table
        if (isStudentIdInUse(studentLibraryId, "fines","lease_book")) {
            // Show a warning message
            JOptionPane.showMessageDialog(
                    null,
                    "Student's ID is still in use . Cannot delete.",
                    "ID in Use",
                    JOptionPane.WARNING_MESSAGE
            );
        } else {

            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String deleteSql = "DELETE FROM student WHERE student_library_id = ?";
                try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
                    deleteStatement.setInt(1, studentLibraryId);
                    int rowsAffected = deleteStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Student deleted successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        clearTextFields();
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Student not found or deletion failed.",
                                "Deletion Failed",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        "Error deleting student: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private boolean isStudentIdInUse(int studentLibraryId, String... tableNames) {
        for (String tableName : tableNames) {
            if (isStudentIdInUseInTable(studentLibraryId, tableName)) {
                return true;
            }
        }
        return false;
    }


    private boolean isStudentIdInUseInTable(int studentLibraryId, String tableName) {
        String query = "SELECT COUNT(*) AS count FROM " + tableName + " WHERE student_library_id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, studentLibraryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    private void fetchStudentDetails(int studentLibraryId) {

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM student WHERE student_library_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentLibraryId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String studentName = resultSet.getString("names");
                int courseId = resultSet.getInt("course_id");
                String admissionNumber = resultSet.getString("admission_number");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String courseName = getCourseNameById(courseId);

                nameField.setText(studentName);
                admissionNoField.setText(admissionNumber);
                phoneField.setText(phoneNumber);
                emailField.setText(email);
                courseComboBox.setText(courseName);

            } else {
                JOptionPane.showMessageDialog(null, "Student not found.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Could not fetch student details from the database.");
        }
    }
    private void fetchStudentDetailsById(int studentIdentifier) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM student WHERE admission_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentIdentifier);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String studentName = resultSet.getString("names");
                int courseId = resultSet.getInt("course_id");
                String admissionNumber = resultSet.getString("admission_number");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String courseName = getCourseNameById(courseId);


                nameField.setText(studentName);
                admissionNoField.setText(admissionNumber);
                phoneField.setText(phoneNumber);
                emailField.setText(email);
                courseComboBox.setText(courseName);

            } else {
                JOptionPane.showMessageDialog(null, "Student not found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Could not fetch student details from the database.");
        }
    }

    private String getCourseNameById(int courseId) {

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT course_name FROM course WHERE course_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("course_name");
            } else {
                return "Unknown Course";
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Could not fetch course name from the database.");
            return "Unknown Course";
        }
    }


    private void clearTextFields() {
        nameField.setText("");
        admissionNoField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
          DeleteStudent stu = new DeleteStudent();
          stu.setVisible(true);
        });
    }

    public void setVisible(boolean b) {
    }
}
