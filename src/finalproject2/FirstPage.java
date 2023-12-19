package finalproject2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class FirstPage extends JPanel {
	private ImageIcon background = new ImageIcon("src/PICTURE/houseBackground.png"); // 背景
	protected CardLayout cardLayout;// 切換面板
	protected JPanel cardPanel;// 面板
	// 建構子

	public FirstPage() {
		setLayout(new GridBagLayout()); // 使用 GridBagLayout
	}

	public FirstPage(CardLayout cardLayout, JPanel cardPanel) {
		this.cardLayout = cardLayout;
		this.cardPanel = cardPanel;
		JPanel loginPanel = open();
		this.add(loginPanel);
	}
	public JPanel open() {
		JPanel jPanel=new JPanel() {
			
		};
		
		return jPanel;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame jFrame = new JFrame("可愛的怪物");
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setSize(new Dimension(1000, 800));
			jFrame.setLocationRelativeTo(null);

			FirstPage firstPage = new FirstPage();
			jFrame.add(firstPage);

			jFrame.setVisible(true);
		});
	}
}
