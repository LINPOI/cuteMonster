package finalproject2;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Achievement {
	public static final String FirstMonster = "My First Monster";//
	public static final String TenMonster = "The 10th monster has been raised";
	public static final String redMonster = "Find red slime";//
	public static final String greenMonser = "Find is green slime";//
	public static final String purpleMonser = "Find is purple slime";//
	public static final String FireMonster = "Find Fire Slime!!";//
	public static final String IceMonster = "Find Ice slime!!";//
	public static final String PoisonMonster = "Find Poison Slime!!";//
	public static final String PhantomMonster = "Find Phantom Slime!!";//
	public static final String PropsWing = "Get wings!";//
	public static final String GODProps = "Get artifact for the first time";//
	DatabaseOperations databaseOperations = new DatabaseOperations();

	public void addAchievement(Account account, String string) {
		// 如果資料庫中沒有這值就新增這值
		String retrievedString = databaseOperations.query_Achievement_Data(account, string);
		if (retrievedString == null || !retrievedString.equals(string)) {
			databaseOperations.insert_Achievement_Data(account, string);
		}
	}

	public void showAchievement(Account account, String string) {
		String retrievedString = databaseOperations.query_Achievement_Data(account, string);
		if (retrievedString == null || !retrievedString.equals(string)) {
			JOptionPane.showMessageDialog(null, string, "獲得獎狀", JOptionPane.INFORMATION_MESSAGE);
			addAchievement(account, string);
		}
	}

	public ArrayList<String> showAchievement_string(Account account) {

		return databaseOperations.query_Achievement_ALL_Data(account);
	}

}
