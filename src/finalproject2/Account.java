package finalproject2;

import java.util.LinkedList;

public class Account {
	private String username="";
    private String password="";
    private int year=0;
    private int monster_id=1;
    private RWFile rwFile=new RWFile();
    public Monster monster=new Monster(username);
    public String[] key() {
    	return new String[] {
    			"帳號","密碼","養育年份"
    	};
    }
    public void inf(String[] inf) {
        this.username = inf[0];
        this.password = inf[1];
        this.year=Integer.valueOf(inf[2]) ;
    }
  
    public void setUsername(String username) {
    	this.username = username;
    }
    
    public String getUsername() {
     	readAccount();
        return username;
    }

    public void setPassword( String password) {
    	this.password = password;
    	saveUser();
    }
    
    public String getPassword() {
    	readAccount();
        return password;
    }
    public void setYear( int year) {
    	this.year = year;
    	saveUser();
    }
    
    public int getYear() {
    	readAccount();
        return year;
    }
    public void setmonster_id(int monster_id) {
    	this.monster_id = monster_id;
    	saveUser();
    }
    
    public int getmonster_id() {
     	readAccount();
        return monster_id;
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
    public void saveUser() {
    	LinkedList<String> linkedList_user=new LinkedList<String>();
    	linkedList_user.add(username);
    	linkedList_user.add(password);
    	linkedList_user.add(String.valueOf(year));
    	linkedList_user.add(String.valueOf(monster_id));
    	//
    	rwFile.saveToFile_Account(linkedList_user, username);
    }
    public void saveUser(Account account) {
    	LinkedList<String> linkedList_user=new LinkedList<String>();
    	linkedList_user.add(username);
    	linkedList_user.add(password);
    	linkedList_user.add(String.valueOf(year));
    	linkedList_user.add(String.valueOf(monster_id));
    	//
    	rwFile.saveToFile_Account(linkedList_user, username);
    	
    }
    public LinkedList<String> readMonster(){
    	return  rwFile.read_Monster(monster.getName());
    }
    public void readAccount(){
    	LinkedList<String> user=rwFile.read_Account(username);
    	if(user.size()!=0) {
    		username=user.get(0);
        	password=user.get(1);
        	year=Integer.valueOf(user.get(2));
        	monster_id=Integer.valueOf(user.get(3));
    	}
    }
}
