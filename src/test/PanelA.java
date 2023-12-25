package test;

import javax.swing.JLabel;
import javax.swing.JPanel;

//第一個面板，用於顯示數值
class PanelA extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label = new JLabel("PanelB 的數值： ");
		private int value=0;
	public PanelA(finalproject2.Subject subject) {
		subject.addObserver(this); // 註冊為觀察者
		label.setText("PanelB 的數值： " + value);
		add(label);
	}

	@Override
	public void update(int value) {
		 this.value=value;// 更新數值
	}

}