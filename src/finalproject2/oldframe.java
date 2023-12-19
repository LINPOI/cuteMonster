package finalproject2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class oldframe {
	private JPanel cardPanel;
    private CardLayout cardLayout;
    private int[][] grid = null;
    private Timer timer;
    //private LogIn logIn = new LogIn();
    private Account account = new Account();
    private GridBagConstraints constraints = new GridBagConstraints();
    private JFrame jFrame = new JFrame("可愛的怪物");
    private ImageIcon slimebutton1x1 = new ImageIcon("src/PICTURE/slimebutton1x1.png");

    public oldframe() {
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 5;
        constraints.gridheight = 5;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        
        
        /*
         * 設置jFrame
         */
        jFrame.setSize(new Dimension(1200, 1000));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image iconImageIcon = slimebutton1x1.getImage();
        jFrame.setIconImage(iconImageIcon);
        jFrame.setLayout(new GridBagLayout());
        
        /*
         * 設置切換panel
         */
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        //cardPanel.add(logIn.open(),"login");
        jFrame.add(cardPanel, constraints);
        
        
        /*
         * 開啟顯示
         */
        jFrame.setVisible(true);//顯示
        jFrame.setLocationRelativeTo(null);//置中
        
    }

    public void open() {
        
    	//timer.start();
    	
       

        timer = new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // account = logIn.getAccount();
                if(account.getPassword()!=null) {
                	 System.out.println(account.getUsername() + "," + account.getPassword());
                     timer.stop();
                     System.out.println("計時器關閉");
                }
            }
        });

       
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            oldframe frame = new oldframe();
            frame.open();
        });
    }
}
