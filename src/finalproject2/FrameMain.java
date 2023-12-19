package finalproject2;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import test.AnotherFrame;

public class FrameMain {

	/*
	 * 控制面板資訊切換
	 */
	private JPanel cardPanel;
	private CardLayout cardLayout;

	private int[][] grid = null;// 網格資料
	private Timer timer;// 時間監聽
	// private LogIn logIn = new LogIn();//登入
	private Account account = new Account();// 帳號資料
	private GridBagConstraints constraints = new GridBagConstraints();// 網格格式
	private JFrame jFrame;// 框架
	private ImageIcon slimebutton1x1 = new ImageIcon("src/PICTURE/slimebutton1x1.png");// 圖片設置

	public FrameMain() {
		// TODO Auto-generated constructor stub
		/*
		 * 設置框架
		 */
		jFrame = new JFrame("可愛的怪物"); // 建立框架
		jFrame.setSize(new Dimension(1000, 800)); // 框架尺寸
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//按下關閉時的動作
		Image slimebutton1x1_Image = slimebutton1x1.getImage();	//取得圖片
		jFrame.setIconImage(slimebutton1x1_Image);			//應用圖示
		jFrame.setLayout(new GridBagLayout());		//框架格式設定
		jFrame.setLocationRelativeTo(null);//置中顯示
		
		/*
		 *初始化面板切換資料 
		 */
		 cardLayout = new CardLayout();
	     cardPanel = new JPanel(cardLayout);
	     
	     /*
	      * 取得jpanel
	      */
	     LogIn login=new LogIn(cardLayout, cardPanel);
	     FirstPage firstPage=new FirstPage(cardLayout, cardPanel);
	     
	     /*
	      * 放入切換面板資料
	      */
	     cardPanel.add(login, "login");
	     cardPanel.add(firstPage, "first");
	     
	     /*
	      * 
	      */
	     jFrame.add(cardPanel);//加入框架中
	     cardLayout.show(cardPanel, "login");//首頁為登入
	     jFrame.setVisible(true);//顯示
	     
	}
	public static void main(String[] args) {
	     SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	             new FrameMain();
	         }
	     });
	 }
}
