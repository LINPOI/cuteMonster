package finalproject2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class RWFile {
	final String urlString="D:\\cuteMonster\\files\\";
	public void saveToFile(String filename,String string) {
		
		try {
			File file = new File(urlString+filename+".txt");
			// 檢查檔案是否存在，如果不存在，則創建新檔案
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("檔案不存在，已創建新檔案");
			}

			FileWriter writer = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);

			bufferedWriter.write(string);
			bufferedWriter.close();
			writer.close();

			//System.out.println("字串已成功寫入檔案。");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveToFile(String string) {
		try {
			File file = new File( urlString+"file.txt");
			// 檢查檔案是否存在，如果不存在，則創建新檔案
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("檔案不存在，已創建新檔案");
			}

			FileWriter writer = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);

			bufferedWriter.write(string);
			bufferedWriter.close();
			writer.close();

			//System.out.println("字串已成功寫入檔案。");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveToFile_Account(LinkedList<String> linkedList, String username) {
		try {
			File file = new File(urlString+"user" + username + ".txt");
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
			e.printStackTrace();
		}
	}

	public void saveToFile_monster(LinkedList<String> linkedList, String monster) {
		try {
			File file = new File(urlString+"monster" + monster + ".txt");
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

			// System.out.println("鏈結串列已成功寫入檔案。");

		} catch (IOException e) {
			// e.printStackTrace();
			System.out.println("鏈結串列寫入檔案失敗。");
		}
	}

	public LinkedList<String> read_Account(String Account) {

		LinkedList<String> linkedList = new LinkedList<>();
		try {
			File file = new File(urlString+"user" + Account + ".txt");
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
			// e.printStackTrace();
			// System.out.println("尚未新建");
		}
		return linkedList;
	}

	public LinkedList<String> read_Monster(String monster) {

		LinkedList<String> linkedList = new LinkedList<>();
		try {
			File file = new File(urlString+"monster" + monster + ".txt");
			FileReader reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);
			// System.out.println("file:"+file);
			// 讀取檔案中的每行文字並存入鏈結串列
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				linkedList.add(line);
			}

			bufferedReader.close();
			reader.close();

		} catch (IOException e) {
			// e.printStackTrace();
			//System.out.println("尚未新建");
		}
		return linkedList;
	}

	public String readFromFile() {
	    StringBuilder content = new StringBuilder();
	    try {
	        File file = new File(urlString+"file.txt");
	        if (!file.exists()) {
	            System.out.println("檔案不存在readFromFile()");
	            return "";
	        }

	        FileReader fileReader = new FileReader(file);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);

	        String line;
	        while ((line = bufferedReader.readLine()) != null) {
	            content.append(line); // 逐行讀取並串接內容
	        }

	        bufferedReader.close();
	        fileReader.close();
	        return content.toString();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "";  
	    }
	   
	}
	public String readFromFile(String filename) {
	    StringBuilder content = new StringBuilder();
	    try {
	        File file = new File(urlString + filename + ".txt");
	        if (!file.exists()) {
	            System.out.println("檔案不存在readFromFile(String filename)");
	            return "";
	        }

	        FileReader fileReader = new FileReader(file);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);

	        String contents = bufferedReader.readLine(); // 讀取單行字串

	        bufferedReader.close();
	        fileReader.close();

	        return content != null ? contents : ""; // 如果內容為空則返回空字串
	      
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "";
	    }
	   
	}
}
