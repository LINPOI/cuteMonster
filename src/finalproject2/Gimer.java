package finalproject2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Gimer extends JPanel {
	/**
	 * 名稱: 養育年分: 養育寵物相冊: 獎狀:
	 */
	private String[] message;
	private DatabaseOperations databaseOperations = new DatabaseOperations();
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Account account;
	private boolean notOpen = true;
	private JPanel jPanel2 = new JPanel();

	public Gimer(JFrame frame, Account account) {
		this.frame = frame;
		this.account = account;

	}

	public void open() {
		notOpen = false;
		account = databaseOperations.queryData(account);
		String yearString = String.valueOf(account.getYear());
		message = new String[] { "名稱", account.getUsername(), "養育年份", yearString, "獎狀", "養育寵物相冊" };// ??username呢
		JFrame jFrame = new JFrame("玩家資料");
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jFrame.setSize(new Dimension(800, 600)); // 框架尺寸
		jFrame.setLayout(new BorderLayout());
		this.setLayout(new GridLayout(2, 2));
		for (int i = 0; i < 4; i += 2) {
			jlabels(i);
			jlabelInfs(i + 1);
		}
		jlabels2(4);
		achievement();
		// photoAlbum();

		JScrollPane scrollPane = new JScrollPane(this);
		// 設置 JScrollPane 的滾動條自動出現方式
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JScrollPane scrollPane2 = new JScrollPane(jPanel2);
		// 設置 JScrollPane 的滾動條自動出現方式
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jFrame.add(scrollPane, BorderLayout.NORTH);
		jFrame.add(scrollPane2, BorderLayout.CENTER);
		jFrame.setLocationRelativeTo(frame);// 置中顯示

		jFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				jFrame.setVisible(false);
				jFrame.dispose();
				notOpen = true;
			}
		});
		jFrame.setVisible(true); // 顯示
	}

	public void jlabels(int i) {
		JLabel jLabel = new JLabel(message[i] + "：");
		this.add(jLabel);
	}

	public void jlabels2(int i) {
		JLabel jLabel = new JLabel(message[i] + "：");
		jPanel2.add(jLabel);
	}

	public void jlabelInfs(int i) {
		JLabel jLabel = new JLabel(message[i]);
		this.add(jLabel);
	}

	public boolean notOpen() {
		return notOpen;
	}

//	public void photoAlbum() {
//		ArrayList<Monster> monsters = account.getMonstersData(account);
//		JPanel jPanel = new JPanel();
//		jPanel.setLayout(new GridLayout(0, 3));
//		if (monsters != null) {
//			for (Monster monster : monsters) {
//				ImageIcon icon = new ImageIcon(account.imegeurl_String(monster));
//				JLabel picture = new JLabel();
//				picture.setIcon(icon);
//				JLabel monsterNameJLabel = new JLabel(monster.getName());
//				JLabel monsterYearJLabel = new JLabel("" + monster.getAge());
//				// jPanel.add(picture);
//				jPanel.add(monsterNameJLabel);
//				// jPanel.add(monsterYearJLabel);
//				jPanel2.add(jPanel);
//			}
//		} else {
//			System.out.println("空的!");
//		}
//	}
	public void achievement() {
		ArrayList<String> arrayList=new Achievement().showAchievement_string(account);
				 
		if (!arrayList.isEmpty()) {
			JPanel jPanel3=new JPanel();
			jPanel3.setLayout(new GridLayout(arrayList.size(),1 ));
			for (String str : arrayList) {
				JLabel jLabel=new JLabel(str);
				jPanel3.add(jLabel);
			}
			jPanel2.add(jPanel3);
		} else {
			System.out.println("空的!");
		}
	}
}
