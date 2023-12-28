package finalproject2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import java.awt.event.*;

public class LogIn extends JPanel implements Commonly_GridBagConstraints {
	private Subject subject;//新增觀察者
	
	protected CardLayout cardLayout;// 切換面板
	protected JPanel cardPanel;// 面板

	private RWFile rwFile=new RWFile();

	protected JTextField textField = null;// 文字框
	protected JPasswordField passwordField = null;// 密碼框
	protected JButton button = null;// 按鈕框
	protected JLabel jLabel = null;// 標題框

	// 監聽
	protected ActionListener[] actionListeners = null;
	private ActionListener register = null;
	private ActionListener logIn = null;
	
	protected String[] textStrings;// 顯示文字陣列
	protected ArrayList<JComponent> gUIComponents = new ArrayList<>();

	protected String register_username = "";
	protected String register_password = "";

	// 獲得資料庫
	protected DatabaseOperations databaseOperations = new DatabaseOperations();
	/*
	 * 
	 */
	/*
	 * 控制面板資訊切換
	 */
	/*
	 * cardLayout.show(cardPanel, "...");
	 */
	List<Account> accountList = new ArrayList<>(); // 全部帳號
	Account account = new Account(); // 帳號
	Account currentAccount = new Account();// 新帳號
	protected int[][] grid;// 元件布局
	// 元件
	JPanel jPanel = new JPanel();
	protected int height = 800;
	protected int width = 1000;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogIn(CardLayout cardLayout, JPanel cardPanel,Subject subject ) {
		databaseOperations.create_Account_Table();//新增使用者資料
		this.cardLayout = cardLayout;
		this.cardPanel = cardPanel;//切換panel
		this.subject=subject;//觀察者
		JPanel loginPanel = open();
		this.setLayout(new BorderLayout());
		this.add(new JLabel("              "
				+ "                        "
				+ "                        "),BorderLayout.EAST);
		this.add(new JLabel("              "
				+ "                        "
				+ "                        "),BorderLayout.WEST);
		this.add(loginPanel,BorderLayout.CENTER);
	}
	public JPanel open() {
		jPanel.setLayout(new GridBagLayout());
		jPanel.setSize(new Dimension(width, height));

		textStrings = new String[] { "帳號", "密碼", "註冊", "登入" };
		textField = new JTextField();
		getGrid();
		Action();
		actionListeners = new ActionListener[] { register, logIn };
		textFields();
		passwordField();

		for (int i = 0; i < 2; i++) {
			lables(i);
		}

		for (int i = 0; i < 2; i++) {
			buttons(i + 2);
		}
		spacer(30);
		spacer(20);
		for (int i = 0; i < gUIComponents.size(); i++) {
			addComponent(grid, gUIComponents, i);
		}

		passwordField.addKeyListener(new MyKeyListener());
		// jPanel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));// 設置邊界
		return jPanel;
	}

