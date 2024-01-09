package finalproject2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ButtonModel;
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

import Props.Props;
import Props.PropsList;

public class SlimeLife extends JPanel implements Commonly_GridBagConstraints {

	/**
	 * 點擊史萊姆
	 */
	private static final long serialVersionUID = 1L;
	private Subject subject;
	private Account account;
	private ImageIcon slimebutton1x1 = new ImageIcon("src/PICTURE/slimebutton1x1.png");// 應用照
	private JFrame jFrame = new JFrame("史萊姆的窩");
	private JPanel jPanel1 = new JPanel();
	private JPanel jPanel2 = new JPanel();
	private JLabel haveMoneyJLabel = new JLabel();
	private JLabel[] dialogJLabels;
	private MyButton[] dialogButtons;

	private JList<String> jList;
	private JList<String> jList2;
	private DefaultListModel<String> propsNewList = new DefaultListModel<String>();// 建立可獲得的道具清單
	private DefaultListModel<String> propsNewList2 = new DefaultListModel<String>();// 建立可獲得的道具清單
	private ArrayList<Integer> propsID = new ArrayList<Integer>();

	private int select = -1;

	/*
	 * dialog的變數
	 */
	private JDialog dialog = new JDialog();
	private int userWantPropsNumber = 1;
	private String[] labelStrings;
	private String[] buttonStrings;
	private ActionListener[] actionListeners;
	private double totalValue = 0.0;

	/*
	 * 
	 */
	private boolean notOpen=true;
	/*
	 * 
	 */	

