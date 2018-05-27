package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import iview.PlayIf;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.enums.PLAY_RATING;
import model.enums.PLAY_TYPE;
import nodes.TopButton;

public class Play implements PlayIf {

	@Override
	public void mgtEntry(List<model.Play> plays) {
		// TODO Auto-generated method stub
		TopButton add = new TopButton("添加剧目");
		add.setOnAction(e -> {
			add.recover();//初始化按钮以及界面 并且恢复上一个按钮的事件以及属性
			add(plays);//调用添加用户面板
		});
		          /*mod = new TopButton("修改"),
		          del = new TopButton("删除"),
				  showList = new TopButton("全部剧目");*/
		MainFrame.top.removeAll(MainFrame.top);
		MainFrame.top.addAll(add/*,mod,del,showList*/);
		MainFrame.center.removeAll(MainFrame.center);
		ScrollPane centerPane = new ScrollPane();
		FlowPane flowPane = new FlowPane();
		centerPane.prefWidthProperty().bind(MainFrame.centerWidth);
		//flowPane.prefWidthProperty().bind(MainFrame.centerWidth);
		//System.out.println(centerPane.getBoundsInParent());
		double w = centerPane.getPrefWidth();
		flowPane.setHgap(w/30);
		flowPane.setVgap(20);
		flowPane.setPadding(new Insets(w/30));
		centerPane.setFitToWidth(true);
		centerPane.setContent(flowPane);
		
		for(model.Play play : plays) {
			VBox vBox = new VBox();
			ImageView image = new ImageView(new Image(play.getImgUrl(), w/5,0, true, false));
			image.setOnMouseClicked(e -> {
				query(plays ,play);
			});
			Text text = new Text(play.getName());
			vBox.getChildren().addAll(image,text);
			flowPane.getChildren().add(vBox);
		}
		MainFrame.center.add(centerPane);
	}
	//	List<service.Play> Play = service.Play.this.getPlays();
	/*	add.setOnAction(e -> {
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
		showList.setOnAction(e -> {
			showList.recover();
	//		showList(service.Play.this.getPlays()); 
		});*/

	
	@SuppressWarnings("unused")
	@Override
	public boolean add(List<model.Play> plays) {
		// TODO Auto-generated method stub
		
		 GridPane grid = new GridPane();
		 grid.setHgap(5);
	     grid.setVgap(5);
	/*	 grid.prefWidthProperty().bind(MainFrame.centerWidth);//将面板首选宽度与预留面板的宽度绑定
		double width = grid.getPrefWidth();
		grid.setPadding(new Insets(30,width*2.0/7,0,width*2.0/7));//面板上左右内边距*/
		 
		 Button saveBtn = new Button("保存");
	     Button cleanBtn = new Button("清空");
	     Button backBtn = new Button("返回");
		 HBox buttonBox = new HBox(saveBtn, cleanBtn,backBtn);
	     saveBtn.setMaxWidth(Double.MAX_VALUE);
	     cleanBtn.setMaxWidth(Double.MAX_VALUE);
	     backBtn.setMaxWidth(Double.MAX_VALUE);
	     
	     grid.add(buttonBox, 45, 16, 2, 2);
	        
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
		 Label cover = new Label("封面：");
		 TextField covers = new TextField();
		 covers.setPromptText("输入网址");
	
	        grid.add(id, 42, 7);  // column=42, row=7
	        grid.add(name, 42, 8);  
	        grid.add(type, 42, 9);  
	        grid.add(area, 42, 10);
	        grid.add(rating, 42, 11); 
	        grid.add(startDate, 42, 12); 
	        grid.add(endDate, 42, 13); 
	        grid.add(duration, 42, 14); 
	        grid.add(price, 42, 15);
	        grid.add(cover, 42, 16);
	            
	        grid.add(ids, 43, 7);  // column=43, row=7
	        grid.add(names, 43, 8);  
	        grid.add(types, 43, 9);  
	        grid.add(areas, 43, 10); 
	        grid.add(ratings, 43, 11); 
	        grid.add(startDates, 43, 12); 
	        grid.add(endDates, 43, 13); 
	        grid.add(durations, 43, 14); 
	        grid.add(prices, 43, 15); 
	        grid.add(covers, 43, 16);
      
	        saveBtn.setOnAction(e -> {
			String Id = ids.getText();
			String Name = names.getText();
			PLAY_TYPE Type = types.getValue();
			String Area = areas.getText();
			PLAY_RATING Rating = ratings.getValue();
			String Durations = durations.getText();
			String StartDate = startDate.getText();
			String EndDate = endDate.getText();
			String Prices = prices.getText();
	//		String Cover = covers.getText();
		
			if(!Id.isEmpty() && !Name.isEmpty()  && !Area.isEmpty()&& !Durations.isEmpty() && !Prices.isEmpty()) {
				
				int ID = Integer.valueOf(Id).intValue();
				int DURATION =  Integer.valueOf(Durations).intValue();
				int PRICE = Integer.valueOf(Prices).intValue();
				DateTimeFormatter START = DateTimeFormatter.ofPattern(StartDate);
				DateTimeFormatter END = DateTimeFormatter.ofPattern(EndDate);

				model.Play play = new model.Play(ID,Name,Type,Area,Rating,DURATION,LocalDate.parse(StartDate, START),LocalDate.parse(EndDate, END),PRICE,"");
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
	    	add(plays);
				
		});
	    backBtn.setOnAction(e -> {
	    	MainFrame.center.removeAll();
	    	mgtEntry(plays);
	    });
	 
	        return true;	
	}

	@Override
	public boolean modify(List<model.Play> plays ,model.Play play) {
		// TODO Auto-generated method stub
		MainFrame.center.removeAll(MainFrame.center);
		GridPane grid = new GridPane();
		grid.setHgap(10);
	    grid.setVgap(10);
		grid.setAlignment(Pos.CENTER);
		VBox outer = new VBox();
		outer.setSpacing(40);
		outer.setAlignment(Pos.CENTER);
		MainFrame.center.add(outer);
		grid.add(new Text("剧目ID:"), 0, 0);
		grid.add(new Text("剧目名称:"), 0, 1);
		grid.add(new Text("剧目类型:"), 0, 2);
		grid.add(new Text("来源地区:"),0,3);
		grid.add(new Text("影片级别:"), 0, 4);
		grid.add(new Text("时长:"), 0, 5);
		grid.add(new Text("开始时间:"), 0, 6);
		grid.add(new Text("结束时间:"), 0, 7);
		grid.add(new Text("票价:"), 0, 8);
		grid.add(new Text("封面"), 0, 9);
		grid.add(new Text(play.getId()+""), 1, 0);
		TextField name = new TextField(play.getName());
		grid.add(name, 1, 1);
		ComboBox<PLAY_TYPE> type = new ComboBox<>(FXCollections.observableArrayList(PLAY_TYPE.values()));
		type.setValue(play.getType());
		grid.add(type, 1, 2);
		TextField area = new TextField(play.getArea());
		grid.add(area, 1, 3);
		ComboBox<PLAY_RATING> rating = new ComboBox<>(FXCollections.observableArrayList(PLAY_RATING.values()));
		rating.setValue(play.getRating());
		grid.add(rating, 1, 4);
		TextField dura = new TextField(play.getDuration()+"");
		grid.add(dura, 1, 5);
		DatePicker strDate = new DatePicker(play.getStartDate());
		grid.add(strDate, 1, 6);
		DatePicker endDate = new DatePicker(play.getEndDate());
		grid.add(endDate, 1,7);
		TextField price = new TextField(play.getPrice()+"");
		grid.add(price, 1, 8);
		TextField imgUrl = new TextField(play.getImgUrl());
		grid.add(imgUrl, 1, 9);
		Button save = new Button("保存");
		Button ret = new Button("返回");
		HBox hBoxButt = new HBox();
		hBoxButt.setAlignment(Pos.CENTER);
		hBoxButt.setSpacing(50);
		hBoxButt.getChildren().addAll(save,ret);
		outer.getChildren().addAll(grid,hBoxButt);
		
		save.setOnAction(e -> {
			play.setName(name.getText());
			play.setType(type.getValue());
			play.setArea(area.getText());
			play.setRating(rating.getValue());
			play.setDuration(Integer.valueOf(dura.getText()));
			play.setStartDate(strDate.getValue());
			play.setEndDate(endDate.getValue());
			play.setPrice(Integer.valueOf(price.getText()));
			play.setImgUrl(imgUrl.getText());
			MainFrame.popupMessage("修改剧目成功！");
			query(plays, play);
		});
		ret.setOnAction(e -> query(plays, play));
		
		return false;
	}

	@Override
	public boolean delece(List<model.Play> plays ,model.Play play) {
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
	public boolean query(List<model.Play> plays, model.Play play) {
		// TODO Auto-generated method stub
		MainFrame.center.removeAll(MainFrame.center);
		GridPane grid = new GridPane();
		grid.setHgap(10);
	    grid.setVgap(10);
		
		VBox outer = new VBox();
		outer.setSpacing(40);
		outer.setAlignment(Pos.CENTER);
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(30);
		MainFrame.center.add(outer);
		grid.add(new Text("剧目ID:"), 0, 0);
		grid.add(new Text("剧目名称:"), 0, 1);
		grid.add(new Text("剧目类型:"), 0, 2);
		grid.add(new Text("来源地区:"),0,3);
		grid.add(new Text("影片级别:"), 0, 4);
		grid.add(new Text("时长:"), 0, 5);
		grid.add(new Text("开始时间:"), 0, 6);
		grid.add(new Text("结束时间:"), 0, 7);
		grid.add(new Text("票价:"), 0, 8);
		
		grid.add(new Text(play.getId()+""), 1, 0);
		grid.add(new Text(play.getName()), 1, 1);
		grid.add(new Text(play.getType().toString()), 1, 2);
		grid.add(new Text(play.getArea()), 1, 3);
		grid.add(new Text(play.getRating().toString()), 1, 4);
		grid.add(new Text(play.getDuration()+""), 1, 5);
		grid.add(new Text(play.getStartDate().toString()), 1, 6);
		grid.add(new Text(play.getEndDate().toString()), 1, 7);
		grid.add(new Text(play.getPrice()+""), 1, 8);
		
		ImageView image = new ImageView( new Image(play.getImgUrl()));
		image.setPreserveRatio(true);

		hBox.getChildren().addAll(image,grid);
		Button mod = new Button("修改");
		Button del = new Button("删除");
		Button ret = new Button("返回");
		HBox hBoxButt = new HBox();
		hBoxButt.setAlignment(Pos.CENTER);
		hBoxButt.setSpacing(50);
		hBoxButt.getChildren().addAll(mod,del,ret);
		outer.getChildren().addAll(hBox,hBoxButt);
		mod.setOnAction(e -> {
			modify(plays ,play);
		});
		del.setOnAction(e -> {
			plays.remove(play);
			mgtEntry(plays);
		});
		ret.setOnAction(e -> mgtEntry(plays));
		return false;
	}

	@Override
	public void showList(List<model.Play> plays) {
		
		// TODO Auto-generated method stub
	/*	for(int i = 0; i<plays.size();i++) {
			System.out.println(plays);
		}
*/
		System.out.println(plays);
	}

}
