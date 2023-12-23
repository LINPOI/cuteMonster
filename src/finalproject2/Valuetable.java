package finalproject2;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class ValueTable extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Monster monster=new Monster();
	private String[] listValue;
	private int width=130;
	private int height=130;
	public ValueTable() {
		// TODO Auto-generated constructor stub
		listValue = new String[monster.getValueName().length];
		for(int i=0;i<monster.getValueName().length;i++) {
			listValue[i]=monster.getValueName(i)+"："+monster.getValue(i);
		}
		JList<String>list=new JList<String>(listValue);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(width, height));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setOpaque(false); // 透明背景
        this.add(scrollPane);
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame jFrame = new JFrame("列表");
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setSize(new Dimension(1000, 800));
			jFrame.setLocationRelativeTo(null);
			jFrame.setBackground(Color.BLUE);
			ValueTable firstPage = new ValueTable();
			jFrame.add(firstPage);

			jFrame.setVisible(true);
		});
	}
}
