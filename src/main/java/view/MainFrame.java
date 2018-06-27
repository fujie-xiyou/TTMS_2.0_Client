package view;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.List;

import javafx.animation.FadeTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.LoginUser;
import nodes.LeftButton;
import service.AccountSer;
import service.PlaySer;
import service.ScheduleSer;
import service.StudioSer;

public class MainFrame {
    public static ObservableList<Node> top;
    public static ObservableList<Node> center;
    public static ObservableList<Node> bottom;
    public static double leftWidth; //左侧栏宽度
    public static DoubleProperty centerWidth;//中部面板宽度

    public void mainFrame() {
        Stage stage = new Stage();//新建一个舞台(窗口)
        stage.setResizable(false);//使窗口大小固定
        stage.setTitle("HLW剧院票务管理系统");
        stage.show();//使窗口可见
        BorderPane mainPane = new BorderPane();//用于存放顶栏,中间面板,底栏(消息面板)
        HBox topPane = new HBox();//顶栏
        //设置顶栏背景色
        //移步到CSS文件
        //topPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
        topPane.getStyleClass().add("top-bar");
        mainPane.setTop(topPane);//放置顶栏

        StackPane centerPane = new StackPane();//中间内容面板
        center = centerPane.getChildren();//使用静态变量保存中间面板节点列表
        centerWidth = new SimpleDoubleProperty();
        centerWidth.bind(centerPane.widthProperty());
        mainPane.setCenter(centerPane);
        top = topPane.getChildren();//使用静态变量保存顶栏节点列表
        VBox leftPane = new VBox();//左栏
        //设置左栏背景色
        //移动至CSS文件
        //leftPane.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
        //leftPane.setPadding(new Insets(0,5,0,5));
        leftPane.getStyleClass().add("left-bar");
        StackPane bottomPane = new StackPane();//底栏(用于显示提示消息)
        mainPane.setBottom(bottomPane);
        bottom = bottomPane.getChildren();

        Pane outerPane = new BorderPane(mainPane, null, null, null, leftPane);
        LeftButton stu = new LeftButton("演出厅管理"),
                sch = new LeftButton("演出计划"),
                play = new LeftButton("剧目管理"),
                sale = new LeftButton("    售票    "),
                ret = new LeftButton("    退票    "),
                que = new LeftButton("查询"),
                ans = new LeftButton("排序和统计"),
                acc = new LeftButton("账户管理"),
                out = new LeftButton("退出登录");
        if (LoginUser.getLoginUser().getType().equals(model.enums.ACCOUNT_TYPE.CLERK)) {
            leftPane.getChildren().addAll(sale, ret, out);
        } else {
            leftPane.getChildren().addAll(stu, sch, play, acc, out);
        }
        leftPane.setAlignment(Pos.TOP_CENTER);
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double width = bounds.getWidth() * 0.66;
        Scene scene = new Scene(outerPane, width, 9.0 / 16 * width);
        scene.getStylesheets().add("MainStyle.css");
        stage.setScene(scene);

        //舞台加载完毕后 将左栏中按钮的宽度设置为左栏的宽度
        leftWidth = leftPane.getWidth();
        //使用迭代器遍历
        Iterable<Node> iterable = leftPane.getChildren();
        iterable.forEach(node -> {
            ((LeftButton) node).setPrefWidth(leftWidth);
        });
        //售票
        sale.setOnAction(e -> {
            sale.recover();
            Sale saIf = new Sale();
            PlaySer.clearList();
            saIf.mgtEntry();
        });
        ret.setOnAction(e -> {
            ret.recover();
            new ReturnTicketView().mgtEnter();
        });
        //账号管理
        acc.setOnAction(e -> {
            acc.recover();
            Account account = new Account();
            AccountSer.clearList();
            account.mhtEntry();
        });

        //剧目管理
        play.setOnAction(e -> {
            play.recover();
            Play playif = new Play();
            PlaySer.clearList();
            playif.mgtEntry(null);
        });

        //查询
        que.setOnAction(e -> {
            que.recover();
            QueriesMenu query = new QueriesMenu();
            query.queriseMenu();
        });
        //演出厅管理
        stu.setOnAction(e -> {
            stu.recover();
            Studio studio = new Studio();
            StudioSer.clearList();
            studio.mgtEntry();
        });
        //演出计划
        sch.setOnAction(e -> {
            sch.recover();
            Schedule schedule = new Schedule();
            ScheduleSer.clearList();
            schedule.mgtEntry();
        });
        //注销登录
        out.setOnAction(e -> {
            new Account().logout(stage);
        });


    }

    public static void popupMessage(String message, int millis) {
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER_LEFT);
        Rectangle rectangle = new Rectangle();
        rectangle.getStyleClass().add("msg-bar");
        rectangle.widthProperty().bind(pane.widthProperty());
        rectangle.heightProperty().bind(pane.heightProperty());
        HBox hBox = new HBox(new ImageView(new Image("msg.png", 16, 16, true, true)), new Text(message));
        hBox.setSpacing(10);
        pane.getChildren().addAll(rectangle, hBox);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(1);
        //fadeTransition.setAutoReverse(true);  
        fadeTransition.play();
        bottom.removeAll(bottom);
        bottom.add(pane);
        new Thread(new Task<Void>() {
            @Override
            public Void call() {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void succeeded() {
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), pane);
                fadeTransition.setFromValue(1);
                fadeTransition.setToValue(0);
                fadeTransition.setCycleCount(1);
                fadeTransition.play();
            }
        }).start();
    }

    public static void popupMessage(String message) {
        popupMessage(message, 2000);
    }

}
