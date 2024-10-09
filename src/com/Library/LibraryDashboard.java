package com.Library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryDashboard extends javax.swing.JFrame {
    public LibraryDashboard() {
        initComponents();
    }
    @SuppressWarnings("unchecked")

    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        DashboardTitlePanel = new javax.swing.JPanel();
        DashboardLabel = new javax.swing.JLabel();
        StudentPanel = new javax.swing.JPanel();
        BooksPanel = new javax.swing.JPanel();
        PatronsPanel = new javax.swing.JPanel();
        UserPanel = new javax.swing.JPanel();
        CheckedOutPanel = new javax.swing.JPanel();
        AvailablePanel = new javax.swing.JPanel();
        LostBooksPanel = new javax.swing.JPanel();
        DamagedPanel = new javax.swing.JPanel();
        PendingFines = new javax.swing.JPanel();
        PayFines = new javax.swing.JPanel();
        TimePanel = new javax.swing.JPanel();
        RemindersPanel = new javax.swing.JPanel();
        RemindersLabel = new javax.swing.JLabel();
        BooksCategoryPanel = new javax.swing.JPanel();
        CategoryLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        BookMangementPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        leaseBook = new javax.swing.JButton();
        ReturnBook = new javax.swing.JButton();
        RegLeaseButton = new javax.swing.JButton();
        fines = new javax.swing.JButton();
        BottomPanel = new javax.swing.JPanel();
        AdminPanel = new javax.swing.JPanel();
        AdminButton = new javax.swing.JButton();
        TitleLabel = new javax.swing.JLabel();
        OnHoldPanel = new javax.swing.JPanel();
        MenuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainPanel.setBackground(new java.awt.Color(102, 102, 102));

        DashboardTitlePanel.setBackground(new java.awt.Color(153, 0, 204));
        DashboardTitlePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 255), 2));

        DashboardLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        DashboardLabel.setForeground(new java.awt.Color(0, 0, 153));
        DashboardLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DashboardLabel.setText("DASHBOARD");

        javax.swing.GroupLayout DashboardTitlePanelLayout = new javax.swing.GroupLayout(DashboardTitlePanel);
        DashboardTitlePanel.setLayout(DashboardTitlePanelLayout);
        DashboardTitlePanelLayout.setHorizontalGroup(
                DashboardTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(DashboardTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(DashboardTitlePanelLayout.createSequentialGroup()
                                        .addGap(0, 66, Short.MAX_VALUE)
                                        .addComponent(DashboardLabel)
                                        .addGap(0, 66, Short.MAX_VALUE)))
        );
        DashboardTitlePanelLayout.setVerticalGroup(
                DashboardTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 52, Short.MAX_VALUE)
                        .addGroup(DashboardTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(DashboardTitlePanelLayout.createSequentialGroup()
                                        .addGap(0, 10, Short.MAX_VALUE)
                                        .addComponent(DashboardLabel)
                                        .addGap(0, 10, Short.MAX_VALUE)))
        );

        StudentPanel.setBackground(new java.awt.Color(204, 102, 255));
        StudentPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 153), 3));

        javax.swing.GroupLayout StudentPanelLayout = new javax.swing.GroupLayout(StudentPanel);
        StudentPanel.setLayout(StudentPanelLayout);

        TotalMembers member = new TotalMembers();
        StudentPanel.add(member);

        StudentPanelLayout.setHorizontalGroup(
                StudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(member, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        StudentPanelLayout.setVerticalGroup(
                StudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(member, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

// Revalidate the layout
        StudentPanel.revalidate();

        TotalBooks books = new TotalBooks();
        StudentPanel.add(books);
        BooksPanel.setBackground(new java.awt.Color(204, 102, 255));
        BooksPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 153), 3));

        javax.swing.GroupLayout BooksPanelLayout = new javax.swing.GroupLayout(BooksPanel);
        BooksPanel.setLayout(BooksPanelLayout);
        BooksPanelLayout.setHorizontalGroup(
                BooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(books, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BooksPanelLayout.setVerticalGroup(
                BooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(books, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BooksPanel.revalidate();
        TotalPatrons pat = new TotalPatrons();
        PatronsPanel.add(pat);

        PatronsPanel.setBackground(new java.awt.Color(204, 102, 255));
        PatronsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 153), 3));

        javax.swing.GroupLayout PatronsPanelLayout = new javax.swing.GroupLayout(PatronsPanel);
        PatronsPanel.setLayout(PatronsPanelLayout);
        PatronsPanelLayout.setHorizontalGroup(
                PatronsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PatronsPanelLayout.setVerticalGroup(
                PatronsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PatronsPanel.revalidate();
        WelcomeMessage welcome = new WelcomeMessage();
        UserPanel.add(welcome);
        UserPanel.setBackground(new java.awt.Color(204, 153, 255));
        UserPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 153)));

        javax.swing.GroupLayout UserPanelLayout = new javax.swing.GroupLayout(UserPanel);
        UserPanel.setLayout(UserPanelLayout);
        UserPanelLayout.setHorizontalGroup(
                UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(welcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        UserPanelLayout.setVerticalGroup(
                UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(welcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
       CheckedOutBooks check = new CheckedOutBooks();
       CheckedOutPanel.add(check);
        CheckedOutPanel.setBackground(new java.awt.Color(204, 102, 255));
        CheckedOutPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));

        javax.swing.GroupLayout CheckedOutPanelLayout = new javax.swing.GroupLayout(CheckedOutPanel);
        CheckedOutPanel.setLayout(CheckedOutPanelLayout);
        CheckedOutPanelLayout.setHorizontalGroup(
                CheckedOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(check, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CheckedOutPanelLayout.setVerticalGroup(
                CheckedOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(check, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AvailableBooks avail = new AvailableBooks();
        AvailablePanel.add(avail);
        AvailablePanel.setBackground(new java.awt.Color(204, 102, 255));
        AvailablePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));

        javax.swing.GroupLayout AvailablePanelLayout = new javax.swing.GroupLayout(AvailablePanel);
        AvailablePanel.setLayout(AvailablePanelLayout);
        AvailablePanelLayout.setHorizontalGroup(
                AvailablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(avail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AvailablePanelLayout.setVerticalGroup(
                AvailablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(avail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        LostBooks lost = new LostBooks();
        LostBooksPanel.add(lost);
        LostBooksPanel.setBackground(new java.awt.Color(204, 102, 255));
        LostBooksPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));

        javax.swing.GroupLayout LostBooksPanelLayout = new javax.swing.GroupLayout(LostBooksPanel);
        LostBooksPanel.setLayout(LostBooksPanelLayout);
        LostBooksPanelLayout.setHorizontalGroup(
                LostBooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        LostBooksPanelLayout.setVerticalGroup(
                LostBooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DamagedBooks damage = new DamagedBooks();
        DamagedPanel.add(damage);
        DamagedPanel.setBackground(new java.awt.Color(204, 102, 255));
        DamagedPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 51, 255), 2));

        javax.swing.GroupLayout DamagedPanelLayout = new javax.swing.GroupLayout(DamagedPanel);
        DamagedPanel.setLayout(DamagedPanelLayout);
        DamagedPanelLayout.setHorizontalGroup(
                DamagedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(damage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DamagedPanelLayout.setVerticalGroup(
                DamagedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(damage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        InRepairBooks rep = new InRepairBooks();
        PendingFines.add(rep);
        PendingFines.setBackground(new java.awt.Color(204, 102, 255));
        PendingFines.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 2));

        javax.swing.GroupLayout PendingFinesLayout = new javax.swing.GroupLayout(PendingFines);
        PendingFines.setLayout(PendingFinesLayout);
        PendingFinesLayout.setHorizontalGroup(
                PendingFinesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PendingFinesLayout.setVerticalGroup(
                PendingFinesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TotalFines total = new TotalFines();
        PayFines.add(total);
        PayFines.setBackground(new java.awt.Color(204, 102, 255));
        PayFines.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 255), 2));

        javax.swing.GroupLayout PayFinesLayout = new javax.swing.GroupLayout(PayFines);
        PayFines.setLayout(PayFinesLayout);
        PayFinesLayout.setHorizontalGroup(
                PayFinesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PayFinesLayout.setVerticalGroup(
                PayFinesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        com.Library.TimePanel time = new TimePanel();
        TimePanel.add(time);
        TimePanel.setBackground(new java.awt.Color(204, 153, 255));
        TimePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 255), 6, true));

        javax.swing.GroupLayout TimePanelLayout = new javax.swing.GroupLayout(TimePanel);
        TimePanel.setLayout(TimePanelLayout);
        TimePanelLayout.setHorizontalGroup(
                TimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TimePanelLayout.setVerticalGroup(
                TimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        RemindersPanel.setBackground(new java.awt.Color(204, 153, 255));
        RemindersPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 255)));
        ReminderPanel rem = new ReminderPanel();
        RemindersPanel.add(rem);
        javax.swing.GroupLayout RemindersPanelLayout = new javax.swing.GroupLayout(RemindersPanel);
        RemindersPanel.setLayout(RemindersPanelLayout);
        RemindersPanelLayout.setHorizontalGroup(
                RemindersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(RemindersPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(rem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        RemindersPanelLayout.setVerticalGroup(
                RemindersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(RemindersPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(rem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap(223, Short.MAX_VALUE))
        );

        BooksCategoryPanel.setBackground(new java.awt.Color(204, 153, 255));
        BooksCategoryPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 255)));

        CategoryLabel.setBackground(new java.awt.Color(204, 153, 255));
        CategoryLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        CategoryLabel.setForeground(new java.awt.Color(0, 0, 102));
        CategoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CategoryLabel.setText("BOOKS CATEGORIES");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("1.TECHNOLOGY & ART");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("4.FICTION AND SCIENCE");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("2.BUSINESS AND FINANCE");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("5.EDUCATION");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("3.CATERING AND HEALTH");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("6.REFERENCE");

        javax.swing.GroupLayout BooksCategoryPanelLayout = new javax.swing.GroupLayout(BooksCategoryPanel);
        BooksCategoryPanel.setLayout(BooksCategoryPanelLayout);
        BooksCategoryPanelLayout.setHorizontalGroup(
                BooksCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BooksCategoryPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(BooksCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(BooksCategoryPanelLayout.createSequentialGroup()
                                                .addComponent(CategoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addGroup(BooksCategoryPanelLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(BooksCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(BooksCategoryPanelLayout.createSequentialGroup()
                                                                .addGroup(BooksCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel5)
                                                                        .addComponent(jLabel7))
                                                                .addGap(188, 188, 188)
                                                                .addGroup(BooksCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel6)
                                                                        .addComponent(jLabel8)))
                                                        .addGroup(BooksCategoryPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel3)
                                                                .addGap(210, 210, 210)
                                                                .addComponent(jLabel4)))
                                                .addContainerGap(189, Short.MAX_VALUE))))
        );
        BooksCategoryPanelLayout.setVerticalGroup(
                BooksCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BooksCategoryPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(CategoryLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BooksCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BooksCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(BooksCategoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BookMangementPanel.setBackground(new java.awt.Color(204, 153, 255));
        BookMangementPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 255), 2));

        jLabel1.setBackground(new java.awt.Color(153, 0, 204));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BOOK MANAGEMENT");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 255)));

        leaseBook.setBackground(new java.awt.Color(204, 102, 255));
        leaseBook.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        leaseBook.setText("LEASE BOOK");
        leaseBook.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        ReturnBook.setBackground(new java.awt.Color(204, 102, 255));
        ReturnBook.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ReturnBook.setText("RETURN BOOK");
        ReturnBook.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        RegLeaseButton.setBackground(new java.awt.Color(204, 102, 255));
        RegLeaseButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        RegLeaseButton.setText("REGISTER & LEASE");
        RegLeaseButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        fines.setBackground(new java.awt.Color(204, 102, 255));
        fines.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        fines.setText("FINES & CHARGES");
        fines.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout BookMangementPanelLayout = new javax.swing.GroupLayout(BookMangementPanel);
        BookMangementPanel.setLayout(BookMangementPanelLayout);
        BookMangementPanelLayout.setHorizontalGroup(
                BookMangementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BookMangementPanelLayout.createSequentialGroup()
                                .addGroup(BookMangementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(BookMangementPanelLayout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 25, Short.MAX_VALUE))
                                        .addGroup(BookMangementPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(BookMangementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(fines, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(leaseBook, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(ReturnBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(RegLeaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        BookMangementPanelLayout.setVerticalGroup(
                BookMangementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BookMangementPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(leaseBook, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ReturnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(RegLeaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fines, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(18, Short.MAX_VALUE))
        );

// Lease Book Button Listener
        leaseBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               LeaseBook lease = new LeaseBook();
               lease.setVisible(true);
            }
        });

// Return Book Button Listener
        ReturnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                com.Library.ReturnBook returnB = new ReturnBook();
                returnB.setVisible(true);
            }
        });

// Register Lease Button Listener
        RegLeaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RegisterStudentLeaseBook reg = new RegisterStudentLeaseBook();
            }
        });

// Fines Button Listener
        fines.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FineManagement fine = new FineManagement();
                fine.setVisible(true);
            }
        });
        AdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLogin log = new AdminLogin();
                log.setVisible(true);
            }
        });


        com.Library.BottomPanel bottom = new BottomPanel();
        BottomPanel.add(bottom);
        BottomPanel.setBackground(new java.awt.Color(204, 153, 255));
        BottomPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 255), 2));

        javax.swing.GroupLayout BottomPanelLayout = new javax.swing.GroupLayout(BottomPanel);
        BottomPanel.setLayout(BottomPanelLayout);
        BottomPanelLayout.setHorizontalGroup(
                BottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BottomPanelLayout.setVerticalGroup(
                BottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        AdminPanel.setBackground(new java.awt.Color(204, 204, 255));
        AdminPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 255)));

        AdminButton.setBackground(new java.awt.Color(204, 102, 255));
        AdminButton.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        AdminButton.setText("ADMIN");
        AdminButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout AdminPanelLayout = new javax.swing.GroupLayout(AdminPanel);
        AdminPanel.setLayout(AdminPanelLayout);
        AdminPanelLayout.setHorizontalGroup(
                AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(AdminButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AdminPanelLayout.setVerticalGroup(
                AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(AdminButton, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
        );

        TitleLabel.setBackground(new java.awt.Color(204, 102, 255));
        TitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TitleLabel.setForeground(new java.awt.Color(102, 102, 255));
        TitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleLabel.setText("MOONLIGHT ACADEMY LIBRARY MANAGEMENT SYSTEM");
        OnHoldBooks onbook = new OnHoldBooks();
        OnHoldPanel.add(onbook);
        OnHoldPanel.setBackground(new java.awt.Color(204, 102, 255));
        OnHoldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 2));

        javax.swing.GroupLayout OnHoldPanelLayout = new javax.swing.GroupLayout(OnHoldPanel);
        OnHoldPanel.setLayout(OnHoldPanelLayout);
        OnHoldPanelLayout.setHorizontalGroup(
                OnHoldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(onbook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        OnHoldPanelLayout.setVerticalGroup(
                OnHoldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(onbook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
                MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(DashboardTitlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(BookMangementPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(BottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(MainPanelLayout.createSequentialGroup()
                                                .addGap(116, 116, 116)
                                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(DamagedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(PendingFines, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(MainPanelLayout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(OnHoldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(PayFines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(MainPanelLayout.createSequentialGroup()
                                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(MainPanelLayout.createSequentialGroup()
                                                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(StudentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(CheckedOutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(AvailablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(BooksPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(PatronsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(LostBooksPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(BooksCategoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(UserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(TimePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(MainPanelLayout.createSequentialGroup()
                                                                .addComponent(RemindersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(AdminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        MainPanelLayout.setVerticalGroup(
                MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(MainPanelLayout.createSequentialGroup()
                                                .addComponent(TitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(MainPanelLayout.createSequentialGroup()
                                                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(PatronsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(BooksPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(StudentPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(LostBooksPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(AvailablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(CheckedOutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(DamagedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(OnHoldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(PendingFines, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(PayFines, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(BooksCategoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(MainPanelLayout.createSequentialGroup()
                                                                .addComponent(UserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(15, 15, 15)
                                                                .addComponent(TimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(RemindersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(AdminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(MainPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(DashboardTitlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BookMangementPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(BottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        MenuBar.setBackground(new java.awt.Color(204, 102, 255));
        MenuBar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 5, true));
        MenuBar.setForeground(new java.awt.Color(0, 0, 102));
        MenuBar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        MenuBar.setMargin(new java.awt.Insets(0, 40, 0, 0));

        jMenu1.setText("LIBRARY INVENTORY             ");
        MenuBar.add(jMenu1);

        jMenu2.setText("SUPPLIER            ");
        jMenu2.add(jSeparator1);

        MenuBar.add(jMenu2);

        jMenu3.setText("ADD REMINDERS           ");


        jMenu4.setText("HELP                  ");
        MenuBar.add(jMenu4);

        jMenu5.setText("LOGOUT");
        MenuBar.add(jMenu5);

        setJMenuBar(MenuBar);
        MenuBar.add(jMenu3);
        JMenuItem addReminderItem = new JMenuItem("Add Reminder");
        addReminderItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             RemindersManagement rem = new RemindersManagement();
             rem.setVisible(true);
            }
        });

        jMenu3.add(addReminderItem);


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LibraryDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibraryDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibraryDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibraryDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibraryDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton AdminButton;
    private javax.swing.JPanel AdminPanel;
    private javax.swing.JPanel AvailablePanel;
    private javax.swing.JPanel BookMangementPanel;
    private javax.swing.JPanel BooksCategoryPanel;
    private javax.swing.JPanel BooksPanel;
    private javax.swing.JPanel BottomPanel;
    private javax.swing.JLabel CategoryLabel;
    private javax.swing.JPanel CheckedOutPanel;
    private javax.swing.JPanel DamagedPanel;
    private javax.swing.JLabel DashboardLabel;
    private javax.swing.JPanel DashboardTitlePanel;
    private javax.swing.JPanel LostBooksPanel;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JPanel OnHoldPanel;
    private javax.swing.JPanel PatronsPanel;
    private javax.swing.JPanel PayFines;
    private javax.swing.JPanel PendingFines;
    private javax.swing.JButton RegLeaseButton;
    private javax.swing.JLabel RemindersLabel;
    private javax.swing.JPanel RemindersPanel;
    private javax.swing.JButton ReturnBook;
    private javax.swing.JPanel StudentPanel;
    private javax.swing.JPanel TimePanel;
    private javax.swing.JLabel TitleLabel;
    private javax.swing.JPanel UserPanel;
    private javax.swing.JButton fines;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JButton leaseBook;
    // End of variables declaration
}
