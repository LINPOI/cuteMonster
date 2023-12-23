package test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LayeredPanelExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Layered Panel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // 创建层叠面板
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(400, 300));

            // 创建背景面板并添加背景图片
            JPanel backgroundPanel = new JPanel();
            backgroundPanel.setBounds(0, 0, 400, 300);
            try {
                BufferedImage backgroundImage = ImageIO.read(new File("src/PICTURE/houseBackground.png"));
                JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
                backgroundPanel.add(backgroundLabel);
            } catch (IOException e) {
                e.printStackTrace();
            }
            layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER); // 将背景添加到默认层级

            // 创建前景面板并添加按钮
            JPanel foregroundPanel = new JPanel();
            foregroundPanel.setBounds(0, 0, 400, 300); // 与背景面板大小相同
            foregroundPanel.setOpaque(false); // 设置为透明以显示背景
            foregroundPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // 设置按钮居中

            JButton button1 = new JButton("Button 1");
            JButton button2 = new JButton("Button 2");
            foregroundPanel.add(button1);
            foregroundPanel.add(button2);

            layeredPane.add(foregroundPanel, JLayeredPane.PALETTE_LAYER); // 将前景添加到顶层

            frame.add(layeredPane);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}