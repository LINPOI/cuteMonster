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
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Valuetable extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DefaultListModel<String>  listValue= new DefaultListModel<>();
	private String outputString = "";
	private int width = 130;
	private int height = 130;
	private JList<String> list;
	private Subject subject;
	public Valuetable(Account account, Subject subject) {
		this.subject = subject;
		subject.addObserver(this);
		
		this.setLayout(new BorderLayout());
		// TODO Auto-generated constructor stub
		for (int i = 0; i < account.monster.getValueName().length; i++) {
			String item  = account.monster.getValueName(i) + "：" + account.monster.getValue(i);
			listValue.addElement(item); // 添加新元素
		}
		String wing = (account.monster.getWing()) ? "有" : "無";
		listValue.addElement("翅膀：" + wing);

		list = new JList<String>(listValue);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(width, height));
		list.setBackground(new Color(255, 255, 135));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setOpaque(false);
		JLabel label = new JLabel(outputString);
		label.setOpaque(true); // 透明背景
		label.setBackground(new Color(255, 217, 230));
		this.add(scrollPane, BorderLayout.NORTH);
		this.add(label, BorderLayout.PAGE_END);
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
						//System.out.println("所選擇的是: " + listValue[index]); // 輸出所選項目的內容
						label.setText(account.monster.getValueName(index) + ":" + account.monster.getInf(index));
						
					}
				}
			}
		});

	}

	public String getOutput() {
		return outputString;
	}

	@Override
	public void updataAccount(Account account) {
		// TODO Auto-generated method stub
		account.saveAccount(account);
		//System.out.println("year"+account.getYear());
		listValue.clear(); // 清除目前所有元素
		for (int i = 0; i < account.monster.getValueName().length; i++) {
		    String item = account.monster.getValueName(i) + "：" + account.monster.getValue(i);
		    listValue.addElement(item); // 添加新元素
		    //System.out.println(account.monster.getValueName(i) + "：" + account.monster.getValue(i));
		}

		String wing = (account.monster.getWing()) ? "有" : "無";
		listValue.addElement("翅膀：" + wing);
		list.revalidate();
		list.repaint();
	}
}
