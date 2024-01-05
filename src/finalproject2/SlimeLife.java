package finalproject2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Props.Props;

public class SlimeLife extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Subject subject;
	private Account account;
	
	private ImageIcon slimebutton1x1 = new ImageIcon("src/PICTURE/slimebutton1x1.png");// 應用照
	private JFrame jFrame = new JFrame("史萊姆的窩");
	private JPanel jPanel = new JPanel();
	private JList<String> jList;
	private LinkedList<Integer> propsID=new LinkedList<Integer>();
	
	private int select=-1;
	private int jListSelect=-1;
	public SlimeLife(Account account, Subject subject) {
		this.account = account;
		this.subject = subject;
		// TODO Auto-generated constructor stub
		jFrame.setLayout(new BorderLayout());
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 按下關閉時的動作(關閉全部)
		Image slimebutton1x1_Image = slimebutton1x1.getImage(); // 取得圖片
		jFrame.setIconImage(slimebutton1x1_Image); // 應用圖示
		jFrame.setSize(new Dimension(1000, 800)); // 框架尺寸
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(250, 250, 210));
		center();
		east();
		jFrame.add(this);
		jFrame.setLocationRelativeTo(null);// 置中顯示
		jFrame.setVisible(true); // 顯示
	}

	public void center() {
		DefaultListModel<String> propsNewList = new DefaultListModel<String>();// 建立可獲得的道具清單
		for (Props props : account.getProps_ArrayList()) {
			propsNewList.addElement(props.getName());
			propsID.add(props.getID());
		}//得到id，請與name同步

		 jList = new JList<String>(propsNewList);
		JScrollPane scrollPane = new JScrollPane(jList);
		scrollPane.setPreferredSize(new Dimension(150, 100));
		jList.setCellRenderer(new MyListCellRenderer());
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					jList.clearSelection();
				}
			}
		});
		jList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) { // 確保不是選擇中的變更
					jListSelect = jList.getSelectedIndex(); // 獲取所選項目的索引
				}
			}
		});
		this.add(scrollPane, BorderLayout.CENTER);
	}

	public void east() {
		Button jButton = new Button("USE");
		// 字型格式
		jButton.setFont(FontFactory.commonFont(3, 60));
		int getNumber= jList.getSelectedIndex();
		System.out.println("已選擇:"+getNumber);
		if(getNumber!=-1) {
			select= propsID.get(getNumber);
		}
		
		// 顏色
		jButton.setBackground(new Color(77, 57, 0));
		jButton.setForeground(Color.white);
		// 按鈕大小
		jButton.setPreferredSize(new Dimension(200, 100));
		jButton.addActionListener(e -> {
			if(select!=-1)account.useProps(account, select);
			System.out.println("已使用ID:"+select);
		});
		this.add(jButton, BorderLayout.SOUTH);
	}
}
