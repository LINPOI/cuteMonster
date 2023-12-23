package test;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BackgroundPanel extends JPanel {
    private BufferedImage backgroundImage;
    private JLabel jLabel;
    private JButton button2;

    public BackgroundPanel() {
        // 设置布局为 BorderLayout，这样按钮会在南侧
        setLayout(new BorderLayout());

        // 创建按钮并添加到南侧
        jLabel = new JLabel("jLabel 1");
        button2 = new JButton("Button 2");
        button2.setPreferredSize(new Dimension(20, 20));
        add(jLabel);
        add(button2);

        // 加载背景图片
        try {
            backgroundImage = ImageIO.read(new File("src/PICTURE/houseBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            JFrame frame = new JFrame("Background Panel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            BackgroundPanel panel = new BackgroundPanel();
            frame.add(panel);
            frame.setSize(400, 300);
            frame.setVisible(true);
        });
    }
}