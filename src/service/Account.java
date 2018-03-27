package service;

import java.util.LinkedList;
import java.util.List;

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
	public static List<Account> getAccounts() {
		//用与界面开发阶段生成测试数据
		List<Account> accounts = new LinkedList<>();
		accounts.add(new Account(1, ACCOUNT_TYPE.MANG, "fujie", "经过加密的密码"));
		accounts.add(new Account(2, ACCOUNT_TYPE.CLERK, "zqn", "经过加密的密码"));
		return accounts;
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
