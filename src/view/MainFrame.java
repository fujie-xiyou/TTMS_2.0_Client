package view;




import javafx.scene.paint.Color;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


class LeftButton extends Button{
	//继承Button用来统一定义Button属性
	public LeftButton(String value) {
		// TODO 自动生成的构造函数存根
		super(value);
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, null, null)));
	}
}
public class MainFrame implements MainIf {

	@Override
	public void mainFrame() {
		// TODO 自动生成的方法存根
		Stage stage = new Stage();//新建一个舞台(窗口)
		stage.show();//使窗口可见
		Pane topPane = new HBox();
		Pane leftPane = new VBox();
		Pane centerPane = new GridPane();
		Pane mainPane = new BorderPane(centerPane,topPane,null,null,leftPane);
		Button  sch = new LeftButton("演出厅管理"),
				play = new LeftButton("剧目管理"),
				sale = new LeftButton("售票"),
				ret = new LeftButton("退票"),
				que = new LeftButton("查询"),
				ans = new LeftButton("排序和统计"),
				acc = new LeftButton("账户管理"),
				out = new LeftButton("退出登录");
		leftPane.getChildren().addAll(sch,play,sale,ret,que,ans,acc,out);
		Scene scene = new Scene(mainPane,);
		stage.setScene(scene);
		
	}


}
