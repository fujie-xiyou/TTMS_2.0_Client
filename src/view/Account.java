package view;

import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
		
		List<service.Account> accounts = null;//假的用户列表
		
		add.setOnAction(e -> {
			add.recover();//初始化按钮以及界面 并且恢复上一个按钮的事件以及属性
			add(accounts);//调用添加用户面板
		});
		mod.setOnAction(e -> {
			mod.recover();
			modify(accounts);
		});
	}

	@Override
	public void add(List<service.Account> accounts) {
		// TODO 自动生成的方法存根
		VBox centerPane = new VBox();//添加用户界面将使用VBox布局
		centerPane.setPadding(new Insets(100));//面板内边距
		
		centerPane.setAlignment(Pos.CENTER);//面板内容居中
		
		MainFrame.center.add(centerPane);//将布局添加至预留布局中
		/* 由于为中部面板预留的布局面板为StackPane类型 可能无法满足实际需求  
		 * 因此在开发具体界面时 应该结合实际情况使用合适的布局面板(比如本界面选择VBox)
		 * 并且将其添加至MainFrame.center中  即可作为中部面板显示*/
		
		TextField name = new TextField();
		name.setPromptText("用户名");
		PasswordField pass = new PasswordField();
		pass.setPromptText("密码");
		
		centerPane.getChildren().addAll(name ,pass);
		System.out.println(centerPane.getPrefWidth());

		
	}

	@Override
	public void modify(List<service.Account> accounts) {
		// TODO 自动生成的方法存根
	
	}

	@Override
	public void delete(List<service.Account> accounts) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void query(List<service.Account> accounts) {
		// TODO 自动生成的方法存根
	
	}

}
