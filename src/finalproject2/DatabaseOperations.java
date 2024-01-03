package finalproject2;

import java.sql.*;
import java.util.*;

public class DatabaseOperations {
	List<Account> accountList = new ArrayList<>();
	String userName = "";
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
			pstmt.setInt(3, account.getYear()); 
			pstmt.executeUpdate();
			System.out.println("成功新增資料");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/*
	 * 新增怪獸資料
	 */
	public boolean insert_Monster_Data(Account account) {
		try {
			String query = "INSERT INTO `" + account.getUsername()
					+ "的怪獸` (`玩家`, `怪獸名稱`, `怪獸年齡`, `攻擊力`, `生命力`, `智力`, `火系`, `冰系`, `毒系`, `幻影系` ,`翅膀`"
					+ ",`飢餓度`,`飢渴度`,`心情指數`,`健康度`) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, account.getUsername());
			pstmt.setString(2, account.monster.getName());
			for (int i = 0; i < account.monster.getValueName().length ; i++) {
				pstmt.setInt(i+3, account.monster.getValue(i));
			}
			pstmt.setBoolean(11, account.monster.getWing());
			for (int i = 0; i < account.monster.getStates_name().length; i++) {
				pstmt.setInt(i+12, account.monster.getStates(i));
			}
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
	 * 新增怪獸相冊資料
	 */
	public void insertInto_photoAlbum_Data(Account account, String monsterImage) {
	    try {
	        // 創建 PreparedStatement 物件
	        PreparedStatement pstmt = conn.prepareStatement(
	                "INSERT INTO `" + account.getUsername() + "的photo_album` (玩家, monster_name, monster_image) VALUES (?, ?, ?)");

	        // 設定參數值
	        pstmt.setString(1, account.getUsername());
	        pstmt.setString(2, account.monster.getName());
	        pstmt.setString(3, monsterImage);

	        // 執行 SQL 命令以新增資料
	        pstmt.executeUpdate();
	        System.out.println("成功新增資料到相冊資料表");
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	/*
	 * 新增使用者資料表
	 */
	public void create_Account_Table() {
		try {
			// 創建 Statement 物件
			Statement stmt = conn.createStatement();

			// SQL 命令 - 建立寵物資料表
			String createTableSQL = "CREATE TABLE  IF NOT EXISTS `Account` ("
					+ "`帳號` CHAR(50) NOT NULL DEFAULT '0' COLLATE 'latin1_swedish_ci', "
					+ "`密碼` CHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci', "
					+ "`養育年份` INT(11) NULL DEFAULT '0',"
					+ "PRIMARY KEY (`帳號`) USING BTREE"
					+ ") ENGINE=InnoDB;";

			// 執行 SQL 命令以建立資料表
			stmt.executeUpdate(createTableSQL);
			System.out.println("成功新增資料表");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 新增怪獸資料表
	 */
	public void create_Monster_Table(Account account) {
		try {
			// 創建 Statement 物件
			Statement stmt = conn.createStatement();

			// SQL 命令 - 建立寵物資料表
			String createTableSQL = "CREATE TABLE  IF NOT EXISTS `" + account.getUsername() + "的怪獸` ("
					+ "`玩家` CHAR(50) NOT NULL DEFAULT '" + account.getUsername() + "' COLLATE 'latin1_swedish_ci', "
					+ "`怪獸ID` INT AUTO_INCREMENT PRIMARY KEY, "
					+ "`怪獸名稱` CHAR(50) NOT NULL COLLATE 'latin1_swedish_ci', " + "`怪獸年齡` INT(11) NULL DEFAULT NULL,"
					+ "	`攻擊力` INT(11) NULL DEFAULT NULL," + "	`生命力` INT(11) NULL DEFAULT NULL,"
					+ "	`智力` INT(11) NULL DEFAULT NULL," + "	`火系` INT(11) NULL DEFAULT NULL,"
					+ "	`冰系` INT(11) NULL DEFAULT NULL," + "	`毒系` INT(11) NULL DEFAULT NULL,"
					+ "	`幻影系` INT(11) NULL DEFAULT NULL," + "`翅膀` TINYINT(1) NOT NULL DEFAULT 0, "
					+ "	`飢餓度` INT(11) NULL DEFAULT NULL," + "	`飢渴度` INT(11) NULL DEFAULT NULL,"
					+ "	`心情指數` INT(11) NULL DEFAULT NULL," + "	`健康度` INT(11) NULL DEFAULT NULL,"
					+ "FOREIGN KEY (`玩家`) REFERENCES `account`(`帳號`) ON DELETE CASCADE ON UPDATE CASCADE"
					+ ") ENGINE=InnoDB COLLATE 'latin1_swedish_ci';";

			// 執行 SQL 命令以建立資料表
			stmt.executeUpdate(createTableSQL);
			System.out.println("成功新增資料表");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 新增相冊資料表
	 * 玩家名稱
	 * 相冊ID
	 * 怪獸名稱
	 * 怪獸圖片
	 */
	public void create_photoAlbum_Table(Account account) {
		try {
			// 創建 Statement 物件
			Statement stmt = conn.createStatement();

			// SQL 命令 - 建立寵物資料表
			String createTableSQL = "CREATE TABLE IF NOT EXISTS `"+account.getUsername()+"的photo_album` ("
					+ "玩家 VARCHAR(100),"
		            + "album_id INT AUTO_INCREMENT PRIMARY KEY,"
		            + "monster_name VARCHAR(100),"
		            + "monster_image VARCHAR(255),"
					+ "FOREIGN KEY (`玩家`) REFERENCES `account`(`帳號`) ON DELETE CASCADE ON UPDATE CASCADE"
					+ ") ENGINE=InnoDB COLLATE 'latin1_swedish_ci';";

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

	public void updateData(Account account) {
		try {
			String query = "UPDATE account SET  `養育年份` = ? WHERE `帳號` = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, account.getYear());
			pstmt.setString(2, account.getUsername());
			pstmt.executeUpdate();
			System.out.println("成功更新資料");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 *更新怪物資料
	 */
	public boolean updateMonsterData(Account account) {
		 try {
		        String query = "UPDATE `lin的怪獸` SET " +
		        		"`怪獸名稱` = ? ,"+
		                "`怪獸年齡` = ?, " +
		                "`攻擊力` = ?, " +
		                "`生命力` = ?, " +
		                "`智力` = ?, " +
		                "`火系` = ?, " +
		                "`冰系` = ?, " +
		                "`毒系` = ?, " +
		                "`幻影系` = ?, " +
		                "`翅膀` = ?, " +
		                "`飢餓度` = ?, " +
		                "`飢渴度` = ?, " +
		                "`心情指數` = ?, " +
		                "`健康度` = ? " +
		                "WHERE `怪獸ID` = ? AND `玩家` = ? ";

		        PreparedStatement pstmt = conn.prepareStatement(query);
		        int i=1;
		        pstmt.setString(i++, account.monster.getName());
		        for(int j=0;j<account.monster.getValue().length;j++) {
		        	 pstmt.setInt(i++, account.monster.getValue(j));
		        }
		        pstmt.setBoolean(i++, account.monster.getWing());
		        for(int j=0;j<account.monster.getStates().length;j++) {
		        	 pstmt.setInt(i++, account.monster.getStates(j));
		        }
		       
		        pstmt.setInt(15, account.monster.getID());
		        pstmt.setString(16, account.getUsername());

		        int updatedRows = pstmt.executeUpdate();

		        if (updatedRows > 0) {
		            System.out.println("成功更新怪獸資料");
		            return true;
		        } else {
		            System.out.println("未找到匹配的怪獸資料");
		            return false;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println("更新怪獸資料失敗");
		        return false;
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
				Account account = new Account();
				String[] inf = new String[account.key().length];
				for (int i = 0; i < account.key().length; i++) {
					inf[i] = rs.getString(account.key()[i]);
				}
				// 其他欄位...
				account.setInf(inf);

				// 印出資料或進行其他操作
				// System.out.println("帳號: " + account.getUsername() + ", 密碼: " +
				// account.getPassword());
				accountList.add(account);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountList;

	}

	/*
	 * 查單一人物
	 */
	public Account queryData(Account account) {
	    Account account2 = new Account();
	    //System.out.println("進來的user:"+account.getUsername()+"的資料");
	    try {
	        String query = "SELECT * FROM account WHERE `帳號`=?";
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, account.getUsername());
	        ResultSet rs = pstmt.executeQuery();

	        // 處理查詢結果
	        while (rs.next()) {
	            String[] inf = new String[account2.key().length];
	            for (int i = 0; i < account2.key().length; i++) {
	                inf[i] = rs.getString(account2.key()[i]);
	            }
	            // 假設有設置 Account 的方法來設置資訊
	            account2.setInf(inf);

	            //System.out.println("已讀取user:"+account.getUsername()+"的資料");
	           
	        }
	        rs.close();
	        pstmt.close();
//	        System.out.println("----------------------------------------\n"
//                    + "帳號: " + account2.getUsername() + ", 密碼: " + account2.getPassword() + ", 養育年份:"
//                    + account2.getYear());
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // 執行其他錯誤處理或者拋出自定義的例外
	    }
	    return account2;
	}

	/*
	 * 從怪獸資料表中查詢特定怪獸的資訊
	 */
	public Monster queryMonsterInfo(Account accountinput) {
		Monster monster=new Monster(accountinput.getUsername());
		try {
			// 創建 Statement 物件
			Statement stmt = conn.createStatement();

			// 構建查詢怪獸資訊的 SQL 命令
			String queryMonsterSQL = "SELECT * FROM `" + accountinput.getUsername() + "的怪獸` WHERE 怪獸ID = "
					+ accountinput.monster.getID();

			// 執行 SQL 命令以查詢怪獸資訊
			ResultSet resultSet = stmt.executeQuery(queryMonsterSQL);

			// 如果查詢結果不為空，則列印怪獸資訊
			while (resultSet.next()) {
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
				monster.setHungerValue(resultSet.getInt("飢餓度")); 
				monster.setThirstValue(resultSet.getInt("飢渴度")); 
				monster.setMoodValue(resultSet.getInt("心情指數")); 
				monster.setHealthValue(resultSet.getInt("健康度")); 
				
				System.out.println("資料庫讀單一怪獸:怪獸名稱: " + name);
				monster.setID(id);
				monster.setName(name);
				monster.addAge(age);
				monster.addAttack(attack);
				monster.addHP(hp);
				monster.addIntelligence(intelligence);
				monster.addFire(fire);
				monster.addIce(ice);
				monster.addPoison(poison);
				monster.addIllusion(phantom);
				monster.setWing(wing);
			}

			stmt.close();
			return monster;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 查詢怪獸資料表中的所有數據
	 */
	public Monster monsterData(Account accountinput) {
		Monster monster=new Monster(accountinput.getUsername());
		try {
			// 創建 Statement 物件
			Statement stmt = conn.createStatement();

			// 構建查詢所有數據的 SQL 命令
			String selectAllDataSQL = "SELECT * FROM `" + accountinput.getUsername() + "的怪獸`";

			// 執行 SQL 命令以查詢所有數據
			ResultSet resultSet = stmt.executeQuery(selectAllDataSQL);

			if (!resultSet.next()) {
				System.out.println("資料庫中沒有任何怪獸資料");
				stmt.close();
				return null;
			}
			// 處理查詢結果
			do {
				monster.setID(resultSet.getInt("怪獸ID"));
				monster.setName(resultSet.getString("怪獸名稱"));
				monster.addAge(resultSet.getInt("怪獸年齡"));
				monster.addAttack(resultSet.getInt("攻擊力"));
				monster.addHP( resultSet.getInt("生命力"));
				monster.addIntelligence(resultSet.getInt("智力"));
				monster.addFire(resultSet.getInt("火系"));
				monster.addIce(resultSet.getInt("冰系"));
				monster.addPoison(resultSet.getInt("毒系"));
				monster.addIllusion(resultSet.getInt("幻影系"));
				monster.setWing(resultSet.getBoolean("翅膀"));
				monster.addHungerValue(resultSet.getInt("飢餓度")); 
				monster.addThirstValue(resultSet.getInt("飢渴度")); 
				monster.addMoodValue(resultSet.getInt("心情指數")); 
				monster.addHealthValue(resultSet.getInt("健康度")); 
			} while (resultSet.next());

			// 關閉 Statement 物件
			stmt.close();
			return monster;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * 怪獸相冊
	 */
	public void queryFrom_photoAlbum(Account account) {
	    try {
	    	// 創建 Statement 物件
	    				Statement stmt = conn.createStatement();

	        // 創建 PreparedStatement 物件
	    				String queryMonsterSQL ="SELECT * FROM `" + account.getUsername() + "的photo_album`";

	        // 執行 SQL 查詢
	        ResultSet rs = stmt.executeQuery(queryMonsterSQL);

	        // 處理查詢結果
	        while (rs.next()) {
	            // 取得每列資料
	            String playerName = rs.getString("玩家");
	            int albumId = rs.getInt("album_id");
	            String monsterName = rs.getString("monster_name");
	            String monsterImage = rs.getString("monster_image");

	            // 在這裡處理取得的資料，例如印出或進行其他操作
	            System.out.println("玩家: " + playerName + ", 相冊ID: " + albumId + ", 怪獸名稱: " + monsterName + ", 怪獸圖片: " + monsterImage);
	        }

	        // 關閉資源
	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	/*
	 * 關閉連線
	 */
	public void closeConnection() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}