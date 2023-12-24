package test;

import java.util.Scanner;

public class SubClass {
    private DataChangeListener listener;

    public SubClass(DataChangeListener listener) {
        this.listener = listener;
    }

    public void buttonPressed() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press any key to update data...");
        scanner.nextLine(); // 等待用户输入以模拟按下按钮

     // 告诉主类更新数据
        if (listener instanceof MainClass) {
            MainClass mainClass = (MainClass) listener;
            listener.onDataChanged(mainClass.getData() + 1);
        }
    }
}

