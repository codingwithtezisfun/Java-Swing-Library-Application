package com.Library;
import com.sun.awt.AWTUtilities;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Calendar;
import java.util.Date;


public class LeaseBook extends JFrame {
    JTextArea suggestionsTextArea;
    JTextField studentLibraryIdTextField,statusTextField,DaysField,dueDateTextField,leaseDateTextField,bookNameTextField,authorNameTextField,
            bookIdTextField,libraryBookIdTextField;
    JLabel libraryId,studentNameLabel,admissionNoLabel,courseLabel,emailLabel,phoneNumberLabel;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    int lineStart,lineEnd, mouseX, mouseY;

    public LeaseBook() {
        setAlwaysOnTop(true);
        setUndecorated(true);
        setSize(830, 700);
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

        setLayout(new BorderLayout());
        Font labelFont = new Font("SansSerif", Font.BOLD, 16); // Example font
        Font Font1 = new Font("SansSerif", Font.BOLD, 16); // Example font

        // Panel One (Top Panel)
        JPanel panelOne = new JPanel();
        // panelOne.add(Box.createRigidArea(new Dimension(5, 5)));

        panelOne.setPreferredSize(new Dimension(600, 150));
        panelOne.setBorder(BorderFactory.createLineBorder(new Color(128, 218, 235),3));
        // panelOne.setBackground(Color.RED);
        add(panelOne, BorderLayout.NORTH);
        // label  title
        JLabel studentsTitleLabel = new JLabel("  STUDENT'S DETAILS  ");
        studentsTitleLabel.setBorder(BorderFactory.createLineBorder(new Color(128, 218, 235), 2));
        studentsTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        libraryId = new JLabel("LIBRARY ID: ");
        libraryId.setFont(Font1);
        studentNameLabel = new JLabel("STUDENT NAME: ");
        studentNameLabel.setFont(Font1);
        admissionNoLabel = new JLabel("ADMISSION NO: ");
        admissionNoLabel.setFont(Font1);
        courseLabel = new JLabel("COURSE: ");
        courseLabel.setFont(Font1);
        emailLabel = new JLabel("EMAIL: ");
        emailLabel.setFont(Font1);
        phoneNumberLabel = new JLabel("PHONE NUMBER: ");
        phoneNumberLabel.setFont(Font1);
        JButton FetchStu = new JButton("FETCH");
        FetchStu.setFont(Font1);
        FetchStu.setMaximumSize(new Dimension(50, 20));
        JButton Clr= new JButton("CLEAR");
        Clr.setFont(Font1);
        Clr.setMaximumSize(new Dimension(50, 20));
        JTextField admissionNoTextField = new JTextField();
        admissionNoTextField.setFont(Font1);
        admissionNoTextField.setForeground(Color.BLUE);

        FetchStu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the admission number from the text field
                String admissionNumber = admissionNoTextField.getText();

                // Check if the text field is not empty
                if (!admissionNumber.isEmpty()) {
                    // Perform the action to fetch student information
                    fetchStudentInfo(admissionNumber);
                } else {
                    // Show dialog message if the text field is empty
                   setAlwaysOnTop(false);
                    JOptionPane.showMessageDialog(null, "Please fill in an admission number.", "Empty Admission Number", JOptionPane.WARNING_MESSAGE);
                   setAlwaysOnTop(true);
                }
            }
        });

        JPanel studentsTitlePanel = new JPanel();
        studentsTitlePanel.setMaximumSize(new Dimension(getWidth(), 25));
        studentsTitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        studentsTitlePanel.setBackground(new Color(224, 255, 255));

        studentsTitlePanel.add(studentsTitleLabel);
        JPanel studentDetailsPanel = new JPanel(new GridBagLayout());
        //  studentDetailsPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        studentDetailsPanel.setBackground(new Color(224, 255, 255));
        addBottomGap(studentDetailsPanel,5);
        JPanel studentSearch = new JPanel(new GridBagLayout());
        //studentSearch.setPreferredSize(new Dimension(getWidth(), getHeight()));
        studentSearch.setBackground(new Color(224, 255, 255));
        addBottomGap(studentSearch,5);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 40);

        gbc.gridy=0;
        gbc.gridx=0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        studentSearch.add(admissionNoLabel, gbc);

        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        studentSearch.add(admissionNoTextField, gbc);
        // gbc.insets = new Insets(0, 0, 0, 40);

        gbc.gridx++;
        studentSearch.add(FetchStu, gbc);
        gbc.gridx++;
        studentSearch.add(Clr, gbc);
        //other
        GridBagConstraints gb = new GridBagConstraints();
        gb.insets = new Insets(1, 0, 1, 100);

        gb.gridx = 0;
        gb.gridy=0;
        gb.anchor = GridBagConstraints.WEST;
        studentDetailsPanel.add(libraryId, gb);

        gb.gridx++;
        studentDetailsPanel.add(studentNameLabel, gb);
        gb.anchor = GridBagConstraints.EAST;

        gb.gridx = 0;
        gb.gridy++;
        studentDetailsPanel.add(courseLabel, gb);

        gb.anchor = GridBagConstraints.WEST;

        gb.gridx++;
        studentDetailsPanel.add(emailLabel, gb);

        gb.gridx = 0;
        gb.gridy++;
        studentDetailsPanel.add(phoneNumberLabel, gb);
        gb.anchor = GridBagConstraints.WEST;



