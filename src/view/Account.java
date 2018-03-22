package view;

import java.util.List;

import nodes.TopButton;

public class Account implements AccountIf {
	@Override
	public void mhtEntry() {
		// TODO 自动生成的方法存根
		TopButton add = new TopButton("添加"),
				  mod = new TopButton("修改"),
				  del = new TopButton("删除"),
				  que = new TopButton("查询");
		MainFrame.top.addAll(add,mod,del,que);
		add.setOnAction(e -> {
			add.recover();
			MainFrame.popupMessage("消息!可是很长的!");
		});
		mod.setOnAction(e -> {
			mod.recover();
			MainFrame.popupMessage("我咋看着不长呢?");
		});
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
