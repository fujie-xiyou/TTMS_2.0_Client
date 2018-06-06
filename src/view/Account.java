package view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LoginUser;
import model.Result;
import model.enums.ACCOUNT_TYPE;
import nodes.TopButton;
import service.AccountSer;
import service.HttpCommon;
import tools.ConfirmDel;
import tools.LoadingButton;
public class Account {
	public static model.Account CurUser;
	private AccountSer accountSer = new AccountSer(); 

	public void mhtEntry() {
		MainFrame.center.removeAll(MainFrame.center);
		MainFrame.top.removeAll(MainFrame.top);

		/*		  mod = new TopButton("修改"),
				  del = new TopButton("删除"),
				  que = new TopButton("查询");
		MainFrame.top.addAll(add,mod,del,que);*/
		
		new Thread(new Task<List<model.Account>>() {
			@Override
			public List<model.Account> call(){
				return accountSer.fetchAll();
			}
			@Override
			public void running() {
				MainFrame.center.add(new ImageView("file:Resource/loading1.gif"));
			}
			@Override 
			public void succeeded() {
				MainFrame.center.removeAll(MainFrame.center);
				TopButton add = new TopButton("添加用户");
				MainFrame.top.add(add);
				List<model.Account> accounts = getValue();
				add.setOnAction(e -> {
					add.recover();//初始化按钮以及界面 并且恢复上一个按钮的事件以及属性
					add();//调用添加用户面板
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
					del.getStyleClass().add("del-button");
					mod.setOnAction(e -> modify(account));
					del.setOnAction(e -> {
						ConfirmDel.setConfirmDel(del, ee -> delete(account,del));
					});
					centerPane.add(mod, 4, row);
					centerPane.add(del, 5, row);
					row++;
				}
				MainFrame.center.add(centerPane);
			}
		}).start();

		
	//	List<service.Account> accounts = null;//假的用户列表
		
		
	}

	public void add() {
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
		add.setDefaultButton(true);
		Button cla = new Button("  返回  ");
		cla.setCancelButton(true);
		HBox hBox = new HBox(add,cla);
		hBox.setSpacing(20);
		centerPane.getChildren().addAll(text,typeBox,nameField ,passField,hBox);
		
		add.setOnAction(e -> {
			String name = nameField.getText();
			String pass = Hashing.md5().newHasher().putString(passField.getText(), Charsets.UTF_8).hash().toString();
			ACCOUNT_TYPE type = typeBox.getValue();
			//ACCOUNT_TYPE type = 
			if(!name.isEmpty() && !pass.isEmpty()  && type != null) {
				model.Account account = new model.Account(-1,type,name,pass);
				new Thread( new Task<Result>() {
					@Override
					public Result call() {
						return accountSer.add(account);
					}
					@Override 
					public void running() {
						LoadingButton.setLoading(add);
					}
					@Override 
					public void succeeded() {
						LoadingButton.setNormal(add);
						mhtEntry();
						Result result = getValue();
						if (result.isStatus()) {
							MainFrame.popupMessage("用户 " + name + " 新增成功!");
							return;
						} else {
							MainFrame.popupMessage("新增用户失败:" + result.getReasons());
						}
						
					}
				}).start();

			}else {
				MainFrame.popupMessage("请检查输入!");
			}
			
		});
		cla.setOnAction(e -> mhtEntry());
	}

	public void modify(model.Account account) {
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
		ok.setDefaultButton(true);
		Button cla = new Button("返回");
		cla.setCancelButton(true);
		centerPane.add(ok, 0, 4);
		centerPane.add(cla, 1, 4);
		MainFrame.center.add(centerPane);
		ok.setOnAction(e -> {
			if(name.getText().isEmpty()) {
				MainFrame.popupMessage("用户名不合法！");
				return;
			}
			model.Account newAcc = new model.Account();
			newAcc.setUid(account.getUid());
			newAcc.setPassword(account.getPassword());
			newAcc.setUsername(name.getText());
			newAcc.setType(type.getValue());
			if(!password.getText().isEmpty()) {
				String pass = Hashing.md5().newHasher().putString(password.getText(), Charsets.UTF_8).hash().toString();
				newAcc.setPassword(pass);
			}
			new Thread(new Task<Result>() {
				@Override
				public Result call() {
					return accountSer.modify(newAcc);
				}
				@Override
				public void running() {
					LoadingButton.setLoading(ok);
				}
				public void succeeded() {
					LoadingButton.setNormal(ok);
					Result result = getValue();
					if(result.isStatus()) {
						//mhtEntry();
						account.setType(newAcc.getType());
						account.setUsername(newAcc.getUsername());
						account.setPassword(newAcc.getPassword());
						MainFrame.popupMessage("修改成功！");
					}else {
						MainFrame.popupMessage("修改失败: "+result.getReasons());
					}
					
				}
			}).start();
			
		});
		cla.setOnAction(e -> mhtEntry());
	}

	public void delete(model.Account account,Button delButton) {
		if(account.equals(LoginUser.getLoginUser())) {
			MainFrame.popupMessage("这个世界虽然不完美，但我们仍然可以疗愈自己。");
			return;
		}
		new Thread(new Task<Result>() {
			@Override
			public Result call() {
				return accountSer.delete(account);
			}
			@Override
			public void running() {
				LoadingButton.setLoading(delButton);
			}
			@Override
			public void succeeded() {
				mhtEntry();
				LoadingButton.setNormal(delButton);
				Result result = getValue();
				if(result.isStatus()) {
					MainFrame.popupMessage("已删除用户: "+account.getUsername());
				}
				else {
					MainFrame.popupMessage("删除失败: "+result.getReasons());
				}
				
			}
		}).start();
		
		
	}


	public void query(model.Account account) {
		// TODO 自动生成的方法存根
	
	}

	public void logout(Stage mainFrame) {
		Account.CurUser = null;
		HttpCommon.setCookie(null);
		mainFrame.close();
		Login.login.show();
	}

}