// Addition of studentsTitlePanel and studentDetailsPanel to panelOne
        panelOne.setLayout(new BorderLayout());
        panelOne.add(studentsTitlePanel,BorderLayout.NORTH);
        panelOne.add(studentDetailsPanel,BorderLayout.CENTER);
        panelOne.add(studentSearch,BorderLayout.SOUTH);
        panelOne.setPreferredSize(new Dimension(getWidth(), 160));
        panelOne.setBackground(new Color(224, 255, 255));



        // West Panel (Left)
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout());
        westPanel.setBorder(BorderFactory.createLineBorder(new Color(128, 218, 235),3));

        // Search Panel (Top of West Panel)
        JPanel searchPanel = new JPanel();
        searchPanel.setPreferredSize(new Dimension(350, 150));
        //searchPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        searchPanel.setBackground(new Color(224, 255, 255));
        westPanel.add(searchPanel, BorderLayout.NORTH);

        // Bottom Panel (Bottom of West Panel)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(350, getHeight())); // No specific height for the bottom panel
        // bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottomPanel.setBackground(new Color(224, 255, 255));
        westPanel.add(bottomPanel, BorderLayout.CENTER);

// Book details by name
        JLabel fetchTitleLabel = new JLabel("  FETCH BOOK DETAILS BY NAME ");
        fetchTitleLabel.setFont(labelFont);
        fetchTitleLabel.setBorder(BorderFactory.createLineBorder(new Color(128, 218, 235),2));

// Create radio buttons

        JLabel bookNameLabel = new JLabel("BOOK NAME:");
        bookNameLabel.setFont(Font1);
        Dimension preferredSize = new Dimension(200, 20); // Adjust the height as needed
        bookNameTextField = new JTextField();
        bookNameTextField.setFont(Font1);
        bookNameTextField.setPreferredSize(preferredSize);
        bookNameTextField.setForeground(Color.BLUE);
        bookNameTextField.addKeyListener(new AutoSuggestListener(bookNameTextField, suggestionsTextArea, true));

        suggestionsTextArea = new JTextArea(5,20);
        suggestionsTextArea.setBackground(new Color(175,238,238));
        suggestionsTextArea.setForeground(new Color(232,0,13));
        suggestionsTextArea.setFont(new Font("Arial", Font.BOLD, 20));
        suggestionsTextArea.setEditable(false);
        suggestionsTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int caretPosition = suggestionsTextArea.viewToModel(e.getPoint());
                    lineStart = suggestionsTextArea.getLineStartOffset(suggestionsTextArea.getLineOfOffset(caretPosition));
                    lineEnd = suggestionsTextArea.getLineEndOffset(suggestionsTextArea.getLineOfOffset(caretPosition));
                    String clickedSuggestion = suggestionsTextArea.getText().substring(lineStart, lineEnd).trim();

                    if (isBookIdSuggestion(clickedSuggestion)) {
                        libraryBookIdTextField.setText(String.valueOf(Integer.parseInt(clickedSuggestion.trim())));
                        fetchCategoryAndShelfInfo();
                    } else if (isBookNameSuggestion(clickedSuggestion)) {
                        bookNameTextField.setText(clickedSuggestion);
                    }

                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });




