package view;

import java.util.List;

import javafx.scene.control.Button;

public class Account implements AccountIf {

	@Override
	public void mhtEntry() {
		// TODO 自动生成的方法存根
		MainFrame.top.add(new Button("测试"));
	}

	@Override
	public boolean add(List<service.Account> accounts) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean modify(List<service.Account> accounts, String name) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean delete(List<service.Account> accounts, String name) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean query(List<service.Account> accounts, String name) {
		// TODO 自动生成的方法存根
		return false;
	}

}
