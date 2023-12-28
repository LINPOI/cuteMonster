package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSwitchExample2 {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public PanelSwitchExample2() {
        frame = new JFrame("Panel Switch Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 創建兩個面板
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.RED);
        JButton button1 = new JButton("切換到面板 2");
        panel1.add(button1);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.BLUE);
        JButton button2 = new JButton("切換到面板 1");
        panel2.add(button2);

        // 在 cardPanel 上使用 CardLayout 來管理面板的顯示
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add(panel1, "Panel 1");
        cardPanel.add(panel2, "Panel 2");

        // 在按鈕的監聽器中切換面板
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Panel 2");
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Panel 1");
            }
        });

        frame.add(cardPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PanelSwitchExample();
            }
        });
    }
}