// Create a panel for labels, text fields, and radio buttons using GridLayout (3x2)
        JPanel detailsPanel = new JPanel(new GridLayout(3, 2,10,10));
        detailsPanel.setBackground(new Color(224, 255, 255));
        addRightLeftGap(detailsPanel,15);
        detailsPanel.add(new JLabel());
        detailsPanel.add(new JPanel());
        detailsPanel.add(bookNameLabel);
        detailsPanel.add(bookNameTextField);
        detailsPanel.add(new JLabel());
        detailsPanel.add(new JPanel()); // Placeholder for spacing

// Create a sub-panel for radio buttons using GridLayout (2x3)

// Create a panel for the entire bottom area
        JPanel textPanel = new JPanel();
        addRightLeftGap(textPanel,20);
        textPanel.setPreferredSize(new Dimension(350, getHeight())); // No specific height for the bottom panel
        // textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textPanel.setBackground(new Color(224, 255, 255));
        textPanel.setLayout(new BorderLayout());

// Add the title and radioPanel to the top of the textPanel
        textPanel.add(fetchTitleLabel, BorderLayout.NORTH);
        // Create buttons for "Clear" and "Fetch"
        JButton clearButton = new JButton("   CLEAR   ");
        clearButton.setFont(Font1);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the text fields
                bookNameTextField.setText("");
                bookIdTextField.setText("");
                authorNameTextField.setText("");
                libraryBookIdTextField.setText("");
                suggestionsTextArea.setText("");
            }
        });

        JButton fetchBook = new JButton("   FETCH   ");
        fetchBook.setFont(Font1);
        fetchBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected book name from the text field
                String selectedBookName = bookNameTextField.getText();

                // Check if the text field is not empty
                if (!selectedBookName.isEmpty()) {
                    try {
                        // Fetch book details based on book name and populate suggestions area with book IDs
                        List<Integer> bookIds = fetchBookDetails(selectedBookName);

                        if (!bookIds.isEmpty()) {
                            // Book details found, display book IDs
                            suggestionsTextArea.setText(String.join("\n", bookIds.stream().map(String::valueOf).collect(Collectors.toList())));
                        } else {
                            // Book name not found, show message
                          setAlwaysOnTop(false);
                            JOptionPane.showMessageDialog(null, "Book name not found.", "Book Not Found", JOptionPane.WARNING_MESSAGE);
                            setAlwaysOnTop(true);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    // Show dialog message if the text field is empty
                    setAlwaysOnTop(false);
                    JOptionPane.showMessageDialog(null, "Please fill in a book name.", "Empty Book Name", JOptionPane.WARNING_MESSAGE);
                    setAlwaysOnTop(true);
                }
            }
        });


// Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(224, 255, 255));
        buttonPanel.add(clearButton);
        buttonPanel.add(fetchBook);

// Create a sub-panel for detailsPanel and textArea using BorderLayout
        JPanel subPanel = new JPanel(new BorderLayout());
        subPanel.add(detailsPanel, BorderLayout.NORTH);
        subPanel.add(new JScrollPane(suggestionsTextArea), BorderLayout.CENTER);
        subPanel.add(buttonPanel, BorderLayout.SOUTH);

// Add the subPanel to the textPanel
        textPanel.add(subPanel, BorderLayout.CENTER);

// Add the textPanel to the bottomPanel
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(textPanel, BorderLayout.CENTER);

// Add the bottomPanel to westPanel as before
        westPanel.add(bottomPanel, BorderLayout.CENTER);

        add(westPanel, BorderLayout.WEST);
        // East Panel (Right)
        JPanel eastPanel = new JPanel();
        eastPanel.setBackground(new Color(224, 255, 255));
        eastPanel.setPreferredSize(new Dimension(400, getHeight())); // No specific height for the east panel
        //  eastPanel.setBorder(BorderFactory.createLineBorder(new Color(128, 218, 235),3));
        eastPanel.setLayout(new GridLayout(8, 2,10,20));
        addAllGap(eastPanel,15);
        add(eastPanel, BorderLayout.CENTER);

