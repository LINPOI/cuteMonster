package finalproject2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import GenetticAlgorithm.Chromosome;
import GenetticAlgorithm.GenetticAlgorithm;
import Props.Props;
import Props.PropsList;

public class InteractiveJFrame extends JPanel {
	/**
	 * 此頁負責LOGIN->互動部分操作
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 分頁設置
	 */
	private JPanel cardPanel;
	private CardLayout cardLayout;
	private String nowPanel;
	private String pastPanel;
	private JFrame jFrame = new JFrame("探索");
	public static final String InteractiveJPanel = "InteractiveJPanel";
	public static final String trainpaJPanel = "trainpaJPanel";
	public static final String exploreJPanel = "exploreJPanel";
	public static final String selectDifficultyJPanel = "selectDifficultyJPanel";
	// private JPanel jPanel;
	private String[] buttonName = new String[] { "訓練", "探索", "簡單", "普通", "困難" };
	private Color[] colors = new Color[] { new Color(135, 206, 250), new Color(0, 255, 128) };
	private BufferedImage backgroundImage; // 背景
	private Account account;
	private ImageIcon slimebutton1x1 = new ImageIcon("src/PICTURE/slimebutton1x1.png");// 應用照
	// 監聽

	private ActionListener train = null;
	private ActionListener explore = null;
	protected ActionListener[] actionListeners = null;

	// 取得關卡選擇
	private int location;
	private int difficulty;

	// 觀察者
	private Subject subject;
	/*
	 * 
	 * 
	 */

