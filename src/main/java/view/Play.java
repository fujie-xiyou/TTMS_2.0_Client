package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
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
import model.Result;
import model.enums.PLAY_RATING;
import model.enums.PLAY_TYPE;
import nodes.LeftButton;
import nodes.TopButton;
import service.PlaySer;
import tools.ConfirmDel;
import tools.LoadingButton;
import tools.LoadingPage;

public class Play {
	private List<model.Play> plays = null;
	private PlaySer playSer = new PlaySer();

	public void mgtEntry(List<model.Play> playss) {
		// TODO Auto-generated method stub
		/*
		 * mod = new TopButton("修改"), del = new TopButton("删除"), showList = new
		 * TopButton("全部剧目");
		 */
		MainFrame.top.removeAll(MainFrame.top);

		MainFrame.center.removeAll(MainFrame.center);
		new Thread(new Task<List<model.Play>>() {
			@Override
			public List<model.Play> call() {
				plays = playSer.fetchAll();
				return plays;
			}

			@Override
			public void running() {
				LoadingPage.loadingPage(this);
				super.running();
			}

			@Override
			public void succeeded() {
				TopButton add = new TopButton("添加剧目");
				MainFrame.top.addAll(add/* ,mod,del,showList */);
				add.setOnAction(e -> {
					add.recover();// 初始化按钮以及界面 并且恢复上一个按钮的事件以及属性
					add(plays);// 调用添加用户面板
				});
				MainFrame.center.removeAll(MainFrame.center);
				ScrollPane centerPane = new ScrollPane();
				FlowPane flowPane = new FlowPane();
				centerPane.prefWidthProperty().bind(MainFrame.centerWidth);
				// flowPane.prefWidthProperty().bind(MainFrame.centerWidth);
				// System.out.println(centerPane.getBoundsInParent());
				double w = centerPane.getPrefWidth();
				flowPane.setHgap(w / 30);
				flowPane.setVgap(20);
				flowPane.setPadding(new Insets(w / 30));
				centerPane.setFitToWidth(true);
				centerPane.setContent(flowPane);

				for (model.Play play : plays) {
					VBox vBox = new VBox();
					ImageView image = new ImageView(new Image(play.getImgUrl(), w / 5, 0, true, false));
					image.setOnMouseClicked(e -> {
						query(plays, play);
					});
					Text text = new Text(play.getName());
					vBox.getChildren().addAll(image, text);
					flowPane.getChildren().add(vBox);
				}
				MainFrame.center.add(centerPane);
			}
		}).start();

	}
	// List<service.Play> Play = service.Play.this.getPlays();
	/*
	 * add.setOnAction(e -> { add.recover();//初始化按钮以及界面 并且恢复上一个按钮的事件以及属性
	 * add();//调用添加用户面板 }); mod.setOnAction(e -> { mod.recover();//初始化按钮以及界面
	 * 并且恢复上一个按钮的事件以及属性 modify(-1);//调用修改用户面板 }); del.setOnAction(e -> {
	 * del.recover();//初始化按钮以及界面 并且恢复上一个按钮的事件以及属性 delece(-1);//调用删除面板 });
	 * showList.setOnAction(e -> { showList.recover(); //
	 * showList(service.Play.this.getPlays()); });
	 */

