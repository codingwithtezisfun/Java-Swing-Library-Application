import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PersonsDetails extends JFrame {
    private JTextField searchField;
    private JLabel nameLabel, idLabel, genderLabel, dobLabel, pinLabel, photoLabel;

    public PersonsDetails() {
        setTitle("Persons Information/Details - search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JLabel searchLabel = new JLabel("Enter person's ID for search");
        searchField = new JTextField(10);

        JButton searchButton = new JButton("Search");
        JButton cancelButton = new JButton("Cancel");

        nameLabel = new JLabel("Full Name: ");
        idLabel = new JLabel("Person ID: ");
        genderLabel = new JLabel("Gender: ");
        dobLabel = new JLabel("Date of Birth: ");
        pinLabel = new JLabel("PIN: ");
        photoLabel = new JLabel("Photo: ");

        Font labelFont = new Font("Arial", Font.PLAIN, 15);
        nameLabel.setFont(labelFont);
        idLabel.setFont(labelFont);
        genderLabel.setFont(labelFont);
        dobLabel.setFont(labelFont);
        pinLabel.setFont(labelFont);
        photoLabel.setFont(labelFont);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(searchLabel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(searchField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(searchButton, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        add(nameLabel, gbc);

        gbc.gridy = 3;
        add(idLabel, gbc);

        gbc.gridy = 4;
        add(genderLabel, gbc);

        gbc.gridy = 5;
        add(dobLabel, gbc);

        gbc.gridy = 6;
        add(pinLabel, gbc);

        gbc.gridy = 7;
        add(photoLabel, gbc);

        gbc.gridy = 9;
        gbc.gridx = 1;
        add(cancelButton, gbc);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int personId = Integer.parseInt(searchField.getText());
                fetchPersonInformation(personId);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
    }

    private void fetchPersonInformation(int personId) {
        String url = "jdbc:mysql://localhost:3306/exams";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM persons WHERE personid = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, personId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        nameLabel.setText("Full Name: " + resultSet.getString("firstname") + " " +
                                resultSet.getString("middlename") + " " + resultSet.getString("lastname"));
                        idLabel.setText("Person ID: " + resultSet.getInt("personid"));
                        genderLabel.setText("Gender: " + resultSet.getString("gender"));
                        dobLabel.setText("Date of Birth: " + resultSet.getDate("dateofbirth"));
                        pinLabel.setText("PIN: " + resultSet.getString("pin"));
                        String filePath = resultSet.getString("photo");
                        ImageIcon photoIcon = new ImageIcon(filePath);

                        photoLabel.setIcon(photoIcon);
                    } else {
                        JOptionPane.showMessageDialog(PersonsDetails.this, "Person not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(PersonsDetails.this, "Database error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private Image scaleOurImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH);
        return image;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PersonsDetails().setVisible(true);
            }
        });
    }
}
