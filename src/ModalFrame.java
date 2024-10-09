import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModalFrame extends JDialog {
    public ModalFrame(JFrame parent) {
        super(parent, "Modal Frame", true); // Set modal to true

        // Set JDialog properties
        setSize(300, 200);

        // Create a button to close the frame
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog
            }
        });

        // Create a panel and add the button to it
        JPanel panel = new JPanel();
        panel.add(closeButton);

        // Add the panel to the dialog
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame parentFrame = new JFrame("Parent Frame");
            parentFrame.setSize(400, 300);
            parentFrame.setLocationRelativeTo(null);

            JButton openModalButton = new JButton("Open Modal Frame");
            openModalButton.addActionListener(e -> {
                ModalFrame modalFrame = new ModalFrame(parentFrame);
                modalFrame.setLocationRelativeTo(parentFrame);
                modalFrame.setVisible(true);
            });

            JPanel panel = new JPanel();
            panel.add(openModalButton);

            parentFrame.add(panel);
            parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            parentFrame.setVisible(true);
        });
    }
}
