package test;

import java.awt.GridLayout;

import javax.swing.JFrame;

//主類，用於建立和顯示面板
public class Main {
	public static void main(String[] args) {
		Subject subject = new Subject(); // 創建 Subject

		// 創建兩個面板並添加到 JFrame 中
		PanelA panelA = new PanelA(subject);
		PanelB panelB = new PanelB(subject);

		JFrame frame = new JFrame("Panel 通訊");
		frame.setLayout(new GridLayout(2, 1)); // 2 行 1 列的 GridLayout
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panelA);
		frame.add(panelB);
		frame.pack();
		frame.setVisible(true);
	}
}