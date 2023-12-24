package test2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Child2 extends JPanel {
	private JButton button = new JButton("增加數值");
	public Child2(Father.Mather value) {
		// TODO Auto-generated constructor stub
		setLayout(new FlowLayout());
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				value.x++;
			}
		});
	}
}