	public void textFields() {
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(60, 25));
		gUIComponents.add(textField);
	}

	public void passwordField() {
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(60, 25));
		gUIComponents.add(passwordField);
	}

	public void lables(int i) {
		jLabel = new JLabel(textStrings[i]);
		jLabel.setPreferredSize(new Dimension(30, 30));
		jLabel.setFont(FontFactory.commonFont(1));
		gUIComponents.add(jLabel);
	}

	public void buttons(int i) {
		button = new JButton(textStrings[i]);
		button.addActionListener(actionListeners[i - 2]);
		gUIComponents.add(button);
	}

	public void spacer(int size) {
		JLabel spacer = new JLabel("                    ");
		jLabel.setPreferredSize(new Dimension(size, size));
		gUIComponents.add(spacer);
	}

	public int[][] getGrid() {
		// TODO Auto-generated method stub
		grid = new int[][] { 
				{ 1, 2, 2, 1, 1, 0, BOTH, CENTER }, // TEXTFIELD
				{ 1, 5, 2, 1, 1, 0, BOTH, CENTER },

				{ 1, 1, 2, 1, 1, 0, BOTH, CENTER }, // TEXT
				{ 1, 4, 2, 1, 1, 0, BOTH, CENTER },

				{ 1, 7, 1, 1, 1, 0, BOTH, CENTER }, // BUTTON
				{ 2, 7, 1, 1, 1, 0, BOTH, CENTER },

				{ 1, 6, 2, 1, 1, 0, BOTH, CENTER }, // 空格
				{ 1, 3, 2, 1, 1, 0, BOTH, CENTER }, 

		};
		return grid;
	}

	public void Action() {
		accountList = databaseOperations.queryData();// 讀取資料

		register = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int read = 3;
				// TODO Auto-generated method stub
				char[] passwordChars = passwordField.getPassword();
				String password = new String(passwordChars);
				password = password.toLowerCase();
				String Username = textField.getText().toLowerCase();
				account.setUsername(Username);
				account.setPassword(password);
				if (!account.getUsername().equals("") && !account.getPassword().equals("")) {
					read = Queryinf(account);
				}

				if (read == 1 || read == 2) {
					JOptionPane.showMessageDialog(jPanel, "帳號已存在");
				} else if (read == 3) {
					JOptionPane.showMessageDialog(jPanel, "帳密不可為空");
				} else {
					Boolean good = databaseOperations.insert_Account_Data(account);
					if (good) {
						JOptionPane.showMessageDialog(jPanel, "註冊成功");
						
						register_username = Username;
						register_password = password;
						databaseOperations.create_Monster_Table(Username);
						
					} else {
						JOptionPane.showMessageDialog(jPanel, "錯誤");
					}

				}
			}
		};
		logIn = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				success();
			}
		};
	}

	private class MyKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			// 執行特定的動作，當鍵被按下時
			int keyCode = e.getKeyCode();
			// System.out.println("按下: " + KeyEvent.getKeyText(keyCode)+keyCode);
			if (keyCode == 10) {
				success();
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// 當鍵被顯示時 (鍵盤敲擊)

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// 當鍵被釋放時
		}
	}

	public void success() {
		char[] passwordChars = passwordField.getPassword();
		String password = new String(passwordChars);
		password = password.toLowerCase();
		String username = textField.getText().toLowerCase();
		account.setUsername(username);
		account.setPassword(password);
		int read = 3;
		if (!username.equals("") || !password.equals("")) {
			read = Queryinf(account);
		}
		if (read == 1) {
			System.out.println("登入成功");
			currentAccount = account;
			rwFile.saveToFile(username);
			if(!databaseOperations.monsterData(username)) {//第一次登入
				cardLayout.show(cardPanel, "first");
				String userInput= JOptionPane.showInputDialog(null, "請輸入怪物名字:");
				account.monster.setName(userInput); 
				account.saveUser(account);
				subject.setStrings(new String[] {userInput}); // 設置新數值
				databaseOperations.insert_Monster_Data(account);//?這裡成功了?
				
			}else {//第n次登入
				cardLayout.show(cardPanel, "first");
				databaseOperations.queryMonsterInfo(account);
				subject.setStrings(new String[] {account.monster.getName(),account.getUsername()}); // 設置新數值
				account= databaseOperations.queryData(account);
			}
			
			
		} else if (read == 2) {
			JOptionPane.showMessageDialog(jPanel, "密碼錯誤");
			currentAccount = new Account();
		} else if (read == 3) {
			JOptionPane.showMessageDialog(jPanel, "未輸入");
		} else {
			if (account.getUsername().equals(register_username) && account.getPassword().equals(register_password)) {//直接登入
				System.out.println("登入成功");
				currentAccount = account;
				cardLayout.show(cardPanel, "first");
				String userInput= JOptionPane.showInputDialog(null, "請輸入怪物名字:");   
				account.monster.setName(userInput); 
				account.saveUser(account);
				subject.setStrings(new String[] {userInput,account.getUsername()}); // 設置新數值
				databaseOperations.insert_Monster_Data(account);//?這裡成功了?
			} else {
				JOptionPane.showMessageDialog(jPanel, "帳號不存在");
				currentAccount = new Account();
			}

		}
	}

	public int Queryinf(Account account_input) {// 0:沒找到 1:找到 2.密碼錯誤
		for (Account account : accountList) {
			String username = account.getUsername();
			String password = account.getPassword();
			if (username.equals(account_input.getUsername())) {
				if (password.equals(account_input.getPassword())) {
					System.out.println("帳密正確");

					return 1;
				}
				System.out.println("密碼錯誤");
				return 2;
			}
			// System.out.println("帳號: " + username + ", 密碼: " + password + "，輸入帳號: " +
			// account_input.getUsername() + ", 密碼: " + account_input.getPassword());
		}
		return 0;
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
		jPanel.add(gUIComponents.get(i), constraints);
	}

	public Account getAccount() {
		return currentAccount;
	}
}
