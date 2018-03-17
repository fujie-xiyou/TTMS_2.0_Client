package view;

import java.util.List;

import service.Account;

public interface AccountIf {
	public void mhtEntry();
	public boolean add(List<Account> accounts);
	public boolean modify(List<Account> accounts ,String name);
	public boolean delete(List<Account> accounts ,String name);
	public boolean query(List<Account> accounts ,String name);
	
}
