package finalproject2;

import java.sql.*;
import java.util.*;

public class DatabaseOperations {
	List<Account> accountList = new ArrayList<>();
	String userName="";
	private Account account=new Account(); 
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
    public boolean insert_Account_Data(Account account) {
        try {
            String query = "INSERT INTO account (`帳號`, `密碼`,`養育年份`) VALUES (?, ?, ?)";
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
    public boolean insert_Monster_Data(Account account) {
        try {
            String query = "INSERT INTO `"+account.getUsername()+"的怪獸` (`玩家`, `怪獸名稱`, `怪獸年齡`, `攻擊力`, `生命力`, `智力`, `火系`, `冰系`, `毒系`, `幻影系` ,`翅膀`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, account.getUsername());
            pstmt.setString(2, account.monster.getName());
            for(int i=3;i<account.monster.getValueName().length+3;i++) {
            	pstmt.setInt(i, account.monster.getValue(i-3));
            }
            pstmt.setBoolean(11, account.monster.getWing());
            pstmt.executeUpdate();
            System.out.println("成功新增怪獸資料");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        	System.out.println("新增怪獸資料失敗");
            return false;
        }
    }
    /*
     * 新增怪獸資料表
     */
    public void createTable(String username) {
    	account.setUsername(username);
        try {
        	 // 創建 Statement 物件
            Statement stmt = conn.createStatement();
          
            // SQL 命令 - 建立寵物資料表
            String createTableSQL = "CREATE TABLE  IF NOT EXISTS `"+username+"的怪獸` (" +
            		"`玩家` CHAR(50) NOT NULL DEFAULT '"+username +"' COLLATE 'latin1_swedish_ci', " +
                    "`怪獸ID` INT AUTO_INCREMENT PRIMARY KEY, " + 
                    "`怪獸名稱` CHAR(50) NOT NULL COLLATE 'latin1_swedish_ci', " +
                    "`怪獸年齡` INT(11) NULL DEFAULT NULL,"
                    + "	`攻擊力` INT(11) NULL DEFAULT NULL,"
                    + "	`生命力` INT(11) NULL DEFAULT NULL,"
                    + "	`智力` INT(11) NULL DEFAULT NULL,"
                    + "	`火系` INT(11) NULL DEFAULT NULL,"
                    + "	`冰系` INT(11) NULL DEFAULT NULL,"
                    + "	`毒系` INT(11) NULL DEFAULT NULL,"
                    + "	`幻影系` INT(11) NULL DEFAULT NULL,"+
                    "`翅膀` TINYINT(1) NOT NULL DEFAULT 0, " + 
                    "FOREIGN KEY (`玩家`) REFERENCES `account`(`帳號`) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ") ENGINE=InnoDB COLLATE 'latin1_swedish_ci';";

            // 執行 SQL 命令以建立資料表
            stmt.executeUpdate(createTableSQL);
            System.out.println("成功新增資料表");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
     * 從怪獸資料表中查詢特定怪獸的資訊
     */
    public String queryMonsterInfo(Account account) {
    	 String name="";
        try {
            // 創建 Statement 物件
            Statement stmt = conn.createStatement();
          
            // 構建查詢怪獸資訊的 SQL 命令
            String queryMonsterSQL = "SELECT * FROM `" + account.getUsername() + "的怪獸` WHERE 怪獸ID = " + account.getmonster_id();
            
            // 執行 SQL 命令以查詢怪獸資訊
            ResultSet resultSet = stmt.executeQuery(queryMonsterSQL);
            
            // 如果查詢結果不為空，則列印怪獸資訊
            while(resultSet.next()) {
                int id = resultSet.getInt("怪獸ID");
                name = resultSet.getString("怪獸名稱");
                int age = resultSet.getInt("怪獸年齡");
                int attack = resultSet.getInt("攻擊力");
                int hp = resultSet.getInt("生命力");
                int intelligence = resultSet.getInt("智力");
                int fire = resultSet.getInt("火系");
                int ice = resultSet.getInt("冰系");
                int poison = resultSet.getInt("毒系");
                int phantom = resultSet.getInt("幻影系");
                Boolean wing = resultSet.getBoolean("翅膀");
                System.out.println("怪獸ID: " + id);
                System.out.println("怪獸名稱: " + name);
                account.monster.setName(name);
                account.monster.setAge(age);
                account.monster.setAttack(attack);
                account.monster.setHealth(hp);
                account.monster.setIntelligence(intelligence);
                account.monster.setFire(fire);
                account.monster.setIce(ice);
                account.monster.setPoison(poison);
                account.monster.setIllusion(phantom);
                account.monster.setWing(wing);
            }
           
            stmt.close();
            return name;
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
    /*
     * 查詢怪獸資料表中的所有數據
     */
    public boolean monsterData(String username) {
        try {
            // 創建 Statement 物件
            Statement stmt = conn.createStatement();
          
            // 構建查詢所有數據的 SQL 命令
            String selectAllDataSQL = "SELECT * FROM `" +username+"的怪獸`";
            
            // 執行 SQL 命令以查詢所有數據
            ResultSet resultSet = stmt.executeQuery(selectAllDataSQL);
            
            if (!resultSet.next()) {
                System.out.println("資料庫中沒有任何怪獸資料");
                stmt.close();
                return false;
            }
            // 處理查詢結果
             do{
            	 int id = resultSet.getInt("怪獸ID");
                 String name = resultSet.getString("怪獸名稱");
                 int age = resultSet.getInt("怪獸年齡");
                 int attack = resultSet.getInt("攻擊力");
                 int hp = resultSet.getInt("生命力");
                 int intelligence = resultSet.getInt("智力");
                 int fire = resultSet.getInt("火系");
                 int ice = resultSet.getInt("冰系");
                 int poison = resultSet.getInt("毒系");
                 int phantom = resultSet.getInt("幻影系");
                 Boolean wing = resultSet.getBoolean("翅膀");
                 System.out.println("怪獸ID: " + id);
                 System.out.println("怪獸名稱: " + name);
                 System.out.println("怪獸年齡: " + age);
                 System.out.println("攻擊力: " + attack);
                 System.out.println("生命力: " + hp);
                 System.out.println("智力: " + intelligence);
                 System.out.println("火系: " + fire);
                 System.out.println("冰系: " + ice);
                 System.out.println("毒系: " + poison);
                 System.out.println("幻影系: " + phantom);
                 System.out.println("翅膀: " + wing);
                 account.monster.setName(name);
                 account.monster.setAge(age);
                 account.monster.setAttack(attack);
                 account.monster.setHealth(hp);
                 account.monster.setIntelligence(intelligence);
                 account.monster.setFire(fire);
                 account.monster.setIce(ice);
                 account.monster.setPoison(poison);
                 account.monster.setIllusion(phantom);
                 account.monster.setWing(wing);
            }while (resultSet.next());
           
            // 關閉 Statement 物件
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
}