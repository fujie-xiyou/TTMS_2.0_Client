package view;
import iview.QueryPlayIf;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class QueryPlay implements QueryPlayIf{

	@Override
	public void queryPlayIf() {
		VBox play=new VBox(50);
		// TODO Auto-generated method stub
		Text playText = new Text("查询剧目:");
		playText.setFill(Color.DARKGREY);
		playText.setFont(new Font(20));
		TextField fileName=new TextField();
		
		fileName.setPromptText("请输入剧目名称:");
	//	String fileNames=fileName.getText();
		Button check=new Button("开始查询");
		play.getChildren().addAll(playText,fileName,check);
		playText.setTranslateY(-50);
		fileName.setTranslateY(-20);
		play.setAlignment(Pos.CENTER);
		play.prefWidthProperty().bind(MainFrame.centerWidth);
		double width = play.getPrefWidth();
		play.setPadding(new Insets(30,width*2.0/7,0,width*2.0/7));//面板上左右内边距
		play.setSpacing(30);
		
		MainFrame.center.add(play);

		check.setOnAction(e->{
			
			if(!fileName.getText().isEmpty())
			{
		
			MainFrame.popupMessage("查询成功!");
		}
		else 
		{
			MainFrame.popupMessage("请检查输入!");
		}
		});
		
			
	}
	
	public void querysch() {
		VBox sch=new VBox(50);
		Text schText = new Text("查询演出计划:");
		
		schText.setFill(Color.DARKGREY);
		schText.setFont(new Font(20));
		TextField id=new TextField();
		id.setPromptText("请输入剧目名称:");
		//String ids=id.getText();
		Button check1=new Button("开始查询");
		sch.getChildren().addAll(schText,id,check1);
		schText.setTranslateY(-50);
		id.setTranslateY(-20);
		sch.setAlignment(Pos.CENTER);
		sch.prefWidthProperty().bind(MainFrame.centerWidth);
		double width = sch.getPrefWidth();
		sch.setPadding(new Insets(30,width*2.0/7,0,width*2.0/7));//面板上左右内边距
		sch.setSpacing(30);
		
		MainFrame.center.add(sch);
        check1.setOnAction(e->{
        	if(!id.getText().isEmpty()) 
    		{
    			System.out.println("hhhhh");
    			MainFrame.popupMessage("查询成功!");
    		}
    		else 
    		{
    			MainFrame.popupMessage("请检查输入!");
    		}
		});
	}

}
