package view;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class QueryPlay implements QueryPlayIf{
	public void querysch() {
		VBox sch=new VBox(50);
		Text schText = new Text("查询演出计划:");
		schText.setFill(Color.DARKGREY);
		schText.setFont(new Font(20));
		TextField idField=new TextField();
		idField.setPromptText("请输入ID:");
		Button check=new Button("开始查询");
		check.getStyleClass().add("my-button");
		sch.getChildren().addAll(schText,idField,check);
		sch.setTranslateY(-50);
		sch.setAlignment(Pos.CENTER);
		sch.prefWidthProperty().bind(MainFrame.centerWidth);
		double width = sch.getPrefWidth();
		sch.setPadding(new Insets(30,width*2.0/7,0,width*2.0/7));//面板上左右内边距
		sch.setSpacing(30);
		
		MainFrame.center.add(sch);
	}
	@Override
	public void queryPlayIf() {
		VBox play=new VBox(50);
		// TODO Auto-generated method stub
		Text playText = new Text("查询剧目:");
		playText.setFill(Color.DARKGREY);
		playText.setFont(new Font(20));
		TextField idField=new TextField();
		idField.setPromptText("请输入ID:");
		Button check=new Button("开始查询");
		check.getStyleClass().add("my-button");
		play.getChildren().addAll(playText,idField,check);
		playText.setTranslateY(-50);
		
		play.setAlignment(Pos.CENTER);
		play.prefWidthProperty().bind(MainFrame.centerWidth);
		double width = play.getPrefWidth();
		play.setPadding(new Insets(30,width*2.0/7,0,width*2.0/7));//面板上左右内边距
		play.setSpacing(30);
		
		MainFrame.center.add(play);
		check.setOnAction(e->{
			
		});
	}

}
