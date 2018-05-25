package view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import iview.AccountIf;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Result;
import model.enums.ACCOUNT_TYPE;
import nodes.TopButton;
import service.AccountSer;
import service.HttpCommon;
public class Account implements AccountIf {
	public static model.Account CurUser;
	private AccountSer accountSer = new AccountSer(); 
	@Override
	public void mhtEntry() {
		List<model.Account> accounts = accountSer.fetchAll();
		// TODO 自动生成的方法存根
		MainFrame.center.removeAll(MainFrame.center);
		MainFrame.top.removeAll(MainFrame.top);
		TopButton add = new TopButton("添加用户");
		MainFrame.top.add(add);
		/*		  mod = new TopButton("修改"),
				  del = new TopButton("删除"),
				  que = new TopButton("查询");
		MainFrame.top.addAll(add,mod,del,que);
		
		List<service.Account> accounts = null;//假的用户列表
		*/
		add.setOnAction(e -> {
			add.recover();//初始化按钮以及界面 并且恢复上一个按钮的事件以及属性
			add(accounts);//调用添加用户面板
		});
		/*
		mod.setOnAction(e -> {
			mod.recover();
			modify(accounts);
		});*/
		GridPane centerPane = new GridPane();
		centerPane.setVgap(20);
		centerPane.setHgap(30);
		centerPane.setPadding(new Insets(30));
		centerPane.add(new Text("用户ID"), 0, 0);
		centerPane.add(new Text("用户名"), 1, 0);
		centerPane.add(new Text("用户类型"), 2, 0);
		//centerPane.add(new Text("密码"), 3, 0);
		int row = 1;
		for(model.Account account : accounts) {
			centerPane.add(new Text(account.getUid()+""),0 , row);
			centerPane.add(new Text(account.getUsername()),1 , row);
			centerPane.add(new Text(account.getType().toString()),2 , row);
			//centerPane.add(new Text(account.getPassword()),3 , row);
			Button mod = new Button("修改") , del = new Button("删除");
			mod.getStyleClass().add("my-button");
			mod.setOnAction(e -> modify(accounts, account));
			del.getStyleClass().add("my-button");
			del.setOnAction(e -> delete(accounts, account));
			centerPane.add(mod, 4, row);
			centerPane.add(del, 5, row);
			row++;
		}
		MainFrame.center.add(centerPane);
	}

	@SuppressWarnings("unused")
	@Override
	public void add(List<model.Account> accounts) {
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
		Button cla = new Button("  返回  ");
		add.getStyleClass().add("my-button");//为按钮添加my-button类型(css类型) 以获得扁平蓝色按钮
		cla.getStyleClass().add("my-button");
		centerPane.getChildren().addAll(text,typeBox,nameField ,passField,add,cla);
		
		add.setOnAction(e -> {
			String name = nameField.getText();
			String pass = Hashing.md5().newHasher().putString(passField.getText(), Charsets.UTF_8).hash().toString();
			ACCOUNT_TYPE type = typeBox.getValue();
			//ACCOUNT_TYPE type = 
			if(!name.isEmpty() && !pass.isEmpty()  && type != null) {
				
				model.Account account = new model.Account(-1,type,name,pass);
				Result result = accountSer.add(account);
				if(result.isStatus()) {
					accounts.add(account);
					MainFrame.popupMessage("用户 "+name+" 新增成功!");
				}
				else {
					MainFrame.popupMessage("新增用户失败:"+result.getReasons());
				}
			}else {
				MainFrame.popupMessage("请检查输入!");
			}
			
		});
		cla.setOnAction(e -> mhtEntry());
	}

	@Override
	public void modify(List<model.Account> accounts, model.Account account) {
		// TODO 自动生成的方法存根
		MainFrame.center.removeAll(MainFrame.center);
		GridPane centerPane = new GridPane();
		centerPane.setAlignment(Pos.CENTER);
		centerPane.setHgap(20);
		centerPane.setVgap(20);
		centerPane.add(new Text("用户ID:"), 0, 0);
		centerPane.add(new Text(account.getUid()+""),1,0);
		centerPane.add(new Text("用户名:"), 0, 1);
		TextField name = new TextField(account.getUsername());
		centerPane.add(name, 1, 1);
		centerPane.add(new Text("用户类型:"), 0, 2);
		ComboBox<model.enums.ACCOUNT_TYPE> type = new ComboBox<>(FXCollections.observableArrayList(model.enums.ACCOUNT_TYPE.values()));
		type.setValue(account.getType());
		centerPane.add(type, 1, 2);
		centerPane.add(new Text("新密码:"), 0, 3);
		PasswordField password = new PasswordField();
		password.setPromptText("[未更改]");
		centerPane.add(password, 1, 3);
		Button ok = new Button("确认");
		Button cla = new Button("返回");
		ok.getStyleClass().add("my-button");
		cla.getStyleClass().add("my-button");
		centerPane.add(ok, 0, 4);
		centerPane.add(cla, 1, 4);
		MainFrame.center.add(centerPane);
		ok.setOnAction(e -> {
			account.setUsername(name.getText());
			account.setType(type.getValue());
			if(!password.getText().isEmpty()) account.setPassword(password.getText());
			//后续要调用业务逻辑层的修改方法
			mhtEntry();
		});
		cla.setOnAction(e -> mhtEntry());
	}

	@Override
	public void delete(List<model.Account> accounts, model.Account account) {
		// TODO 自动生成的方法存根
		accounts.remove(account);
		mhtEntry();
	}

	@Override
	public void query(List<model.Account> accounts, model.Account account) {
		// TODO 自动生成的方法存根
	
	}
	@Override
	public void logout(Stage mainFrame) {
		Account.CurUser = null;
		HttpCommon.setCookie(null);
		mainFrame.close();
		Login.login.show();
	}

}
