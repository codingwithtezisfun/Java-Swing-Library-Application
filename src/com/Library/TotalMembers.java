package com.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TotalMembers extends JPanel {

    private JPanel mainPanel;
    private JLabel totalBooksLabel,totalBooks;
    private JPanel buttonPanel;
    private JButton addBookButton;
    private JButton deleteBookButton;
    private JButton updateBookButton;
    private CardLayout cardLayout;

    public TotalMembers() {
        setSize(400, 200);

        // Create main panel
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
        JPanel books = new JPanel(new BorderLayout());
        // Create total books label
        totalBooksLabel = new JLabel("<html><u> TOTAL MEMBERS  </u></html>\n");
        totalBooksLabel.setFont(new Font("Arial", Font.BOLD, 27));
        totalBooksLabel.setHorizontalAlignment(JLabel.CENTER);
        books.add(totalBooksLabel,BorderLayout.NORTH);

        totalBooks = new JLabel();
        totalBooks.setText(Integer.toString(gettotalMembers()));
        totalBooks.setFont(new Font("Arial", Font.BOLD, 70));
        totalBooks.setHorizontalAlignment(JLabel.CENTER);
        books.add(totalBooks,BorderLayout.CENTER);

        // Add total books label to the main panel
        mainPanel.add(books, BorderLayout.NORTH);

        // Create button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add vertical gap

// Create buttons
        addBookButton = new JButton("REGISTER STUDENT");
        deleteBookButton = new JButton("DELETE STUDENT");
        updateBookButton = new JButton("UPDATE STUDENT");

// Set font size to 20
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        addBookButton.setFont(buttonFont);
        deleteBookButton.setFont(buttonFont);
        updateBookButton.setFont(buttonFont);

// Center the text
        addBookButton.setHorizontalAlignment(SwingConstants.CENTER);
        deleteBookButton.setHorizontalAlignment(SwingConstants.CENTER);
        updateBookButton.setHorizontalAlignment(SwingConstants.CENTER);

// Remove border
        addBookButton.setBorderPainted(false);
        deleteBookButton.setBorderPainted(false);
        updateBookButton.setBorderPainted(false);

// Set background color to magenta
        addBookButton.setBackground(Color.MAGENTA);
        deleteBookButton.setBackground(Color.MAGENTA);
        updateBookButton.setBackground(Color.MAGENTA);

        // Set maximum width for buttons
        addBookButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, addBookButton.getPreferredSize().width));
        deleteBookButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, deleteBookButton.getPreferredSize().width));
        updateBookButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, updateBookButton.getPreferredSize().width));

// Add buttons to the panel
        buttonPanel.add(addBookButton);
        buttonPanel.add(Box.createVerticalStrut(5)); // Add additional vertical gap
        buttonPanel.add(deleteBookButton);
        buttonPanel.add(Box.createVerticalStrut(5)); // Add additional vertical gap
        buttonPanel.add(updateBookButton);


        // Add button panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Create a card layout for main panel and button panel
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
// Create a card for the main content panel
        JPanel mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.add(books, BorderLayout.NORTH);

// Add the main content panel to the main panel
        mainPanel.add(mainContentPanel, "main");

// Add the button panel to the main panel
        mainPanel.add(buttonPanel, "buttons");

        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cardLayout.show(mainPanel, "buttons");

                // Set up the timer for hiding buttons after 5 seconds
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        SwingUtilities.invokeLater(() -> {
                            cardLayout.show(mainPanel, "main");
                        });
                    }
                }, 6000);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // The card layout will handle switching back to the main panel after 5 seconds
            }
        });


        // ActionListeners for buttons (you can add your functionality)
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new RegisterStudent();
            }
        });

        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              DeleteStudent del = new DeleteStudent();
              del.setVisible(true);
            }
        });

        updateBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateStudent up = new UpdateStudent();
                up.setVisible(true);

            }
        });
    }

    // Example method to get the total number of books (you can replace it with your database query)
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public int gettotalMembers() {
        int totalMembers= 0;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT COUNT(*) AS totalMembers FROM student WHERE student_library_id IS NOT NULL";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    totalMembers = resultSet.getInt("totalMembers");
                } else {
                    System.out.println("No result found in the resultSet");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return totalMembers;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           TotalMembers check = new TotalMembers();
        check.setVisible(true);
               });
    }
}
