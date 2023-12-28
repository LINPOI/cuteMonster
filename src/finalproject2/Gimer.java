package finalproject2;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Gimer {
	/**
	 * 名稱: 養育年分: 養育寵物相冊: 獎狀:
	 */
	private String[] message;
	private RWFile rwFile=new RWFile();
	private DatabaseOperations databaseOperations=new DatabaseOperations();
	private static final long serialVersionUID = 1L;
	JPanel jPanel;

	public Gimer(JFrame frame,Account account) {
		String fileContent = rwFile.readFromFile();
        account.setUsername(fileContent);
        account=databaseOperations.queryData(account);
       String yearString=String.valueOf(account.getYear()) ;
		message = new String[] { "名稱",account.getUsername(), "養育年份",yearString, "養育寵物相冊", "獎狀"  };//??username呢
		JDialog jDialog = new JDialog(frame, "玩家資料", true);
		jDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jPanel = new JPanel();
		jDialog.setSize(new Dimension(400, 200)); // 框架尺寸
		jPanel.setLayout(new GridLayout(4, 4));
		for(int i=0;i<6;i+=2) {
			jlabels(i);
			jlabelInfs(i+1);
		}
		JScrollPane scrollPane = new JScrollPane(jPanel);
		// 設置 JScrollPane 的滾動條自動出現方式
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		jDialog.add(scrollPane);
		jDialog.setLocationRelativeTo(frame);// 置中顯示
		jDialog.setVisible(true); // 顯示
	}

	public void jlabels(int i) {
		JLabel jLabel = new JLabel(message[i]+"：");
		jPanel.add(jLabel);
	}
	public void jlabelInfs(int i) {
		JLabel jLabel = new JLabel(message[i]);
		jPanel.add(jLabel);
	}
}
