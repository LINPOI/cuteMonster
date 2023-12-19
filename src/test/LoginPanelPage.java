package test;

//LoginPanelPage.java
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanelPage extends JPanel {
 private CardLayout cardLayout;
 private JPanel cardPanel;

 public LoginPanelPage(CardLayout cardLayout, JPanel cardPanel) {
     this.cardLayout = cardLayout;
     this.cardPanel = cardPanel;

     JPanel loginPanel = createLoginPanel();
     this.add(loginPanel);
 }

 private JPanel createLoginPanel() {
     JPanel panel = new JPanel();
     panel.setLayout(new GridLayout(3, 2));
     panel.setBackground(Color.black);

     JLabel usernameLabel = new JLabel("帳號:");
     JTextField usernameField = new JTextField();
     JLabel passwordLabel = new JLabel("密碼:");
     JPasswordField passwordField = new JPasswordField();

     JButton loginButton = new JButton("登入");
     loginButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             // 假設驗證成功，切換到下一個頁面
             cardLayout.show(cardPanel, "next");
         }
     });

     panel.add(usernameLabel);
     panel.add(usernameField);
     panel.add(passwordLabel);
     panel.add(passwordField);
     panel.add(new JLabel());
     panel.add(loginButton);

     return panel;
 }
}
