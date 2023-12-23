package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class c extends JPanel {
    private BufferedImage backgroundImage;
    
    public c() {
    	setSize(new Dimension(1000,800));
    	setLayout(new BorderLayout());
    	ImageIcon button_ImageIcon1 = new ImageIcon("src/PICTURE/gamerbutton.png"); // 更換為你的按鈕圖片路徑
    	ImageIcon button_ImageIcon2 = new ImageIcon("src/PICTURE/interactive.png"); // 更換為你的按鈕圖片路徑
    	Image button_Image1=button_ImageIcon1.getImage();
    	Image button_Image2=button_ImageIcon2.getImage();
    	
        // 加載背景圖片
        try {
            backgroundImage = ImageIO.read(new File("src/PICTURE/houseBackground.png")); // 更換為你的圖片路徑
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // 創建按鈕並設置圖標
        JButton button1 = new JButton();
        button1.setPreferredSize(new Dimension(200, 100));
        Image newbutton_Image1=button_Image1.getScaledInstance(200, 100, Image.SCALE_DEFAULT); // 更換為你希望的寬度和高度
    	
        button1.setIcon(new ImageIcon(newbutton_Image1)); // 更換為你的按鈕圖片路徑
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 添加按鈕點擊後的操作
            }
        });
        
        JButton button2 = new JButton();
        button2.setPreferredSize(new Dimension(200, 100));
        Image newbutton_Image2=button_Image2.getScaledInstance(200,  100, Image.SCALE_DEFAULT); // 更換為你希望的寬度和高度
        button2.setIcon(new ImageIcon(newbutton_Image2)); // 更換為你的按鈕圖片路徑
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 添加按鈕點擊後的操作
            }
        });
        JPanel jPanel2JPanel=new JPanel();
        jPanel2JPanel.setLayout(new BorderLayout());
        jPanel2JPanel.add(button1,BorderLayout.EAST);
        jPanel2JPanel.add(button2,BorderLayout.WEST);
        // 將按鈕添加到面板
        add(jPanel2JPanel,BorderLayout.SOUTH);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 繪製背景圖片
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("ImagePanel Example");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        c panel = new c();
        frame.add(panel);
        
        frame.setVisible(true);
    }
}