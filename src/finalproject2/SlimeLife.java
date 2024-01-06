package finalproject2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Props.Props;
import Props.PropsList;
import javafx.scene.shape.HLineTo;

public class SlimeLife extends JPanel {

	/**
	 * 點擊史萊姆
	 */
	private static final long serialVersionUID = 1L;
	private Subject subject;
	private Account account;
	
	private ImageIcon slimebutton1x1 = new ImageIcon("src/PICTURE/slimebutton1x1.png");// 應用照
	private JFrame jFrame = new JFrame("史萊姆的窩");
	private JPanel jPanel1 = new JPanel();
	private JPanel jPanel2 = new JPanel();
	private JList<String> jList;
	private JList<String> jList2;
	private DefaultListModel<String> propsNewList= new DefaultListModel<String>();// 建立可獲得的道具清單
	private DefaultListModel<String> propsNewList2= new DefaultListModel<String>();// 建立可獲得的道具清單
	private LinkedList<Integer> propsID=new LinkedList<Integer>();
	private int select=-1;
	
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
		jPanel1.setLayout(new BorderLayout());
		jPanel2.setLayout(new BorderLayout());
		jPanel1.setOpaque(false);
		jPanel2.setOpaque(false);
		center1();
		south1();
		center2();
		south2();
		this.add(jPanel1,BorderLayout.WEST);
		this.add(jPanel2,BorderLayout.CENTER);
		//this.add(new JLabel("asds"),BorderLayout.NORTH);
		jFrame.add(this);
		jFrame.setLocationRelativeTo(null);// 置中顯示
		jFrame.setVisible(true); // 顯示
	}

	public void center1() {
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
					int getNumber= jList.getSelectedIndex();
					select= propsID.get(getNumber);
					account.useProps(account,select,getNumber);
					subject.setAccount(account);
					account.checkProps_name();
					 int dialogResult = JOptionPane.showConfirmDialog(jFrame,
				                "要使用"+jList.getSelectedValue()+"嗎?", "確認", JOptionPane.YES_NO_OPTION);

				        if (dialogResult == JOptionPane.YES_OPTION) {
				        	propsNewList.clear(); // 清除目前所有元素
							if(!account.getProps_ArrayList().isEmpty()) {
								for (Props props : account.getProps_ArrayList()) {
									propsNewList.addElement(props.getName());
									propsID.add(props.getID());
								}//得到id，請與name同步
							}
							
							jList.revalidate();
							jList.repaint();
				            // 在這裡放入確認後的程式碼
				            
				        } else {
				            
				            // 在這裡放入取消後的程式碼，或者不做任何事情
				        }
				}
			}
		});
		JLabel jLabel=new JLabel("背包道具" ,SwingConstants.CENTER);
		jLabel.setFont(FontFactory.commonFont(3,30));
		
		jPanel1.add(jLabel,BorderLayout.NORTH);
		jPanel1.add(scrollPane, BorderLayout.CENTER);
	}

	public void south1() {
		Button jButton = new Button("USE");
		// 字型格式
		jButton.setFont(FontFactory.commonFont(3, 60));
		
		
		// 顏色
		jButton.setBackground(new Color(77, 57, 0));
		jButton.setForeground(Color.white);
		// 按鈕大小
		jButton.setPreferredSize(new Dimension(200, 100));
		jButton.addActionListener(e -> {
			
			
			int getNumber= jList.getSelectedIndex();
			account.checkProps_name();
			System.out.println("已選擇:"+getNumber);
			if(getNumber!=-1) {
				select= propsID.get(getNumber);
				account.useProps(account,select,getNumber);
				subject.setAccount(account);
				//account.checkProps_name();
			}
			System.out.println("已使用ID:"+select);
			propsNewList.clear(); // 清除目前所有元素
			if(!account.getProps_ArrayList().isEmpty()) {
				for (Props props : account.getProps_ArrayList()) {
					propsNewList.addElement(props.getName());
					propsID.add(props.getID());
				}//得到id，請與name同步
			}
			
			jList.revalidate();
			jList.repaint();
		});
		jPanel1.add(jButton, BorderLayout.SOUTH);
	}
	public void center2() {
		PropsList propsList=new PropsList();
		for(Props props:propsList) {
			propsNewList2.addElement(props.getName()+"   價位： "+props.getValue());
		}
		jList2=new JList<String>(propsNewList2);
		JScrollPane scrollPane = new JScrollPane(jList2);
		scrollPane.setPreferredSize(new Dimension(150, 100));
		jList2.setCellRenderer(new MyListCellRenderer());
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		
		
		
		jPanel2.add(scrollPane,BorderLayout.CENTER);
		
	}
	public void south2() {
		JPanel jPanel3=new JPanel();
		jPanel3.setLayout(new BorderLayout());
		jPanel3.setPreferredSize(new Dimension(100, 100)); // 設置大小
		jPanel3.setOpaque(false);
		JButton buyButton=new JButton("購買");
		JButton cancel=new JButton("取消"); 
		
		buyButton.addActionListener(buyButtonListener());
		cancel.addActionListener(canceListener());
		//背景
		buyButton.setBackground(new Color(	127, 255, 212	));
		cancel.setBackground(new Color(	204, 255, 0	));
		
		// 去除聚焦
		buyButton.setFocusPainted(false);
		cancel.setFocusPainted(false);
		
		//設定字體
	
		buyButton.setFont(FontFactory.commonFont(1,40));
		cancel.setFont(FontFactory.commonFont(1,40));
		
		//顯示錢
		JLabel jLabel=new JLabel("擁有的錢："+account.getMoney());
		jLabel.setFont(FontFactory.commonFont(1, 30));
		
		jPanel3.add(jLabel,BorderLayout.NORTH);
		jPanel3.add(buyButton,BorderLayout.CENTER);
		jPanel3.add(cancel,BorderLayout.EAST);
		jPanel2.add(jPanel3,BorderLayout.SOUTH);
	}
	public ActionListener buyButtonListener() {
		ActionListener actionListener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		return actionListener; 
	}
	public ActionListener canceListener() {
		ActionListener actionListener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jFrame.setVisible(false);
				jFrame.dispose();
			}
		};
		return actionListener; 
	}
}
