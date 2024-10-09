package com.Library;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LostBooks extends JPanel {

    private JLabel checkedOutBooksLabel;
    private JLabel countLabel;

    public LostBooks() {
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(186, 135, 89));
        checkedOutBooksLabel = new JLabel("<html><u>TOTAL * LOST * BOOKS</u></html>");
        checkedOutBooksLabel.setFont(new Font("Arial", Font.BOLD, 22));
        checkedOutBooksLabel.setHorizontalAlignment(JLabel.CENTER);

        // Fetch and display the count of checked-out books
        int checkedOutBooksCount = getCheckedOutBooksCount();
        countLabel = new JLabel(String.valueOf(checkedOutBooksCount));
        countLabel.setFont(new Font("Arial", Font.BOLD, 37));
        countLabel.setHorizontalAlignment(JLabel.CENTER);

        // Set up layout
        setLayout(new GridLayout(2, 1, 0, 10));
        add(checkedOutBooksLabel);
        add(countLabel);
    }

    private int getCheckedOutBooksCount() {
        int count = 0;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "")) {
            String query = "SELECT COUNT(*) AS checkedOutBooksCount FROM books WHERE status = 'Lost'";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    count = resultSet.getInt("checkedOutBooksCount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return count;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Checked Out Books");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new CheckedOutBooks());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

