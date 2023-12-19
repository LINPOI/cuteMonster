package finalproject2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
public class LogIn extends SetPanel {
	private ActionListener register = null;
	private ActionListener logIn = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LogIn(CardLayout cardLayout, JPanel cardPanel) {
	     this.cardLayout = cardLayout;
	     this.cardPanel = cardPanel;

	     JPanel loginPanel = open();
	     this.add(loginPanel);
	}
	public JPanel open() {
		
		jPanel.setLayout(new GridBagLayout());
		jPanel.setSize(new Dimension(weigth, height));
		
		
		gUIComponents = new ArrayList<JComponent>();

		textStrings = new String[] { "帳號", "密碼", "註冊", "登入" };
		textField=new JTextField();
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
			addComponent(i);
		}
		
		passwordField.addKeyListener(new MyKeyListener());
		//jPanel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));// 設置邊界
		return jPanel;
	}
	public void textFields() {
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(600, 25));
		gUIComponents.add(textField);
	}
	public void passwordField() {
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(600,25));
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
		JLabel spacer = new JLabel("                       ");
		jLabel.setPreferredSize(new Dimension(size, size));
		gUIComponents.add(spacer);
	}

	@Override
	public int[][] getGrid() {
		// TODO Auto-generated method stub
		this.grid = new int[][] { { 0, 1, 2, 1, 1, 0, BOTH, CENTER }, // TEXTFIELD
				{ 0, 4, 2, 1, 1, 0, BOTH, CENTER },

				{ 0, 0, 2, 1, 1, 0, BOTH, CENTER }, // TEXT
				{ 0, 3, 2, 1, 1, 0, BOTH, CENTER },

				{ 0, 6, 1, 1, 1, 0, BOTH, CENTER }, // BUTTON
				{ 1, 6, 1, 1, 1, 0, BOTH, CENTER },

				{ 0, 5, 2, 1, 1, 0, BOTH, CENTER }, // 空格
				{ 0, 2, 2, 1, 1, 0, BOTH, CENTER },

		};
		return grid;
	}

	
	public void Action() {
		accountList = databaseOperations.queryData();// 讀取資料
		
		register = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int read=3;
				// TODO Auto-generated method stub
				char[] passwordChars = passwordField.getPassword();
				String password = new String(passwordChars);
				password=password.toLowerCase();
				String Username = textField.getText().toLowerCase();
				account.setUsername(Username);
				account.setPassword(password);
				if(!account.getUsername().equals("")&&!account.getPassword().equals("")) {
					 read=Queryinf(account);
				}
				
				if(read==1 ||read==2) {
					 JOptionPane.showMessageDialog(jPanel, "帳號已存在");
				}else if(read==3){
					JOptionPane.showMessageDialog(jPanel, "帳密不可為空");
				}else{
					Boolean good= databaseOperations.insertData(account);
					if(good) {
						JOptionPane.showMessageDialog(jPanel, "註冊成功");
						register_username=Username;
						register_password=password;
					}else {
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
            //System.out.println("按下: " + KeyEvent.getKeyText(keyCode)+keyCode);
            if(keyCode==10) {
            	success();
            }
        }
        @Override
        public void keyTyped(KeyEvent e) {
            //當鍵被顯示時 (鍵盤敲擊)
           
        }
        @Override
        public void keyReleased(KeyEvent e) {
            // 當鍵被釋放時
        }
    }
	public void success() {
		char[] passwordChars = passwordField.getPassword();
		String password = new String(passwordChars);
		password=password.toLowerCase();
		String username = textField.getText().toLowerCase();
		account.setUsername(username);
		account.setPassword(password);
		int read=3;
		if(!username.equals("")||!password.equals("")) {
			read=Queryinf(account);
		}
		if(read==1) {
			System.out.println("登入成功");
			currentAccount=account;
			cardLayout.show(cardPanel, "first");
		}else if(read==2){
			JOptionPane.showMessageDialog(jPanel, "密碼錯誤");
			currentAccount=new Account();
		}else if(read==3) {
			JOptionPane.showMessageDialog(jPanel, "未輸入");
		}else {
			if(account.getUsername().equals(register_username)&&account.getPassword().equals(register_password)) {
				System.out.println("登入成功");
				currentAccount=account;
				cardLayout.show(cardPanel, "first");
			}else {
				JOptionPane.showMessageDialog(jPanel, "帳號不存在");
				 currentAccount=new Account();
			}
			 
		}
	}

	public int Queryinf(Account account_input) {//0:沒找到 1:找到 2.密碼錯誤
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
	        //System.out.println("帳號: " + username + ", 密碼: " + password + "，輸入帳號: " + account_input.getUsername() + ", 密碼: " + account_input.getPassword());
	    }
		return 0;
	}
	public Account getAccount() {
		return currentAccount;
	}
}