// Create labels and text fields for the fields you mentioned
        JLabel studentLibraryIdLabel = new JLabel("STUDENT'S LIBRARY ID:");
        studentLibraryIdLabel.setFont(Font1);
        studentLibraryIdTextField = new JTextField(20); // Adjust the size as needed
        studentLibraryIdTextField.setFont(Font1);
        studentLibraryIdTextField.setForeground(Color.BLUE);
        JLabel bookIdLabel = new JLabel("BOOK ID:");
        bookIdLabel.setFont(Font1);
        bookIdTextField = new JTextField(20); // Adjust the size as needed
        bookIdTextField.setFont(Font1);
        bookIdTextField.setForeground(Color.BLUE);
        JLabel authorNameLabel = new JLabel("AUTHOR NAME:");
        authorNameLabel.setFont(Font1);
        authorNameTextField = new JTextField(20); // A
        authorNameTextField.setFont(Font1);
        authorNameTextField.setForeground(Color.BLUE);
        JLabel Days = new JLabel("NUMBER OF LEASE DAYS:");
        Days.setFont(Font1);
        DaysField = new JTextField(20); // Adjust the size as needed
        DaysField.setFont(Font1);
        DaysField.setForeground(Color.BLUE);
        DaysField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                calculateReturnDate();
            }
        });

        JLabel leaseDateLabel = new JLabel("LEASE DATE:");
        leaseDateLabel.setFont(Font1);
        leaseDateTextField = new JTextField(20); // Adjust the size as needed
        leaseDateTextField.setFont(Font1);
        leaseDateTextField.setText(dateFormat.format(new Date()));
        leaseDateTextField.setForeground(Color.BLUE);
        JLabel dueDateLabel = new JLabel("DUE DATE:");
        dueDateLabel.setFont(Font1);
        dueDateTextField = new JTextField(20); // Adjust the size as needed
        dueDateTextField.setFont(Font1);
        dueDateTextField.setForeground(Color.BLUE);
        dueDateTextField.setText("Fill in the No of lease days");
        JLabel statusLabel = new JLabel("STATUS:");
        statusLabel.setFont(Font1);
        statusTextField = new JTextField(20); // Adjust the size as needed
        statusTextField.setForeground(Color.BLUE);
        statusTextField.setFont(Font1);

        JButton cancelButton = new JButton("CANCEL");
        cancelButton.setFont(Font1);
        JButton leaseBookButton = new JButton("LEASE BOOK");
        leaseBookButton.setFont(Font1);
        // Inside your RegLeaseForm class...
        leaseBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method to insert lease book data
                insertLeaseBookData();

                // Get the book ID from the text field
                String bookIdText = bookIdTextField.getText();

                // Check if the text is not empty before parsing to int
                if (!bookIdText.isEmpty()) {
                    try {
                        // Convert the book ID to an integer
                        int bookId = Integer.parseInt(bookIdText);

                        // Call the method to update the book status
                        updateStatus(bookId);

                        // Clear the text fields
                        bookIdTextField.setText("");
                        authorNameTextField.setText("");
                        statusTextField.setText("");
                        dueDateTextField.setText("Fill in the No of lease days");
                        DaysField.setText("");
                        bookNameTextField.setText("");
                        libraryBookIdTextField.setText("");
                        suggestionsTextArea.setText("Continue lease.");
                    } catch (NumberFormatException ex) {
                        // Handle the case where the text in bookIdTextField is not a valid integer
                        ex.printStackTrace();
                        setAlwaysOnTop(false);
                        JOptionPane.showMessageDialog(null, "Invalid Book ID. Please enter a valid Number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        setAlwaysOnTop(true);
                         }
                } else {
                    // Show a message if the book ID field is empty
                    setAlwaysOnTop(false);
                    JOptionPane.showMessageDialog(null, "Please fill in a book ID.", "Empty Book ID field", JOptionPane.WARNING_MESSAGE);
                    setAlwaysOnTop(true);
                    }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method to insert lease book data
                dispose();
            }
        });



        eastPanel.add(studentLibraryIdLabel);
        eastPanel.add(studentLibraryIdTextField);
        eastPanel.add(bookIdLabel);
        eastPanel.add(bookIdTextField);
        eastPanel.add(authorNameLabel);
        eastPanel.add(authorNameTextField);
        eastPanel.add(Days);
        eastPanel.add(DaysField);
        eastPanel.add(leaseDateLabel);
        eastPanel.add(leaseDateTextField);
        eastPanel.add(dueDateLabel);
        eastPanel.add(dueDateTextField);
        eastPanel.add(statusLabel);
        eastPanel.add(statusTextField);
        eastPanel.add(cancelButton);
        eastPanel.add(leaseBookButton);

        JLabel titleLabel = new JLabel("  FETCH BOOK DETAILS BY ID  ");
        titleLabel.setFont(labelFont);
        titleLabel.setBorder(BorderFactory.createLineBorder(new Color(128, 218, 235),2));
        JLabel libraryIdLabel = new JLabel("BOOK ID:");
        libraryIdLabel.setFont(Font1);
        libraryBookIdTextField = new JTextField(10);
        libraryBookIdTextField.setFont(Font1);
        libraryBookIdTextField.setForeground(Color.BLUE);

        JButton fetchButton = new JButton("FETCH");
        fetchButton.setFont(Font1);
