package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test1  {


	public static void main(String[] args) {
		LocalDateTime.parse("2017-09-28 17:07:05",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println("zqn");
	}
}
