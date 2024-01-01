package finalproject2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MonsterPanel extends JPanel implements  Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String srcString = "src/PICTURE/";
	private String[][] slime;
	private String[] blueSlimesStrings;
	private String[] redSlimesStrings;
	private String[] greenSlimesStrings;
	private String[] purpleSlimesStrings;
	private int width=350;
	private int height=350;
	private Account account=new Account();
	private Subject subject;
	private BufferedImage resizedImage;
	public MonsterPanel(boolean havewing, int select[],Account account,Subject subject) {// 翅膀，史萊姆總類
		this.subject= subject;
		subject.addObserver(this);
		/*
		 * \ 0 藍史萊姆01水冰23 1 紅史萊姆0123 2 綠史萊姆0123 3 紫史萊姆012345
		 */
		blueSlimesStrings = new String[] { // 藍史萊姆、暈藍史萊姆、冰史萊姆、暈冰史萊姆
				srcString + "blueslime2.png",srcString + "atblue.png",
				srcString + "iceslime2.png",srcString + "aticeslime2.png", 
				};
		redSlimesStrings = new String[] { // 紅史萊姆、暈紅史萊姆、火史萊姆、暈火史萊姆
				srcString + "redslime.png",srcString + "atred.png",
				srcString + "fireslime2.png",srcString + "atfireslime2.png",
				};
		greenSlimesStrings = new String[] { // 綠史萊姆、暈綠史萊姆、毒史萊姆、暈毒史萊姆
				srcString + "green.png",srcString + "atgreen.png",
				srcString + "poisonslime.png", srcString + "atpoisonslime.png",
				};
		purpleSlimesStrings = new String[] { // 紫史萊姆、暈紫史萊姆、紫1史萊姆、暈紫1史萊姆、紫2史萊姆、暈紫2史萊姆
				srcString + "purpleslime2.png",srcString + "atpurple.png",
				srcString + "purple1" + ".png",srcString + "atpurple1.png",
				srcString + "purple2" + ".png",srcString + "atpurple2.png",
				};
		slime = new String[][] { blueSlimesStrings, redSlimesStrings, greenSlimesStrings, purpleSlimesStrings };

		this.setLayout(new BorderLayout());
		/*
		 * 寬高
		 */
		try {
			BufferedImage wing = ImageIO.read(new File("src/PICTURE/wing.png"));
			BufferedImage Slime = ImageIO.read(new File(slime[select[0]][select[1]]));
			BufferedImage combined = null;
			Graphics2D g = null;
			if (havewing) {
				if (wing.getWidth() != Slime.getWidth() || wing.getHeight() != Slime.getHeight()) {
					Slime = resizeImage(Slime, wing.getWidth(), wing.getHeight());
				}
				combined = new BufferedImage(wing.getWidth(), wing.getHeight(), BufferedImage.TYPE_INT_ARGB);

				g = combined.createGraphics();
				g.drawImage(wing, 0, 0, null);
				g.drawImage(Slime, 0, 0, null);
				g.dispose();

			} else {
				combined = new BufferedImage(Slime.getWidth(), Slime.getHeight(), BufferedImage.TYPE_INT_ARGB);
				g = combined.createGraphics();
				g.drawImage(Slime, 0, 0, null);

			}
			g.dispose();
			
			// 創建縮小後的圖像
			resizedImage = resizeImage(combined, width, height);

			

			ImageIcon icon = new ImageIcon(resizedImage);
			JLabel label = new JLabel(icon);
			JPanel northJPanel= new JPanel();
			northJPanel.setSize(new Dimension(800,400));
			JLabel jLabelNORTH=new JLabel(" ");
			jLabelNORTH.setPreferredSize(new Dimension(100, 200)); // 擠壓空間
			this.add(jLabelNORTH,BorderLayout.NORTH);
			this.add(new JLabel("                                                                "),BorderLayout.EAST);// 擠壓空間
			this.add(label,BorderLayout.CENTER);
			this.setOpaque(false); // 透明背景
			label.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                try {
	                    BufferedImage newImage = ImageIO.read(new File(slime[select[0]][select[1]+1]));
	                    BufferedImage resizedImage = resizeImage(newImage, width, height);
	                    ImageIcon newIcon = new ImageIcon(resizedImage);
	                    label.setIcon(newIcon);
	                    
	                    Timer timer = new Timer(2000, new ActionListener() {
	                        @Override
	                        public void actionPerformed(ActionEvent e) {
	                            try {
	                                BufferedImage originalImage = ImageIO.read(new File(slime[select[0]][select[1]]));
	                                BufferedImage resizedImage = resizeImage(originalImage, width, height);
	                                ImageIcon originalIcon = new ImageIcon(resizedImage);
	                                label.setIcon(originalIcon);
	                            } catch (IOException ex) {
	                                ex.printStackTrace();
	                            }
	                        }
	                    });
	                    timer.setRepeats(false); 
	                    timer.start();
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	            }
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
	}
	@Override
	public void update(String[] strings) {
		// TODO Auto-generated method stub
		account.monster.setName(strings[0]);
		account.setUsername(strings[1]);
		File output =account.imegeurl();
		try {
			ImageIO.write(resizedImage, "PNG", output);
			System.out.println("輸出圖片" + output.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void updataInt(int[] ints) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updataAccount(Account account) {
		// TODO Auto-generated method stub
		
	}
}
