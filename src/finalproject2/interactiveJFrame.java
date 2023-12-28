package finalproject2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InteractiveJFrame extends JPanel{
	private BufferedImage backgroundImage; // 背景
	private Account account;
	private ImageIcon slimebutton1x1 = new ImageIcon("src/PICTURE/slimebutton1x1.png");// 應用照
	public InteractiveJFrame(Account account) {
		JPanel jPanel=new JPanel();
		this.setLayout(new BorderLayout());
		JFrame jFrame =new JFrame();
		this.account=account;
		jFrame.setLayout(new BorderLayout());
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 按下關閉時的動作(關閉全部)
		Image slimebutton1x1_Image = slimebutton1x1.getImage(); // 取得圖片
		jFrame.setIconImage(slimebutton1x1_Image); // 應用圖示
		jFrame.setSize(new Dimension(1000, 800)); // 框架尺寸
		try {
			backgroundImage = ImageIO.read(new File("src/PICTURE/outsideBackground.png"));//背景照
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * 按鈕設定
		 */
		JButton jButton=new JButton("開啟訓練");
		JButton jButton2=new JButton("探索");
		jButton.setFont(FontFactory.commonFont(1));//字型格式
		jButton2.setFont(FontFactory.commonFont(1));
		jButton.setBackground(new Color(0,255,128));//顏色
		jButton2.setBackground(new Color(0,255,255));
		jButton.setPreferredSize(new Dimension(200,200));//按鈕大小
		jButton2.setPreferredSize(new Dimension(200,200));
		jButton.setBorderPainted(false);//去除外框
		jButton2.setBorderPainted(false);
		jButton.setFocusPainted(false);//去除聚焦
		jButton2.setFocusPainted(false);
		
		jPanel.add(jButton);
		jPanel.add(jButton2);
		//
		jPanel.setOpaque(false);						 	// 透明背景
		JLabel jLabelNORTH=new JLabel(" ");
		jLabelNORTH.setPreferredSize(new Dimension(200, 280)); // 擠壓空間
		this.add(jLabelNORTH,BorderLayout.NORTH);
		this.add(jPanel,BorderLayout.CENTER);
		this.setOpaque(false);						 	// 透明背景
		jFrame.add(this);
		jFrame.setLocationRelativeTo(null);// 置中顯示
		jFrame.setVisible(true); // 顯示
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 繪製背景圖片
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}
	public JLabel jLabels() {
		JLabel jLabel =new JLabel();
		return jLabel;
	}
}
