package test;

//NextPanelPage.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextPanelPage extends JPanel {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private CardLayout cardLayout;
 private JPanel cardPanel;

 public NextPanelPage(CardLayout cardLayout, JPanel cardPanel) {
     this.cardLayout = cardLayout;
     this.cardPanel = cardPanel;

     JPanel nextPanel = createNextPanel();
     this.add(nextPanel);
 }

 private JPanel createNextPanel() {
     JPanel panel = new JPanel();
     panel.setLayout(new FlowLayout());

     JLabel label = new JLabel("已登入成功！");
     JButton backButton = new JButton("返回登入");
     backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             // 返回到登入頁面
             cardLayout.show(cardPanel, "login");
         }
     });

     panel.add(label);
     panel.add(backButton);

     return panel;
 }
}
