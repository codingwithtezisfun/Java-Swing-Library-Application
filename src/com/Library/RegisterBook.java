package com.Library;
import com.sun.awt.AWTUtilities;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterBook extends JFrame {
    private JTextField bookNameTextField;
    private JTextField authorNameTextField;
    JRadioButton[] categoryButtons;
    JComboBox shelfField;
    JFrame frame;
    int mouseX, mouseY;

    public RegisterBook() {
       setUndecorated(true);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


        int arcWidth = 20;
        int arcHeight = 20;
        RoundRectangle2D shape = new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), arcWidth, arcHeight);
        AWTUtilities.setWindowShape(this, shape);

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
        mainPanel.setLayout(new BorderLayout(10, 10)); // Added vertical gaps
        mainPanel.setBackground(new Color(224, 255, 255));
        add(mainPanel);

        // Left Panel for Book Registration
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(10, 10)); // Added vertical gaps
        leftPanel.setBackground(new Color(224, 255, 255));
        addBottomGap(leftPanel, 15);
        mainPanel.add(leftPanel, BorderLayout.CENTER);

        // Title Panel for Book Registration
        JPanel leftTitlePanel = new JPanel();
        leftTitlePanel.setBackground(new Color(224, 255, 255));
        leftTitlePanel.setLayout(new FlowLayout());
        leftPanel.add(leftTitlePanel, BorderLayout.NORTH);

        JLabel leftPanelTitle = new JLabel("<html><u>REGISTER BOOK</u></html>");
        leftPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanelTitle.setFont(new Font("Arial", Font.BOLD, 20));

        leftTitlePanel.add(leftPanelTitle);

        // Content Panel for Book Registration
        JPanel ContentPanel = new JPanel(new BorderLayout(10, 10)); // Added vertical gaps
        ContentPanel.setBackground(new Color(224, 255, 255));
        leftPanel.add(ContentPanel, BorderLayout.CENTER);

        // Internal padding for leftContentPanel
        JPanel leftContentPanel = new JPanel();
        leftContentPanel.setBackground(new Color(224, 255, 255));
        leftContentPanel.setLayout(new GridLayout(3, 2, 10, 10));
        addRightGap(leftContentPanel, 15);
        addLeftGap(leftContentPanel, 15);// Added vertical and horizontal gaps
        ContentPanel.add(leftContentPanel, BorderLayout.CENTER);

        JPanel RightContentPanel = new JPanel();
        RightContentPanel.setBackground(new Color(224, 255, 255));
        RightContentPanel.setPreferredSize(new Dimension(255, getHeight()));
        addLeftGap(RightContentPanel, 5);
        addRightGap(RightContentPanel, 5);
        RightContentPanel.setBackground(new Color(224, 254, 200));
        ContentPanel.add(RightContentPanel, BorderLayout.EAST);

        JLabel suggestionsLabel = new JLabel("<html><u>SUGGESTIONS</u></html>");
        suggestionsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        suggestionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        RightContentPanel.add(suggestionsLabel, BorderLayout.NORTH);

        JTextArea suggestionsTextArea = new JTextArea(5,20);
        suggestionsTextArea.setBackground(new Color(224, 254, 200));
        suggestionsTextArea.setFont(new Font("Arial", Font.BOLD, 15));
        suggestionsTextArea.setEditable(false);

        suggestionsTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int caretPosition = suggestionsTextArea.getCaretPosition();
                    int lineStart = suggestionsTextArea.getLineStartOffset(suggestionsTextArea.getLineOfOffset(caretPosition));
                    int lineEnd = suggestionsTextArea.getLineEndOffset(suggestionsTextArea.getLineOfOffset(caretPosition));
                    String clickedSuggestion = suggestionsTextArea.getText().substring(lineStart, lineEnd).trim();

                    if (activeTextField != null) {
                        activeTextField.setText(clickedSuggestion);
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });
        JScrollPane suggestionsScrollPane = new JScrollPane(suggestionsTextArea);
        RightContentPanel.add(suggestionsScrollPane, BorderLayout.CENTER);

        JLabel bookNameLabel = new JLabel("BOOK NAME:");
        bookNameTextField = new JTextField(20);
        bookNameTextField.addKeyListener(new AutoSuggestListener(bookNameTextField, suggestionsTextArea, true));
        JLabel authorNameLabel = new JLabel("AUTHOR NAME:");
        bookNameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                activeTextField = bookNameTextField;
            }
        });
        authorNameTextField = new JTextField(20);
        authorNameTextField.addKeyListener(new AutoSuggestListener(authorNameTextField, suggestionsTextArea, false));
        authorNameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                activeTextField = authorNameTextField;
            }
        });
        JLabel Shelf = new JLabel("SHELF NUMBER:");
        shelfField =  new JComboBox<>();
        int[] courses = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        for (int course : courses) {
            shelfField.addItem(course);
        }

        ButtonGroup categoryGroup = new ButtonGroup();
        String[] categoryLabels = {"TECHNOLOGY AND ART", "BUSINESS AND FINANCE", "CATERING & HEALTH ", "FICTION & SCIENCE ", "EDUCATION", "REFERENCE"};
        categoryButtons = new JRadioButton[6];

        for (int i = 0; i < 6; i++) {
            categoryButtons[i] = new JRadioButton(categoryLabels[i]);
            categoryGroup.add(categoryButtons[i]);
            leftContentPanel.add(categoryButtons[i]);
        }


        JPanel leftButtonPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        leftButtonPanel.setBackground(new Color(224, 255, 255));
        addRightGap(leftButtonPanel, 15);
        addLeftGap(leftButtonPanel, 15);// Added vertical and horizontal gaps
        leftPanel.add(leftButtonPanel, BorderLayout.SOUTH);

        JButton registerButton = new JButton("REGISTER");
        JButton cancelButton = new JButton("CANCEL");
        JButton add = new JButton("ADD");
        JLabel blank = new JLabel("");
        leftButtonPanel.add(bookNameLabel);
        leftButtonPanel.add(bookNameTextField);
        leftButtonPanel.add(blank);
        leftButtonPanel.add(add);
        leftButtonPanel.add(authorNameLabel);
        leftButtonPanel.add(authorNameTextField);
        leftButtonPanel.add(Shelf);
        leftButtonPanel.add(shelfField);
        leftButtonPanel.add(registerButton);
        leftButtonPanel.add(cancelButton);

        // Right Panel for Author Registration
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(224, 255, 255));
        rightPanel.setLayout(new BorderLayout(10, 10)); // Added vertical gaps
        addRightGap(rightPanel, 15);
        //addLeftGap(, 15);
        mainPanel.add(rightPanel, BorderLayout.NORTH);

        // Title Panel for Author Registration
        JPanel rightTitlePanel = new JPanel();
        rightTitlePanel.setBackground(new Color(224, 255, 255));
        rightTitlePanel.setLayout(new FlowLayout());
        rightPanel.add(rightTitlePanel, BorderLayout.NORTH);

        JLabel rightPanelTitle = new JLabel("<html><u>REGISTER AUTHOR</u></html>");
        rightPanelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        rightPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        rightTitlePanel.add(rightPanelTitle);

        // Content Panel for Author Registration
        JPanel rightContentPanel = new JPanel();
        rightContentPanel.setBackground(new Color(224, 255, 255));
        rightContentPanel.setLayout(new GridLayout(2, 2, 10, 10));
        addRightGap(rightContentPanel, 15);
        addLeftGap(rightContentPanel, 15);// Added vertical and horizontal gaps
        rightPanel.add(rightContentPanel, BorderLayout.CENTER);

        JLabel authorNameLabelRight = new JLabel("AUTHOR NAME:");
        JTextField authorNameTextFieldRight = new JTextField(20);

        rightContentPanel.add(authorNameLabelRight);
        rightContentPanel.add(authorNameTextFieldRight);
        JButton registerButtonRight = new JButton("REGISTER");
        JButton cancelButtonRight = new JButton("CANCEL");
        //registerButtonRight.setPreferredSize(new Dimension(30, 20));
        // cancelButtonRight.setPreferredSize(new Dimension(30, 20));

        rightContentPanel.add(registerButtonRight);
        rightContentPanel.add(cancelButtonRight);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataToBooks();
                bookNameTextField.setText("");
                authorNameTextField.setText("");
                shelfField.setSelectedIndex(0);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            dispose();
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Name = bookNameTextField.getText();

                // Add your author registration logic here
                addDataToBookName(Name);
            }
        });

        registerButtonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String authorName = authorNameTextFieldRight.getText();

                // Add your author registration logic here
                addDataToAuthors(authorName);
                authorNameTextField.setText(authorName);
                authorNameTextFieldRight.setText("");
            }
        });

        cancelButtonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Add your cancel logic for author registration here
                authorNameTextFieldRight.setText("");
            }
        });

        // Initialize the frame
        setLocationRelativeTo(null);
        setVisible(true);
    }
    // Helper method to get the selected category
    private String getSelectedCategory() {
        for (JRadioButton radioButton : categoryButtons) {
            if (radioButton.isSelected()) {
                return radioButton.getText();
            }
        }
        return ""; // Default value, you might want to handle this differently
    }

    private void addLeftGap(JComponent component, int gap) {
        component.setBorder(BorderFactory.createEmptyBorder(0, gap, 0, 0));
    }

    private void addRightGap(JComponent component, int gap) {
        component.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, gap));
    }
    private void addBottomGap(JComponent component, int gap) {
        component.setBorder(BorderFactory.createEmptyBorder(0, 0, gap, gap));
    }
    String url = "jdbc:mysql://localhost:3306/library";
    String username = "root";
    String password = "";

    private Connection connectToDatabase() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
    // Method to add data to the authors table
    private void addDataToAuthors(String authorName) {

        try (Connection connection = connectToDatabase()) {
            String sql = "INSERT INTO authors (author_name) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, authorName);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(this, "AUTHOR registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Book registration failed", "error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void addDataToBookName(String bookName) {

        try (Connection connection = connectToDatabase()) {
            String sql = "INSERT INTO book_names (book_name) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, bookName);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Name added", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "failed", "error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Method to add data to the books table
    private void addDataToBooks() {
        String bookName = bookNameTextField.getText();
        String authorName = authorNameTextField.getText();
        String categoryName = getSelectedCategory();
        String shelf = shelfField.getSelectedItem().toString();

        try (Connection connection = connectToDatabase()) {
            // First, get author ID based on author name
            int authorId = getAuthorId(connection, authorName);

            // Next, get category ID based on category name
            int categoryId = getCategoryId(connection, categoryName);
            int bookNameId = getBookNameId(connection, bookName);

            // Insert data into the books table
            String sql = "INSERT INTO books (book_name_id, author_id, category_id, status,shelf_number) VALUES (?, ?, ?, 'Available',?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, bookNameId);
                preparedStatement.setInt(2, authorId);
                preparedStatement.setInt(3, categoryId);
                preparedStatement.setString(4, shelf);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Book registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Book registration failed", "FAIL", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Helper method to get author ID based on author name
    private int getAuthorId(Connection connection, String authorName) throws SQLException {
        String sql = "SELECT id FROM authors WHERE author_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, authorName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        }
        // Return a default value or handle the case where the author is not found
        return -1;
    }

    // Helper method to get category ID based on category name
    private int getCategoryId(Connection connection, String categoryName) throws SQLException {
        String sql = "SELECT id FROM category WHERE category_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, categoryName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        }
        // Return a default value or handle the case where the category is not found
        return -1;
    }
    private int getBookNameId(Connection connection, String bookName) throws SQLException {
        String sql = "SELECT id FROM book_names WHERE book_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, bookName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        }
        // Return a default value or handle the case where the book name is not found
        return -1;
    }
    class AutoSuggestListener implements KeyListener {
        private final JTextComponent textField;
        private final JTextArea suggestionsArea;
        private final boolean isBookNameField;

        public AutoSuggestListener(JTextComponent textField, JTextArea suggestionsArea, boolean isBookNameField) {
            this.textField = textField;
            this.suggestionsArea = suggestionsArea;
            this.isBookNameField = isBookNameField;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // Do nothing on keyTyped
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // Do nothing on keyPressed
        }

        @Override
        public void keyReleased(KeyEvent e) {
            SwingUtilities.invokeLater(() -> {
                try {
                    String input = textField.getText().toLowerCase();
                    List<String> suggestions = getSuggestions(input, isBookNameField);
                    updateSuggestions(suggestions);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            });
        }

        private List<String> getSuggestions(String input, boolean isBookNameField) throws SQLException {
            String columnName = isBookNameField ? "book_name" : "author_name";
            String tableName = isBookNameField ? "book_names" : "authors";

            try (Connection connection = connectToDatabase()) {
                String sql = "SELECT DISTINCT " + columnName + " FROM " + tableName + " WHERE LOWER(" + columnName + ") LIKE ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, "%" + input + "%");
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        List<String> suggestions = new ArrayList<>();
                        while (resultSet.next()) {
                            suggestions.add(resultSet.getString(columnName));
                        }
                        return suggestions;
                    }
                }
            }
        }

        private void updateSuggestions(List<String> suggestions) {
            SwingUtilities.invokeLater(() -> {
                suggestionsArea.setText("");
                for (String suggestion : suggestions) {
                    suggestionsArea.append("  "+ suggestion + "\n");
                }
            });
        }
    }
    private JTextField activeTextField;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegisterBook();
        });
    }
}

