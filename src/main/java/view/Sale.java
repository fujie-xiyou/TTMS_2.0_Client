package view;

import java.util.LinkedList;
import java.util.List;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
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
import model.*;
import model.Play;
import model.Schedule;
import model.Studio;
import model.enums.SEAT_STATUS;
import model.enums.TICKET_STATUS;
import nodes.SeatRectangle;
import nodes.TicketImage;
import service.*;
import tools.LoadingButton;
import tools.LoadingModel;
import tools.LoadingPage;

public class Sale {
    TicketSer ticketSer = new TicketSer();

    public void mgtEntry() {
        MainFrame.center.removeAll(MainFrame.center);
        //获取演出计划
        new Thread(new Task<List<Play>>() {
            @Override
            protected List<Play> call() {
                return new PlaySer().fetchAll();
            }

            @Override
            protected void running() {
                LoadingPage.loadingPage(this);
                super.running();
            }

            @Override
            protected void succeeded() {
                List<Play> plays = getValue();
                ScrollPane centerPane = new ScrollPane();
                FlowPane flowPane = new FlowPane();
                flowPane.prefWidthProperty().bind(MainFrame.centerWidth);
                double w = flowPane.getPrefWidth();
                flowPane.setHgap(w / 30);
                flowPane.setVgap(20);
                flowPane.setPadding(new Insets(w / 30));
                centerPane.setFitToWidth(true);
                centerPane.setContent(flowPane);
                for (model.Play play : plays) {
                    VBox vBox = new VBox();
                    ImageView image = new ImageView(new Image(play.getImgUrl(), w / 5, 0, true, true));
                    image.setOnMouseClicked(e -> {
                        showScheduler(play);
                    });
                    Text text = new Text(play.getName());
                    vBox.getChildren().addAll(image, text);
                    flowPane.getChildren().add(vBox);
                }
                MainFrame.center.add(centerPane);
                super.succeeded();
            }
        }).start();


    }

