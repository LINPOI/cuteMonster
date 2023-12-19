package finalproject2;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class FirstPage extends SetPanel{
	private BufferedImage background = loadImage("src/PICTURE/slime.png"); // 背景
	private BufferedImage scaledBackground; 
	private BufferedImage character = loadImage("src/PICTURE/houseBackground.png"); //史萊姆
	private JPanel backgroundJPanel=null;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FirstPage(CardLayout cardLayout, JPanel cardPanel) {
		this.cardLayout=cardLayout;
		this.cardPanel=cardPanel;
		System.out.println("進入");
		jPanel = new JPanel(); // 初始化 jPanel
		jPanel.setLayout(new GridBagLayout());
		jPanel.setSize(new Dimension(weigth, height));
		this.add(new JLabel("修理中"));
		// TODO Auto-generated constructor stub
			backgroundJPanel = new JPanel() {
			    @Override
			    protected void paintComponent(Graphics g) {
			        super.paintComponent(g);
			        if (scaledBackground != null) {
			            g.drawImage(scaledBackground, 0, 0, this);
			        }
			    }
			};
			scaledBackground=resizeImage(background,weigth,height);
			backgroundJPanel.setPreferredSize(new Dimension(weigth, height));
			this.add(backgroundJPanel);
		}
	 @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        if (background != null && character != null) {
	            // 繪製背景
	            g.drawImage(background, 0, 0, this);

	            // 繪製史萊姆在背景的中央
	            int characterX = (background.getWidth() - character.getWidth()) / 2;
	            int characterY = (background.getHeight() - character.getHeight()) / 2;
	            g.drawImage(character, characterX, characterY, this);
	        }
	    }

	    private BufferedImage loadImage(String path) {
	        BufferedImage image = null;
	        try {
	            image = ImageIO.read(new File(path));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return image;
	    }
	    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
	        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
	        Graphics2D g2d = resizedImage.createGraphics();
	        g2d.drawImage(originalImage, 0, 0, width, height, null);
	        g2d.dispose();
	        return resizedImage;
	    }
}
