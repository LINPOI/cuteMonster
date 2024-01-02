package finalproject2;

import java.io.File;
import java.util.LinkedList;

public class Account {//帳號 密碼 年份 怪獸id 怪獸
	private String username="";
    private String password="";
    private int year=0;
    private DatabaseOperations databaseOperations=new DatabaseOperations();
    public Monster monster=new Monster(username);
    public String[] key() {
    	return new String[] {
    			"帳號","密碼","養育年份"
    	};
    }
    public void setInf(String[] inf) {
        this.username = inf[0];
        this.password = inf[1];
        this.year=Integer.valueOf(inf[2]) ;

    }
  
    public void setUsername(String username) {
    	this.username = username;
    }
    
    public String getUsername() {
     	
        return username;
    }

    public void setPassword( String password) {
    	this.password = password;
  
    }
    
    public String getPassword() {
  
        return password;
    }
    public void setYear( int year) {
    	this.year = year;
 
    }
    
    public int getYear() {
 
        return year;
    }
    
    public void setMonster( Monster monster) {
    	this.monster = monster;
    }
    
    public Monster getMonster() {
        return monster;
    }
    
    
    /*
     * 儲存
     */

    public void saveUser(Account account) {
    	databaseOperations.updateData(account);   	
    }
    public Account readAccount(Account account){
    	Account account2Account=new Account();
    	account2Account= databaseOperations.queryData(account);
    	account2Account.monster=readMonster(account2Account);
    	System.out.println("怪獸"+account.monster.getName());
    	return account2Account;
    }
    public File imegeurl() {
		return new File("src/newpicture/"+ getUsername()+monster.getName()+"output.png");
	}
    public void saveMonsterData(Account account) {
    	databaseOperations.updateMonsterData(account);
	}

	public Monster readMonster(Account account) {
		monster=databaseOperations.queryMonsterInfo(account);
		return monster;
	}
}
