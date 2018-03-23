package service;

public class Account {
	private int uid;
	private ACCOUNT_TYPE type;
	private String username;
	private String password;
	
	public Account(int uid , ACCOUNT_TYPE type , String username, String password) {
		this.uid = uid;
		this.type = type;
		this.username = username;
		this.password = password;
	}
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
