package view;

import java.text.DecimalFormat;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QueryPlay implements QueriesMenuIf{

	Stage stage;
	@Override
	public void queriseMenu() {
		// TODO Auto-generated method stub
		Button bt1=new Button("查询剧目信息");
		bt1.setOnMouseClicked(e->deal());
		Button bt2=new Button("查询剧目演出计划信息");
		bt2.setOnMouseClicked(e->deal());
		HBox hBox=new HBox();
		hBox.getChildren().addAll(bt1,bt2);
		Scene scene=new Scene(hBox,300,300);
		stage.setScene(scene);
		stage.show();
		
	}
	    private void deal() {
		// TODO Auto-generated method stub
		Stage stage2=new Stage();
		TextField idField = new TextField();
		idField.setPromptText("请输入ID");
		Button ok=new Button("确定");
		ok.setOnMouseClicked(e->{
			System.out.println("正在查询......请稍等");
		});
		VBox vBox=new VBox();
	    vBox.getChildren().addAll(idField,ok);
	    Scene scene2=new Scene(vBox,200,200);
	    stage2.setScene(scene2);
	    stage2.show();
		
	}

}
