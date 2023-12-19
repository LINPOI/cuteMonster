package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TimerSwitchExample {
    private Timer timer;
    private boolean isTimerRunning = false;

    public TimerSwitchExample() {
    	timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Timer tick...");
            }
        });
        JButton startButton = new JButton("Start Timer");
        startButton.addActionListener(e -> {
            if (!isTimerRunning) {
            	 timer.start();
                isTimerRunning = true;
                System.out.println("Timer started.");
            }
        });

        JButton stopButton = new JButton("Stop Timer");
        stopButton.addActionListener(e -> {
            if (isTimerRunning) {
            	 timer.stop();
                isTimerRunning = false;
                System.out.println("Timer stopped.");
            }
        });

        JFrame frame = new JFrame("Timer Switch Example");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(startButton);
        frame.add(stopButton);
        frame.pack();
        frame.setVisible(true);
    }

    private void initializeTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Timer tick...");
            }
        });
    }

    private void startTimer() {
        timer.start();
    }

    private void stopTimer() {
        timer.stop();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TimerSwitchExample();
        });
    }
}