package test;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JListUpdateExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JList Update Example");
        DefaultListModel<String> listModel = new DefaultListModel<>();

        JList<String> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.addElement("New Item"); // 在模型中添加新的項目
            }
        });

        JButton removeButton = new JButton("Remove Item");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!list.isSelectionEmpty()) {
                    int selectedIndex = list.getSelectedIndex();
                    listModel.remove(selectedIndex); // 從模型中移除選定的項目
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(addButton);
        panel.add(removeButton);

        frame.add(scrollPane);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}