package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Test1 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		Pane pane = new StackPane(new Text("可把你牛逼坏了"));
		Scene scene = new Scene(pane,200,300);
		//pane.setBackground(new Background(new BackgroundFill(Color.rgb(255, 0, 0, 0.2), null, null)));
		scene.setFill(Color.rgb(255, 100, 255,0.2));
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
