package finalproject2;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.imageio.*;
import javax.swing.*;

import org.mariadb.jdbc.internal.util.constant.StateChange;

public class FirstPage extends JPanel implements Commonly_GridBagConstraints, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage backgroundImage; // 背景
	private String[] imageSrc;
	protected CardLayout cardLayout;// 切換面板
	protected JPanel cardPanel;// 面板
	private JPanel centerJPanel;
	private Subject subject;
	private JFrame jframe;
	private int switchJPanel;


	private JLabel monstername;
	protected int[][] grid;// 元件布局
	protected ArrayList<JComponent> gUIComponents = new ArrayList<>();
	// 建構子
	protected ActionListener[] actionListeners = null;
	private ActionListener gamer = null;
	private ActionListener slime = null;
	private ActionListener interactive = null;
	private DatabaseOperations databaseOperations = new DatabaseOperations();
	private Account account = new Account();
	//
	JPanel jPanel2;

	/*
	 * 
	 * 
	 */
	Gimer gimer = new Gimer(jframe, account);//點擊互動
	SlimeLife slimeLife=new SlimeLife();//點擊史萊姆
	InteractiveJFrame interactiveJFrame = new InteractiveJFrame();//點擊互動
	/*
	 * 
	 */
	public FirstPage(CardLayout cardLayout, JPanel cardPanel, Subject subject, JFrame jFrame) {
		this.cardLayout = cardLayout;
		this.cardPanel = cardPanel;
		this.subject = subject;
		this.jframe = jFrame;

		subject.addObserver(this);
		JPanel loginPanel = open();
		this.setLayout(new BorderLayout());
		this.add(loginPanel);
	}

	public JPanel open() {
		
		JPanel jPanel = new JPanel();
		account = databaseOperations.queryData(account);
		imageSrc = new String[] { "src/PICTURE/gamerbutton.png", "src/PICTURE/slimebutton.png",
				"src/PICTURE/interactive.png" };

		jPanel.setLayout(new BorderLayout());
		jPanel.setOpaque(false); // 透明背景
		jPanel2 = new JPanel();
		jPanel2.setLayout(new GridBagLayout());
		jPanel2.setOpaque(false); // 透明背景

		Action();
		actionListeners = new ActionListener[] { gamer, slime, interactive };

		// System.out.println("使用者姓名" + account.getUsername());
		// 加載背景圖片
		try {
			backgroundImage = ImageIO.read(new File("src/PICTURE/houseBackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 3; i++) {
			setButtonIcons(i);
		}

		getGrid();
		gUIComponents.add(new JLabel("                                                                "));
		gUIComponents.add(new JLabel("                                                                "));
		monstername = new JLabel(account.monster.getName());
		monstername.setFont(FontFactory.commonFont(2));
		gUIComponents.add(monstername);
		for (int i = 0; i < gUIComponents.size(); i++) {
			addComponent(grid, gUIComponents, i);
		}
		/*
		 * 遊戲大廳的各個panel
		 */
//        jPanel.add(new JLabel("eeee"),BorderLayout.EAST);
		//第一個是有沒有翅膀，再來是常規作業
		jPanel.add(new MonsterPanel(account.monster.getWing(), account, subject), BorderLayout.CENTER);
		jPanel.add(new SlimeStatePanel(account,subject),BorderLayout.EAST);
		jPanel.add(new Valuetable(account,subject), BorderLayout.WEST);
		jPanel.add(jPanel2, BorderLayout.SOUTH);
		return jPanel;
	}

	// 設置按鈕
	public void setButtonIcons(int i) {
		JButton button = new JButton();// 新增按鈕
		button.setBackground(new Color(188, 150, 120));

		/*
		 * 新增按鈕圖片
		 */
		ImageIcon button_ImageIcon = new ImageIcon(imageSrc[i]);// 新增圖片
		Image button_Image1 = button_ImageIcon.getImage();// 轉為image
		button.setPreferredSize(new Dimension(200, 100));// 設定按鈕大小
		Image newbutton_Image = button_Image1.getScaledInstance(200, 100, Image.SCALE_DEFAULT); // 圖片格式
		button.setIcon(new ImageIcon(newbutton_Image));// 新增圖片至按鈕

		button.addActionListener(actionListeners[i]);// 新增監聽

		gUIComponents.add(button);
	}

	public int[][] getGrid() {
		// TODO Auto-generated method stub
		grid = new int[][] { { 1, 1, 1, 1, 1, 1, NONE, CENTER }, // BUTTON
				{ 2, 1, 1, 1, 1, 1, NONE, CENTER }, { 3, 1, 1, 1, 1, 1, NONE, CENTER },

				{ 0, 1, 1, 1, 1, 1, NONE, CENTER }, { 4, 1, 1, 1, 1, 1, NONE, CENTER },
				{ 1, 0, 3, 1, 1, 1, NONE, CENTER }// text

		};
		return grid;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 繪製背景圖片
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}

	public void Action() {
		//玩家按鈕動作
		gamer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("點擊玩家");
				gimer = new Gimer(jframe, account);
					gimer.open();
			};
		};
		//史萊姆按鈕動作
		slime = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("點擊史萊姆");
				//裝備
				if(!account.monster.dead()) {
					if(slimeLife.notOpen()) {
						slimeLife=new SlimeLife();
						slimeLife.open(account,subject);
					}else {
						slimeLife.toFront();
					}
				}
				
				
			};
		};
		//互動
		interactive = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("點擊互動");
				
				//如果死了就不讓按
				if(!account.monster.dead()) {
					//確認不重複被開啟
					if(interactiveJFrame.notOpen()) {
						interactiveJFrame=new InteractiveJFrame();
						interactiveJFrame.open(account,subject);
					}else {
						interactiveJFrame.toFront();
					}
				}
				
				
			};
		};
	}

	public void addComponent(int[][] grid, ArrayList<JComponent> gUIComponents, int i) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = grid[i][0];
		constraints.gridy = grid[i][1];
		constraints.gridwidth = grid[i][2];
		constraints.gridheight = grid[i][3];
		constraints.weightx = grid[i][4];
		constraints.weighty = grid[i][5];
		constraints.fill = grid[i][6];
		constraints.anchor = grid[i][7];
		jPanel2.add(gUIComponents.get(i), constraints);
	}


	@Override
	public void updataAccount(Account account) {
		// TODO Auto-generated method stub
		this.account=account;
		
		monstername.setText(account.monster.getName());
	}

}
