import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JDialog {
    private JTextField usernameField, passwordField;

    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        /*CREATE DATABASE IF NOT EXISTS library;

        USE library;

        CREATE TABLE IF NOT EXISTS users (
                id INT PRIMARY KEY AUTO_INCREMENT,
        username VARCHAR(255) NOT NULL,
        password VARCHAR(255) NOT NULL
);

        INSERT INTO users (username, password)
        VALUES
                ('James', '1234'),
                ('Sarah', '1000'),
        ('JamesC', '2000'),
        ('MaryN', '3000'),
        ('DanielO', '4000');
*/

        JLabel usernameLabel = new JLabel("Enter Username:");
        JLabel passwordLabel = new JLabel("Enter Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        Font textFieldFont = new Font("Arial", Font.PLAIN, 15);
        usernameField.setFont(textFieldFont);
        passwordField.setFont(textFieldFont);
        usernameLabel.setFont(textFieldFont);
        passwordLabel.setFont(textFieldFont);

        JPanel topPanel = new JPanel(new GridLayout(4, 1));
        addAllGap(topPanel, 10);
        topPanel.setBackground(Color.gray);
        topPanel.add(usernameLabel);
        topPanel.add(usernameField);
        topPanel.add(passwordLabel);
        topPanel.add(passwordField);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(loginButton);
        JLabel empty = new JLabel();
        buttonsPanel.add(empty);
        buttonsPanel.add(cancelButton);
        buttonsPanel.setBackground(Color.gray);

        setLayout(new BorderLayout());

        add(topPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (authenticateUser()) {
                    dispose();
                   PersonsDetails per =  new PersonsDetails();
                   per.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setSize(300, 250);
    }

    private void addAllGap(JComponent component, int gap) {
        component.setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
    }

    private boolean authenticateUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getText());

        String url = "jdbc:mysql://localhost:3306/users";
        String user = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}
