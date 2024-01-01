package finalproject2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import javafx.scene.input.MouseDragEvent;

public class SlimeStatePanel extends JPanel implements Observer {
	private Subject subject;
	private Account account;
	private Boolean[] switch_progressBar_string=new Boolean[] {false,false,false,false};
	public SlimeStatePanel(Account account,Subject subject) {
		this.subject=subject;
		subject.addObserver(this);
		this.account=account;
		// TODO Auto-generated constructor stub
		for(int i=0;i<account.monster.getStates_name().length;i++) {
		this.add(jProgressBars(i));
		}
		this.setOpaque(false); // 透明背景
		this.setLayout(new GridLayout(4, 0));
		
	}

	public JProgressBar jProgressBars(int i) {
		JProgressBar jProgressBar = new JProgressBar(JProgressBar.VERTICAL);
		jProgressBar.setUI(new CustomProgressBarUI());
		jProgressBar.setMinimum(0); // 設定最小值
		jProgressBar.setMaximum(100); // 設定最大值
		jProgressBar.setValue(account.monster.getStates(i)); // 設定目前的值
		jProgressBar.setFont(FontFactory.commonFont(1));
		jProgressBar.setPreferredSize(new Dimension(30, 100));
		
		jProgressBar.setString(account.monster.getStates_name(i));
		jProgressBar.setStringPainted(true); // 顯示文字內容
		
		if(jProgressBar.getValue()<=30) {// 設定進度條前景色
			jProgressBar.setForeground(Color.red); 
		}else if(jProgressBar.getValue()<50){
			jProgressBar.setForeground(Color.orange); 
		}else if(jProgressBar.getValue()<=80){
			jProgressBar.setForeground(Color.cyan); 
		}else {
			jProgressBar.setForeground(Color.green); 
		}
		
		jProgressBar.setBackground(Color.LIGHT_GRAY); // 設定進度條背景色
		jProgressBar.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (SwingUtilities.isLeftMouseButton(e)) { // 如果是左鍵點擊
	                	switch_progressBar_string[i]=!switch_progressBar_string[i];
	                	if(switch_progressBar_string[i]) {
	                		jProgressBar.setString(account.monster.getStates(i)+"%");
	            		}else {
	            			jProgressBar.setString(account.monster.getStates_name(i));
	            		}
	                	
	                }
	            }
	        });
		return jProgressBar;
	}

	@Override
	public void update(String[] strings) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updataInt(int[] ints) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updataAccount(Account account) {
		// TODO Auto-generated method stub
		
	}
}
