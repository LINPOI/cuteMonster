package finalproject2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InteractiveJFrame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 分頁設置
	 */
	private JPanel cardPanel;
	private CardLayout cardLayout;
	// private JPanel jPanel;
	private String[] buttonName = new String[] { "訓練", "探索" };
	private Color[] colors = new Color[] { new Color(135, 206, 250), new Color(0, 255, 128) };
	private BufferedImage backgroundImage; // 背景
	private Account account;
	private ImageIcon slimebutton1x1 = new ImageIcon("src/PICTURE/slimebutton1x1.png");// 應用照
	// 監聽

	private ActionListener train = null;
	private ActionListener explore = null;
	protected ActionListener[] actionListeners = null;

	public InteractiveJFrame(Account account) {
		this.setLayout(new BorderLayout());
		JFrame jFrame = new JFrame();
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
		cardPanel.setOpaque(false); // 透明背景
		cardPanel.add(open, "open");
		cardPanel.add(trainpaJPanel, "trainpaJPanel");
		cardPanel.add(exploreJPanel, "exploreJPanel");

		this.add(cardPanel, BorderLayout.CENTER);
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
		JPanel jpanel = new JPanel();

		Action();
		actionListeners = new ActionListener[] { train, explore };

		for (int i = 0; i < buttonName.length; i++) {
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
		String[] strings = new String[] { "操場", "學校","-未解鎖-","-未解鎖-","-未解鎖-" };
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
		panel.add(jList);
		panel.setOpaque(false); // 透明背景
		return panel;
	}

	public JButton buttons(int i) {
		JButton jButton = new JButton(buttonName[i]);
		/*
		 * 按鈕設定
		 */

		// 動作
		jButton.addActionListener(actionListeners[i]);

		// 字型格式
		jButton.setFont(FontFactory.commonFont(1, 60));

		// 顏色
		jButton.setBackground(colors[i]);
		// 按鈕大小
		jButton.setPreferredSize(new Dimension(200, 200));

		// 去除外框
		jButton.setBorderPainted(false);

		// 去除聚焦
		jButton.setFocusPainted(false);

		// 加入面板
		return jButton;
	}

	public void Action() {
		train = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "trainpaJPanel");
			}
		};
		explore = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "exploreJPanel");
			}
		};
	}

	static class MyListCellRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (renderer instanceof JLabel) {
				JLabel label = (JLabel) renderer;
				// Modify font size here (change "14" to your desired size)
				label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 30));
			}
			return renderer;
		}
	}
}
//計畫三個panel切換，panel.setOpaque(false); // 透明背景