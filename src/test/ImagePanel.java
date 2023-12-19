package test;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private ImageIcon imageIcon;

    public ImagePanel(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imageIcon != null) {
            g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // Replace the path with the path to your image
            ImageIcon imageIcon = new ImageIcon("src/PICTURE/houseBackground.png");
            ImagePanel panel = new ImagePanel(imageIcon);
            panel.setPreferredSize(new Dimension(800, 600)); // Set preferred size

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}