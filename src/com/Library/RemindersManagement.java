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

public class RemindersManagement extends JDialog {

    private JTextArea enterReminderTextArea;
    private JTextArea displayReminderTextArea;
    private JTextField reminderIdField;

    public RemindersManagement() {


        setLayout(new GridLayout(5, 2, 20, 20)); // 5 rows, 2 columns, with gaps

        add(new JLabel("Enter Reminder:"));

        enterReminderTextArea = new JTextArea();
        enterReminderTextArea.setLineWrap(true);
        enterReminderTextArea.setWrapStyleWord(true);
        JScrollPane enterScrollPane = new JScrollPane(enterReminderTextArea);
        add(enterScrollPane);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReminder();
            }
        });
        add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        add(cancelButton);

        add(new JLabel("Reminder ID:"));

        reminderIdField = new JTextField();
        add(reminderIdField);

        JButton fetchButton = new JButton("Fetch");
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchReminder();
            }
        });
        add(fetchButton);

        displayReminderTextArea = new JTextArea();
        displayReminderTextArea.setEditable(false);
        displayReminderTextArea.setLineWrap(true);
        displayReminderTextArea.setWrapStyleWord(true);
        JScrollPane displayScrollPane = new JScrollPane(displayReminderTextArea);
        add(displayScrollPane);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteReminder();
            }
        });
        add(deleteButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        add(cancelButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addReminder() {
        String reminderText = enterReminderTextArea.getText();
        if (!reminderText.trim().isEmpty()) {
            // Add reminder to the database
            addReminderToDatabase(reminderText);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a reminder text.");
        }
    }

    private void fetchReminder() {
        int reminderId = Integer.parseInt(reminderIdField.getText());
        // Fetch reminder from the database
        String fetchedReminder = fetchReminderFromDatabase(reminderId);
        displayReminderTextArea.setText(fetchedReminder);
    }

    private void deleteReminder() {
        int reminderId = Integer.parseInt(reminderIdField.getText());
        // Delete reminder from the database
        deleteReminderFromDatabase(reminderId);
        clearFields();
    }

    private void addReminderToDatabase(String text) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "")) {
            String query = "INSERT INTO reminders (text) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, text);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Reminder added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String fetchReminderFromDatabase(int reminderId) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "")) {
            String query = "SELECT text FROM reminders WHERE reminder_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, reminderId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getString("text");
                } else {
                    return "No reminder found for the given ID.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error fetching reminder.";
        }
    }

    private void deleteReminderFromDatabase(int reminderId) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "")) {
            String query = "DELETE FROM reminders WHERE reminder_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, reminderId);
                int rowsDeleted = preparedStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Reminder deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "No reminder found with the given ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        enterReminderTextArea.setText("");
        reminderIdField.setText("");
        displayReminderTextArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RemindersManagement());
    }
}