	public InteractiveJFrame(Account account, Subject subject) {
		this.setLayout(new BorderLayout());
		this.subject = subject;// 觀察者

		this.account = account;
		jFrame.setLayout(new BorderLayout());
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 按下關閉時的動作(關閉全部)
		Image slimebutton1x1_Image = slimebutton1x1.getImage(); // 取得圖片
		jFrame.setIconImage(slimebutton1x1_Image); // 應用圖示
		jFrame.setSize(new Dimension(1000, 800)); // 框架尺寸
		try {
			backgroundImage = ImageIO.read(new File("src/PICTURE/outsideBackground.png"));// 背景照
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * 初始化
		 */
		cardPanel = new JPanel();
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		JLabel jLabelNORTH = new JLabel(" ");
		jLabelNORTH.setPreferredSize(new Dimension(200, 280)); // 擠壓空間
		this.add(jLabelNORTH, BorderLayout.NORTH);
		JPanel open = open();
		JPanel trainpaJPanel = trainpaJPanel();
		JPanel exploreJPanel = exploreJPanel();
		JPanel selectDifficultyJPanel = selectDifficultyJPanel();
		cardPanel.setOpaque(false); // 透明背景
		cardPanel.add(open, InteractiveJPanel);
		cardPanel.add(trainpaJPanel, InteractiveJFrame.trainpaJPanel);
		cardPanel.add(exploreJPanel, InteractiveJFrame.exploreJPanel);
		cardPanel.add(selectDifficultyJPanel, InteractiveJFrame.selectDifficultyJPanel);
		this.add(cardPanel, BorderLayout.CENTER);
		this.add(jButton_return(), BorderLayout.SOUTH);
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

	/*
	 * 開啟panel
	 */
	private JPanel open() {
		nowPanel = InteractiveJPanel;
		JPanel jpanel = new JPanel();

		Action();
		actionListeners = new ActionListener[] { train, explore };
		for (int i = 0; i < 2; i++) {
			jpanel.add(buttons(i));
		}
		//

		jpanel.setBackground(Color.black);
		jpanel.setOpaque(false); // 透明背景
		return jpanel;
	}

	/*
	 * 訓練panel
	 */
	private JPanel trainpaJPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		String[] strings = new String[] { "操場", "學校", "-未解鎖-", "-未解鎖-", "-未解鎖-" };
		JList<String> jList = new JList<String>(strings);
		jList.setCellRenderer(new MyListCellRenderer());
		jList.setBackground(new Color(255, 248, 220));
		jList.setPreferredSize(new Dimension(500, 200));
		jList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) { // 確保不是選擇中的變更
					int index = jList.getSelectedIndex(); // 獲取所選項目的索引
					if (index != -1) { // 確認是否有選取項目
						System.out.println("所選擇的是: " + strings[index]); // 輸出所選項目的內容
						// label.setText(account.monster.getValueName(index)+":"+account.monster.getInf(index));
						location = 6;
						if (index < 2) {
							cardLayout.show(cardPanel, selectDifficultyJPanel);
							pastPanel = nowPanel;
							nowPanel = selectDifficultyJPanel;
						}

					}
				}
			}
		});
		panel.setOpaque(false); // 透明背景
		panel.add(jList);

		return panel;
	}

	/*
	 * 探索panel
	 */
	private JPanel exploreJPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		String[] strings = new String[] { "極地", "火山", "沼澤", "神殿", "風場" };
		JList<String> jList = new JList<String>(strings);
		jList.setCellRenderer(new MyListCellRenderer());
		jList.setBackground(new Color(255, 248, 220));
		jList.setPreferredSize(new Dimension(500, 200));
		jList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) { // 確保不是選擇中的變更
					int index = jList.getSelectedIndex(); // 獲取所選項目的索引
					if (index != -1) { // 確認是否有選取項目
						System.out.println("所選擇的是: " + strings[index]); // 輸出所選項目的內容
						location = index + 1;
						// label.setText(account.monster.getValueName(index)+":"+account.monster.getInf(index));
						cardLayout.show(cardPanel, selectDifficultyJPanel);
						pastPanel = nowPanel;
						nowPanel = selectDifficultyJPanel;
					}
				}
			}
		});

		panel.add(jList);
		panel.setOpaque(false); // 透明背景
		return panel;
	}

	/*
	 * 難易度jpanel
	 * 
	 */
	public JPanel selectDifficultyJPanel() {

		JPanel panel = new JPanel();
		for (int i = 2; i < 5; i++) {
			panel.add(buttons(i));
		}
		//
		panel.setBackground(Color.black);
		panel.setOpaque(false); // 透明背景
		return panel;
	}

	/*
	 * 
	 * 動作
	 */
	public void Action() {
		train = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, trainpaJPanel);
				pastPanel = InteractiveJPanel;
				nowPanel = trainpaJPanel;
			}
		};
		explore = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, exploreJPanel);
				pastPanel = InteractiveJPanel;
				nowPanel = exploreJPanel;
			}
		};
	}

	/*
	 * 按鈕
	 * 
	 */
	public JButton buttons(int i) {
		JButton jButton = new JButton(buttonName[i]);
		/*
		 * 按鈕設定
		 */

		// 動作
		if (i > 1) {
			jButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					difficulty = i - 1;
					System.out.println("難度:" + difficulty);
					start();
				}
			});
		} else {
			jButton.addActionListener(actionListeners[i]);
		}

		// 字型格式
		jButton.setFont(FontFactory.commonFont(1, 60));

		// 顏色
		jButton.setBackground(colors[i % 2]);
		// 按鈕大小
		jButton.setPreferredSize(new Dimension(200, 200));

		// 去除外框
		jButton.setBorderPainted(false);

		// 去除聚焦
		jButton.setFocusPainted(false);

		// 加入面板
		return jButton;
	}

	public JPanel jButton_return() {
		JPanel jPanel = new JPanel();
		JButton jButton = new JButton("返回");
		// 字型格式
		jButton.setFont(FontFactory.commonFont(1, 60));

		// 顏色
		jButton.setBackground(new Color(77, 57, 0));
		jButton.setForeground(Color.white);
		// 按鈕大小
		jButton.setPreferredSize(new Dimension(200, 100));
		jButton.addActionListener(e -> {

			// TODO Auto-generated method stub
			if (nowPanel == selectDifficultyJPanel) {
				// System.out.println("點擊返回"+selectDifficultyJPanel);
				nowPanel = pastPanel;
				cardLayout.show(cardPanel, pastPanel);

			} else if (nowPanel == InteractiveJPanel) {
				// System.out.println("點擊返回"+InteractiveJPanel);
				nowPanel = pastPanel;
				jFrame.dispose();
			} else {
				// System.out.println("點擊返回"+nowPanel);
				nowPanel = InteractiveJPanel;
				cardLayout.show(cardPanel, InteractiveJPanel);

			}

		});
		// 去除聚焦
		jButton.setFocusPainted(false);
		jPanel.add(jButton);
		jPanel.setOpaque(false); // 透明背景
		return jPanel;
	}

	

	public void start() {
		/*
		 * 使用演算法並拿到演算法的進行處理
		 */
		GenetticAlgorithm<Props> getPropsAlgorithm = new GenetticAlgorithm<Props>(account, location, difficulty);
		JDialog dialog = new JDialog(jFrame, "獲得道具", true); // 建立模態的 JDialog
		Chromosome<Props> allProps = getPropsAlgorithm.getlastChromosome();
		boolean Alive = allProps.getFitnessValue()>0?true:false;
		Props props=allProps.getChromosome()[0];//得到的道具
		String propsName=Alive?props.getName():"已死亡";
		double score=getPropsAlgorithm.getScore();
		if (!Alive) {
			System.err.println("已死亡");
			account.monster.setHealthValue(0);
			subject.setAccount(account);
		} else {
			/*
			 * 如果戰勝
			 */
			if (location != 6)
				account.addYear(1);
			account.addMoney(difficulty);
			account.monster.addHungerValue(-3);
			account.monster.addThirstValue(-3);
			account.monster.addMoodValue(3);
			System.err.println(propsName);
			System.out.println("getHungerValue" + account.monster.getHungerValue());
			// System.out.println("year" + account.getYear());
			account.addProps(account, props.getID());
		}
		// allProps.getChromosome()[x]
		
		JLabel label = new JLabel(propsName, SwingConstants.CENTER);
		JButton closeButton = new JButton("關閉");

		closeButton.addActionListener(e -> {
			dialog.setVisible(false); // 關閉訊息框
			dialog.dispose(); // 釋放資源
			if (!Alive) {
				jFrame.setVisible(false); // 關閉frame
				jFrame.dispose();
			} else if (new Random().nextBoolean()) {// 50%
				extraReward(score);
			} else {
				jFrame.setVisible(false); // 關閉frame
				jFrame.dispose();
			}
			subject.setAccount(account);
		});
		dialog.setLayout(new BorderLayout());

		dialog.add(label, BorderLayout.CENTER);
		dialog.add(closeButton, BorderLayout.SOUTH);

		dialog.setSize(200, 150);
		dialog.setLocationRelativeTo(jFrame); // 讓訊息框置中於 parent frame
		dialog.setVisible(true); // 顯示訊息框

	}

	private void extraReward(double value) {
		JDialog dialog = new JDialog(jFrame, "獲得額外道具", true); // 建立模態的 JDialog
		dialog.setLayout(new BorderLayout());
		
		PropsList propsList = new PropsList();// 道具清單
		
		// DefaultListModel 是 JList 的標準資料模型
		DefaultListModel<String> propsNewList = new DefaultListModel<String>();// 建立可獲得的道具清單
		/*
		 * 看道具庫該給那些東西
		 */
		for (int i = 0; i < propsList.size(); i++) {
			double Propsvalue = propsList.get(i).getValue();
			System.out.println(value);
			System.out.println(Propsvalue);
			if (value >= Propsvalue) {// 留下價值比獲得積分小的
				propsNewList.addElement(propsList.get(i).getName());
			} else {
				break;
			}
		}
		
		JList<String> jList = new JList<String>(propsNewList);
		
		JScrollPane scrollPane = new JScrollPane(jList); // 將 JList 放入 JScrollPane
		
		
		
		JButton closeButton = new JButton("選擇");

		closeButton.addActionListener(e -> {
			dialog.setVisible(false); // 關閉訊息框
			dialog.dispose(); // 釋放資源
			int selectedValue = jList.getSelectedIndex();
			selectedValue= selectedValue<0?0:selectedValue;
			account.addProps(account, selectedValue);//新增道具
			//account.checkProps_name();
			//System.out.println(selectedValue);
			propsList.get(selectedValue);
				jFrame.setVisible(false); // 關閉frame
				jFrame.dispose();
		});
		
		
		dialog.add(scrollPane, BorderLayout.CENTER);// 將 JScrollPane 添加到 JDialog
		dialog.add(closeButton, BorderLayout.SOUTH); 
		dialog.setSize(200, 150);//設置大小
		dialog.setLocationRelativeTo(jFrame); // 讓訊息框置中於 parent frame
	    dialog.setVisible(true); // 顯示 JDialog
	}
}
//計畫三個panel切換，panel.setOpaque(false); // 透明背景