package view;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
	     
	     grid.add(buttonBox, 25, 25, 25, 25);
	        
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
		 
	
	        grid.add(id, 1, 1);  // column=1, row=1
	        grid.add(name, 1, 2);  // column=1, row=2
	        grid.add(type, 1, 3);  // column=1, row=3
	        grid.add(area, 1, 4); // column=1, row=4
	        grid.add(rating, 1, 5);  // column=1, row=5
	        grid.add(startDate, 1, 6);  // column=1, row=6
	        grid.add(endDate, 1, 7);  // column=1, row=7
	        grid.add(duration, 1, 8); // column=1, row=8
	        grid.add(price, 1, 9); // column=1, row=9
	            
	        grid.add(ids, 2, 1);  // column=2, row=1
	        grid.add(names, 2, 2);  // column=2, row=2
	        grid.add(types, 2, 3);  // column=2, row=3
	        grid.add(areas, 2, 4); // column=2, row=4
	        grid.add(ratings, 2, 5);  // column=2, row=5
	        grid.add(startDates, 2, 6);  // column=2, row=6
	        grid.add(endDates, 2, 7);  // column=2, row=7
	        grid.add(durations, 2, 8); // column=2, row=8
	        grid.add(prices, 2, 9); // column=2, row=9

	        
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
