package com.Library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel {

    public BottomPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        setBackground(new Color(186, 135, 89));

        JButton reports = new JButton("REPORTS", new ImageIcon(scaleOurImage("src\\com\\Library\\Report.jpeg")));
        reports.setFont(new Font("sanserif", Font.BOLD, 18));
        reports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your action for the "REPORTS" button here

            }
        });

        JButton user = new JButton("USER", new ImageIcon(scaleOurImage("src\\com\\Library\\OIP.jpeg")));
        user.setFont(new Font("sanserif", Font.BOLD, 18));
        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               LoginUpdateUser log = new LoginUpdateUser();
               log.setVisible(true);
            }
        });

        JButton logout = new JButton("LOGOUT", new ImageIcon(scaleOurImage("src\\com\\Library\\Exit.jpeg")));
        logout.setFont(new Font("", Font.BOLD, 18));
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               LibraryDashboard lib = new LibraryDashboard();
               lib.dispose();
            }
        });
        reports.setMaximumSize(new Dimension(Integer.MAX_VALUE, reports.getPreferredSize().height));
        user.setMaximumSize(new Dimension(Integer.MAX_VALUE, user.getPreferredSize().height));
        logout.setMaximumSize(new Dimension(Integer.MAX_VALUE, logout.getPreferredSize().height));

        add(Box.createRigidArea(new Dimension(0, 10))); // Vertical gap
        add(reports);
        add(Box.createRigidArea(new Dimension(0, 10))); // Vertical gap
        add(user);
        add(Box.createRigidArea(new Dimension(0, 10))); // Vertical gap
        add(logout);
    }

    private Image scaleOurImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        return image;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Welcome Message");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300); // Adjusted size
            frame.setLocationRelativeTo(null);

            BottomPanel welcomeMessage = new BottomPanel();
            frame.add(welcomeMessage);

            frame.setVisible(true);
        });
    }
}
