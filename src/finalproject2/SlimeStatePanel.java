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


public class SlimeStatePanel extends JPanel implements Observer {
	private Subject subject;
	private Account account;

//	private JProgressBar hungerValue_jJProgressBar;
//	private JProgressBar thirstValue_jJProgressBar;
//	private JProgressBar moodValue_jJProgressBar;
//	private JProgressBar healthValue_jJProgressBar;
	private JProgressBar[] jProgressBars = new JProgressBar[4];
	// {hungerValue_jJProgressBar,thirstValue_jJProgressBar,moodValue_jJProgressBar,healthValue_jJProgressBar}
	private Boolean[] switch_progressBar_string = new Boolean[] { false, false, false, false };

	public SlimeStatePanel(Account account, Subject subject) {
		this.subject = subject;
		subject.addObserver(this);
		this.account = account;
		// TODO Auto-generated constructor stub
		for (int i = 0; i < account.monster.getStates_name().length; i++) {
			this.add(jProgressBars(i));
		}
		this.setOpaque(false); // 透明背景
		this.setLayout(new GridLayout(4, 0));

	}

	public JProgressBar jProgressBars(int i) {
		jProgressBars[i] = new JProgressBar(JProgressBar.VERTICAL);
		jProgressBars[i].setUI(new CustomProgressBarUI());
		jProgressBars[i].setMinimum(0); // 設定最小值
		jProgressBars[i].setMaximum(100); // 設定最大值
		jProgressBars[i].setValue(account.monster.getStates(i)); // 設定目前的值
		jProgressBars[i].setFont(FontFactory.commonFont(1));
		jProgressBars[i].setPreferredSize(new Dimension(30, 100));

		jProgressBars[i].setString(account.monster.getStates_name(i));
		jProgressBars[i].setStringPainted(true); // 顯示文字內容

		if (jProgressBars[i].getValue() <= 30) {// 設定進度條前景色
			jProgressBars[i].setForeground(Color.red);
		} else if (jProgressBars[i].getValue() < 50) {
			jProgressBars[i].setForeground(Color.orange);
		} else if (jProgressBars[i].getValue() <= 80) {
			jProgressBars[i].setForeground(Color.cyan);
		} else {
			jProgressBars[i].setForeground(Color.green);
		}

		jProgressBars[i].setBackground(Color.LIGHT_GRAY); // 設定進度條背景色
		jProgressBars[i].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) { // 如果是左鍵點擊
					switch_progressBar_string[i] = !switch_progressBar_string[i];
					if (switch_progressBar_string[i]) {
						jProgressBars[i].setString(account.monster.getStates(i) + "%");
					} else {
						jProgressBars[i].setString(account.monster.getStates_name(i));
					}

				}
			}
		});
		return jProgressBars[i];
	}

	@Override
	public void updataAccount(Account account) {
		// TODO Auto-generated method stub
		this.account = account;
		for (int i = 0; i < account.monster.getStates_name().length; i++) {
			jProgressBars[i].setValue(account.monster.getStates(i)); // 設定目前的值
			if (switch_progressBar_string[i]) {
				jProgressBars[i].setString(account.monster.getStates(i) + "%");
			} else {
				jProgressBars[i].setString(account.monster.getStates_name(i));
			}
			//System.out.println(account.monster.getStates_name(i)+account.monster.getStates(i));
		}
		this.revalidate();
		this.repaint();
	}
}
