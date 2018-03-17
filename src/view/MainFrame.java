package view;

import javafx.scene.paint.Color;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import nodes.LeftButton;

public class MainFrame implements MainIf {
	public static  ObservableList<Node> top;
	public static double w; //左侧栏宽度
	@Override
	public void mainFrame() {
		Stage stage = new Stage();//新建一个舞台(窗口)
		stage.setResizable(false);
		stage.show();//使窗口可见
		BorderPane centerTop = new BorderPane();
		HBox topPane = new HBox();
		
		//设置顶栏背景色
		topPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
		centerTop.setTop(topPane);
		top = topPane.getChildren();
		VBox leftPane = new VBox();
		BorderPane centerPane = new BorderPane();
		centerPane.setCenter(centerTop);
		
		Pane mainPane = new BorderPane(centerPane,null,null,null,leftPane);
		LeftButton  sch = new LeftButton("演出厅管理"),
				play = new LeftButton("剧目管理"),
				sale = new LeftButton("售票"),
				ret = new LeftButton("退票"),
				que = new LeftButton("查询"),
				ans = new LeftButton("排序和统计"),
				acc = new LeftButton("账户管理"),
				out = new LeftButton("退出登录");
		leftPane.getChildren().addAll(sch,play,sale,ret,que,ans,acc,out);
		leftPane.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
		leftPane.setAlignment(Pos.TOP_CENTER);
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		double width = bounds.getWidth()*0.5;
		Scene scene = new Scene(mainPane, width,9.0/16 * width);
		stage.setScene(scene);
		
		//舞台加载完毕后 将左栏中按钮的宽度设置为左栏的宽度
		w = leftPane.getWidth();
		//使用迭代器遍历
		Iterable<Node> iterable = leftPane.getChildren();
		iterable.forEach(node -> {
			((LeftButton)node).setPrefWidth(w);
		});
		
		//账号管理
		acc.setOnAction(e -> {
			acc.recover();
			AccountIf account = new Account();
			account.mhtEntry();
		});
	}

}
