package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSwitchExample extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public PanelSwitchExample() {
        setTitle("Panel 切換");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel loginPanel = createLoginPanel();
        JPanel nextPanel = createNextPanel();

        cardPanel.add(loginPanel, "login");
        cardPanel.add(nextPanel, "next");

        add(cardPanel);
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("帳號:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("密碼:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("登入");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 假設驗證成功
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

    private JPanel createNextPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("已登入成功！");
        JButton backButton = new JButton("返回登入");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "login");
            }
        });

        panel.add(label);
        panel.add(backButton);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PanelSwitchExample();
            }
        });
    }
}
