package test;

import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Item 1");
        linkedList.add("Item 2");
        linkedList.add("Item 3");
        linkedList.add("Item 4");
        linkedList.add("Item 5");

        // 顯示原始列表
        System.out.println("初始列表:");
        linkedList.forEach(System.out::println);

        // 刪除並輸出第三個項目
        if (linkedList.size() >= 3) {
            ListIterator<String> iterator = linkedList.listIterator();
            int count = 0;
            while (iterator.hasNext()) {
                count++;
                String item = iterator.next();
                if (count == 3) {
                    System.out.println("刪除並輸出第三個項目: " + item);
                    iterator.remove();
                    break;
                }
            }
        }

        // 顯示更新後的列表
        System.out.println("\n更新後的列表:");
        linkedList.forEach(System.out::println);
    }
}