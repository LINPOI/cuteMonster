package test;

//在另外一個 JFrame 中使用 LoginPanelPage 和 NextPanelPage
import javax.swing.*;
import java.awt.*;

public class AnotherFrame extends JFrame {
 private CardLayout cardLayout;
 private JPanel cardPanel;

 public AnotherFrame() {
     setTitle("另一個頁面");
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setSize(300, 200);
     setLocationRelativeTo(null);

     cardLayout = new CardLayout();
     cardPanel = new JPanel(cardLayout);

     // 創建 LoginPanelPage 和 NextPanelPage
     LoginPanelPage loginPanelPage = new LoginPanelPage(cardLayout, cardPanel);
     NextPanelPage nextPanelPage = new NextPanelPage(cardLayout, cardPanel);

     // 將 LoginPanelPage 和 NextPanelPage 添加到 cardPanel 中
     cardPanel.add(loginPanelPage, "login");
     cardPanel.add(nextPanelPage, "next");

     // 將 cardPanel 添加到 AnotherFrame 中
     add(cardPanel);

     // 預設顯示登入頁面
     cardLayout.show(cardPanel, "login");

     setVisible(true);
 }

 public static void main(String[] args) {
     SwingUtilities.invokeLater(new Runnable() {
         public void run() {
             new AnotherFrame();
         }
     });
 }
}
