package com.Library;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FineManagement extends JFrame {

    private JTextField studentIdField;
    private JTextField studentLibraryIdField;
    private JTextField bookIdField;
    private JTextField fineAmountField;
    private JComboBox<String> statusComboBox;

    int  mouseX, mouseY;
    JFrame frame;


    public FineManagement() {
        setAlwaysOnTop(true);
        setUndecorated(true);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setBackground(new Color(224, 255, 255));
      //  this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        int arcWidth = 20;
        int arcHeight = 20;
        RoundRectangle2D shape = new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), arcWidth, arcHeight);
        AWTUtilities.setWindowShape(this,shape);

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - mouseX;
                int y = e.getYOnScreen() - mouseY;
                setLocation(x, y);
            }
        });


        setLayout(new GridLayout(7, 2, 10, 10)); // 7 rows, 2 columns, with gaps

        add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        add(studentIdField);

        JButton fetchButton = new JButton("Fetch");
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchDetails();
            }
        });
        add(new JLabel());
        add(fetchButton);


        add(new JLabel("Student Library ID:"));
        studentLibraryIdField = new JTextField();
        add(studentLibraryIdField);

        add(new JLabel("Book ID:"));
        bookIdField = new JTextField();
        add(bookIdField);

        add(new JLabel("Fine Amount:"));
        fineAmountField = new JTextField();
        add(fineAmountField);

        add(new JLabel("Status:"));
        String[] statusOptions = {"pending", "cleared"};
        statusComboBox = new JComboBox<>(statusOptions);
        add(statusComboBox);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitAction();
            }
        });
        add(submitButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
        });
        add(cancelButton);

        setVisible(true);
    }
    private void addAllGap(JComponent component, int gap) {
        component.setBorder(BorderFactory.createEmptyBorder(gap, gap,gap, gap));
    }

    private void fetchDetails() {
        int studentId = Integer.parseInt(studentIdField.getText());
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "")) {
            String query = "SELECT student_library_id, book_id, fine_amount, status FROM fines WHERE student_library_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, studentId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    studentLibraryIdField.setText(String.valueOf(resultSet.getInt("student_library_id")));
                    bookIdField.setText(String.valueOf(resultSet.getInt("book_id")));
                    fineAmountField.setText(String.valueOf(resultSet.getDouble("fine_amount")));
                    statusComboBox.setSelectedItem(resultSet.getString("status"));
                } else {
                    JOptionPane.showMessageDialog(this, "No records found for the given Student ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void submitAction() {
        int studentLibraryId = Integer.parseInt(studentLibraryIdField.getText());
        int bookId = Integer.parseInt(bookIdField.getText());
        double fineAmount = Double.parseDouble(fineAmountField.getText());
        String status = (String) statusComboBox.getSelectedItem();

        updateFines(studentLibraryId, bookId, fineAmount, status);

        clearFields();
    }

    private void clearFields() {
        studentIdField.setText("");
        studentLibraryIdField.setText("");
        bookIdField.setText("");
        fineAmountField.setText("");
        statusComboBox.setSelectedIndex(0);
    }

    private void updateFines(int studentLibraryId, int bookId, double fineAmount, String status) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "")) {
            String query = "UPDATE fines SET fine_amount = ?, status = ? WHERE student_library_id = ? AND book_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDouble(1, fineAmount);
                preparedStatement.setString(2, status);
                preparedStatement.setInt(3, studentLibraryId);
                preparedStatement.setInt(4, bookId);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Fines updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "No fines updated. Check your input values.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FineManagement());
    }
}