// Add an ActionListener to fetchButton

        // Helper method to get category name from selected radio buttons
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookIdText = libraryBookIdTextField.getText();

                if (!bookIdText.isEmpty()) {
                    try {
                        int bookId = Integer.parseInt(bookIdText);
                        String result = fetchBookDetailsById(bookId);
                        suggestionsTextArea.setText(result);
                        bookIdTextField.setText(bookIdText);
                    } catch (NumberFormatException ex) {
                        setAlwaysOnTop(false);
                        JOptionPane.showMessageDialog(null, "Invalid Book Id. Please enter a valid Number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        setAlwaysOnTop(true);
                    }
                } else {
                    setAlwaysOnTop(false);
                    JOptionPane.showMessageDialog(null, "Please fill in a book Id.", "Empty Book Id field", JOptionPane.WARNING_MESSAGE);
                    setAlwaysOnTop(true);
                }
            }
        });


        // Create a panel for the title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(224, 255, 255));
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(titleLabel);

// Create a panel for labels, text field, and button using GridLayout
        JPanel detailsPanel2 = new JPanel(new GridLayout(2, 2,10,10));
        detailsPanel2.setBackground(new Color(224, 255, 255));
        addBottomGap(detailsPanel2,15);
        detailsPanel2.add(libraryIdLabel);
        detailsPanel2.add(libraryBookIdTextField);
        detailsPanel2.add(new JLabel());
        detailsPanel2.add(fetchButton);

        searchPanel.setLayout(new GridLayout(2, 1));
        searchPanel.add(titlePanel);
        searchPanel.setBackground(new Color(224, 255, 255));
        searchPanel.add(detailsPanel2);
        westPanel.add(searchPanel, BorderLayout.NORTH);
    }

    private Connection connectToDatabase() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/library";
        String dbUser = "root";
        String dbPassword = "";

        return DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
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
            String columnName = isBookNameField ? "book_name" : "id"; // Use "id" for book_name_id
            String tableName = isBookNameField ? "book_names" : "books";

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
            StringBuilder sb = new StringBuilder();
            for (String suggestion : suggestions) {
                sb.append("   ").append(suggestion).append("\n");
            }
            suggestionsTextArea.setText(sb.toString());
        }
    }
    private void updateStatus(int bookId) {
        try (Connection connection = connectToDatabase()) {
            String updateStatusQuery = "UPDATE books SET status = 'Checked Out' WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateStatusQuery)) {
                preparedStatement.setInt(1, bookId);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Status updated successfully");
                } else {
                    System.out.println("Book ID not found or status already set to Checked Out");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
    private List<Integer> fetchBookDetails(String bookName) throws SQLException {
        try (Connection connection = connectToDatabase()) {
            // Fetch book_name_id from book_names table based on book_name
            String getIdSql = "SELECT id FROM book_names WHERE book_name = ?";
            try (PreparedStatement getIdStatement = connection.prepareStatement(getIdSql)) {
                getIdStatement.setString(1, bookName);
                try (ResultSet idResultSet = getIdStatement.executeQuery()) {
                    if (idResultSet.next()) {
                        int bookNameId = idResultSet.getInt("id");

                        // Fetch book details from books table based on book_name_id
                        String getBooksSql = "SELECT id FROM books WHERE book_name_id = ?";
                        try (PreparedStatement getBooksStatement = connection.prepareStatement(getBooksSql)) {
                            getBooksStatement.setInt(1, bookNameId);
                            try (ResultSet booksResultSet = getBooksStatement.executeQuery()) {
                                List<Integer> bookIds = new ArrayList<>();
                                while (booksResultSet.next()) {
                                    bookIds.add(booksResultSet.getInt("id"));
                                }
                                return bookIds;
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
           setAlwaysOnTop(false);
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error processing book information: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            setAlwaysOnTop(true);
        }

        // Return an empty list in case of an error
        return Collections.emptyList();
    }

    private String fetchBookDetailsById(int bookId) {
        String query = "SELECT * FROM books WHERE id = ?";
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int bookNameId = resultSet.getInt("book_name_id");
                int authorId = resultSet.getInt("author_id");

                String bookName = getBookName(connection, bookNameId);
                String authorName = getAuthorName(connection, authorId);

                // Set the values to their respective text fields
                bookNameTextField.setText(bookName);
                authorNameTextField.setText(authorName);
                statusTextField.setText(resultSet.getString("status"));

                return "  Details fetched successfully";
            } else {
                return "  Book not found";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(LeaseBook.this, "Error processing book details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            setAlwaysOnTop(true);
            return "  Error processing book details";
        }
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
    private boolean isBookNameSuggestion(String suggestion) {

        return suggestion.matches(".*[a-zA-Z].*");
    }
    private boolean isBookIdSuggestion(String suggestion) {
        // Check if the suggestion is a positive integer
        return suggestion.matches("\\d+") && Integer.parseInt(suggestion) >= 0;
    }
    // Modify the method signature to remove the bookId parameter
    private void fetchCategoryAndShelfInfo() {
        String clickedSuggestion = suggestionsTextArea.getText().substring(lineStart, lineEnd).trim();

        if (clickedSuggestion != null && isBookIdSuggestion(clickedSuggestion)) {
            int bookId = Integer.parseInt(clickedSuggestion);

            try (Connection connection = connectToDatabase()) {
                // Query to fetch category ID, shelf number, and other necessary information
                String sql = "SELECT category_id, shelf_number, status FROM books WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, bookId);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            int categoryId = resultSet.getInt("category_id");
                            int shelfNumber = resultSet.getInt("shelf_number");
                            String status = resultSet.getString("status");

                            // Fetch category name using the category ID
                            String categoryName = getCategoryName(categoryId);

                            if (categoryName != null) {
                                // Display the information in a popup
                                String message = "Category: " + categoryName + "\nShelf Number: " + shelfNumber + "\nStatus: " + status;
                               setAlwaysOnTop(false);
                                JOptionPane.showMessageDialog(LeaseBook.this, message, "Book Information", JOptionPane.INFORMATION_MESSAGE);
                                setAlwaysOnTop(true);
                            } else {
                               setAlwaysOnTop(false);
                                JOptionPane.showMessageDialog(LeaseBook.this, "Category not found", "Error", JOptionPane.ERROR_MESSAGE);
                               setAlwaysOnTop(true);
                            }
                        } else {
                            setAlwaysOnTop(false);
                            JOptionPane.showMessageDialog(LeaseBook.this, "Book not found", "Error", JOptionPane.ERROR_MESSAGE);
                            setAlwaysOnTop(true);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                setAlwaysOnTop(false);
                JOptionPane.showMessageDialog(LeaseBook.this, "Error fetching book information", "Error", JOptionPane.ERROR_MESSAGE);
                setAlwaysOnTop(true);
            }
        } else {
            setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(LeaseBook.this, "Invalid book ID", "Error", JOptionPane.ERROR_MESSAGE);
            setAlwaysOnTop(true);
        }
    }

    // Method to fetch category name by category ID
    private String getCategoryName(int categoryId) {
        try (Connection connection = connectToDatabase()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void insertLeaseBookData() {
        String studentLibraryIdText = studentLibraryIdTextField.getText();
        String bookIdText = bookIdTextField.getText();
        String leaseDateText = leaseDateTextField.getText();
        String daysText = DaysField.getText();
        String statusText = statusTextField.getText();

        // Get the return date directly from the text field
        String returnDateText = dueDateTextField.getText();

        // Validate input
        if (studentLibraryIdText.isEmpty() || bookIdText.isEmpty() || leaseDateText.isEmpty() ||
                daysText.isEmpty() || statusText.isEmpty() || returnDateText.isEmpty()) {
            setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            setAlwaysOnTop(true);
            return;
        }

        try {
            int studentLibraryId = Integer.parseInt(studentLibraryIdText);
            int bookId = Integer.parseInt(bookIdText);
            Date leaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(leaseDateText);


            // You can use returnDateText directly, assuming it is in the right format for your SQL database

            try (Connection connection = connectToDatabase()) {
                String sql = "INSERT INTO lease_book (student_library_id, book_id, lease_date, due_date, status) VALUES (?, ?, ?, ?,'Checked Out')";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, studentLibraryId);
                    preparedStatement.setInt(2, bookId);
                    preparedStatement.setDate(3, new java.sql.Date(leaseDate.getTime()));

                    // Assuming returnDateText is already in the correct format for your SQL database
                    preparedStatement.setString(4, returnDateText);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                       setAlwaysOnTop(false);
                        JOptionPane.showMessageDialog(this, "Lease information inserted successfully\n" +"    BOOK STATUS: Checked Out", "Success", JOptionPane.INFORMATION_MESSAGE);
                        setAlwaysOnTop(true);
                    } else {
                       setAlwaysOnTop(false);
                        JOptionPane.showMessageDialog(this, "Error inserting lease information", "Error", JOptionPane.ERROR_MESSAGE);
                        setAlwaysOnTop(true);
                    }
                }
            }
        } catch (NumberFormatException | ParseException | SQLException e) {
            e.printStackTrace();
            setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(this, "Error processing lease information: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            setAlwaysOnTop(true);
        }
    }

    private void calculateReturnDate() {
        try {
            int numberOfDays = Integer.parseInt(DaysField.getText());

            Date currentDate = new Date();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
            //  MySQL date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String returnDate = dateFormat.format(calendar.getTime());

            // Set the return date in the return date field
            dueDateTextField.setText(returnDate);

        } catch (NumberFormatException ex) {
            dueDateTextField.setText("Fill in the No of days");
        }
    }
    // Method to set text fields with student information
    public void fetchStudentInfo(String admissionNumber) {
        String query = "SELECT * FROM student WHERE admission_number = ?";

        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            // Set the admission number parameter
            preparedStatement.setString(1, admissionNumber);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieve data from the result set
                int studentLibraryId = resultSet.getInt("student_library_id");
                String names = resultSet.getString("names");
                int courseId = resultSet.getInt("course_id");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");

                libraryId.setText("<html>LIBRARY ID:<font color='blue'>" + studentLibraryId + "</font></html>");
                studentNameLabel.setText("<html>STUDENT NAME:<font color='blue'>" + names + "</font></html>");
                courseLabel.setText("<html>COURSE:<font color='blue'>" + courseId + "</font></html>");
                emailLabel.setText("<html>EMAIL:<font color='blue'>" + email + "</font></html>");
                phoneNumberLabel.setText("<html>PHONE NUMBER:<font color='blue'>" + phoneNumber + "</font></html>");
                studentLibraryIdTextField.setText(String.valueOf(studentLibraryId));
            } else {
                setAlwaysOnTop(false);
                JOptionPane.showMessageDialog(this, "Check admission No and try again!: " , "Admission No Error", JOptionPane.ERROR_MESSAGE);
                setAlwaysOnTop(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
           setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(this, "Error : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            setAlwaysOnTop(true);
        }
    }
    private void addLeftGap(JComponent component, int gap) {
        component.setBorder(BorderFactory.createEmptyBorder(0,gap, 0, 0));
    }

    private void addRightLeftGap(JComponent component, int gap) {
        component.setBorder(BorderFactory.createEmptyBorder(0, gap, 0,gap));
    }
    private void addBottomGap(JComponent component, int gap) {
        component.setBorder(BorderFactory.createEmptyBorder(0, gap,gap, gap));
    }
    private void addAllGap(JComponent component, int gap) {
        component.setBorder(BorderFactory.createEmptyBorder(gap, gap,gap, gap));
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LeaseBook fm = new LeaseBook();
            //  form.pack(); // Automatically size the frame to fit the content
            fm.setVisible(true);

        });
    }
}
