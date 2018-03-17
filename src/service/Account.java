package service;


enum ACCOUNT_TYPE {
	ANOMT(1,"匿名用户"),
	CLERK(2,"售票员"),
	MANG(3,"经理"),
	ADMIN(4,"管理员");
	
	private String name;
	private int index;
	private ACCOUNT_TYPE(int index , String name) {
		this.index = index;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public int getIndex() {
		return index;
	}
	
}

public class Account {
	private int uid;
	private ACCOUNT_TYPE type;
	private String username;
	private String password;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public ACCOUNT_TYPE getType() {
		return type;
	}
	public void setType(ACCOUNT_TYPE type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
