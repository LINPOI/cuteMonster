package finalproject2;

import java.awt.Color;

import javax.swing.JButton;

public class MyButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MyButton() {
		// TODO Auto-generated constructor stub
		mystyle();
	}
	
	public void mystyle() {
		this.setBackground(new Color(127, 255, 212));

		// 去除聚焦
		this.setFocusPainted(false);

		// 設定字體
		this.setFont(FontFactory.commonFont(1, 20));
	}
}
