package finalproject2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class RWFile {
	 public  void saveToFile(LinkedList<String> linkedList) {
		 try {
	            File file = new File("C:\\Users\\user\\eclipse-workspace\\finalproject2\\FILE.txt");
	            FileWriter writer = new FileWriter(file);
	            BufferedWriter bufferedWriter = new BufferedWriter(writer);

	            // 將鏈結串列中的每個元素寫入檔案，每個元素佔據一行
	            for (String item : linkedList) {
	                bufferedWriter.write(item);
	                bufferedWriter.newLine(); // 新增換行符號，使每個元素獨立一行
	            }

	            bufferedWriter.close();
	            writer.close();

	            System.out.println("鏈結串列已成功寫入檔案。");

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 public LinkedList<String> readFromFile() {
	      
	        LinkedList<String> linkedList = new LinkedList<>();
	        try {
	            File file = new File("C:\\Users\\user\\eclipse-workspace\\finalproject2\\FILE.txt");
	            FileReader reader = new FileReader(file);
	            BufferedReader bufferedReader = new BufferedReader(reader);

	            // 讀取檔案中的每行文字並存入鏈結串列
	            String line;
	            while ((line = bufferedReader.readLine()) != null) {
	                linkedList.add(line);
	            }

	            bufferedReader.close();
	            reader.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return linkedList;
	    }
}
