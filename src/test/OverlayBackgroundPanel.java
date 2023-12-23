package test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OverlayBackgroundPanel extends JPanel {
    private BufferedImage backgroundImage;
    private JButton button1;
    private JButton button2;

    public OverlayBackgroundPanel() {
        setLayout(new OverlayLayout(this));

        // 加载背景图片
        try {
            backgroundImage = ImageIO.read(new File("src/PICTURE/houseBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建按钮
        button1 = new JButton("Button 1");
        button2 = new JButton("Button 2");

        // 创建一个面板来放置按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // 设置为透明以显示背景
        buttonPanel.setLayout(new BorderLayout()); // 设置按钮居中
        buttonPanel.add(button1,BorderLayout.CENTER);
        buttonPanel.add(button2,BorderLayout.NORTH);

        // 将按钮面板添加到覆盖布局中
        add(buttonPanel);
    }

    // 绘制背景图片
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Overlay Background Panel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            OverlayBackgroundPanel panel = new OverlayBackgroundPanel();
            frame.add(panel);
            frame.setSize(400, 300);
            frame.setVisible(true);
        });
    }
}