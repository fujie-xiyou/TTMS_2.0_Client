package view;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Login extends Application {
	public static Stage login;

	@Override
	public void start(Stage primaryStage) throws Exception {
		login = primaryStage;
		// TODO 自动生成的方法存根
		//Application.setUserAgentStylesheet(getClass().getResource("MainStyle.css").toExternalForm());
		//设置CSS文件(会清空默认风格)
		
		//Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
		//切换到另一种javafx界面风格
		primaryStage.setTitle("登录");
		primaryStage.setResizable(false);
		VBox pane = new VBox();
		//FlowPane pane = new FlowPane();
		//pane.setOrientation(Orientation.VERTICAL);
		pane.setAlignment(Pos.CENTER);
		double w = Screen.getPrimary().getBounds().getWidth();
		pane.setPrefWidth(w/4);
		pane.setPrefHeight(w/4/4*3);
		pane.setPadding(new Insets(w/4/4));
		pane.setSpacing(20);
		TextField name = new TextField();
		name.setPromptText("用户名");
		PasswordField password = new PasswordField();
		password.setPromptText("密码");
		Button login = new Button("登录");
		login.getStyleClass().add("my-button");
		pane.getChildren().addAll(name,password,login);
		Scene scene = new Scene(pane);
		scene.getStylesheets().add(getClass().getResource("MainStyle.css").toExternalForm()); 
		primaryStage.setScene(scene);
		primaryStage.show();
		login.setOnAction(e ->{
			List<model.Account> accounts = model.Account.getAccounts();
			if(!name.getText().isEmpty()) {
				for(model.Account account : accounts) {
					if(account.getUsername().equals(name.getText())) {
						Account.CurUser = account;
						primaryStage.close();
						new MainFrame().mainFrame();
					}
				}
			}

		});
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
