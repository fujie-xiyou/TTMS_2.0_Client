package view;

import javafx.scene.paint.Color;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;


class LeftButton extends Button{
	//继承Button用来统一定义Button属性
	
	private static LeftButton lastButton = null;
	private static EventHandler<ActionEvent> lastEvent = null;
	
	private void initButton() {
		// 设置按钮颜色
		this.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));

		// 鼠标移入按钮时 按钮变灰
		this.setOnMouseEntered(e -> {
			this.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
		});

		// 鼠标移出按钮时 按钮颜色恢复
		this.setOnMouseExited(e -> {
			this.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
		});
		
		//点击按钮时 背景变白  点击其他按钮时 背景恢复
		this.setOnAction(e -> {
			recover();
		});
	}
	
	//放入点击事件 实现点击按钮 背景变白  
	//点击其他按钮 背景恢复 按钮事件恢复
	public void recover() {
		
		if(lastButton != null) {
			lastButton.initButton();
			lastButton.setOnAction(lastEvent);
		}
		//这里居然能调用私有方法initButton!
		
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		lastButton = this;
		lastEvent = this.getOnAction();
		this.setOnAction(null);
		this.setOnMouseEntered(null);
		this.setOnMouseExited(null);
	}
	public LeftButton(String value) {
		// TODO 自动生成的构造函数存根
		super(value);
		
		initButton();
		

	}
}
public class MainFrame implements MainIf {
	public static  ObservableList<Node> top;
	@Override
	public void mainFrame() {
		// TODO 自动生成的方法存根
		Stage stage = new Stage();//新建一个舞台(窗口)
		stage.setResizable(false);
		stage.show();//使窗口可见
		BorderPane centerTop = new BorderPane();
		HBox topPane = new HBox();
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
		double w = leftPane.getWidth();
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
