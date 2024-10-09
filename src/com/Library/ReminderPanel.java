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
import java.util.ArrayList;

public class ReminderPanel extends JPanel {

    private JTextArea reminderTextArea;
    private Timer reminderTimer;
    private int currentReminderIndex = 0;
    private ArrayList<String> reminders = new ArrayList<>();

    public ReminderPanel() {
        setLayout(new BorderLayout());

        reminderTextArea = new JTextArea(17,13);
        reminderTextArea.setFont(new Font("Arial", Font.BOLD, 25));
        reminderTextArea.setLineWrap(true);
        reminderTextArea.setWrapStyleWord(true);
        reminderTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reminderTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch reminders from the database
        fetchReminders();

        // Set up the timer to display reminders
        int delay = 7000; // 3 seconds
        reminderTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNextReminder();
            }
        });
        reminderTimer.start();
    }

    private void fetchReminders() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "")) {
            String query = "SELECT * FROM reminders WHERE reminder_id IS NOT NULL";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String reminderText = resultSet.getString("text");
                    reminders.add(reminderText);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void displayNextReminder() {
        if (reminders.isEmpty()) {
            reminderTextArea.setText("No reminders available.");
        } else {
            // Display the current reminder
            reminderTextArea.setText(reminders.get(currentReminderIndex));

            // Move to the next reminder
            currentReminderIndex = (currentReminderIndex + 1) % reminders.size();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Reminder Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

            ReminderPanel reminderPanel = new ReminderPanel();
            frame.add(reminderPanel);

            frame.setVisible(true);
        });
    }
}
