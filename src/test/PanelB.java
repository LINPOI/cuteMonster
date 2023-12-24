package test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

//第二個面板，用於更改數值
class PanelB extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button = new JButton("增加數值");

	public PanelB(test.Subject subject) {
		setLayout(new FlowLayout());
		add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int 新數值 = subject.getValue() + 5; // 假設每次按下按鈕增加 5
				subject.setValue(新數值); // 設置新數值
			}
		});
	}
}