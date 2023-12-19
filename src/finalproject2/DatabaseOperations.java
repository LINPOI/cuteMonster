package finalproject2;

import java.sql.*;
import java.util.*;

public class DatabaseOperations {
	List<Account> accountList = new ArrayList<>();
    private Connection conn;

    public DatabaseOperations() {
        // 連接到資料庫
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cutemonster", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  /*
   * 新增
   */
    public boolean insertData(Account account) {
        try {
            String query = "INSERT INTO account (`帳號`, `密碼`) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, account.getUsername());
            pstmt.setString(2, account.getPassword());
            pstmt.executeUpdate();
            System.out.println("成功新增資料");
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            return false;
        }
    }
/*
 * 刪除
 */
    public void deleteData(Account account) {
        try {
            String query = "DELETE FROM account WHERE `帳號` = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, account.getUsername());
            pstmt.executeUpdate();
            System.out.println("成功刪除資料");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * 更新
     */

    public void updateData(Account account,String newPassword ) {
        try {
            String query = "UPDATE account SET `密碼` = ? WHERE `帳號` = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, account.getUsername());
            pstmt.executeUpdate();
            System.out.println("成功更新資料");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * 查詢
     */
    @SuppressWarnings("null")
	public List<Account> queryData() {
    	
    	
        try {
            String query = "SELECT * FROM account";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            // 處理查詢結果
            while (rs.next()) {
            	Account account=new Account();
            	String[] inf=new String[account.key().length];
            	for(int i=0;i<account.key().length;i++) {
            		inf[i]=rs.getString(account.key()[i]);
            	}
                // 其他欄位...
            	account.inf(inf);
                 
                // 印出資料或進行其他操作
               // System.out.println("帳號: " + account.getUsername() + ", 密碼: " + account.getPassword());
                accountList.add(account);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return  accountList;
        
    }
/*
 * 關閉連線
 */
    public void closeConnection() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
		DatabaseOperations databaseOperations=new DatabaseOperations();
		databaseOperations.queryData();
		databaseOperations.closeConnection();
	}
}