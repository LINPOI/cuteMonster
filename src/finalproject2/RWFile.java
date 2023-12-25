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
	            File file = new File("C:\\Users\\user\\eclipse-workspace\\finalproject2\\file.txt");
	            // 檢查檔案是否存在，如果不存在，則創建新檔案
	            if (!file.exists()) {
	                file.createNewFile();
	                System.out.println("檔案不存在，已創建新檔案");
	            }

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
	 public  void saveToFile_Account(LinkedList<String> linkedList,String username) {
		 try {
	            File file = new File("C:\\Users\\user\\eclipse-workspace\\finalproject2\\user"+username+".txt");
	            // 檢查檔案是否存在，如果不存在，則創建新檔案
	            if (!file.exists()) {
	                file.createNewFile();
	                System.out.println("檔案不存在，已創建新檔案");
	            }

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
	 public  void saveToFile_monster(LinkedList<String> linkedList,String monster) {
		 try {
	            File file = new File("C:\\Users\\user\\eclipse-workspace\\finalproject2\\monster"+monster+".txt");
	            // 檢查檔案是否存在，如果不存在，則創建新檔案
	            if (!file.exists()) {
	                file.createNewFile();
	                System.out.println("檔案不存在，已創建新檔案");
	            }

	            FileWriter writer = new FileWriter(file);
	            BufferedWriter bufferedWriter = new BufferedWriter(writer);

	            // 將鏈結串列中的每個元素寫入檔案，每個元素佔據一行
	            for (String item : linkedList) {
	                bufferedWriter.write(item);
	                bufferedWriter.newLine(); // 新增換行符號，使每個元素獨立一行
	            }

	            bufferedWriter.close();
	            writer.close();

	            //System.out.println("鏈結串列已成功寫入檔案。");

	        } catch (IOException e) {
	            //e.printStackTrace();
	        	System.out.println("鏈結串列寫入檔案失敗。");
	        }
	    }
	 public LinkedList<String> read_Account(String Account) {
	      
	        LinkedList<String> linkedList = new LinkedList<>();
	        try {
	            File file = new File("C:\\Users\\user\\eclipse-workspace\\finalproject2\\user"+Account+".txt");
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
	            //e.printStackTrace();
	        	//System.out.println("尚未新建");
	        }
	        return linkedList;
	    }
	 public LinkedList<String> read_Monster(String monster) {
	      
	        LinkedList<String> linkedList = new LinkedList<>();
	        try {
	            File file = new File("C:\\Users\\user\\eclipse-workspace\\finalproject2\\monster"+monster+".txt");
	            FileReader reader = new FileReader(file);
	            BufferedReader bufferedReader = new BufferedReader(reader);
	            //System.out.println("file:"+file);
	            // 讀取檔案中的每行文字並存入鏈結串列
	            String line;
	            while ((line = bufferedReader.readLine()) != null) {
	                linkedList.add(line);
	            }

	            bufferedReader.close();
	            reader.close();

	        } catch (IOException e) {
	            //e.printStackTrace();
	        	System.out.println("尚未新建");
	        }
	        return linkedList;
	    }
	 public LinkedList<String> read_File() {
	      
	        LinkedList<String> linkedList = new LinkedList<>();
	        try {
	            File file = new File("C:\\Users\\user\\eclipse-workspace\\finalproject2\\file.txt");
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
	            //e.printStackTrace();
	        	System.out.println("尚未新建");
	        }
	        return linkedList;
	    }
}
