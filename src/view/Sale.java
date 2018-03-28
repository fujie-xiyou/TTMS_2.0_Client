package view;

import java.security.Provider.Service;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import service.Seat;
import service.Ticket;

public class Sale implements SaleIf {

	@Override
	public void mgtEntry(List<service.Studio> studios,List<service.Play> plays) {
		// TODO Auto-generated method stub
		MainFrame.center.removeAll(MainFrame.center);
		ScrollPane centerPane = new ScrollPane();
		FlowPane flowPane = new FlowPane();
		flowPane.prefWidthProperty().bind(MainFrame.centerWidth);
		double w = flowPane.getPrefWidth();
		flowPane.setHgap(w/30);
		flowPane.setVgap(20);
		flowPane.setPadding(new Insets(w/30));
		centerPane.setFitToWidth(true);
		centerPane.setContent(flowPane);
		
		for(service.Play play : plays) {
			VBox vBox = new VBox();
			ImageView image = new ImageView(new Image(play.getImgUrl(), w/5, 0, true, true));
			image.setOnMouseClicked(e -> {
				showScheduler(studios ,plays ,play);
			});
			Text text = new Text(play.getName());
			vBox.getChildren().addAll(image,text);
			flowPane.getChildren().add(vBox);
		}
		MainFrame.center.add(centerPane);
		
	}

	@Override
	public void showScheduler(List<service.Studio> studios,List<service.Play> plays, service.Play play) {
		// TODO Auto-generated method stub
		MainFrame.center.removeAll(MainFrame.center);

		VBox outer = new VBox();
		//outer.setSpacing(40);
		outer.setAlignment(Pos.BASELINE_LEFT);
		//outer.setPadding(new Insets(30));
		MainFrame.center.add(outer);
		
		double w = MainFrame.centerWidth.getValue();

		GridPane grid = new GridPane();
		grid.setHgap(10);
	    grid.setVgap(0);
		
		grid.add(new Text("剧目名称:"), 0, 0);
		grid.add(new Text("类型/地区:"), 0, 1);
		grid.add(new Text("级别:"), 0, 2);
		grid.add(new Text("时长:"), 0, 3);
		grid.add(new Text("时间:"), 0, 4);
		grid.add(new Text("票价:"), 0, 5);
		
		grid.add(new Text(play.getName()), 1, 0);
		grid.add(new Text(play.getType()+"/"+play.getArea()), 1, 1);
		grid.add(new Text(play.getRating().toString()), 1, 2);
		grid.add(new Text(play.getDuration()+""), 1, 3);
		grid.add(new Text(play.getStartDate()+" 至 "+play.getEndDate()), 1, 4);
		grid.add(new Text(play.getPrice()+""), 1, 5);
		
		ImageView image = new ImageView( new Image(play.getImgUrl(),w/10,0,true,false));
		image.setPreserveRatio(true);
		Button ret = new Button("返回");
		ret.getStyleClass().add("my-button");
		ret.setOnAction(e -> mgtEntry(studios ,plays));
		FlowPane retButtPane = new FlowPane();
		//FlowPane是膨胀式面板，它会占据上级面板所有剩余空间
		retButtPane.setAlignment(Pos.BOTTOM_RIGHT);
		retButtPane.getChildren().add(ret);
		HBox hBox = new HBox();
		//hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(60);
		hBox.setPadding(new Insets(20));
		hBox.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE	, null, null)));
		hBox.getChildren().addAll(image,grid,retButtPane);
		ScrollPane schPane = new ScrollPane();
		outer.getChildren().addAll(hBox,schPane);
		GridPane schListPane = new GridPane();
		
		schPane.setContent(schListPane);
		schListPane.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));
		//根据剧目ID获得演出计划
		List<service.Schedule> schs = null; 
		schListPane.add(new Text("演出厅"), 0, 0);
		schListPane.add(new Text("演出时间")	,1,0);
		schListPane.add(new Text("剩余票数"), 2, 0);
		schListPane.add(new Text("票价"), 3, 0);
		int row = 1;
		for(service.Schedule schedule : schs) {
			schListPane.add(new Text(schedule.getStudioByID(studios, schedule.getStudioID()).getName()),0,row);
			schListPane.add(new Text(schedule.getDate().toString()), 1, row);
			schListPane.add(new Text(schedule.getSeatCount()+""), 2, row);
			schListPane.add(new Text("￥"+schedule.getPlayByID(plays, schedule.getId()).getPrice()+""), 3, row);
			Button buy = new Button("购买");
			buy.getStyleClass().add("my-button");
			schListPane.add(buy, 4, row);
		}

	}

	@Override
	public void showTicket(service.Schedule sch) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean sellTicket(List<Ticket> tickets, List<Seat> seats) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void retrurnTicket() {
		// TODO Auto-generated method stub

	}

}
