package test;

import javax.swing.JLabel;
import javax.swing.JPanel;

//第一個面板，用於顯示數值
class PanelA extends JPanel implements test.Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label = new JLabel("PanelB 的數值： ");

	public PanelA(test.Subject subject) {
		subject.addObserver(this); // 註冊為觀察者
		add(label);
	}

	@Override
	public void update(int value) {
		label.setText("PanelB 的數值： " + value); // 更新數值
	}

}