    public void showScheduler(model.Play play) {
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
        grid.add(new Text(play.getType() + "/" + play.getArea()), 1, 1);
        grid.add(new Text(play.getRating().toString()), 1, 2);
        grid.add(new Text(play.getDuration() + ""), 1, 3);
        grid.add(new Text(play.getStartDate() + " 至 " + play.getEndDate()), 1, 4);
        grid.add(new Text(play.getPrice() + ""), 1, 5);

        ImageView image = new ImageView(new Image(play.getImgUrl(), w / 10, 0, true, false));
        image.setPreserveRatio(true);
        Button ret = new Button("返回");
        ret.setOnAction(e -> mgtEntry());
        FlowPane retButtPane = new FlowPane();
        //FlowPane是膨胀式面板，它会占据上级面板所有剩余空间
        retButtPane.setAlignment(Pos.BOTTOM_RIGHT);
        retButtPane.getChildren().add(ret);
        HBox hBox = new HBox();
        //hBox.setAlignment(Pos.BASELINE_LEFT);
        hBox.setSpacing(60);
        hBox.setPadding(new Insets(20));
        hBox.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, null, null)));
        hBox.getChildren().addAll(image, grid, retButtPane);
        ScrollPane schPane = new ScrollPane();
        schPane.setFitToWidth(true);
        outer.getChildren().add(hBox);
        GridPane schListPane = new GridPane();
        schPane.setContent(schListPane);
        //TODO 加载演出计划过程中给个加载图
        new Thread(new Task<List<Schedule>>() {
            @Override
            protected List<Schedule> call() {
                return new ScheduleSer().fetchByPlay(play);
            }

            @Override
            protected void running() {
                outer.getChildren().add(LoadingModel.giveMeALoadingModel());
                super.running();
            }

            @Override
            protected void succeeded() {
                outer.getChildren().remove(1);
                outer.getChildren().add(schPane);
                List<Schedule> schs = getValue();
                //schListPane.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));
                schListPane.setHgap(40);
                schListPane.setVgap(10);
                schListPane.setPadding(new Insets(20));
                //根据剧目ID获得演出计划
                schListPane.add(new Text("演出厅"), 0, 0);
                schListPane.add(new Text("演出时间"), 1, 0);
                schListPane.add(new Text("剩余票数"), 2, 0);
                schListPane.add(new Text("票价"), 3, 0);
                int row = 1;
                if (schs == null) return;
                for (model.Schedule schedule : schs) {
                    schListPane.add(new Text(schedule.getStudio().getName()), 0, row);
                    schListPane.add(new Text(schedule.getDate().toString()), 1, row);
                    schListPane.add(new Text(schedule.getTicketCount() + ""), 2, row);
                    schListPane.add(new Text("￥" + schedule.getPlay().getPrice() + ""), 3, row);
                    Button buy = new Button("购买");
                    FlowPane buyButt = new FlowPane();
                    buyButt.setAlignment(Pos.BASELINE_RIGHT);
                    buyButt.getChildren().add(buy);
                    buy.setOnAction(e -> showTicket(schedule));
                    schListPane.add(buyButt, 4, row);
                    row++;
                }
                super.succeeded();
            }
        }).start();
    }

    public void showTicket(model.Schedule sch) {
        Long timeStamp = null;
        VBox outer = new VBox();
        MainFrame.center.removeAll(MainFrame.center);
        MainFrame.center.add(outer);
        outer.setAlignment(Pos.CENTER);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        outer.getChildren().add(gridPane);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(50);
        outer.setSpacing(30);
        outer.getChildren().add(hBox);
        Button buy = new Button("确认");
        buy.setDefaultButton(true);
        buy.setDisable(true);
        Button cal = new Button("返回");
        cal.setOnAction(e -> showScheduler(sch.getPlay()));
        cal.setCancelButton(true);
        hBox.getChildren().addAll(buy, cal);

        new Thread(new Task<Long>() {
            @Override
            protected Long call() {
                try {
                    new ScheduleSer().getTickets(sch);
                    new SeatSer().fetchAll(sch.getStudio());
                    return new TicketSer().timeStamp();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void running() {
                gridPane.getChildren().add(LoadingModel.giveMeALoadingModel());
                super.running();
            }

            @Override
            protected void succeeded() {
                Long timeStamp = getValue();
                gridPane.getChildren().removeAll(gridPane.getChildren());
                Studio studio = sch.getStudio();
                Seat[][] seats = studio.getSeats();
                Ticket[][] tickets = sch.getTickets();
                buy.setDisable(false);
                List<Ticket> chosedTicket = new LinkedList<>();
                for (int i = 0; i < seats.length; i++) {
                    gridPane.add(new Text((i + 1) + ""), 0, i + 1);
                    for (int j = 0; j < seats[0].length; j++) {
                        gridPane.add(new Text((j + 1) + ""), j + 1, 0);
                        if (!seats[i][j].getStatus().equals(SEAT_STATUS.BROKEN)) {
                            TicketImage seat = new TicketImage(tickets[i][j], chosedTicket, timeStamp);
                            gridPane.add(seat, j + 1, i + 1);
                        }
                    }
                }

                buy.setOnAction(e -> {
                    if (!chosedTicket.isEmpty()) {
                        new Thread(new Task<Void>() {
                            @Override
                            protected Void call() {
                                for (Ticket ticket : chosedTicket) {
                                    if (!ticketSer.lockedTicked(ticket)) chosedTicket.remove(ticket);
                                    else ticket.setStatus(TICKET_STATUS.SOLD);
                                }
                                return null;
                            }

                            @Override
                            protected void running() {
                                LoadingButton.setLoading(buy);
                                super.running();
                            }

                            @Override
                            protected void succeeded() {
                                if (!chosedTicket.isEmpty()) {
                                    new Thread(new Task<Order>() {
                                        @Override
                                        protected Order call() {
                                            return ticketSer.purchaseTickets(chosedTicket);
                                        }

                                        @Override
                                        protected void succeeded() {
                                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("提示");
                                            LoadingButton.setNormal(buy);
                                            Order order = getValue();
                                            if(order != null){
                                                alert.setHeaderText("购买成功！");
                                                StringBuffer msgBuf = new StringBuffer();
                                                msgBuf.append("订单ID： "+ order.getId()+"  总计： ￥"+order.getTotal()+"\n");
                                                for (Ticket ticket : chosedTicket) {
                                                    msgBuf.append("票ID: " + ticket.getId() + "(" + ticket.getSeat_row() + "排" + ticket.getSeat_col() + "列)  单价: ￥"+ticket.getPrice()+"\n");
                                                }
                                                alert.setContentText(msgBuf.toString());
                                            }else {
                                                alert.setHeaderText("购买失败！");
                                                alert.setContentText("请重试！");
                                            }
                                            alert.show();
                                            showTicket(sch);
                                            super.succeeded();
                                        }
                                    }).start();
                                } else {
                                    MainFrame.popupMessage("没有选择有效的票！");
                                    LoadingButton.setNormal(buy);
                                }

                                super.succeeded();
                            }
                        }).start();

                    }
                });

                super.succeeded();
            }

            @Override
            protected void failed() {
                System.out.println("失败了");
                super.failed();
            }
        }).start();

    }

    private void chosed(SeatRectangle seat, List<Ticket> chosedTicket) {
        seat.setOnMouseClicked(e -> {
            seat.setFill(Color.LIMEGREEN);
            chosedTicket.add(seat.getTicket());
            calened(seat, chosedTicket);
        });
    }

    private void calened(SeatRectangle seat, List<Ticket> chosedTicket) {
        seat.setOnMouseClicked(e -> {
            seat.setFill(Color.GRAY);
            chosedTicket.remove(seat.getTicket());
            chosed(seat, chosedTicket);
        });
    }

    public boolean sellTicket(List<Ticket> tickets, List<Seat> seats) {
        // TODO Auto-generated method stub
        return false;
    }

    public void retrurnTicket() {
        // TODO Auto-generated method stub

    }

}
