package com.Library;
import com.sun.awt.AWTUtilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextField;


public class ReturnBook extends JFrame {

    JTextField studentLibraryIdTextField,DaysField,dueDateTextField,leaseDateTextField,bookNameTextField,authorNameTextField,
            bookIdTextField,libraryBookIdTextField,overTextField,damageText,totalText,existingText,admissionNoTextField,
    libraryIdField,lostText;
    JLabel studentNameLabel,admissionNoLabel,courseLabel,libraryIdlabel,admissionLabel;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    JComboBox shelfField,statusField;
    int  mouseX, mouseY;


    public ReturnBook() {
        setAlwaysOnTop(true);
        setUndecorated(true);
        setSize(830, 650);
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
         admissionLabel = new JLabel("ADMISSION NO: ");
        admissionLabel.setFont(Font1);
        studentNameLabel = new JLabel("STUDENT NAME: ");
        studentNameLabel.setFont(Font1);

        courseLabel = new JLabel("COURSE: ");
        courseLabel.setFont(Font1);

        JButton FetchStu = new JButton("FETCH");
        FetchStu.setFont(Font1);
        FetchStu.setMaximumSize(new Dimension(50, 20));

        admissionNoLabel = new JLabel("ADMISSION NO: ");
        admissionNoLabel.setFont(Font1);
        admissionNoTextField = new JTextField();
        admissionNoTextField.setFont(Font1);
        admissionNoTextField.setForeground(Color.BLUE);
        libraryIdlabel = new JLabel("LIBRARY ID: ");
        libraryIdlabel.setFont(Font1);
        libraryIdField= new JTextField();
        libraryIdField.setFont(Font1);
        libraryIdField.setForeground(Color.BLUE);

        JButton Clr= new JButton("CLEAR");
        Clr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the admission and library ID fields
                admissionNoTextField.setText("");
                libraryIdField.setText("");

            }
        });

        Clr.setFont(Font1);
        Clr.setMaximumSize(new Dimension(50, 20));

        FetchStu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the admission number from the text field
                String admissionNumber = admissionNoTextField.getText();
                String studentLibraryId= libraryIdField.getText();

                // Check if either admission number or library ID is provided
                if (!admissionNumber.isEmpty() && studentLibraryId.isEmpty()) {
                    // Fetch student information by admission number
                    fetchStudentInfo(admissionNumber);
                } else if (admissionNumber.isEmpty() && !studentLibraryId.isEmpty()) {
                    // Fetch student information by library ID
                    fetchStudentInfoWithLibId(studentLibraryId);
            } else if (!admissionNumber.isEmpty() && !studentLibraryId.isEmpty()) {
                // Fetch student information by library ID
                    setAlwaysOnTop(false);
                    JOptionPane.showMessageDialog(null, "Please fill in one of the two.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                    setAlwaysOnTop(true);
            } else {
                    // Show dialog message if both or none of the text fields are filled
                    setAlwaysOnTop(false);
                    JOptionPane.showMessageDialog(null, "Please fill in either admission number or library ID.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                    setAlwaysOnTop(true);
                }
            }
        });

        JPanel studentsTitlePanel = new JPanel();
        studentsTitlePanel.setMaximumSize(new Dimension(getWidth(), 25));
        studentsTitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        studentsTitlePanel.setBackground(new Color(224, 255, 255));

        studentsTitlePanel.add(studentsTitleLabel);
        JPanel studentDetailsPanel = new JPanel(new GridLayout(3,1,10,10));
        studentDetailsPanel.add(studentNameLabel);
               studentDetailsPanel.add(admissionLabel);
        studentDetailsPanel.add(courseLabel);

        studentDetailsPanel.setBackground(new Color(224, 255, 255));
        addBottomGap(studentDetailsPanel,5);

        JPanel studentSearch = new JPanel(new GridLayout(3,2,10,10));
        studentSearch.add(admissionNoLabel);
        studentSearch.add(admissionNoTextField);
        studentSearch.add(libraryIdlabel);
        studentSearch.add(libraryIdField);
        studentSearch.add(FetchStu);
        studentSearch.add(Clr);
        studentSearch.setBackground(new Color(224, 255, 255));
        addBottomGap(studentSearch,5);


