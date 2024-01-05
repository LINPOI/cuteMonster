package finalproject2;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.AbstractDocument.BranchElement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.BreakIterator;

public class MonsterPanel extends JPanel implements Observer {
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
	private int width = 350;
	private int height = 350;
	private Account account = new Account();
	private Subject subject;
	private BufferedImage resizedImage;
	private JLabel label = new JLabel();
	private JButton newMonsterButton = new JButton();
	private DatabaseOperations databaseOperations = new DatabaseOperations();

	public MonsterPanel(boolean havewing, int select[], Account account, Subject subject) {// 翅膀，史萊姆總類
		open(havewing, select, account, subject);
	}

	public void open(boolean havewing, int select[], Account account, Subject subject) {// 翅膀，史萊姆總類
		this.subject = subject;
		subject.addObserver(this);
		/*
		 * \ 0 藍史萊姆01水冰23 1 紅史萊姆0123 2 綠史萊姆0123 3 紫史萊姆012345
		 */
		blueSlimesStrings = new String[] { // 藍史萊姆、暈藍史萊姆、冰史萊姆、暈冰史萊姆
				srcString + "blueslime2.png", srcString + "atblue.png", srcString + "iceslime2.png",
				srcString + "aticeslime2.png", };
		redSlimesStrings = new String[] { // 紅史萊姆、暈紅史萊姆、火史萊姆、暈火史萊姆
				srcString + "redslime.png", srcString + "atred.png", srcString + "fireslime2.png",
				srcString + "atfireslime2.png", };
		greenSlimesStrings = new String[] { // 綠史萊姆、暈綠史萊姆、毒史萊姆、暈毒史萊姆
				srcString + "green.png", srcString + "atgreen.png", srcString + "poisonslime.png",
				srcString + "atpoisonslime.png", };
		purpleSlimesStrings = new String[] { // 紫史萊姆、暈紫史萊姆、紫1史萊姆、暈紫1史萊姆、紫2史萊姆、暈紫2史萊姆
				srcString + "purpleslime2.png", srcString + "atpurple.png", srcString + "purple1" + ".png",
				srcString + "atpurple1.png", srcString + "purple2" + ".png", srcString + "atpurple2.png", };
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
			label = new JLabel(icon);
			JPanel northJPanel = new JPanel();
			northJPanel.setSize(new Dimension(800, 400));
			JLabel jLabelNORTH = new JLabel(" ");
			jLabelNORTH.setPreferredSize(new Dimension(100, 200)); // 擠壓空間
			this.add(jLabelNORTH, BorderLayout.NORTH);
			this.add(new JLabel("                                      "), BorderLayout.EAST);// 擠壓空間
			this.add(label, BorderLayout.CENTER);
			this.setOpaque(false); // 透明背景
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						BufferedImage newImage = ImageIO.read(new File(slime[select[0]][select[1] + 1]));
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
	private boolean restart=false;
	@Override
	public void updataAccount(Account account) {
		restart=!restart;//處理重複出現按鈕bug
		// TODO Auto-generated method stub
		File output = account.imegeurl();
		try {
			ImageIO.write(resizedImage, "PNG", output);
			System.out.println("輸出圖片" + output.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * 如果任一狀態<0死亡
		 */
		for (int i = 0; i < account.monster.getStates().length; i++) {
			/*
			 * 如果死亡
			 */
			if(restart) {
				remove(newMonsterButton);
				ImageIcon icon = new ImageIcon(resizedImage);
				label.setIcon(icon);
				add(label);
				revalidate();
				repaint();
				break;
			}
			System.out.print(account.monster.getStates(i)+"\t");
			if (account.monster.getStates(i) <= 0) {
				databaseOperations.updateMonsterData(account);// 更新怪物狀態:死亡

				remove(label);
				JPanel newMonsterPanel = new JPanel();
				newMonsterPanel.setLayout(null); // 使用 null 佈局
				newMonsterButton = new JButton("重養一隻");
				// 字型格式
				newMonsterButton.setFont(FontFactory.commonFont(1, 60));
				newMonsterButton.setBackground(new Color(216, 191, 216));
				newMonsterButton.setBounds(200, 100, 300, 100); // 設置按鈕位置和大小
				newMonsterButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String userInput = JOptionPane.showInputDialog(null, "請輸入怪物名字:");
						while (userInput == null) {
							userInput = JOptionPane.showInputDialog(null, "怪獸名稱不可為空:");
						}
						/*
						 * 新怪獸
						 */
						account.monster = new Monster(account.getUsername());
						account.monster.setName(userInput);
						account.setselectMonster(account.getselectMonster()+1);
						System.err.println("account.getselectMonster()"+account.getselectMonster());
						account.saveAccount(account);
						subject.setAccount(account); // 設置新數值
						databaseOperations.insert_Monster_Data(account);//

						remove(newMonsterPanel);
					}

				});

				newMonsterPanel.add(newMonsterButton);
				newMonsterPanel.setOpaque(false); // 透明背景
				add(newMonsterPanel);
				revalidate();
				repaint();
				break;
			} else {
				/*
				 * 重新復活了的話
				 */
				remove(newMonsterButton);
				ImageIcon icon = new ImageIcon(resizedImage);
				label.setIcon(icon);
				add(label);
				revalidate();
				repaint();
				break;
			}
		}

	}
}