	@SuppressWarnings("unused")
	public boolean add(List<model.Play> plays) {
		// TODO Auto-generated method stub

		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		/*
		 * grid.prefWidthProperty().bind(MainFrame.centerWidth);//将面板首选宽度与预留面板的宽度绑定
		 * double width = grid.getPrefWidth(); grid.setPadding(new
		 * Insets(30,width*2.0/7,0,width*2.0/7));//面板上左右内边距
		 */

		Button saveBtn = new Button("保存");
		Button cleanBtn = new Button("清空");
		Button backBtn = new Button("返回");
		HBox buttonBox = new HBox(saveBtn, cleanBtn, backBtn);
		buttonBox.setSpacing(90);
		buttonBox.setAlignment(Pos.CENTER);
		saveBtn.setMaxWidth(Double.MAX_VALUE);
		cleanBtn.setMaxWidth(Double.MAX_VALUE);
		backBtn.setMaxWidth(Double.MAX_VALUE);

		MainFrame.center.addAll(grid);
		grid.setAlignment(Pos.TOP_CENTER);

		// Label id = new Label("ID：");
		// TextField ids = new TextField();
		// ids.setPromptText("ID");
		Label name = new Label("剧目名：");
		TextField names = new TextField();
		names.setPromptText("剧目名");
		Label type = new Label("剧目类型：");
		/*
		 * ChoiceBox<String> types = new ChoiceBox<>(); types.getItems().addAll("电影",
		 * "歌剧", "音乐会");
		 */
		ComboBox<PLAY_TYPE> types = new ComboBox<>(FXCollections.observableArrayList(PLAY_TYPE.values()));
		types.setPromptText("请选择..");
		Label area = new Label("来源地：");
		TextField areas = new TextField();
		areas.setPromptText("来源地");
		Label rating = new Label("级别：");
		ComboBox<PLAY_RATING> ratings = new ComboBox<>(FXCollections.observableArrayList(PLAY_RATING.values()));
		/*
		 * ChoiceBox<String> ratings = new ChoiceBox<>();
		 * ratings.getItems().addAll("CHILD", "TEENAGE", "ADULT");
		 */
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

		// grid.add(id, 0, 7); // column=0, row=7
		grid.add(name, 0, 8);
		grid.add(type, 0, 10);
		grid.add(area, 0, 12);
		grid.add(rating, 0, 14);
		grid.add(startDate, 0, 16);
		grid.add(endDate, 0, 18);
		grid.add(duration, 0, 20);
		grid.add(price, 0, 22);
		grid.add(cover, 0, 24);

		// grid.add(ids, 1, 7); // column=1, row=7
		grid.add(names, 1, 8);
		grid.add(types, 1, 10);
		grid.add(areas, 1, 12);
		grid.add(ratings, 1, 14);
		grid.add(startDates, 1, 16);
		grid.add(endDates, 1, 18);
		grid.add(durations, 1, 20);
		grid.add(prices, 1, 22);
		grid.add(covers, 1, 24);
		grid.add(buttonBox, 0, 30, 2, 2);
		saveBtn.setOnAction(e -> {

			String Name = names.getText();
			PLAY_TYPE Type = types.getValue();
			String Area = areas.getText();
			PLAY_RATING Rating = ratings.getValue();
			String Durations = durations.getText();
			LocalDate StartDate = startDates.getValue();
			LocalDate EndDate = endDates.getValue();
			String Prices = prices.getText();
			String Covers = covers.getText();
			// String Cover = covers.getText();
			if (!Name.isEmpty() && Type != null &&!Area.isEmpty() && Rating != null && !Durations.isEmpty() && !Prices.isEmpty()) {
				int DURATION = 0;
				int PRICE = 0;
				try{
					DURATION = Integer.valueOf(Durations);
					PRICE = Integer.valueOf(Prices);
					new Image(Covers);
				}catch (Exception n){
					MainFrame.popupMessage("请检查输入!");
					return;
				}

				model.Play play = new model.Play(-1, Name, Type, Area, Rating, DURATION,StartDate,EndDate,PRICE,Covers);
				new Thread(new Task<Result>() {
					@Override
					public Result call() throws Exception{
						try {
							return playSer.add(play);
						}catch (Exception e){
							e.printStackTrace();
						}
						return null;
					}
					@Override
					public void running(){
						LoadingButton.setLoading(saveBtn);
					}
					@Override
					public void succeeded(){
						LoadingButton.setNormal(saveBtn);
						Result result = getValue();
						if(result.isStatus()){
							mgtEntry(plays);
							MainFrame.popupMessage("新增成功!");
						}else {
							MainFrame.popupMessage(result.getReasons().toString());
						}
					}
					@Override
					public void failed(){
						LoadingButton.setNormal(saveBtn);
						MainFrame.popupMessage("新增失败,服务器异常");
					}

				}).start();

			} else {
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

	public boolean modify(List<model.Play> plays, model.Play play) {
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
		grid.add(new Text("来源地区:"), 0, 3);
		grid.add(new Text("影片级别:"), 0, 4);
		grid.add(new Text("时长:"), 0, 5);
		grid.add(new Text("开始时间:"), 0, 6);
		grid.add(new Text("结束时间:"), 0, 7);
		grid.add(new Text("票价:"), 0, 8);
		grid.add(new Text("封面"), 0, 9);
		grid.add(new Text(play.getId() + ""), 1, 0);
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
		TextField dura = new TextField(play.getDuration() + "");
		grid.add(dura, 1, 5);
		DatePicker strDate = new DatePicker(play.getStartDate());
		grid.add(strDate, 1, 6);
		DatePicker endDate = new DatePicker(play.getEndDate());
		grid.add(endDate, 1, 7);
		TextField price = new TextField(play.getPrice() + "");
		grid.add(price, 1, 8);
		TextField imgUrl = new TextField(play.getImgUrl());
		grid.add(imgUrl, 1, 9);
		Button save = new Button("保存");
		save.setDefaultButton(true);
		Button ret = new Button("返回");
		HBox hBoxButt = new HBox();
		hBoxButt.setAlignment(Pos.CENTER);
		hBoxButt.setSpacing(50);
		hBoxButt.getChildren().addAll(save, ret);
		outer.getChildren().addAll(grid, hBoxButt);
		save.setOnAction(e -> {
			int duraInt = 0 ,priceInt = 0;
			try{
				duraInt = Integer.valueOf(dura.getText());
				Integer.valueOf(price.getText());
				new Image(imgUrl.getText());
			}catch (Exception n){
				MainFrame.popupMessage("请检查输入！");
				return;
			}
			play.setName(name.getText());
			play.setType(type.getValue());
			play.setArea(area.getText());
			play.setRating(rating.getValue());
			play.setDuration(duraInt);
			play.setStartDate(strDate.getValue());
			play.setEndDate(endDate.getValue());
			play.setPrice(priceInt);
			play.setImgUrl(imgUrl.getText());
			new Thread(new Task<Result>(){
			    @Override
                public Result call() throws Exception{
			        return playSer.modify(play);
                }

                @Override
                protected void running() {
			        LoadingButton.setLoading(save);
                    super.running();
                }
                @Override
                protected void succeeded() {
			        LoadingButton.setNormal(save);
			        Result result = getValue();
                    if(result.isStatus()) {
                        MainFrame.popupMessage("修改成功!");
                    }else {
			            MainFrame.popupMessage("修改失败: "+result.getReasons());
                    }
					query(plays, play);
					super.succeeded();
                }

                @Override
                protected void failed() {
                    LoadingButton.setNormal(save);
                    MainFrame.popupMessage("修改失败: 服务器异常!");
                    super.failed();
                }
            }).start();
		});
		ret.setOnAction(e -> query(plays, play));

		return true;
	}

	public boolean delete(List<model.Play> plays, model.Play play,Button del) {
		new Thread(new Task<Result>() {
			@Override
			public Result call() throws Exception{
				return playSer.delete(play);
			}
			@Override
			public void running(){
				LoadingButton.setLoading(del);
			}
			@Override
			public void succeeded(){
				LoadingButton.setNormal(del);
				Result result = getValue();
				if(result.isStatus()){
					mgtEntry(plays);
					MainFrame.popupMessage("删除成功!");
				}else {
					MainFrame.popupMessage("删除失败: "+result.getReasons());
				}
			}
			@Override
			public void failed(){
				MainFrame.popupMessage("删除失败: 服务器异常!");
			}
		}).start();
		return true;

	}

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
		grid.add(new Text("来源地区:"), 0, 3);
		grid.add(new Text("影片级别:"), 0, 4);
		grid.add(new Text("时长:"), 0, 5);
		grid.add(new Text("开始时间:"), 0, 6);
		grid.add(new Text("结束时间:"), 0, 7);
		grid.add(new Text("票价:"), 0, 8);

		grid.add(new Text(play.getId() + ""), 1, 0);
		grid.add(new Text(play.getName()), 1, 1);
		grid.add(new Text(play.getType().toString()), 1, 2);
		grid.add(new Text(play.getArea()), 1, 3);
		grid.add(new Text(play.getRating().toString()), 1, 4);
		grid.add(new Text(play.getDuration() + ""), 1, 5);
		grid.add(new Text(play.getStartDate().toString()), 1, 6);
		grid.add(new Text(play.getEndDate().toString()), 1, 7);
		grid.add(new Text(play.getPrice() + ""), 1, 8);

		ImageView image = new ImageView(new Image(play.getImgUrl()));
		image.setPreserveRatio(true);

		hBox.getChildren().addAll(image, grid);
		Button mod = new Button("修改");
		Button del = new Button("删除");
		Button ret = new Button("返回");
		del.getStyleClass().add("del-button");
		HBox hBoxButt = new HBox();
		hBoxButt.setAlignment(Pos.CENTER);
		hBoxButt.setSpacing(50);
		hBoxButt.getChildren().addAll(mod, del, ret);
		outer.getChildren().addAll(hBox, hBoxButt);
		mod.setOnAction(e -> {
			modify(plays, play);
		});
		del.setOnAction(e -> {
			ConfirmDel.setConfirmDel(del,ee -> delete(plays,play,del));
		});
		ret.setOnAction(e -> mgtEntry(plays));
		return false;
	}

	public void showList(List<model.Play> plays) {

		// TODO Auto-generated method stub
		/*
		 * for(int i = 0; i<plays.size();i++) { System.out.println(plays); }
		 */
		System.out.println(plays);
	}

}
