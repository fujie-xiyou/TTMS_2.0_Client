package view;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.*;
import model.Account;
import model.Play;
import model.Schedule;
import model.enums.ORDER_STATUS;
import model.enums.ORDER_TYPE;
import service.*;
import sun.applet.Main;
import tools.LoadingButton;
import tools.LoadingModel;


public class ReturnTicketView {
    public void mgtEnter() {
        MainFrame.center.removeAll(MainFrame.center);
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20,400,20,400));
        pane.setSpacing(50);
        MainFrame.center.add(pane);
        TextField text = new TextField();
        text.setPromptText("请输入订单ID");
        Button ok = new Button("查找");
        pane.getChildren().addAll(text,ok);
        ok.setDefaultButton(true);
        ok.setOnAction(e -> {
            String idString = text.getText();
            int id;
            try {
                id = Integer.valueOf(idString);
            } catch (NumberFormatException n) {
                MainFrame.popupMessage("输入不合法");
                return;
            }
            new Thread(new Task<Order>() {
                @Override
                protected Order call() {
                    return new OrderSer().fetchByID(id);
                }

                @Override
                protected void running() {
                    LoadingButton.setLoading(ok);
                    super.running();
                }

                @Override
                protected void succeeded() {
                    LoadingButton.setNormal(ok);
                    if (getValue() == null) MainFrame.popupMessage("订单ID不存在");
                    else if(getValue().getType().equals(ORDER_TYPE.REFUND)) MainFrame.popupMessage("订单不是有效的购票订单！");
                    else showOrder(getValue());
                    super.succeeded();
                }
            }).start();
        });
    }

    public void showOrder(Order order) {
        MainFrame.center.removeAll(MainFrame.center);
        new Thread(new Task<String[]>() {
            @Override
            protected String[] call () throws Exception{
                Account account = new AccountSer().fetchByID(order.getUid());
                Ticket ticket = new TicketSer().fetchByID(order.getItems().get(0).getTicketID());
                Schedule schedule = new ScheduleSer().fetchByID(ticket.getScheduleID());
                Play play = schedule.getPlay();
                String[] strings = {account.getUsername(), play.getName()};
                return strings;
            }

            @Override
            protected void running() {
                super.running();
                MainFrame.center.add(LoadingModel.giveMeALoadingModel());
            }

            @Override
            protected void succeeded() {
                MainFrame.center.removeAll(MainFrame.center);
                String[] strings = getValue();
                GridPane pane = new GridPane();
                MainFrame.center.add(pane);
                pane.setAlignment(Pos.CENTER);
                pane.setVgap(30);
                pane.setHgap(30 * 2);
                pane.add(new Text("订单ID："), 0, 0);
                pane.add(new Text(order.getId() + ""), 1, 0);
                pane.add(new Text("剧目名称： "),0,1);
                pane.add(new Text(strings[1]),1,1);
                pane.add(new Text("订单时间： "), 0, 2);
                pane.add(new Text(order.getDateTime().toString().replace('T',' ')), 1,2);
                pane.add(new Text("订单类型： "), 0, 3);
                pane.add(new Text(order.getType().toString()), 1, 3);
                pane.add(new Text("订单状态"), 0, 4);
                Text status = new Text(order.getStatus().toString());
                status.setFill(Color.GREEN);
                pane.add(status, 1, 4);
                pane.add(new Text("订单金额： "), 0, 5);
                pane.add(new Text("￥ " + order.getTotal()), 1, 5);
                pane.add(new Text("创建人： "),0,6);
                pane.add(new Text(strings[0]),1,6);
                Button ok = new Button("退票");
                ok.setDefaultButton(true);
                if (order.getType().equals(ORDER_TYPE.REFUND) || !order.getStatus().equals(ORDER_STATUS.TBU)) {
                    ok.setDisable(true);
                }
                Button cal = new Button("返回");
                pane.add(ok, 0, 8);
                pane.add(cal, 1, 8);
                ok.setOnAction(e -> {
                    new Thread(new Task<Result>() {
                        @Override
                        protected Result call() {
                            return new TicketSer().returnTickets(order);
                        }

                        @Override
                        protected void running() {
                            LoadingButton.setLoading(ok);
                            super.running();
                        }

                        @Override
                        protected void succeeded() {
                            LoadingButton.setNormal(ok);
                            showOrder(new OrderSer().fetchByID(order.getId()));

                            MainFrame.popupMessage("退票成功！总计退款 ￥ "+order.getTotal(),3000);
                            super.succeeded();
                        }

                        @Override
                        protected void failed() {
                            LoadingButton.setNormal(ok);
                            MainFrame.popupMessage("退票失败！");
                            super.failed();
                        }
                    }).start();
                });
                cal.setOnAction(e -> {
                    mgtEnter();
                });

                super.succeeded();
            }

            @Override
            protected void failed() {
                mgtEnter();
                MainFrame.popupMessage("订单异常！");
                getException().printStackTrace();
                super.failed();
            }
        }).start();


    }
}
