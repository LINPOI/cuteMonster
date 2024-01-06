package finalproject2;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageTranscoder;

import Props.Props;
import Props.PropsList;

public class Account {// 帳號 密碼 年份 怪獸id 怪獸
	private String username = "";
	private String password = "";
	private int year = 0;
	private int selectMonster = 1;
	private double money=0.0;
	private DatabaseOperations databaseOperations = new DatabaseOperations();
	private ArrayList<Props> propslist = new ArrayList<Props>();
	/*
	 * 準備做儲藏庫
	 */

	public Monster monster = new Monster(username);

	public String[] key() {
		return new String[] { "帳號", "密碼", "養育年份", "selectMonster" };
	}

	public void setInf(String[] inf) {
		this.username = inf[0];
		this.password = inf[1];
		this.year = Integer.valueOf(inf[2]);

	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {

		return username;
	}

	public void setPassword(String password) {
		this.password = password;

	}

	public String getPassword() {

		return password;
	}

	public void setYear(int year) {
		this.year = year;

	}

	public void addYear(int year) {
		this.year += year;
		monster.addAge(year);
	}

	public int getYear() {

		return year;
	}
	
	public void setselectMonster(int selectMonster) {
		this.selectMonster = selectMonster;
	}

	public int getselectMonster() {
		return selectMonster;
	}

	public void addMoney(double money) {
		this.money += money*1.0;

	}

	public double getMoney() {
		return money*1.0;
	}
	/*
	 * 儲存
	 */

	public void saveAccount(Account account) {
		databaseOperations.updateData(account);
		saveMonsterData(account);
	}

	/*
	 * 讀取
	 */
	public Account readAccount(Account account) {
		Account account2 = new Account();
		account2 = databaseOperations.queryData(account);
		account2.monster = readMonster(account2);
		account2.propslist=readProps(account2);
		System.out.println("怪獸" + account.monster.getName());
		return account2;
	}

	public void saveProps(Account account) {
		databaseOperations.insert_Props_Data(account);
	}

	public File imegeurl() {
		return new File("src/newpicture/" + getUsername() + monster.getName() + monster.getID() + "output.png");
	}

	public void saveMonsterData(Account account) {
		databaseOperations.updateMonsterData(account);
	}

	public Monster readMonster(Account account) {
		monster = databaseOperations.queryMonsterInfo(account);
		return monster;
	}

	public void addProps(Account account,int id) {
		PropsList propsList = new PropsList();
		propslist.add(propsList.get(id));
		databaseOperations.insert_Props_Data(account);
	}
	public ArrayList<Props> readProps(Account account) {
		 return databaseOperations.queryPropsData(account);
	}
	
	public int getProps_id() {
		return propslist.get(propslist.size() - 1).getID();
	}
	public int getProps_id(int i) {
		return propslist.get(i).getID();
	}
	public void checkProps_name() {
		for(Props props:propslist) {
			System.out.println("道具:"+props.getName());
		}
	}
	public String getProps_name(int i) {
		return propslist.get(i).getName();
	}
	
	public ArrayList<String> getProps_name() {
		ArrayList<String> arraylist=new ArrayList<String>();
		for(Props props:propslist) {
			arraylist.add(props.getName());
		}
		return arraylist;
	}
	
	public ArrayList<Props> getProps_ArrayList() {
		return propslist;
	}
	public void useProps(Account account, int selectPropsId,int value) {
		/*
		 * 使用的時候取得要使用的道具及刪除該道具
		 */
		PropsList propsList = new PropsList(account);
		for (int i = 0; i < propslist.size(); i++) {
			if (propslist.get(i).getID() == selectPropsId) {
				databaseOperations.delete_Props(account, value);//毒資料庫值山資料
				propslist.remove(i);//刪除道具
				propsList.get(selectPropsId).use();//使用道具
				saveAccount(account);//儲存新數值
				break;
			}
		}
	}
}
