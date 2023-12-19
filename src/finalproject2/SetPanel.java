package finalproject2;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SetPanel  extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 控制面板資訊切換
	 */
	/*
	 * cardLayout.show(cardPanel, "...");
	 */
	 protected CardLayout cardLayout;//切換面板
	 protected JPanel cardPanel;//面板
	List<Account> accountList = new ArrayList<>();	//全部帳號
	Account account=new Account();	//帳號
	Account currentAccount=new Account();//新帳號
	protected int[][] grid;//元件布局
	// 元件
	JPanel jPanel = new JPanel();
	protected int height=800;
	protected int weigth=1000;
	/*
	 * 
	 */
	
	
	protected JTextField textField = null;//文字框
	protected JPasswordField passwordField=null;//密碼框
	protected JButton button = null;//按鈕框
	protected JLabel jLabel = null;//標題框

	// 監聽
	protected ActionListener[] actionListeners = null;
	

	protected String[] textStrings;// 顯示文字陣列
	protected ArrayList<JComponent> gUIComponents = new ArrayList<>();
	
	protected  String register_username="";
	protected  String register_password="";

	// 獲得資料庫
	protected DatabaseOperations databaseOperations = new DatabaseOperations();
	/*
	 * 
	 */
	int[][] getGrid() {
		return grid;
	}
	public void addComponent(int i) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = grid[i][0];
		constraints.gridy = grid[i][1];
		constraints.gridwidth = grid[i][2];
		constraints.gridheight = grid[i][3];
		constraints.weightx = grid[i][4];
		constraints.weighty = grid[i][5];
		constraints.fill = grid[i][6];
		constraints.anchor = grid[i][7];
		jPanel.add(gUIComponents.get(i), constraints);
	}
	/*
	 *填滿fill
	 */
	final int NONE=GridBagConstraints.NONE;//不填滿
	final int HORIZONTAL=GridBagConstraints.HORIZONTAL;//填滿水平
	final int VERTICAL=GridBagConstraints.VERTICAL;//填滿垂直
	final int BOTH=GridBagConstraints.BOTH;//填滿
	
	/*
	 * 對齊方式anchor
	 */
	final int NORTH=GridBagConstraints.NORTH;//靠上
	final int SOUTH=GridBagConstraints.SOUTH;//靠下
	final int WEST=GridBagConstraints.WEST;//靠左
	final int EAST=GridBagConstraints.EAST;//靠右
	
	final int NORTHEAST=GridBagConstraints.NORTHEAST;//右上角
	final int NORTHWEST=GridBagConstraints.NORTHWEST;//左上角
	final int SOUTHEAST=GridBagConstraints.SOUTHEAST;//右下角
	final int SOUTHWEST=GridBagConstraints.SOUTHWEST;//左下角
	final int CENTER=GridBagConstraints.CENTER;//置中對齊
}
