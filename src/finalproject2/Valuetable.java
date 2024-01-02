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

public class Valuetable extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String[] listValue;
	private String outputString="";
	private int width=130;
	private int height=130;
	public Valuetable(Account account) {
		this.setLayout(new BorderLayout());
		// TODO Auto-generated constructor stub
		listValue = new String[account.monster.getValueName().length+1];
		for(int i=0;i<account.monster.getValueName().length;i++) {
			listValue[i]=account.monster.getValueName(i)+"："+account.monster.getValue(i);
		}
		String wing="無";
		if(account.monster.getWing()) {
			wing="有";
		}
		
		listValue[listValue.length-1]="翅膀："+wing;
		
		JList<String>list=new JList<String>(listValue);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(width, height));
		list.setBackground(new Color(255,255,135));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setOpaque(false);						 
		JLabel label=new JLabel(outputString);
		 label.setOpaque(true);	// 透明背景
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
                        label.setText(account.monster.getValueName(index)+":"+account.monster.getInf(index));
                        
                    }
                }
            }
        });

	}
	public String getOutput() {
		return outputString;
	}
}
