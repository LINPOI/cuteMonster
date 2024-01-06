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
	
	
	private int width = 350;
	private int height = 350;
	
	private Boolean wing=false;
	private Account account = new Account();
	private Subject subject;
	
	
	private BufferedImage resizedImage;
	private JLabel label = new JLabel();
	private JButton newMonsterButton = new JButton();
	private DatabaseOperations databaseOperations = new DatabaseOperations();

	private int[] select=new int[4];
	private String[] blueSlimesStrings= new String[] { // 藍史萊姆、暈藍史萊姆、冰史萊姆、暈冰史萊姆
			srcString + "blueslime2.png", srcString + "atblue.png", srcString + "iceslime2.png",
			srcString + "aticeslime2.png", };
	private String[] redSlimesStrings = new String[] { // 紅史萊姆、暈紅史萊姆、火史萊姆、暈火史萊姆
			srcString + "redslime.png", srcString + "atred.png", srcString + "fireslime2.png",
			srcString + "atfireslime2.png", };
	private String[] greenSlimesStrings = new String[] { // 綠史萊姆、暈綠史萊姆、毒史萊姆、暈毒史萊姆
			srcString + "green.png", srcString + "atgreen.png", srcString + "poisonslime.png",
			srcString + "atpoisonslime.png", };
	private String[] purpleSlimesStrings = new String[] { // 紫史萊姆、暈紫史萊姆、紫1史萊姆、暈紫1史萊姆、紫2史萊姆、暈紫2史萊姆
			srcString + "purpleslime2.png", srcString + "atpurple.png", srcString + "purple1" + ".png",
			srcString + "atpurple1.png", srcString + "purple2" + ".png", srcString + "atpurple2.png", };
	private String[][] slime = new String[][] { blueSlimesStrings, redSlimesStrings, greenSlimesStrings, purpleSlimesStrings };
	
	private boolean restart = false;
	public MonsterPanel(boolean wing, Account account, Subject subject) {// 翅膀，史萊姆總類
		this.subject = subject;
		subject.addObserver(this);
		this.account=account;
		this.wing=wing;
		open( );
		
	}

	public void open( ) {
		remove(newMonsterButton);
		
		/*
		 * \ 0 藍史萊姆01水冰23 1 紅史萊姆0123 2 綠史萊姆0123 3 紫史萊姆012345
		 */
		

		this.setLayout(new BorderLayout());
		/*
		 * 寬高
		 */
		int ice = account.monster.getIce();
		int fire = account.monster.getFire();
		int poison = account.monster.getPoison();
		int illusion = account.monster.getIllusion();
		int[] attribute = new int[] { ice, fire, poison, illusion };
		int max = Math.max(Math.max(Math.max(ice, fire), poison), illusion);
		for (int i = 0; i < attribute.length; i++) {
			if (max == attribute[i]) {
				select[0] = i;
				break;
			}
		}
		if (max > 60&& max==illusion && account.monster.getAge() > 30) {
			select[1]=4;
		}else if (max > 30 && account.monster.getAge() > 10){
			select[1]=2;
		}else {
			select[1]=0;
		}
		try {
			BufferedImage wing = ImageIO.read(new File("src/PICTURE/wing.png"));
			BufferedImage Slime = ImageIO.read(new File(slime[select[0]][select[1]]));
			BufferedImage combined = null;
			Graphics2D g = null;
			if (this.wing) {
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
			label.setIcon(icon);
			
			this.add(label, BorderLayout.CENTER);
			JPanel northJPanel = new JPanel();
			northJPanel.setSize(new Dimension(800, 400));
			JLabel jLabelNORTH = new JLabel(" ");
			jLabelNORTH.setPreferredSize(new Dimension(100, 200)); // 擠壓空間
			this.add(jLabelNORTH, BorderLayout.NORTH);
			this.add(new JLabel("                                                  "), BorderLayout.WEST);// 擠壓空間
			revalidate();
			repaint();
			
			
			
			
			
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

	

	@Override
	public void updataAccount(Account account) {
		this.account=account;
		boolean goodstate=true;
		// TODO Auto-generated method stub
		File output = account.imegeurl();
		try {
			ImageIO.write(resizedImage, "PNG", output);
			// System.out.println("輸出圖片" + output.getAbsolutePath());
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
			//System.out.print("狀態" + account.monster.getStates(i) + "\t" + restart);
			if (restart) {
				open();
				break;
			}
			// System.out.print("狀態"+ account.monster.getStates(i)+"\t");
			if (account.monster.getStates(i) <= 0) {
				goodstate=false;
				restart = !restart;// 處理重複出現按鈕bug
				databaseOperations.updateMonsterData(account);// 更新怪物狀態:死亡
				JOptionPane.showMessageDialog(null, "狀態為零已死亡", "死亡", JOptionPane.INFORMATION_MESSAGE);
				remove(label);
				JPanel newMonsterPanel = new JPanel();
				newMonsterPanel.setLayout(null); // 使用 null 佈局
				newMonsterButton = new JButton("重養一隻");
				// 字型格式
				newMonsterButton.setFont(FontFactory.commonFont(1, 60));
				newMonsterButton.setBackground(new Color(216, 191, 216));
				newMonsterButton.setBounds(50, 100, 300, 100); // 設置按鈕位置和大小
				newMonsterButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						restart = !restart;// 處理重複出現按鈕bug
						String userInput = JOptionPane.showInputDialog(null, "請輸入怪物名字:");
						while (userInput == null) {
							userInput = JOptionPane.showInputDialog(null, "怪獸名稱不可為空:");
						}
						/*
						 * 新怪獸
						 */
						account.monster = new Monster(account.getUsername());
						account.monster.setName(userInput);
						account.setselectMonster(account.getselectMonster() + 1);
						System.err.println("account.getselectMonster()" + account.getselectMonster());
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
			}
		}
		if(goodstate) {
			open();
		}
	}
}
