package test2;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Father extends JPanel{
	private Mather mather;
	public Father() {
		setLayout(new GridLayout(0,2));
		Child1 child1 = new Child1(mather);
		Child2 child2 = new Child2(mather);
		add(child1);
		add(child2);
		setVisible(true);
	}

	public static void main(String[] args) {
		Father father1Father = new Father();
	}
	public class Mather {
		int x=0;
		
	}
}
