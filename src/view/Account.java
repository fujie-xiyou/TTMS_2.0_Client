package view;

import java.util.List;

import javafx.scene.Node;
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
		
		//修改顶栏按钮高宽
		//for each循环遍历
		for(Node node : MainFrame.top) {
			TopButton topButton = (TopButton)node;
			topButton.setPrefWidth(MainFrame.w);
			//topButton.setPrefHeight(topButton.getPrefHeight()*1.5);
			System.out.println(topButton.getHeight());
		}
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
