package finalproject2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ValueTable extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Monster monster=new Monster();
	private String[] listValue;
	private String outputString="";
	private int width=130;
	private int height=130;
	public ValueTable() {
		this.setLayout(new BorderLayout());
		// TODO Auto-generated constructor stub
		listValue = new String[monster.getValueName().length];
		for(int i=0;i<monster.getValueName().length;i++) {
			listValue[i]=monster.getValueName(i)+"："+monster.getValue(i);
		}
		JList<String>list=new JList<String>(listValue);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(width, height));
		list.setBackground(new Color(255,255,135));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setOpaque(false); // 透明背景
		JLabel label=new JLabel(outputString);
		 label.setOpaque(true);
		label.setBackground(new Color(255,217,230));
        this.add(scrollPane,BorderLayout.NORTH);
        this.add(label,BorderLayout.PAGE_END);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { 
                    list.clearSelection();
                }
            }
        });
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // 確保不是選擇中的變更
                    int index = list.getSelectedIndex(); // 獲取所選項目的索引
                    if (index != -1) { // 確認是否有選取項目
                        System.out.println("所選擇的是: " + listValue[index]); // 輸出所選項目的內容
                        label.setText(monster.getValueName(index)+":"+monster.getInf(index));
                        
                    }
                }
            }
        });

	}
	public String getOutput() {
		return outputString;
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
