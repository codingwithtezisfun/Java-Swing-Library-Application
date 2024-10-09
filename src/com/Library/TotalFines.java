package com.Library;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TotalFines extends JPanel {

    private JLabel pendingFinesLabel;
    private JLabel totalFinesLabel;

    public TotalFines() {
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(186, 135, 89));
        pendingFinesLabel = new JLabel("<html><u>* PENDING FINES.</u></html>");
        pendingFinesLabel.setFont(new Font("Arial", Font.BOLD, 22));

        // Fetch and display the total pending fines
        BigDecimal totalPendingFines = getTotalPendingFines();
        totalFinesLabel = new JLabel("SH," + totalPendingFines);
        totalFinesLabel.setFont(new Font("Arial", Font.BOLD, 25));

        // Set up layout
        setLayout(new GridLayout(2, 1, 0, 10));
        add(pendingFinesLabel);
        add(totalFinesLabel);
    }

    private BigDecimal getTotalPendingFines() {
        BigDecimal totalFines = BigDecimal.ZERO;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "")) {
            String query = "SELECT SUM(fine_amount) AS totalPendingFines FROM fines WHERE status = 'pending'";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    totalFines = resultSet.getBigDecimal("totalPendingFines");
                    if (totalFines == null) {
                        totalFines = BigDecimal.ZERO;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return totalFines;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Total Fines");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new TotalFines());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
