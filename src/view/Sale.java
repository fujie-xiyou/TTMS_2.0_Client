package view;

import java.util.LinkedList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Seat;
import model.Ticket;
import model.enums.SEAT_STATUS;
import model.enums.TICKET_STATUS;
import nodes.SeatRectangle;

public class Sale implements SaleIf {

	@Override
	public void mgtEntry(List<model.Studio> studios,List<model.Play> plays) {
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
		List<model.Schedule> schs = model.Schedule.getSchedules();
		for(model.Play play : plays) {
			VBox vBox = new VBox();
			ImageView image = new ImageView(new Image(play.getImgUrl(), w/5, 0, true, true));
			image.setOnMouseClicked(e -> {
				showScheduler(schs,studios ,plays ,play);
			});
			Text text = new Text(play.getName());
			vBox.getChildren().addAll(image,text);
			flowPane.getChildren().add(vBox);
		}
		MainFrame.center.add(centerPane);
		
	}

	@Override
	public void showScheduler(List<model.Schedule> schs,List<model.Studio> studios,List<model.Play> plays, model.Play play) {
		// TODO Auto-generated method stub
		MainFrame.center.removeAll(MainFrame.center);
		MainFrame.bottom.removeAll(MainFrame.bottom);
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
		//hBox.setAlignment(Pos.BASELINE_LEFT);
		hBox.setSpacing(60);
		hBox.setPadding(new Insets(20));
		hBox.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE	, null, null)));
		hBox.getChildren().addAll(image,grid,retButtPane);
		ScrollPane schPane = new ScrollPane();
		schPane.setFitToWidth(true);
		outer.getChildren().addAll(hBox,schPane);
		GridPane schListPane = new GridPane();
		
		schPane.setContent(schListPane);
		//schListPane.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));
		schListPane.setHgap(40);
		schListPane.setVgap(10);
		schListPane.setPadding(new Insets(20));
		//根据剧目ID获得演出计划
		schListPane.add(new Text("演出厅"), 0, 0);
		schListPane.add(new Text("演出时间")	,1,0);
		schListPane.add(new Text("剩余票数"), 2, 0);
		schListPane.add(new Text("票价"), 3, 0);
		int row = 1;
		for(model.Schedule schedule : schs) {
			schListPane.add(new Text(schedule.getStudioByID(studios, schedule.getStudioID()).getName()),0,row);
			schListPane.add(new Text(schedule.getDate().toString()), 1, row);
			schListPane.add(new Text(schedule.getSeatCount()+""), 2, row);
			schListPane.add(new Text("￥"+schedule.getPlayByID(plays, schedule.getId()).getPrice()+""), 3, row);
			Button buy = new Button("购买");
			buy.getStyleClass().add("my-button");
			FlowPane buyButt = new FlowPane();
			buyButt.setAlignment(Pos.BASELINE_RIGHT);
			buyButt.getChildren().add(buy);
			buy.setOnAction(e -> showTicket(studios,schedule,schs,plays,play));
			schListPane.add(buyButt, 4, row);
			row++;
		}

	}

	@Override
	public void showTicket(List<model.Studio> studios, model.Schedule sch ,List<model.Schedule> schs ,List<model.Play> plays , model.Play play) {
		// TODO Auto-generated method stub
		model.Studio studio = sch.getStudioByID(studios, sch.getStudioID());
		model.Seat[][] seats = studio.getSeats();
		VBox outer = new VBox();
		MainFrame.center.removeAll(MainFrame.center);
		MainFrame.center.add(outer);
		outer.setAlignment(Pos.CENTER);
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(20);
		gridPane.setHgap(20);
		outer.getChildren().add(gridPane);
		List<model.Ticket> chosedTicket = new LinkedList<>();
		for(int i= 0; i< seats.length; i++) {
			gridPane.add(new Text((i+1)+""), 0, i+1);
			for(int j=0; j < seats[0].length; j++) {
				gridPane.add(new Text((j+1)+""), j+1, 0);
				if(!seats[i][j].getStatus().equals(SEAT_STATUS.BROKEN)) {
					SeatRectangle seat = new SeatRectangle(seats[i][j],30, 20);
					seat.setTicket(sch.getTickets()[i][j]);
					if(seat.getTicket().getStatus().equals(TICKET_STATUS.AVL)) {
						seat.setFill(Color.WHITE);
						chosed(seat,chosedTicket);
					}else {
						seat.setFill(Color.RED);
					}
					gridPane.add(seat, j+1, i+1);
					
				}
			}
		}
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(50);
		outer.setSpacing(30);
		outer.getChildren().add(hBox);
		Button buy = new Button("确认");
		buy.getStyleClass().add("my-button");
		Button cal = new Button("返回");
		cal.getStyleClass().add("my-button");
		hBox.getChildren().addAll(buy,cal);
		buy.setOnAction(e -> {
			if(!chosedTicket.isEmpty()) {
				StringBuffer msg = new StringBuffer("出票成功! 票ID列表: ");
				for(Ticket ticket : chosedTicket) {
					ticket.setStatus(TICKET_STATUS.SOLD);
					msg.append(ticket.getId()+",");
					showTicket(studios, sch,schs,plays,play);
				}
				MainFrame.popupMessage(msg.toString());
			}
		});
		cal.setOnAction(e -> showScheduler(schs,studios,plays,play));
	}
	private void chosed(SeatRectangle seat ,List<Ticket> chosedTicket) {
		seat.setOnMouseClicked(e -> {
			seat.setFill(Color.LIMEGREEN);
			chosedTicket.add(seat.getTicket());
			calened(seat, chosedTicket);
		});
	}
	private void calened(SeatRectangle seat , List<Ticket> chosedTicket) {
		seat.setOnMouseClicked(e -> {
			seat.setFill(Color.WHITE);
			chosedTicket.remove(seat.getTicket());
			chosed(seat, chosedTicket);
		});
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
