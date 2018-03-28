package view;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import nodes.LeftButton;

public class MainFrame implements MainIf {
	public static  ObservableList<Node> top;
	public static ObservableList<Node> center;
	public static ObservableList<Node> bottom;
	public static double leftWidth; //左侧栏宽度
	public static DoubleProperty centerWidth;//中部面板宽度
	@Override
	public void mainFrame() {
		Stage stage = new Stage();//新建一个舞台(窗口)
		stage.setResizable(false);//使窗口大小固定
		stage.show();//使窗口可见
		BorderPane mainPane = new BorderPane();//用于存放顶栏,中间面板,底栏(消息面板)
		HBox topPane = new HBox();//顶栏
		//设置顶栏背景色
		topPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
		
		mainPane.setTop(topPane);//放置顶栏
		
		StackPane centerPane = new StackPane();//中间内容面板
		center = centerPane.getChildren();//使用静态变量保存中间面板节点列表
		centerWidth = new SimpleDoubleProperty();
		centerWidth.bind(centerPane.widthProperty());
		mainPane.setCenter(centerPane);
		top = topPane.getChildren();//使用静态变量保存顶栏节点列表
		VBox leftPane = new VBox();//左栏
		//设置左栏背景色
		leftPane.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
		
		HBox bottomPane = new HBox();//底栏(用于显示提示消息)
		mainPane.setBottom(bottomPane);
		bottom = bottomPane.getChildren();
		
		Pane outerPane = new BorderPane(mainPane,null,null,null,leftPane);
		LeftButton  sch = new LeftButton("演出厅管理"),
				play = new LeftButton("剧目管理"),
				sale = new LeftButton("    售票    "),
				ret = new LeftButton("    退票    "),
				que = new LeftButton("查询"),
				ans = new LeftButton("排序和统计"),
				acc = new LeftButton("账户管理"),
				out = new LeftButton("退出登录");
		if(Account.CurUser.getType().equals(service.ACCOUNT_TYPE.CLERK)) {
			leftPane.getChildren().addAll(sale,ret,out);
		}else {
			leftPane.getChildren().addAll(sch,play,que,ans,acc,out);
		}
		leftPane.setAlignment(Pos.TOP_CENTER);
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		double width = bounds.getWidth()*0.5;
		Scene scene = new Scene(outerPane, width,9.0/16 * width);
		scene.getStylesheets().add(getClass().getResource("MainStyle.css").toExternalForm()); 
		stage.setScene(scene);
		
		//舞台加载完毕后 将左栏中按钮的宽度设置为左栏的宽度
		leftWidth = leftPane.getWidth();
		//使用迭代器遍历
		Iterable<Node> iterable = leftPane.getChildren();
		iterable.forEach(node -> {
			((LeftButton)node).setPrefWidth(leftWidth);
		});
		
		//账号管理
		acc.setOnAction(e -> {
			acc.recover();
			AccountIf account = new Account();
			List<service.Account> accounts = service.Account.getAccounts();
			account.mhtEntry(accounts);
		});
		
		//剧目管理
		play.setOnAction(e -> {
			play.recover();
			PlayIf playif  = new Play();
			List<service.Play> plays = service.Play.getPlays();
			playif.mgtEntry(plays);
		});
		
		//查询
		que.setOnAction(e -> {
			que.recover();
			QueriesMenuIf query = new QueriesMenu();
			query.queriseMenu();
		});
		//演出厅管理
		sch.setOnAction(e->{
			sch.recover();
			ScheduleIf sche=new Schedule();
			sche.mgtEntry(0);
		});
		//注销登录
		out.setOnAction(e -> {
			new Account().logout(stage);
		});
		
		
	}
	public static void popupMessage(String message) {
		Text text = new Text(message);
		bottom.removeAll(bottom);
		bottom.add(text);
		/*
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bottom.removeAll(bottom);
		*/
	}

}
