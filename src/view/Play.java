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
		del.setOnAction(e -> {
			del.recover();//初始化按钮以及界面 并且恢复上一个按钮的事件以及属性
			delece(-1);//调用删除面板
		});
		que.setOnAction(e -> {
			que.recover();//初始化按钮以及界面 并且恢复上一个按钮的事件以及属性
			query(-1);//调用管理面板
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
	     
	     grid.add(buttonBox, 45, 15, 2, 2);
	        
		 MainFrame.center.addAll(grid);

		 Label id = new Label("ID：");
		 TextField ids = new TextField();
		 ids.setPromptText("ID");
		 Label name = new Label("剧目名：");
		 TextField names = new TextField();
		 names.setPromptText("剧目名");
		 Label type = new Label("剧目类型：");
		/* ChoiceBox<String> types = new  ChoiceBox<>();
		 types.getItems().addAll("电影", "歌剧", "音乐会");*/
		 ComboBox<PLAY_TYPE> types = new ComboBox<>(FXCollections.observableArrayList(PLAY_TYPE.values()));
		 types.setPromptText("请选择..");
		 Label area = new Label("来源地：");
		 TextField areas = new TextField();
		 areas.setPromptText("来源地");
		 Label rating = new Label("级别：");
		 ComboBox<PLAY_RATING> ratings = new ComboBox<>(FXCollections.observableArrayList(PLAY_RATING.values()));
		/* ChoiceBox<String> ratings = new  ChoiceBox<>();
		 ratings.getItems().addAll("CHILD", "TEENAGE", "ADULT");*/
		 ratings.setPromptText("请选择..");
		 Label startDate = new Label("开始时间：");
		 DatePicker startDates = new DatePicker();
		 startDates.setPromptText("开始时间");
		 Label endDate = new Label("结束时间：");
		 DatePicker endDates = new DatePicker();
		 endDates.setPromptText("结束时间");
		 Label duration = new Label("演出时长：");
		 TextField durations = new TextField();
		 durations.setPromptText("按分钟计算");
		 Label price = new Label("价钱：");
		 TextField prices = new TextField();
		 prices.setPromptText("价钱/元");		 
	
	        grid.add(id, 42, 7);  // column=42, row=7
	        grid.add(name, 42, 8);  
	        grid.add(type, 42, 9);  
	        grid.add(area, 42, 10);
	        grid.add(rating, 42, 11); 
	        grid.add(startDate, 42, 12); 
	        grid.add(endDate, 42, 13); 
	        grid.add(duration, 42, 14); 
	        grid.add(price, 42, 15);
	            
	        grid.add(ids, 43, 7);  // column=43, row=7
	        grid.add(names, 43, 8);  
	        grid.add(types, 43, 9);  
	        grid.add(areas, 43, 10); 
	        grid.add(ratings, 43, 11); 
	        grid.add(startDates, 43, 12); 
	        grid.add(endDates, 43, 13); 
	        grid.add(durations, 43, 14); 
	        grid.add(prices, 43, 15); 
      
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
		IdField.setPromptText("请输入剧目ID:"); 
		Button Check=new Button("开始查询");
		Check.getStyleClass().add("my-button");
		grid.add(ModId, 20, 5);
		grid.add(IdField, 22, 5);
		grid.add(Check, 24, 5);
		
		MainFrame.center.add(grid);

		
		Check.setOnAction(e->{
	          //???
			if(!IdField.getText().isEmpty()) {
			
					MainFrame.popupMessage("正在查询!");
			//		int IDS = Integer.valueOf(IdField.getText());
				if(true) {
					GridPane Grid= new GridPane(); 
					Grid.setHgap(5);
					Grid.setVgap(5);
					Button saveBt = new Button("保存");
					saveBt.getStyleClass().add("my-button");
					Button agBt = new Button("重新输入");
					agBt.getStyleClass().add("my-button");
					HBox buttonBox = new HBox(saveBt, agBt);
				/*	saveBt.setMaxWidth(Double.MAX_VALUE);
					agBt.setMaxWidth(Double.MAX_VALUE);*/
					
					Grid.add(buttonBox, 50, 25, 5, 5);
			      
					Label ID = new Label("ID：");
					TextField IDs = new TextField();
					Label NAME = new Label("剧目名字：");
					TextField NAMEs = new TextField();
					Label TYPE = new Label("剧目类型：");
					ComboBox<PLAY_TYPE> TYPEs = new ComboBox<>(FXCollections.observableArrayList(PLAY_TYPE.values()));
					TYPEs.setPromptText("请选择..");
					Label AREA = new Label("来源地：");
					TextField AREAs = new TextField();
					Label RATING = new Label("级别：");
					ComboBox<PLAY_RATING> RATINGs = new ComboBox<>(FXCollections.observableArrayList(PLAY_RATING.values()));
					RATINGs.setPromptText("请选择..");
					Label StartDate = new Label("开始时间：");
					DatePicker StartDates = new DatePicker();
					Label EndDate = new Label("结束时间：");
					DatePicker EndDates = new DatePicker();
					Label Duration = new Label("演出时长：");
					TextField Durations = new TextField();
					Label Price = new Label("价钱：");
					TextField Prices = new TextField();
				 
					
					Grid.add(ID, 42, 17);  // column=42, row=7
					Grid.add(NAME, 42, 18);  
					Grid.add(TYPE, 42, 19);  
					Grid.add(AREA, 42, 20);
					Grid.add(RATING, 42, 21); 
					Grid.add(StartDate, 42, 22); 
					Grid.add(EndDate, 42, 23); 
					Grid.add(Duration, 42, 24); 
					Grid.add(Price, 42, 25);
			            
					Grid.add(IDs, 43, 17);  // column=43, row=7
					Grid.add(NAMEs, 43, 18);  
					Grid.add(TYPEs, 43, 19);  
					Grid.add(AREAs, 43, 20); 
					Grid.add(RATINGs, 43, 21); 
					Grid.add(StartDates, 43, 22); 
					Grid.add(EndDates, 43, 23); 
					Grid.add(Durations, 43, 24); 
					Grid.add(Prices, 43, 25); 
					MainFrame.center.add(Grid);
			        
			        saveBt.setOnAction(Event ->{
			        	//??
			        	MainFrame.popupMessage("保存成功(*^▽^*)");
			        });
			        agBt.setOnAction(Event -> {
			        	
			        	modify(1);

			        });
				}

			}else {
			
				MainFrame.popupMessage("请检查输入!");
			}
		
		});
		
		return false;
	}

	@Override
	public boolean delece(int id) {
		// TODO Auto-generated method stub
		GridPane grid = new GridPane();
		grid.setHgap(5);
	    grid.setVgap(5);
		Text delId = new Text("删除剧目:");
		delId.setFill(Color.BLACK);
		delId.setFont(new Font(20));
		TextField IdField=new TextField();
		IdField.setPromptText("请输入剧目ID:"); 
		Button Del=new Button("删除");
		Del.getStyleClass().add("my-button");
		grid.add(delId, 40, 40);
		grid.add(IdField, 45, 40);
		grid.add(Del, 50, 40);
		
		MainFrame.center.add(grid);
		
		Del.setOnAction(e->{
	          //??
			if(!IdField.getText().isEmpty()) {
				
				MainFrame.popupMessage("删除成功!");
			}else {
			
				MainFrame.popupMessage("请检查输入!");
			}
		});
		return false;
	}

	@Override
	public boolean query(int id) {
		// TODO Auto-generated method stub
		GridPane grid = new GridPane();
		grid.setHgap(5);
	    grid.setVgap(5);
		Text queId = new Text("管理剧目:");
		queId.setFill(Color.BLACK);
		queId.setFont(new Font(20));
		TextField IdField=new TextField();
		IdField.setPromptText("请输入剧目ID:"); 
		Button Que=new Button("查询");
		Que.getStyleClass().add("my-button");
		grid.add(queId, 40, 40);
		grid.add(IdField, 45, 40);
		grid.add(Que, 50, 40);
		
		MainFrame.center.add(grid);
		
		Que.setOnAction(e->{
	          //??
			if(!IdField.getText().isEmpty()) {
				
				MainFrame.popupMessage("正在查询!");
			}else {
			
				MainFrame.popupMessage("请检查输入!");
			}
		});
		return false;
	}

	@Override
	public void showList(List<service.Play> plays) {
		
		// TODO Auto-generated method stub

	}

}
