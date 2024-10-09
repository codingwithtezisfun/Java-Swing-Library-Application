package com.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import  java.util.Calendar;

public class TimePanel extends JPanel {
    private JLabel timeLabel;
    private JLabel dateLabel;
    private SimpleDateFormat timeFormat;
    private SimpleDateFormat dateFormat;

    public TimePanel() {
        setPreferredSize(new Dimension(200, 100));
        setBackground(new java.awt.Color(102, 102, 102));
        // // Calender jCalender = jCalender.getDate();
        //   int year = jCalender.get(Calender.YEAR);
        //   int month = jCalender.get(Calender.MONTH);
        //  int day = jCalender.get(Calender.DAY_OF_MONTH);

        timeFormat = new SimpleDateFormat("\n HH:mm a");
        dateFormat = new SimpleDateFormat("EEEE, yyyy-MM-dd");

        //   Calender new Calendar = Calender.getInstance();
        //  newCalender.set(year,month,day);
        //  Date date = newCalender.getTime();
        // dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //   String formattedDateOnly =dateFormat.format(date);

        timeLabel = new JLabel(getFormattedTime());
        timeLabel.setFont(new Font("Arial", Font.BOLD, 60));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setVerticalAlignment(JLabel.CENTER);
       /// dateLabel = new JLabel(getFormattedDate());
       // dateLabel.setFont(new Font("Arial", Font.BOLD, 20));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(timeLabel);
       // add(dateLabel);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLabel.setText(getFormattedTime());
               // dateLabel.setText(getFormattedDate());
            }
        });
        timer.start();
    }

    private String getFormattedTime() {
        return timeFormat.format(new Date());
    }

   // private String getFormattedDate() {
      //  return dateFormat.format(new Date());
   // }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Date and Time Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(200, 200);

            TimePanel dateTimePanel = new TimePanel();
            frame.add(dateTimePanel);

            frame.setVisible(true);
        });
    }
}