	public void open(Account account, Subject subject) {
		this.account = account;
		this.subject = subject;
		notOpen=false;//已開啟
		jFrame.setLayout(new BorderLayout());
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 按下關閉時的動作(關閉全部)
		Image slimebutton1x1_Image = slimebutton1x1.getImage(); // 取得圖片
		jFrame.setIconImage(slimebutton1x1_Image); // 應用圖示
		jFrame.setSize(new Dimension(1000, 800)); // 框架尺寸
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(250, 250, 210));
		jPanel1.setLayout(new BorderLayout());
		jPanel2.setLayout(new BorderLayout());
		jPanel1.setOpaque(false);
		jPanel2.setOpaque(false);
		center1();
		south1();
		center2();
		south2();
		this.add(jPanel1, BorderLayout.WEST);
		this.add(jPanel2, BorderLayout.CENTER);
		// this.add(new JLabel("asds"),BorderLayout.NORTH);
		jFrame.add(this);
		jFrame.setLocationRelativeTo(null);// 置中顯示
		jFrame.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
					notOpen=true;
            }
			
		
		});
		jFrame.setVisible(true); // 顯示
	}

	/*
	 * 顯示左中清單，左上label
	 */
	public void center1() {
		propsID.clear();
		for (Props props : account.getProps_ArrayList()) {
			propsNewList.addElement(props.getName());
			propsID.add(props.getID());
		} // 得到id，請與name同步

		jList = new JList<String>(propsNewList);
		JScrollPane scrollPane = new JScrollPane(jList);
		scrollPane.setPreferredSize(new Dimension(150, 100));
		jList.setCellRenderer(new MyListCellRenderer());
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int getNumber = jList.getSelectedIndex();
					select = propsID.get(getNumber);
					account.useProps(account, select, getNumber);
					subject.setAccount(account);
					// account.checkProps_name();
					int dialogResult = JOptionPane.showConfirmDialog(jFrame, "要使用" + jList.getSelectedValue() + "嗎?",
							"確認", JOptionPane.YES_NO_OPTION);

					if (dialogResult == JOptionPane.YES_OPTION) {
						propsNewList.clear(); // 清除目前所有元素
						if (!account.getProps_ArrayList().isEmpty()) {
							propsID.clear();
							for (Props props : account.getProps_ArrayList()) {
								propsNewList.addElement(props.getName());
								propsID.add(props.getID());
							} // 得到id，請與name同步
						}

						jList.revalidate();
						jList.repaint();

					} else {

					}
				}
			}
		});
		JLabel jLabel = new JLabel("背包道具", SwingConstants.CENTER);
		jLabel.setFont(FontFactory.commonFont(3, 30));

		jPanel1.add(jLabel, BorderLayout.NORTH);
		jPanel1.add(scrollPane, BorderLayout.CENTER);
	}

	/*
	 * 左下方使用按鈕
	 */
	public void south1() {
		Button jButton = new Button("USE");
		// 字型格式
		jButton.setFont(FontFactory.commonFont(3, 60));

		// 顏色
		jButton.setBackground(new Color(77, 57, 0));
		jButton.setForeground(Color.white);
		// 按鈕大小
		jButton.setPreferredSize(new Dimension(200, 100));
		jButton.addActionListener(e -> {

			int getNumber = jList.getSelectedIndex();
			// account.checkProps_name();
			// System.out.println("已選擇:"+getNumber);
			if (getNumber != -1) {
				select = propsID.get(getNumber);
				account.useProps(account, select, getNumber);
				subject.setAccount(account);
				// account.checkProps_name();
			}
			// System.out.println("已使用ID:"+select);
			propsNewList.clear(); // 清除目前所有元素
			if (!account.getProps_ArrayList().isEmpty()) {
				propsID.clear();
				for (Props props : account.getProps_ArrayList()) {
					propsNewList.addElement(props.getName());
					propsID.add(props.getID());
				} // 得到id，請與name同步
			}

			jList.revalidate();
			jList.repaint();
		});
		jPanel1.add(jButton, BorderLayout.SOUTH);
	}

	/*
	 * 顯示右上角的商店以及價位
	 */
	public void center2() {
		PropsList propsList = new PropsList();
		int showPropsNumber = 40;
		for (Props props : propsList) {
			if (showPropsNumber > 0) {
				propsNewList2.addElement(props.getName() + "   價位： " + props.getValue());
				showPropsNumber--;
			}

		}
		jList2 = new JList<String>(propsNewList2);
		JScrollPane scrollPane = new JScrollPane(jList2);
		scrollPane.setPreferredSize(new Dimension(150, 100));
		jList2.setCellRenderer(new MyListCellRenderer());
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		jPanel2.add(scrollPane, BorderLayout.CENTER);

	}

	/*
	 * 顯示右下角的購買以及取消
	 */
	public void south2() {
		JPanel jPanel3 = new JPanel();
		jPanel3.setLayout(new BorderLayout());
		jPanel3.setPreferredSize(new Dimension(100, 100)); // 設置大小
		jPanel3.setOpaque(false);
		JButton buyButton = new JButton("購買");
		JButton cancel = new JButton("取消");

		buyButton.addActionListener(buyButtonListener());
		cancel.addActionListener(canceListener());
		// 背景
		buyButton.setBackground(new Color(127, 255, 212));
		cancel.setBackground(new Color(204, 255, 0));

		// 去除聚焦
		buyButton.setFocusPainted(false);
		cancel.setFocusPainted(false);

		// 設定字體

		buyButton.setFont(FontFactory.commonFont(1, 40));
		cancel.setFont(FontFactory.commonFont(1, 40));

		// 顯示錢
		haveMoneyJLabel.setText("擁有的錢：" + account.getMoney());
		haveMoneyJLabel.setFont(FontFactory.commonFont(1, 30));

		jPanel3.add(haveMoneyJLabel, BorderLayout.NORTH);
		jPanel3.add(buyButton, BorderLayout.CENTER);
		jPanel3.add(cancel, BorderLayout.EAST);
		jPanel2.add(jPanel3, BorderLayout.SOUTH);
	}

	/*
	 * 購買按鈕使用
	 */
	public ActionListener buyButtonListener() {
		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PropsList propsList = new PropsList();

				/*
				 * 販售
				 */
				if (jList2.getSelectedIndex() != -1)
					selectPropsNumber();

				/*
				 * 刷新
				 */
				haveMoneyJLabel.revalidate();
				haveMoneyJLabel.repaint();
				jList.revalidate();
				jList.repaint();// 刷新列表
			}
		};
		return actionListener;
	}

	/*
	 * 取消按鈕使用
	 */
	public ActionListener canceListener() {
		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jFrame.setVisible(false);
				jFrame.dispose();
			}
		};
		return actionListener;
	}

	/*
	 * 按下購買後顯示此框: 道具:name |<| x |>| 總金額:total |確認| |取消|
	 */
	public void selectPropsNumber() {
		/*
		 * 基礎設定
		 */
		dialog = new JDialog(jFrame, "購買道具", true); // 建立模態的 JDialog
		dialog.setLayout(new GridBagLayout());
		dialog.setSize(200, 150);
		dialog.setLocationRelativeTo(jFrame); // 讓訊息框置中於 parent frame

		/*
		 * 元件
		 */
		PropsList propsList = new PropsList();
		Props selectProps = propsList.get(jList2.getSelectedIndex());

		/*
		 * 初始化
		 */
		userWantPropsNumber = 1;
		totalValue = (double) userWantPropsNumber * selectProps.getValue();
		labelStrings = new String[] { "道具:" + selectProps.getName(), "" + userWantPropsNumber, "總金額" + totalValue };
		buttonStrings = new String[] { "<", ">", "確認", "取消" };
		dialogJLabels = new JLabel[labelStrings.length];
		dialogButtons = new MyButton[buttonStrings.length];
		actionListeners = new ActionListener[buttonStrings.length];
		/*
		 * 
		 */
		for (int i = 0; i < labelStrings.length; i++) {
			jlabel(i);
		}
		dialog_button_action();
		for (int i = 0; i < buttonStrings.length; i++) {
			jButton(i);
		}

		/*
		 * 顯示設定
		 */
		dialog.setVisible(true); // 顯示訊息框
	}

	private GridBagConstraints layouts(int i) {
		GridBagConstraints[] gridBagConstraints = { layoutt(1, 1, 5, 1, 1, 1, BOTH, NORTH), // 道具
				layoutt(3, 2, 1, 1, 1, 1, BOTH, NORTH), // x
				layoutt(1, 3, 5, 1, 1, 1, BOTH, NORTH), // 總金額

				layoutt(1, 2, 1, 1, 1, 1, NONE, NORTH), // <
				layoutt(5, 2, 1, 1, 1, 1, NONE, NORTH), // >
				layoutt(1, 4, 2, 1, 1, 1, BOTH, NORTH), // 確認
				layoutt(5, 4, 2, 1, 1, 1, BOTH, NORTH),// 取消

		};

		return gridBagConstraints[i];
	}

	private GridBagConstraints layoutt(int x, int y, int width, int height, int tx, int ty, int fill, int anchor) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.weightx = tx;
		constraints.weighty = ty;
		constraints.fill = fill;
		constraints.anchor = anchor;
		return constraints;
	}

	public void jlabel(int i) {
		dialogJLabels[i] = new JLabel(labelStrings[i], SwingConstants.CENTER);
		dialogJLabels[i].setFont(FontFactory.commonFont(1, 20));
		dialog.add(dialogJLabels[i], layouts(i));
	}

	public void jButton(int i) {
		Color[] colors = new Color[] { new Color(255, 245, 238), new Color(255, 245, 238), new Color(102, 255, 230),
				new Color(255, 250, 205) };
		dialogButtons[i] = new MyButton();
		dialogButtons[i].setText(buttonStrings[i]);
		dialogButtons[i].setBackground(colors[i]);
		dialogButtons[i].addActionListener(actionListeners[i]);
		dialog.add(dialogButtons[i], layouts(i + labelStrings.length));
	}

	public void dialog_button_action() {
		PropsList propsList = new PropsList();
		Props selectProps = propsList.get(jList2.getSelectedIndex());
		/*
		 * <
		 */
		actionListeners[0] = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (userWantPropsNumber > 1)
					userWantPropsNumber--;
				totalValue = (double) userWantPropsNumber * selectProps.getValue();
				dialogJLabels[1].setText("" + userWantPropsNumber);
				dialogJLabels[2].setText("總金額" + totalValue);
			}
		};
		/*
		 * >
		 */
		actionListeners[1] = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userWantPropsNumber++;
				totalValue = (double) userWantPropsNumber * selectProps.getValue();
				dialogJLabels[1].setText("" + userWantPropsNumber);
				dialogJLabels[2].setText("總金額" + totalValue);
			}
		};
		/*
		 * 確認
		 */
		actionListeners[2] = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (totalValue <= account.getMoney()) {
					JOptionPane.showMessageDialog(null, "已購買", "提示", JOptionPane.INFORMATION_MESSAGE);
					/*
					 * account控制
					 */
					account.addMoney(-totalValue);// 扣錢
					for (int i = 0; i < userWantPropsNumber; i++) {
						account.addProps(account, jList2.getSelectedIndex());// 將所有選的道具給玩家
					}
					/*
					 * 更新jlist內容
					 */
					propsNewList.clear(); // 清除目前所有元素
					if (!account.getProps_ArrayList().isEmpty()) {
						propsID.clear();
						for (Props props : account.getProps_ArrayList()) {
							propsNewList.addElement(props.getName());
							propsID.add(props.getID());

						} // 得到id，請與name同步
					}
					/*
					 * 更新標籤
					 */
					haveMoneyJLabel.setText("擁有的錢：" + account.getMoney());
					/*
					 * 自動關閉dialog
					 */
					dialog.setVisible(false);
					dialog.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "錢不夠", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		};
		/*
		 * 取消
		 */
		actionListeners[3] = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.setVisible(false);
				dialog.dispose();
			}
		};
	}
	public boolean notOpen() {
		System.out.println(notOpen);
		return  notOpen;
	}
	public void toFront() {
		jFrame.toFront();
	}
}
