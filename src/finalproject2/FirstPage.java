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



public class FirstPage extends JPanel implements Commonly_GridBagConstraints, Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage backgroundImage ; // 背景
	private String[] imageSrc;
	protected CardLayout cardLayout;// 切換面板
	protected JPanel cardPanel;// 面板
	private Subject subject;
	
	private JLabel monstername;
	protected int[][] grid;// 元件布局
	protected ArrayList<JComponent> gUIComponents = new ArrayList<>();
	// 建構子
	protected ActionListener[] actionListeners = null;
	private ActionListener gamer = null;
	private ActionListener slime = null;
	private ActionListener interactive = null;
	
	private Account account=new Account();
	//
	JPanel jPanel2;
	
	public FirstPage(CardLayout cardLayout, JPanel cardPanel ,Subject subject) {
		this.cardLayout = cardLayout;
		this.cardPanel = cardPanel;
		this.subject=subject;
		subject.addObserver(this); 
		JPanel loginPanel = open();
		this.setLayout(new BorderLayout()); 
		this.add(loginPanel);
	}
	public JPanel open() {
		
		imageSrc=new String[]{"src/PICTURE/gamerbutton.png",
				"src/PICTURE/slimebutton.png",
				"src/PICTURE/interactive.png"};
		actionListeners = new ActionListener[] { gamer, slime,interactive };
		JPanel jPanel=new JPanel();
		jPanel.setLayout(new BorderLayout()); 
		jPanel.setOpaque(false); // 透明背景
		jPanel2=new JPanel();
		jPanel2.setLayout(new GridBagLayout());
		jPanel2.setOpaque(false); // 透明背景
		System.out.println("使用者姓名"+account.getUsername());
		// 加載背景圖片
        try {
            backgroundImage = ImageIO.read(new File("src/PICTURE/houseBackground.png")); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for(int i=0;i<3;i++) {
        	setButtonIcons(i);
        }
        
        Action();
        getGrid();
        gUIComponents.add(new JLabel("                                                                "));
        gUIComponents.add(new JLabel("                                                                "));
         monstername=new JLabel(account.monster.getName());
         monstername.setFont(new Font("Arial", Font.BOLD, 20));
        gUIComponents.add(monstername);
        for (int i = 0; i < gUIComponents.size(); i++) {
			addComponent(grid, gUIComponents, i);
		}
        /*
         * 遊戲大廳的各個panel
         */
//        jPanel.add(new JLabel("eeee"),BorderLayout.EAST);
        jPanel.add(new MonsterPanel(false,new int[] {0,0}),BorderLayout.CENTER);
        jPanel.add(new Valuetable(account),BorderLayout.WEST);
        jPanel.add(jPanel2,BorderLayout.SOUTH);
		return jPanel;
	}
	
	//設置按鈕
	public void setButtonIcons(int i) {
		JButton button = new JButton();//新增按鈕
		button.setBackground(new Color(188,150,120));
		button.addActionListener(actionListeners[i]);//新增監聽
		//button.setFocusable(false); //不要出現框框
		//button.setBorderPainted(false); // 移除按鈕的邊框繪製
		//button.setContentAreaFilled(false); // 移除按鈕的填充效果
		/*
		 * 新增按鈕圖片
		 */
		ImageIcon button_ImageIcon = new ImageIcon(imageSrc[i]);//新增圖片
		Image button_Image1=button_ImageIcon.getImage();//轉為image
		button.setPreferredSize(new Dimension(200, 100));//設定按鈕大小
        Image newbutton_Image=button_Image1.getScaledInstance(200, 100, Image.SCALE_DEFAULT); // 圖片格式
        button.setIcon(new ImageIcon(newbutton_Image));//新增圖片至按鈕
        gUIComponents.add(button);
	}
	public int[][] getGrid() {
		// TODO Auto-generated method stub
		grid = new int[][] { 
				{ 1, 1, 1, 1, 1, 1, NONE, CENTER }, // BUTTON
				{ 2, 1, 1, 1, 1, 1, NONE, CENTER },
				{ 3, 1, 1, 1, 1, 1, NONE, CENTER },
				
				{ 0, 1, 1, 1, 1, 1, NONE, CENTER },
				{ 4, 1, 1, 1, 1, 1, NONE, CENTER },
				{ 1, 0, 3, 1, 1, 1, NONE, CENTER }//text
				
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

		gamer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			};
		};
		slime = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
			};
		};
		interactive = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
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
	public void update(String[] strings) {
		// TODO Auto-generated method stub
		account.monster.setName(strings[0]);
		monstername.setText(account.monster.getName());
		
	}
	
	
}
