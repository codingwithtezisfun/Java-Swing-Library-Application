package com.Library;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WelcomeMessage extends JPanel {

    private JLabel lblUser;
    private JLabel lblWelcome;
    private JLabel lblDate;

    public WelcomeMessage() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        lblUser = new JLabel(new ImageIcon(scaleOurImage("src\\com\\Library\\OIP.jpeg")));
        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the component
        add(lblUser);

        lblWelcome = new JLabel("Welcome");
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the component
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblWelcome);

        lblDate = new JLabel(getCurrentDate());
        lblDate.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the component
        lblDate.setFont(new Font("Arial", Font.BOLD, 17));
        add(lblDate);
    }

    private Image scaleOurImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        return image;
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "" + dateFormat.format(new Date());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Welcome Message");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);

            WelcomeMessage welcomeMessage = new WelcomeMessage();
            frame.add(welcomeMessage);

            frame.setVisible(true);
        });
    }
}
