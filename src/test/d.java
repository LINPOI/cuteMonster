package test;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class d {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JList Update Example");
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Item 1");
        listModel.addElement("Item 2");
        listModel.addElement("Item 3");

        JList<String> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        JButton updateButton = new JButton("Update Item");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexToModify = 0; // 假設你想要修改索引 1 的值
                String newValue = "New Value"; // 新的值
                listModel.setElementAt(newValue, indexToModify); // 通過索引更新清單中的值
            }
        });

        frame.add(scrollPane);
        frame.add(updateButton, BorderLayout.SOUTH);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}