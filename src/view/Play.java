package view;

import java.util.List;
import nodes.TopButton;

public class Play implements PlayIf {

	@Override
	public void mgtEntry() {
		// TODO Auto-generated method stub
		TopButton add = new TopButton("添加"),
		          mod = new TopButton("修改"),
		          del = new TopButton("删除"),
		          que = new TopButton("管理");
		MainFrame.top.addAll(add,mod,del,que);
		
		
		add.setOnAction(e -> {
			add.recover();//初始化按钮以及界面 并且恢复上一个按钮的事件以及属性
			add();//调用添加用户面板
		});

	}
	
	@SuppressWarnings("unused")
	@Override
	public boolean add() {
		// TODO Auto-generated method stub
		List<service.Play> plays;
		
		
		return false;
	}

	@Override
	public boolean modify(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delece(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean query(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showList(List<service.Play> plays) {
		// TODO Auto-generated method stub

	}

}
