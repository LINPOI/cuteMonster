package finalproject2;

import java.util.LinkedList;

public class Account {
	private String username="";
    private String password=null;
    private RWFile rwFile=new RWFile();
    public Monster monster=new Monster();
    
    public String[] key() {
    	return new String[] {
    			"帳號","密碼"
    	};
    }
    public void inf(String[] inf) {
        this.username = inf[0];
        this.password = inf[1];
    }
  
    public void setUsername(String username) {
    	this.username = username;
    	monster.setName(username);
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
    public void saveUser() {
    	LinkedList<String> linkedList=new LinkedList<String>();
    	linkedList.add(username);
    	linkedList.add(password);
    	linkedList.add(monster.getName());
    	for(int i=0;i<monster.getValue().length;i++) {
    		linkedList.add(String.valueOf(monster.getValue(i)));
    	}
    	
    	
    	rwFile.saveToFile(linkedList);
    }
    public LinkedList<String> readUser(){
    	return  rwFile.readFromFile();
    }
}
