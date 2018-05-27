package test;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Test extends Application{
	public static void main(String[] args)  {
		launch(args);
	}

//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		// TODO 自动生成的方法存根
//		String url = new File("Resource/loading3.gif").toURI().toURL().toString();
//		//这一通胡乱操作  相当于给前面加了个 file:   。。。。。。。。。。
//		System.out.println(url);
//		new Image(url);
//		Scene scene = new Scene(new Pane(new ImageView("file:Resource/loading2.gif")));
//		primaryStage.setScene(scene);
//		primaryStage.show();
//		
//	}@Override  
    public void start(Stage arg0) throws Exception {  
        Pane root = new StackPane();  
        Scene scene = new Scene(root,400,400);  
        scene.getStylesheets().add("file:Resource/MainStyle.css");
        Text text = new Text("我日了你德玛");
        text.setFill(Color.RED);
        text.getStyleClass().add("left--button");
        root.getChildren().add(text);  
        
        //定义矩形的淡入淡出效果  
        FadeTransition fadeTransition=new FadeTransition(Duration.millis(1000),root);  
        fadeTransition.setFromValue(1.0f);  
        fadeTransition.setToValue(0.0f);  
        fadeTransition.setCycleCount(1);  
        //fadeTransition.setAutoReverse(true);  
        fadeTransition.play();  
//          
//        //定义矩形的平移效果  
//        TranslateTransition translateTransition=new TranslateTransition(Duration.millis(2000), rectParallel);  
//        translateTransition.setFromX(50);  
//        translateTransition.setToX(350);  
//        translateTransition.setCycleCount(2);  
//        translateTransition.setAutoReverse(true);  
//        //translateTransition.play();  
//          
//        //定义矩形旋转效果  
//        RotateTransition rotateTransition =   
//                new RotateTransition(Duration.millis(3000), rectParallel);  
//        rotateTransition.setByAngle(180f);//旋转度数  
//        rotateTransition.setCycleCount(4);  
//        rotateTransition.setAutoReverse(true);  
//        //rotateTransition.play();  
//          
//        //矩形的缩放效果  
//        ScaleTransition scaleTransition =   
//                new ScaleTransition(Duration.millis(2000), rectParallel);  
//            scaleTransition.setToX(2f);  
//            scaleTransition.setToY(2f);  
//            scaleTransition.setCycleCount(2);  
//            scaleTransition.setAutoReverse(true);  
//        //scaleTransition.play();  
//          
//        //并行执行动画  
//        ParallelTransition parallelTransition=new ParallelTransition(fadeTransition,rotateTransition,  
//                                        translateTransition,scaleTransition);  
//        parallelTransition.setCycleCount(Timeline.INDEFINITE);  
//        parallelTransition.play();  
//              
        arg0.setScene(scene);  
        arg0.show();  
    }  
}
