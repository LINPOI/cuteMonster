package test;

import java.sql.*;

public class Test {
    public Test() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
        	try {
        		Class.forName("org.mariadb.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            // 連接到資料庫
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cutemonster", "root", "");

            // 創建一個 Statement 物件
            stmt = conn.createStatement();

            // 執行 SQL 查詢
            rs = stmt.executeQuery("SELECT * FROM account");

            // 處理查詢結果
            while (rs.next()) {
                // 根據資料庫表格的欄位名稱讀取資料
                String account = rs.getString("帳號");
                String password = rs.getString("密碼");
                // 其他欄位...

                // 印出資料
                System.out.println("帳號: " + account + ", 密碼: " + password);
                // 其他欄位...
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 關閉連線和資源
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
		Test test=new Test();
	}
}

