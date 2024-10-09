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

public class DeleteBook extends JFrame {
    JTextField bookNameTextField,authorNameTextField, searchBookIdTextField,shelfField,categoryTextField,statusField;

     JButton fetchButton,deleteButton,clearButton;

    int  mouseX, mouseY;


    public DeleteBook() {
        setAlwaysOnTop(true);
        setUndecorated(true);
        setSize(550, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(224, 255, 255));
        add(mainPanel);
        Font Font1 = new Font("SansSerif", Font.BOLD, 16);

        // Search Panel for updating book information
        JPanel searchTitle = new JPanel(new BorderLayout());
        searchTitle.setBackground(new Color(224, 255, 255));
        JPanel searchPanel = new JPanel(new GridLayout(2,2,5,5));
        searchPanel.setBackground(new Color(224, 255, 255));
        searchPanel.setPreferredSize(new Dimension(500, 100));
        addAllGap(searchPanel,20);
        JLabel PanelTitle = new JLabel("<html><u>DELETE BOOK FORM</u></html>");
            PanelTitle.setBackground(new Color(224, 255, 255));
        PanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        PanelTitle.setFont(new Font("Arial", Font.BOLD, 25));
        searchTitle.add(PanelTitle,BorderLayout.NORTH);
        searchTitle.add(searchPanel,BorderLayout.CENTER);

        JLabel searchLabel = new JLabel("SEARCH BOOK BY ID:");
        searchLabel.setFont(Font1);
        searchBookIdTextField = new JTextField(15);
        searchBookIdTextField.setFont(Font1);
        searchBookIdTextField.setForeground(Color.BLUE);
        fetchButton = new JButton("FETCH");
        fetchButton.setFont(Font1);
        clearButton = new JButton("CLEAR");
        clearButton.setFont(Font1);
        searchPanel.add(searchLabel);
        searchPanel.add(searchBookIdTextField);
        searchPanel.add(fetchButton);
        searchPanel.add(clearButton);

        mainPanel.add(searchTitle, BorderLayout.NORTH);

        JLabel Title = new JLabel("<html><u>BOOK DETAILS</u></html>");
        Title.setBackground(new Color(224, 255, 255));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        Title.setFont(new Font("Arial", Font.BOLD, 20));
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(new Color(224, 255, 255));
        JPanel leftContentPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        contentPanel.add(leftContentPanel,BorderLayout.CENTER);
        contentPanel.add(Title,BorderLayout.NORTH);

        JLabel bookNameLabel = new JLabel("BOOK NAME:");
        bookNameLabel.setFont(Font1);
        bookNameTextField = new JTextField(20);
        bookNameTextField.setForeground(Color.BLUE);
        bookNameTextField.setEditable(false); // Set
        bookNameTextField.setFont(Font1);

        JLabel authorNameLabel = new JLabel("AUTHOR NAME:");
        authorNameLabel.setFont(Font1);

        authorNameTextField = new JTextField(20);
        authorNameTextField.setFont(Font1);
        authorNameTextField.setEditable(false); // Set
        authorNameTextField.setForeground(Color.BLUE);

        // Replace JComboBox for Shelf with JLabel and JTextField
        JLabel shelfLabel = new JLabel("SHELF NUMBER:");
        shelfLabel.setFont(Font1);
         shelfField = new JTextField();
        shelfField.setFont(Font1);
        shelfField.setForeground(Color.BLUE);
        shelfField.setEditable(false); // Set to false if you don't want the user to edit this field

// Replace JComboBox for Status with JLabel and JTextField
        JLabel statusLabel = new JLabel("BOOK STATUS:");
        statusLabel.setFont(Font1);
         statusField = new JTextField();
        statusField.setForeground(Color.BLUE);
        statusField.setFont(Font1);
        statusField.setEditable(false); // Set to false if you don't want the user to edit this field

        JLabel categoryLabel = new JLabel("CATEGORY:");
        categoryLabel.setFont(Font1);

         categoryTextField = new JTextField();
        categoryTextField.setFont(Font1);
        categoryTextField.setForeground(Color.BLUE);
        categoryTextField.setEditable(false); // Set

        JButton registerButton = new JButton("DELETE");
        registerButton.setFont(Font1);
        JButton cancelButton = new JButton("CANCEL");
        cancelButton.setFont(Font1);
        addAllGap(leftContentPanel,10);
        leftContentPanel.setBackground(new Color(224, 255, 255));
        leftContentPanel.add(bookNameLabel);
        leftContentPanel.add(bookNameTextField);
        leftContentPanel.add(authorNameLabel);
        leftContentPanel.add(authorNameTextField);
        leftContentPanel.add(shelfLabel);
        leftContentPanel.add(shelfField);
        leftContentPanel.add(statusLabel);
        leftContentPanel.add(statusField);
        leftContentPanel.add(categoryLabel);
        leftContentPanel.add(categoryTextField);
        leftContentPanel.add(registerButton);
        leftContentPanel.add(cancelButton);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        fetchButton.addActionListener(e -> fetchBookInformation());
        clearButton.addActionListener(e -> clearFields());
        cancelButton.addActionListener(e -> dispose());
        registerButton.addActionListener(e -> deleteBook());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void fetchBookInformation() {
        try (Connection connection = connectToDatabase()) {
            int bookId = Integer.parseInt(searchBookIdTextField.getText());
            String sql = "SELECT * FROM books WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, bookId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int bookNameId = resultSet.getInt("book_name_id");
                        int authorId = resultSet.getInt("author_id");
                        String Location = resultSet.getString("shelf_number");
                        String status = resultSet.getString("status");
                        int category = resultSet.getInt("category_id");

                        String bookName = getBookName(connection, bookNameId);
                        String authorName = getAuthorName(connection, authorId);
                        String cat = getCategoryName(connection,category);

                        bookNameTextField.setText(bookName);
                        authorNameTextField.setText(authorName);
                        shelfField.setText(Location);
                        statusField.setText(status);
                        categoryTextField.setText(cat);

                    } else {
                        JOptionPane.showMessageDialog(this, "Book not found", "Error", JOptionPane.ERROR_MESSAGE);
                        clearFields();
                    }
                }
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching book information", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private String getCategoryName(Connection connection,int categoryId) {
            // Query to fetch category name
            String sql = "SELECT category_name FROM category WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, categoryId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("category_name");
                    }
                }
            }
         catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void deleteBook() {
        try (Connection connection = connectToDatabase()) {
            int bookId = Integer.parseInt(searchBookIdTextField.getText());
            String sql = "DELETE FROM books WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, bookId);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Book deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Book has reference/ is in use in other tables", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Book has reference/ is in use in other tables", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        bookNameTextField.setText("");
        authorNameTextField.setText("");
        shelfField.setText("");
        statusField.setText("");
        categoryTextField.setText("");
        searchBookIdTextField.setText("");
    }

    private Connection connectToDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/library";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }

    private String getBookName(Connection connection, int bookNameId) throws SQLException {
        String sql = "SELECT book_name FROM book_names WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, bookNameId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? resultSet.getString("book_name") : "Unknown";
            }
        }
    }

    private String getAuthorName(Connection connection, int authorId) throws SQLException {
        String sql = "SELECT author_name FROM authors WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, authorId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? resultSet.getString("author_name") : "Unknown";
            }
        }
    }
    private void addRightLeftGap(JComponent component, int gap) {
        component.setBorder(BorderFactory.createEmptyBorder(0, gap, 0,gap));
    }
    private void addAllGap(JComponent component, int gap) {
        component.setBorder(BorderFactory.createEmptyBorder(gap, gap,gap, gap));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new DeleteBook());
    }
}