// Addition of studentsTitlePanel and studentDetailsPanel to panelOne
        JPanel Divide = new JPanel();
        Divide.setLayout(new GridLayout(1,2));
        Divide.add(studentDetailsPanel);
        Divide.add(studentSearch);
        panelOne.setLayout(new BorderLayout());
        panelOne.add(studentsTitlePanel,BorderLayout.NORTH);
        panelOne.add(Divide,BorderLayout.CENTER);
        panelOne.setPreferredSize(new Dimension(getWidth(), 160));
        panelOne.setBackground(new Color(224, 255, 255));



        // West Panel (Left)
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout());
        westPanel.setBorder(BorderFactory.createLineBorder(new Color(128, 218, 235),3));

        // Search Panel (Top of West Panel)
        JPanel searchPanel = new JPanel();
        searchPanel.setPreferredSize(new Dimension(360, 150));
        //searchPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        searchPanel.setBackground(new Color(224, 255, 255));
        westPanel.add(searchPanel, BorderLayout.NORTH);

        // Bottom Panel (Bottom of West Panel)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(360, getHeight())); // No specific height for the bottom panel
       // bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bottomPanel.setBackground(new Color(224, 255, 255));
        westPanel.add(bottomPanel, BorderLayout.CENTER);

// Book details by name
        JLabel fetchTitleLabel = new JLabel("                 FINES AND CHARGES ");
        fetchTitleLabel.setFont(labelFont);
        fetchTitleLabel.setBorder(BorderFactory.createLineBorder(new Color(128, 218, 235),2));

// Create radio buttons

        JLabel over = new JLabel("OVERDUE FINES:");
        over.setFont(Font1);
        overTextField = new JTextField();
        overTextField.setFont(Font1);
        overTextField.setForeground(Color.RED);
        overTextField.setText("0");
        JLabel damage = new JLabel("DAMAGE FINES:");
        damage.setFont(Font1);
        damageText = new JTextField();
         damageText.setFont(Font1);
         damageText.setForeground(Color.RED);
        damageText.setText("0");
        JLabel  Lost = new JLabel("LOST FINES:");
        Lost.setFont(Font1);
        lostText = new JTextField();
        lostText.setFont(Font1);
        lostText.setText("0");
        lostText.setForeground(Color.RED);
        JLabel  existing = new JLabel("EXISTING FINES:");
        existing.setFont(Font1);
        existingText = new JTextField();
         existingText.setFont(Font1);
         existingText.setText("0");
         existingText.setForeground(Color.RED);
        JLabel total  = new JLabel("TOTAL FINES:");
        total.setFont(Font1);
        totalText = new JTextField();
         totalText.setFont(Font1);
         totalText.setForeground(Color.RED);
         totalText.setText("0");

        overTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateTotal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateTotal();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateTotal();
            }
        });

        damageText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateTotal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateTotal();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateTotal();
            }
        });

        existingText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateTotal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateTotal();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateTotal();
            }
        });
        lostText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateTotal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateTotal();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateTotal();
            }
        });


// Create a panel for labels, text fields, and radio buttons using GridLayout (3x2)
        JPanel detailsPanel = new JPanel(new GridLayout(5, 2,10,15));
        detailsPanel.setBackground(new Color(224, 255, 255));
        addRightLeftGap(detailsPanel,15);
        detailsPanel.add(over);
        detailsPanel.add(overTextField);
        detailsPanel.add(damage);
        detailsPanel.add(damageText);
        detailsPanel.add(Lost);
        detailsPanel.add(lostText);
        detailsPanel.add(existing);
        detailsPanel.add(existingText);
        detailsPanel.add(total);
        detailsPanel.add(totalText); // Placeholder for spacing

// Create a sub-panel for radio buttons using GridLayout (2x3)

// Create a panel for the entire bottom area
        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(224, 255, 255));
        addRightLeftGap(textPanel,20);
        textPanel.setPreferredSize(new Dimension(350, getHeight())); // No specific height for the bottom panel
       // textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //textPanel.setBackground(new Color(224, 255, 255));
        textPanel.setLayout(new BorderLayout());

