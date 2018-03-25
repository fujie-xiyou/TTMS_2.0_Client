package view;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nodes.TopButton;
import service.PLAY_RATING;
import service.PLAY_TYPE;

public class Play implements PlayIf {

	@Override
	public void mgtEntry() {
		// TODO Auto-generated method stub
		TopButton add = new TopButton("添加"),
		          mod = new TopButton("修改"),
		          del = new TopButton("删除"),
		          que = new TopButton("管理"),
				  showList = new TopButton("全部剧目");
		MainFrame.top.addAll(add,mod,del,que,showList);
		
		
		add.setOnAction(e -> {
			add.recover();//初始化按钮以及界面 并且恢复上一个按钮的事件以及属性
			add();//调用添加用户面板
		});
		mod.setOnAction(e -> {
			mod.recover();//初始化按钮以及界面 并且恢复上一个按钮的事件以及属性
			modify(-1);//调用修改用户面板
		});

	}
	
	@SuppressWarnings("unused")
	@Override
	public boolean add() {
		// TODO Auto-generated method stub
		List<service.Play> plays;
		 GridPane grid = new GridPane();
		 grid.setHgap(5);
	     grid.setVgap(5);
	/*	 grid.prefWidthProperty().bind(MainFrame.centerWidth);//将面板首选宽度与预留面板的宽度绑定
		double width = grid.getPrefWidth();
		grid.setPadding(new Insets(30,width*2.0/7,0,width*2.0/7));//面板上左右内边距*/
		 
		 Button saveBtn = new Button("保存");
		 saveBtn.getStyleClass().add("my-button");
	     Button cleanBtn = new Button("清空");
	     cleanBtn.getStyleClass().add("my-button");
		 HBox buttonBox = new HBox(saveBtn, cleanBtn);
	     saveBtn.setMaxWidth(Double.MAX_VALUE);
	     cleanBtn.setMaxWidth(Double.MAX_VALUE);
	     
	     grid.add(buttonBox, 25, 9, 2, 2);
	        
		 MainFrame.center.addAll(grid);
		
		

		 Label id = new Label("ID：");
		 TextField ids = new TextField();
		 Label name = new Label("剧目名字：");
		 TextField names = new TextField();
		 Label type = new Label("剧目类型：");
		/* ChoiceBox<String> types = new  ChoiceBox<>();
		 types.getItems().addAll("电影", "歌剧", "音乐会");*/
		 ComboBox<PLAY_TYPE> types = new ComboBox<>(FXCollections.observableArrayList(PLAY_TYPE.values()));
		 types.setPromptText("请选择..");
		 Label area = new Label("来源地：");
		 TextField areas = new TextField();
		 Label rating = new Label("级别：");
		 ComboBox<PLAY_RATING> ratings = new ComboBox<>(FXCollections.observableArrayList(PLAY_RATING.values()));
		/* ChoiceBox<String> ratings = new  ChoiceBox<>();
		 ratings.getItems().addAll("CHILD", "TEENAGE", "ADULT");*/
		 ratings.setPromptText("请选择..");
		 Label startDate = new Label("开始时间：");
		 DatePicker startDates = new DatePicker();
		 Label endDate = new Label("结束时间：");
		 DatePicker endDates = new DatePicker();
		 Label duration = new Label("演出时长：");
		 TextField durations = new TextField();
		 Label price = new Label("价钱：");
		 TextField prices = new TextField();
		 
	
	        grid.add(id, 20, 1);  // column=1, row=1
	        grid.add(name, 20, 2);  // column=1, row=2
	        grid.add(type, 20, 3);  // column=1, row=3
	        grid.add(area, 20, 4); // column=1, row=4
	        grid.add(rating, 20, 5);  // column=1, row=5
	        grid.add(startDate, 20, 6);  // column=1, row=6
	        grid.add(endDate, 20, 7);  // column=1, row=7
	        grid.add(duration, 20, 8); // column=1, row=8
	        grid.add(price, 20, 9); // column=1, row=9
	            
	        grid.add(ids, 21, 1);  // column=2, row=1
	        grid.add(names, 21, 2);  // column=2, row=2
	        grid.add(types, 21, 3);  // column=2, row=3
	        grid.add(areas, 21, 4); // column=2, row=4
	        grid.add(ratings, 21, 5);  // column=2, row=5
	        grid.add(startDates, 21, 6);  // column=2, row=6
	        grid.add(endDates, 21, 7);  // column=2, row=7
	        grid.add(durations, 21, 8); // column=2, row=8
	        grid.add(prices, 21, 9); // column=2, row=9

	        
	        saveBtn.setOnAction(e -> {
			String Id = ids.getText();
			String Name = names.getText();
			PLAY_TYPE Type = types.getValue();
			String Area = areas.getText();
			PLAY_RATING Rating = ratings.getValue();
			String Durations = durations.getText();
			String Prices = prices.getText();
		
			if(!Id.isEmpty() && !Name.isEmpty()  && !Area.isEmpty()&& !Durations.isEmpty() && !Prices.isEmpty()) {
				
				int ID = Integer.valueOf(Id).intValue();
				int DURATION =  Integer.valueOf(Durations).intValue();
				int PRICE = Integer.valueOf(Prices).intValue();
				service.Play play = new service.Play(ID,Name,Type,Area,Rating,DURATION,PRICE);
				if( true) {
					MainFrame.popupMessage("新增成功!");
				}
				else {
					MainFrame.popupMessage("失败");
				}
			}else {
				MainFrame.popupMessage("请检查输入!");
			}
	
		});
	    cleanBtn.setOnAction(e -> {
	    	add();
				
		});
	 
	        return true;
		
	}

	@Override
	public boolean modify(int id) {
		// TODO Auto-generated method stub
		GridPane grid = new GridPane();
		grid.setHgap(5);
	    grid.setVgap(5);
		Text ModId = new Text("请输入要修改的剧目ID:");
		ModId.setFill(Color.BLACK);
		ModId.setFont(new Font(20));
		TextField IdField=new TextField();
		IdField.setPromptText("请输入ID:"); 
		Button Check=new Button("开始查询");
		Check.getStyleClass().add("my-button");
		grid.add(ModId, 5, 1);
		grid.add(IdField, 5, 3);
		grid.add(Check, 10, 3);
		
		MainFrame.center.add(grid);
		
		Check.setOnAction(e->{
	    //??????????????????????????????????????????????????????
		});
		
		return false;
	}

	@Override
	public boolean delece(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean query(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showList(List<service.Play> plays) {
		// TODO Auto-generated method stub

	}

}
