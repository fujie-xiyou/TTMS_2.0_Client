package view;

import service.StudioSer;
import tools.ConfirmDel;

import javafx.scene.control.TextField;
import java.util.List;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nodes.TopButton;

public class Studio {

    private StudioSer studioSer = new StudioSer();
    public void mgtEntry(){
    	MainFrame.center.removeAll(MainFrame.center);
        MainFrame.top.removeAll(MainFrame.top);
        
        new Thread(new Task<List<model.Studio>>() {
			@Override
			public List<model.Studio> call(){
				return studioSer.fetchAll();
			}
			@Override
			public void running() {
				MainFrame.center.add(new ImageView("file:Resource/loading1.gif"));
			}
			@Override 
			public void succeeded() {
				MainFrame.center.removeAll(MainFrame.center);
				TopButton add = new TopButton("添加用户");
				MainFrame.top.add(add);
				List<model.Studio> studios = getValue();
				add.setOnAction(e -> {
					add.recover();//初始化按钮以及界面 并且恢复上一个按钮的事件以及属性
					add();//调用添加用户面板
				});
		
				GridPane centerPane = new GridPane();
				centerPane.setVgap(20);
				centerPane.setHgap(30);
				centerPane.setPadding(new Insets(30));
				centerPane.add(new Text("演出厅ID"), 0, 0);
				centerPane.add(new Text("演出厅名字"), 3, 0);
				centerPane.add(new Text("座位总数"), 6, 0);

				int row = 1;
				for(model.Studio studio : studios) {
					centerPane.add(new Text(studio.getId()+""),0 , row);
					centerPane.add(new Text(studio.getName()),3 , row);
					centerPane.add(new Text(studio.getCount()+""),6 , row);
					Button mod = new Button("修改") , del = new Button("删除");
					del.getStyleClass().add("del-button");
					mod.setOnAction(e -> modify(studio));
					del.setOnAction(e -> {
						ConfirmDel.setConfirmDel(del, ee -> delete(studio,del));
					});
					centerPane.add(mod, 9, row);
					centerPane.add(del, 11, row);
			/*		System.out.println("row="+row);
					System.out.println("count="+studio.getCount());*/
					row=row+2;
					
				}
				centerPane.setAlignment(Pos.TOP_CENTER);
				MainFrame.center.add(centerPane);
			}
		}).start();
    }
    public void add() {
    	
    	VBox centerPane = new VBox();
    	centerPane.prefWidthProperty().bind(MainFrame.centerWidth);
    	double width = centerPane.getPrefWidth();
    	centerPane.setPadding(new Insets(30,width*2.0/1,0,width*2.0/7));
    	MainFrame.center.add(centerPane);
    	Text text = new Text("添加演出厅");
    	text.setFill(Color.DARKGRAY);
    	text.setFont(new Font(30));
    	
    	Label name = new Label("演出厅名字：");
    	TextField nameField = new TextField();
    	nameField.setPromptText("演出厅名");
    	Label row = new Label("座位行数：");
    	TextField Row = new TextField();
    	Row.setPromptText("座位行数");
    	Label col = new Label("座位列数：");
    	TextField Col = new TextField();
    	Col.setPromptText("座位列数");
    	Label  count= new Label("座位总数：");
    	TextField Count = new TextField();
    	Count.setPromptText("座位总数");
    	Button add = new Button("添加");
    	add.setDefaultButton(true);
    	Button cla = new Button("返回");
    	cla.setDefaultButton(true);
    	HBox hBox = new HBox(add,cla);
    	hBox.setSpacing(20);
    	centerPane.getChildren().addAll(text,nameField,Row,Col,Count,hBox);
    	
    	
    }
    public void modify(model.Studio studio) {
		
	}
    public void delete(model.Studio studio, Button del) {
		
	}
    
}
