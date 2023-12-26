package test;

import javax.swing.*;

public class DialogWithinFrameExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("主要視窗");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton openDialogButton = new JButton("打開對話框");
        openDialogButton.addActionListener(e -> {
            JDialog dialog = new JDialog(frame, "這是對話框", true);
            dialog.setSize(300, 200);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.add(new JLabel("這是對話框內容"));
            dialog.add(panel);
            
            // 設置對話框在主視窗中心
            dialog.setLocationRelativeTo(frame);

            dialog.setVisible(true);
        });

        frame.add(openDialogButton);
        frame.setLayout(new java.awt.FlowLayout());

        frame.setVisible(true);
    }
}