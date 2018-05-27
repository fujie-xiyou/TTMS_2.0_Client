package view;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Result;
import service.AccountSer;

public class Login extends Application {
	public static Stage login;

	@Override
	public void start(Stage primaryStage) throws Exception {
		login = primaryStage;
		// TODO 自动生成的方法存根
		// Application.setUserAgentStylesheet(getClass().getResource("MainStyle.css").toExternalForm());
		// 设置CSS文件(会清空默认风格)

		// Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
		// 切换到另一种javafx界面风格
		primaryStage.setTitle("登录");
		primaryStage.setResizable(false);
		VBox pane = new VBox();
		// FlowPane pane = new FlowPane();
		// pane.setOrientation(Orientation.VERTICAL);
		pane.setAlignment(Pos.CENTER);
		double w = Screen.getPrimary().getBounds().getWidth();
		pane.setPrefWidth(w / 4);
		//pane.setPrefHeight(w / 4 / 4 * 3);
		pane.setPadding(new Insets(w / 4 / 5,w/4/5,w/4/7,w/4/5));
		pane.setSpacing(30);
		Text title = new Text("HLW剧院票务管理系统");
		title.setFont(new Font(25));
		TextField name = new TextField();
		name.setPromptText("用户名");
		PasswordField password = new PasswordField();
		password.setPromptText("密码");
		Button login = new Button("登录");
		login.setDefaultButton(true);
		login.getStyleClass().add("login-button");
		Text msg = new Text();
		msg.setFill(Color.RED);
		pane.getChildren().addAll(title ,name, password, login,msg);
		Scene scene = new Scene(pane);
		//scene.getStylesheets().add(getClass().getResource("MainStyle.css").toExternalForm());
		scene.getStylesheets().add("file:Resource/MainStyle.css");

		primaryStage.setScene(scene);
		primaryStage.show();
		login.setOnAction(e -> {		
			if (!name.getText().isEmpty()) {
				login.setDisable(true);
				String pass = Hashing.md5().newHasher().putString(password.getText(), Charsets.UTF_8).hash().toString();
				model.Account account = new model.Account(-1, null, name.getText(), pass);
				
				Task<Result> task = new Task<Result>() {
					@Override
					protected Result call() throws Exception{
						try {
							return new AccountSer().login(account);
						}catch(Exception e) {
							e.printStackTrace();
						}
						return null;
					}
					@Override
					protected void running() {
						login.setGraphic(new ImageView(new Image("file:Resource/loading2.gif", 12, 12, true, true)));
						login.setText("登录中");
						super.running();
					}
					@Override
					protected void succeeded() {
						login.setGraphic(null);
						login.setText("登录");
						login.setDisable(false);
						Result result = getValue();
						if (result.isStatus()) {
							primaryStage.close();
							msg.setText(null);
							new MainFrame().mainFrame();
						}else {
							msg.setText(null);
							for(String str : result.getReasons()) {
								msg.setText(msg.getText()+"\n●"+str);
							}
						}	
					}
				};
				new Thread(task).start();
				
				//pane.getChildren().remove(msg);
			}
		});

	}

	public static void main(String[] args) {
		launch(args);
	}

}
