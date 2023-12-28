package finalproject2;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SlimeStatePanel extends JPanel {
	private Account account;
	public SlimeStatePanel(Account account) {
		this.account=account;
		// TODO Auto-generated constructor stub
		for(int i=0;i<account.monster.getStates_name().length;i++) {
		this.add(jLabel(i));
		}
		this.setOpaque(false); // 透明背景
		this.setLayout(new GridLayout(4, 0));
		
	}
	public JLabel jLabel(int i) {
		JLabel jLabel=new JLabel(account.monster.getStates_name(i));
		
		
		return jLabel;
	}
}
