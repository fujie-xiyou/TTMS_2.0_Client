package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class Test extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO 自动生成的方法存根
		//Application.setUserAgentStylesheet(getClass().getResource("MainStyle.css").toExternalForm());
		//设置CSS文件(会清空默认风格)
		
		//Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
		//切换到另一种javafx界面风格
		
		new MainFrame().mainFrame();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