// Add the title and radioPanel to the top of the textPanel
        textPanel.add(fetchTitleLabel, BorderLayout.NORTH);
        textPanel.add(detailsPanel,BorderLayout.CENTER);
        // Create buttons for "Clear" and "Fetch"  IN THE FINES PANEL
        JButton clearButton = new JButton("CLEAR FINE");
        clearButton.setFont(Font1);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values for studentLibraryIdText and totalFinesText
                String studentLibraryIdText = studentLibraryIdTextField.getText();
                String totalFinesText = totalText.getText();
                String book = bookIdTextField.getText();

                // Call the method
                ClearFinesRecord(studentLibraryIdText,book, totalFinesText);
            }
            private void ClearFinesRecord(String studentLibraryIdText,String book, String totalFinesText) {
                // Check if the student library ID and total fines are not empty
                if (!studentLibraryIdText.isEmpty() && !totalFinesText.isEmpty()&& !book.isEmpty()) {
                    try {
                        // Parse the values to appropriate data types
                        int studentLibraryId = Integer.parseInt(studentLibraryIdText);
                        int bookID = Integer.parseInt(book);
                        double totalFines = Double.parseDouble(totalFinesText);

                        // Display success message only if total fines are greater than 0
                        if (totalFines > 0) {
                            // Insert data into fines table
                            try (Connection connection = connectToDatabase()) {
                                String insertSql = "INSERT INTO fines (student_library_id,book_id, fine_amount, status) VALUES (?,?, ?, 'cleared')";
                                try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                                    insertStatement.setInt(1, studentLibraryId);
                                    insertStatement.setInt(2,bookID);
                                    insertStatement.setDouble(3, totalFines);
                                    insertStatement.executeUpdate();
                                    setAlwaysOnTop(false);
                                    JOptionPane.showMessageDialog(null, "Fines have been Cleared successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    setAlwaysOnTop(true);
                                    damageText.setText("0");
                                    lostText.setText("0");
                                    overTextField.setText("0");
                                    existingText.setText("0");
                                    totalText.setText("0");
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                setAlwaysOnTop(false);
                                JOptionPane.showMessageDialog(null, "Error inserting fine record.", "Error", JOptionPane.ERROR_MESSAGE);
                                setAlwaysOnTop(true);
                            }
                        } else {
                            // Display a message if total fines are not greater than 0
                            setAlwaysOnTop(false);
                            JOptionPane.showMessageDialog(null, "Total fines should be greater than 0.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                            setAlwaysOnTop(true);
                        }

                    } catch (NumberFormatException ex) {
                        // Handle the case where parsing fails
                        ex.printStackTrace();
                        setAlwaysOnTop(false);
                        JOptionPane.showMessageDialog(null, "Invalid input for student library ID,book ID or total fines.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        setAlwaysOnTop(true);
                    }
                } else {
                    // Show a message if the fields are empty
                    setAlwaysOnTop(false);
                    JOptionPane.showMessageDialog(null, "Please fill in both the student library ID,book ID and total fines.", "Empty Fields", JOptionPane.WARNING_MESSAGE);
                    setAlwaysOnTop(true);
                }
            }

        });

        JButton fetchBook = new JButton("FOWARD FINE");
        fetchBook.setFont(Font1);
        fetchBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values for studentLibraryIdText and totalFinesText
                String studentLibraryIdText = studentLibraryIdTextField.getText();
                String totalFinesText = totalText.getText();
                String book = bookIdTextField.getText();

                // Call the method
                insertIntoFinesRecord(studentLibraryIdText,book, totalFinesText);
            }
            private void insertIntoFinesRecord(String studentLibraryIdText,String book, String totalFinesText) {
                // Check if the student library ID and total fines are not empty
                if (!studentLibraryIdText.isEmpty() && !totalFinesText.isEmpty()&& !book.isEmpty()) {
                    try {
                        // Parse the values to appropriate data types
                        int studentLibraryId = Integer.parseInt(studentLibraryIdText);
                        int bookID = Integer.parseInt(book);
                        double totalFines = Double.parseDouble(totalFinesText);

                        // Display success message only if total fines are greater than 0
                        if (totalFines > 0) {
                            // Insert data into fines table
                            try (Connection connection = connectToDatabase()) {
                                String insertSql = "INSERT INTO fines (student_library_id,book_id, fine_amount, status) VALUES (?,?, ?, 'pending')";
                                try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                                    insertStatement.setInt(1, studentLibraryId);
                                    insertStatement.setInt(2,bookID);
                                    insertStatement.setDouble(3, totalFines);
                                    insertStatement.executeUpdate();
                                    setAlwaysOnTop(false);
                                    JOptionPane.showMessageDialog(null, "Fines have been updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    setAlwaysOnTop(true);
                                    damageText.setText("0");
                                    lostText.setText("0");
                                    overTextField.setText("0");
                                    existingText.setText("0");
                                    totalText.setText("0");
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                setAlwaysOnTop(false);
                                JOptionPane.showMessageDialog(null, "Error inserting fine record.", "Error", JOptionPane.ERROR_MESSAGE);
                                setAlwaysOnTop(true);
                            }
                        } else {
                            // Display a message if total fines are not greater than 0
                            setAlwaysOnTop(false);
                            JOptionPane.showMessageDialog(null, "Total fines should be greater than 0.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                            setAlwaysOnTop(true);
                        }

                    } catch (NumberFormatException ex) {
                        // Handle the case where parsing fails
                        ex.printStackTrace();
                        setAlwaysOnTop(false);
                        JOptionPane.showMessageDialog(null, "Invalid input for student library ID,book ID or total fines.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        setAlwaysOnTop(true);
                    }
                } else {
                    // Show a message if the fields are empty
                    setAlwaysOnTop(false);
                    JOptionPane.showMessageDialog(null, "Please fill in both the student library ID,book ID and total fines.", "Empty Fields", JOptionPane.WARNING_MESSAGE);
                    setAlwaysOnTop(true);
                }
            }

        });

// Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(224, 255, 255));
        buttonPanel.add(clearButton);
        buttonPanel.add(fetchBook);
        textPanel.add(buttonPanel,BorderLayout.SOUTH);

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
        JLabel authorNameLabel = new JLabel("BOOK NAME:");
        authorNameLabel.setFont(Font1);
        authorNameTextField = new JTextField(20); // A
        authorNameTextField.setFont(Font1);
        authorNameTextField.setForeground(Color.BLUE);
        JLabel Days = new JLabel("RETURN DATE:");
        Days.setFont(Font1);
        DaysField = new JTextField(20); // Adjust the size as needed
        DaysField.setFont(Font1);
        DaysField.setText(dateFormat.format(new Date()));
        DaysField.setForeground(Color.BLUE);
        JLabel leaseDateLabel = new JLabel("LEASE DATE:");
        leaseDateLabel.setFont(Font1);
        leaseDateTextField = new JTextField(20); // Adjust the size as needed
        leaseDateTextField.setFont(Font1);
        leaseDateTextField.setForeground(Color.BLUE);
        JLabel dueDateLabel = new JLabel("DUE DATE:");
        dueDateLabel.setFont(Font1);
        dueDateTextField = new JTextField(20); // Adjust the size as needed
        dueDateTextField.setFont(Font1);
        dueDateTextField.setForeground(Color.BLUE);
        dueDateTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateFines();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateFines();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateFines();
            }
        });
        JLabel StatusLabel = new JLabel("BOOK STATUS:");
        StatusLabel.setFont(Font1);
        statusField =  new JComboBox<>();
        statusField.setForeground(Color.BLUE);
        statusField.setFont(Font1);
        String[] state = {"Available","Checked Out","On Hold","Lost","Damaged","In Repair" };
        for (String stateItem : state) {
            statusField.addItem(stateItem);
        }
        statusField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the ActionEvent directly
                updateFieldsBasedOnStatus();
            }
        });


        JButton cancelButton = new JButton("CANCEL");
        cancelButton.setFont(Font1);
        JButton leaseBookButton = new JButton("RETURN BOOK");
        leaseBookButton.setFont(Font1);
        // Inside your RegLeaseForm class...
        leaseBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the book ID text from the libraryBookIdTextField
                String bookIdText = bookIdTextField.getText();

                // Check if the text is not empty before parsing to int
                if (!bookIdText.isEmpty()) {

                    try {
                        // Update the lease book table
                        updateLeaseBookTable(bookIdText);
                        updateStatus(bookIdText);

                        // Clear the text fields
                        bookIdTextField.setText("");
                        authorNameTextField.setText("");
                        libraryBookIdTextField.setText("");
                        leaseDateTextField.setText("");
                        dueDateTextField.setText("");
                        bookIdTextField.setText("");

                    }catch (NumberFormatException ex) {
                        // Handle the case where the text in libraryBookIdTextField is not a valid integer
                        ex.printStackTrace();
                        setAlwaysOnTop(false);
                        JOptionPane.showMessageDialog(null, "Invalid Book ID. Please enter a valid Number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        setAlwaysOnTop(true);
                    }

                } else {
                    // Show a message if the libraryBookIdTextField is empty
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
        eastPanel.add(StatusLabel);
        eastPanel.add(statusField);
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
                        fetchBookDetailsById(bookId);
                        fetchLeaseDates(bookId);
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

    private void updateFieldsBasedOnStatus() {
        String selectedStatus = statusField.getSelectedItem().toString();

        try {
            // Update damageTextField and lostTextField based on the selected status
            if ("Damaged".equals(selectedStatus)) {
                damageText.setText("150"); // Set your default value for damage
                lostText.setText("0");
                damageText.setEditable(true);
                lostText.setEditable(false);// Set your default value for lost
            } else if ("Lost".equals(selectedStatus)) {
                damageText.setText("0");
                damageText.setEditable(false);
                lostText.setEditable(true);// Set your default value for damage
                lostText.setText("500"); // Set your default value for lost
            } else {
                // Handle other statuses if needed
                damageText.setText("0"); // Set default values
                damageText.setEditable(false);
                lostText.setEditable(false);
               lostText.setText("0");
            }
        } catch (NumberFormatException e) {
            // Handle the case where the text cannot be parsed to an integer
            e.printStackTrace();
            damageText.setText("0"); // Set default values
            //lostTextField.setText("0");
        }
    }


    private int parseTextField(JTextField textField) {
        String text = textField.getText();
        if (!text.isEmpty()) {
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                // Handle the case where the text is not a valid integer
                totalText.setText("Invalid input");
            }
        }
        return 0; // Default value if text is empty or not a valid integer
    }

    private void calculateTotal() {
        try {
            int overdue = parseTextField(overTextField);
            int damages = parseTextField(damageText);
            int existingCharges = parseTextField(existingText);
            int lost = parseTextField(lostText);

            int total = overdue + damages + existingCharges + lost;
            totalText.setText(String.valueOf(total));
        } catch (NumberFormatException ex) {
            // Handle the case where the text fields do not contain valid integers
            ex.printStackTrace();
            totalText.setText("Invalid input");
        }
    }

    private void calculateFines() {
        try {
            // Get the return date and due date
            String returnDateString = DaysField.getText();
            String dueDateString = dueDateTextField.getText();

            // Check if the date strings match the expected format
            if (isValidDateFormat(returnDateString) && isValidDateFormat(dueDateString)) {
                Date returnDate = dateFormat.parse(returnDateString);
                Date dueDate = dateFormat.parse(dueDateString);

                // Calculate overdue fines
                if (returnDate.after(dueDate)) {
                    long overdueDays = TimeUnit.DAYS.convert(returnDate.getTime() - dueDate.getTime(), TimeUnit.MILLISECONDS);
                    int overdueFines = (int) (overdueDays * 50);
                    overTextField.setText(String.valueOf(overdueFines));
                } else {
                    overTextField.setText("0");
                }
            } else {
                // Handle invalid date format
                overTextField.setText("wrong date");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle parse exception
            overTextField.setText("Check the date");
        }
    }

    // Function to check if the date string matches the expected format
    private boolean isValidDateFormat(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    private void updateStatus(String bookId) {
        try (Connection connection = connectToDatabase()) {
            // Get the selected status from statusField
            String selectedStatus = statusField.getSelectedItem().toString();

            // Update the status based on the selected item
            String updateStatusQuery = "UPDATE books SET status = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateStatusQuery)) {
                preparedStatement.setString(1, selectedStatus);
                preparedStatement.setString(2, bookId);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Status updated successfully");
                } else {
                    System.out.println("Book ID not found or status not updated");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    private String fetchBookDetailsById(int bookId) {
        String query = "SELECT * FROM books WHERE id = ?";
        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int bookNameId = resultSet.getInt("book_name_id");

                String bookName = getBookName(connection, bookNameId);
               // String authorName = getAuthorName(connection, authorId);

                // Set the values to their respective text fields
                authorNameTextField.setText(bookName);
                statusField.setSelectedItem(resultSet.getString("status"));

                return "  Details fetched successfully";
            } else {
                return "  Book not found";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(ReturnBook.this, "Error processing book details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            setAlwaysOnTop(true);
            return "  Error processing book details";
        }
    }
    private void fetchLeaseDates(int bookId) {
        String sql = "SELECT lease_date, due_date FROM lease_book WHERE book_id = ?";

        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, bookId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve lease date and due date from the result set
                    Timestamp leaseDate = resultSet.getTimestamp("lease_date");
                    Timestamp dueDate = resultSet.getTimestamp("due_date");

                    // Set the retrieved dates to the respective text fields
                    leaseDateTextField.setText(dateFormat.format(leaseDate));
                    dueDateTextField.setText(dateFormat.format(dueDate));
                } else {
                    // Handle the case where no record is found for the given book ID
                    JOptionPane.showMessageDialog(this, "No lease record found for the given Book ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching lease dates", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   //fu unction to calculate total based on values in text fields

    // Helper method to parse text from a JTextField
    private String getBookName(Connection connection, int bookNameId) throws SQLException {
        String sql = "SELECT book_name FROM book_names WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, bookNameId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? resultSet.getString("book_name") : "Unknown";
            }
        }
    }

    private void updateLeaseBookTable(String bookId) {
        // Check if the bookId parameter is not empty
        if (!bookId.isEmpty()) {
            // Parse the bookId to an integer
            int searchBookId = Integer.parseInt(bookId);

            // Get the selected status from statusField
            String selectedStatus = statusField.getSelectedItem().toString();

            // Get the current date from DaysField
            String currentDate = DaysField.getText();

            // Create the connection within the method
            try (Connection connection = connectToDatabase()) {
                String updateSql = "UPDATE lease_book SET status = ?, return_date = ? WHERE book_id = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    // Set the status and return_date
                    updateStatement.setString(1, selectedStatus);
                    updateStatement.setString(2, currentDate);
                    updateStatement.setInt(3, searchBookId);

                    int rowsAffected = updateStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Book updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Book update failed", "FAIL", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating book", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Book ID is required for update", "FAIL", JOptionPane.INFORMATION_MESSAGE);
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
                //String phoneNumber = resultSet.getString("phone_number");
              //  String email = resultSet.getString("email");

                //libraryId.setText("<html>LIBRARY ID:<font color='blue'>" + studentLibraryId + "</font></html>");
                studentNameLabel.setText("<html>STUDENT NAME:<font color='blue'>" + names + "</font></html>");
                courseLabel.setText("<html>COURSE:<font color='blue'>" + courseId + "</font></html>");
                if (!admissionNoTextField.getText().isEmpty()) {
                    String adm = admissionNoTextField.getText();
                    admissionLabel.setText("<html>ADMISSION NO:<font color='blue'>" + adm + "</font></html>");
                }

                //phoneNumberLabel.setText("<html>PHONE NUMBER:<font color='blue'>" + phoneNumber + "</font></html>");
                studentLibraryIdTextField.setText(String.valueOf(studentLibraryId));
                libraryIdField.setText(String.valueOf(studentLibraryId));
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
    public void fetchStudentInfoWithLibId(String studentLibraryId) {
        String query = "SELECT * FROM student WHERE student_library_id = ?";

        try (Connection connection = connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            // Set the student library ID parameter
            preparedStatement.setString(1, studentLibraryId);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieve data from the result set
                String names = resultSet.getString("names");
                int courseId = resultSet.getInt("course_id");
                int Adm = resultSet.getInt("admission_number");

                studentNameLabel.setText("<html>STUDENT NAME:<font color='blue'>" + names + "</font></html>");
                courseLabel.setText("<html>COURSE:<font color='blue'>" + courseId + "</font></html>");
                admissionLabel.setText("<html>ADMISSION NO:<font color='blue'>" + Adm + "</font></html>");


                studentLibraryIdTextField.setText(String.valueOf(studentLibraryId));
                admissionNoTextField.setText(String.valueOf(Adm));
            } else {
                setAlwaysOnTop(false);
                JOptionPane.showMessageDialog(this, "Check Library ID and try again!", "Library ID Error", JOptionPane.ERROR_MESSAGE);
                setAlwaysOnTop(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            setAlwaysOnTop(true);
        }
    }

    private void addRightLeftGap(JComponent component, int gap) {
        component.setBorder(BorderFactory.createEmptyBorder(gap, gap, 0,gap));
    }
    private void addBottomGap(JComponent component, int gap) {
        component.setBorder(BorderFactory.createEmptyBorder(0, gap,gap, gap));
    }
    private void addAllGap(JComponent component, int gap) {
        component.setBorder(BorderFactory.createEmptyBorder(gap, gap,gap, gap));
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReturnBook form = new ReturnBook();
          //  form.pack(); // Automatically size the frame to fit the content
            form.setVisible(true);

        });
    }
}
