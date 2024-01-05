package test;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeLabelUpdate {
	public TimeLabelUpdate() {
		// TODO Auto-generated constructor stub
		JFrame frame = new JFrame("Time Label Update");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel timeLabel = new JLabel("", JLabel.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(timeLabel, BorderLayout.CENTER);
        frame.setSize(300, 200);
        frame.setVisible(true);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // 每秒更新一次時間
        scheduler.scheduleAtFixedRate(() -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            String currentTime = dateFormat.format(new Date());
            SwingUtilities.invokeLater(() -> timeLabel.setText("Current Time: " + currentTime));
        }, 0, 1, TimeUnit.SECONDS);
    }
    public static void main(String[] args) {
    	new TimeLabelUpdate();
    }
}