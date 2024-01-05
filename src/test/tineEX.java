package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class tineEX {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("當前本地時間: " + now);

        // 可以使用 DateTimeFormatter 將時間按照需要的格式進行格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        System.out.println("格式化後的時間: " + formattedDateTime);
    }
}