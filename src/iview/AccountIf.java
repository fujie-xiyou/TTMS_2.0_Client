package iview;

import java.util.List;

import javafx.stage.Stage;
import model.Account;

public interface AccountIf {
	public void mhtEntry();
	public void add();
	public void modify(Account account);
	public void delete(Account account);
	public void query(Account account);
	public void logout(Stage mainFrame);
	
}
