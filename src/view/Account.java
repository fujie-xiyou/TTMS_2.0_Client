package view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nodes.TopButton;
import service.ACCOUNT_TYPE;

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

	@SuppressWarnings("unused")
	@Override
	public void add(List<service.Account> accounts) {
		// TODO 自动生成的方法存根
		VBox centerPane = new VBox();//添加用户界面将使用VBox布局
		//centerPane.setAlignment(Pos.TOP_CENTER);//面板内容居中
		centerPane.prefWidthProperty().bind(MainFrame.centerWidth);//将面板首选宽度与预留面板的宽度绑定
		double width = centerPane.getPrefWidth();
		centerPane.setPadding(new Insets(30,width*2.0/7,0,width*2.0/7));//面板上左右内边距
		centerPane.setSpacing(30);
		MainFrame.center.add(centerPane);//将布局添加至预留布局中
		/* 由于为中部面板预留的布局面板为StackPane类型 可能无法满足实际需求  
		 * 因此在开发具体界面时 应该结合实际情况使用合适的布局面板(比如本界面选择VBox)
		 * 并且将其添加至MainFrame.center中  即可作为中部面板显示*/
		Text text = new Text("添加用户:");
		text.setFill(Color.DARKGREY);
		text.setFont(new Font(30));
		/*
		ComboBox<String> typeBox = 
				new ComboBox<>(FXCollections.
						observableArrayList(Arrays.asList(ACCOUNT_TYPE.values()).
						stream().filter(typ -> !typ.equals(ACCOUNT_TYPE.ANOMT)).
						map(ACCOUNT_TYPE::getName).
						collect(Collectors.toList())));
		//这句就厉害了
		//集合的流式处理 将账户类型数组过滤掉匿名用户并映射成账户类型名数组
		 * 这句虽然真的很厉害  但是不需要了
		 */
		
		//下拉框选择用户类型
		ComboBox<ACCOUNT_TYPE> typeBox = new ComboBox<>(FXCollections.
				observableArrayList(Arrays.stream(ACCOUNT_TYPE.values()).
						filter(type -> !type.equals(ACCOUNT_TYPE.ANOMT)).
						collect(Collectors.toList())));
		//这句也很厉害 重写ACCOUNT_TYPE的toString方法 使其返回name属性 方便直接将TYPE数组添加至下拉框
		
		typeBox.setPromptText("用户类型..");
		TextField nameField = new TextField();
		nameField.setPromptText("用户名");
		PasswordField passField = new PasswordField();
		passField.setPromptText("密码");
		Button add = new Button("  添加  ");
		add.getStyleClass().add("my-button");//为按钮添加my-button类型(css类型) 以获得扁平蓝色按钮
		centerPane.getChildren().addAll(text,typeBox,nameField ,passField,add);
		
		add.setOnAction(e -> {
			String name = nameField.getText();
			String pass = passField.getText();
			ACCOUNT_TYPE type = typeBox.getValue();
			//ACCOUNT_TYPE type = 
			if(!name.isEmpty() && !pass.isEmpty()  && type != null) {
				service.Account account = new service.Account(-1,type,name,pass);
				if(/*调用业务逻辑层新增用户的方法成功执行*/ true) {
					MainFrame.popupMessage("用户 "+name+" 新增成功!");
				}
				else {
					MainFrame.popupMessage("新增用户失败");
				}
			}else {
				MainFrame.popupMessage("请检查输入!");
			}
			
		});
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
