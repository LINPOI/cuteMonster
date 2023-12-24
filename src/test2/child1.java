package test2;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Child1 extends JPanel{
	private JLabel jLabel;
	public Child1(Father.Mather value) {
		// TODO Auto-generated constructor stub
		setLayout(new FlowLayout());
		jLabel.setText("s");
		add(jLabel);
	}
}
