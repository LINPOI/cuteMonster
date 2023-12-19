package finalproject2;

public class Account {
	private String username="";
    private String password=null;
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
}
