package nodes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import view.MainFrame;

public class TopButton extends Button{
	
	private static TopButton lastButton = null;
	private static EventHandler<ActionEvent> lastEvent = null;
	
	private void initButton() {
		
		//立即清空底栏消息
		MainFrame.bottom.removeAll(MainFrame.bottom);
		
		// 设置按钮颜色
		this.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));

		// 鼠标移入按钮时 按钮变灰
		this.setOnMouseEntered(e -> {
			this.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		});

		// 鼠标移出按钮时 按钮颜色恢复
		this.setOnMouseExited(e -> {
			this.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
		});
		
		//点击按钮时 背景变白  点击其他按钮时 背景恢复
		this.setOnAction(e -> {
			recover();
		});
	}
	
	//放入点击事件中 实现点击按钮 按钮背景变白  
	//点击其他按钮 背景恢复 按钮事件恢复 
	public void recover() {
		MainFrame.center.removeAll(MainFrame.center);
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
	public TopButton(String value) {
		super(value);
		this.initButton();
		
		
		//设置按钮宽度
		this.setPrefWidth(MainFrame.w);
	}
	
}