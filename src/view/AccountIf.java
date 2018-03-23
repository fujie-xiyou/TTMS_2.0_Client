package view;

import java.util.List;

import service.Account;

public interface AccountIf {
	public void mhtEntry();
	public void add(List<Account> accounts);
	public void modify(List<Account> accounts);
	public void delete(List<Account> accounts);
	public void query(List<Account> accounts);
	
}
