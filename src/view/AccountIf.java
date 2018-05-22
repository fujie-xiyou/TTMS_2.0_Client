package view;

import java.util.List;

import javafx.stage.Stage;
import model.Account;

public interface AccountIf {
	public void mhtEntry(List<Account> accounts);
	public void add(List<Account> accounts);
	public void modify(List<Account> accounts, Account account);
	public void delete(List<Account> accounts, Account account);
	public void query(List<Account> accounts, Account account);
	public void logout(Stage mainFrame);
	
}
