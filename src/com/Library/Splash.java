package com.Library;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Splash extends JWindow {
    ImageIcon icon;
    JLabel lbl;
    Timer timer;
    JProgressBar bar;

    public Splash() {
        icon = new ImageIcon("src\\com\\Library\\splash.png");
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        lbl = new JLabel(icon);
        setSize(w, h);
        setLocationRelativeTo(null);
        setLayout(null);
        setContentPane(lbl);

        bar = new JProgressBar();
        bar.setBounds(15, 300, 510, 15);
        bar.setBorderPainted(true);
        bar.setStringPainted(true);
        bar.setBackground(new Color(186,85,211));
        bar.setForeground(new Color(139,0,139));
        bar.setMinimum(0);
        bar.setMaximum(100);
        bar.setValue(5);

        timer = new Timer(50, new ActionListener() {
            private int progressValue = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (progressValue <= 100) {
                    bar.setValue(progressValue);
                    progressValue++;
                } else {
                    dispose();
            LoginWindow log = new LoginWindow();
            log.setVisible(true);
                    timer.stop();
                }
            }
        });

        timer.start();
        add(bar);
        setVisible(true);
    }

    public static void main(String[] args) {

        new Splash();
    }
}
