package view;

import javafx.event.Event;
import model.Seat;
import service.SeatSer;
import service.StudioSer;
import tools.ConfirmDel;
import tools.LoadingButton;
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
import model.Result;
import nodes.TopButton;
import tools.LoadingPage;

public class Studio {
    private StudioSer studioSer = new StudioSer();

    public void mgtEntry() {
        MainFrame.center.removeAll(MainFrame.center);
        MainFrame.top.removeAll(MainFrame.top);

        new Thread(new Task<List<model.Studio>>() {
            @Override
            public List<model.Studio> call() {
                return studioSer.fetchAll();
            }

            @Override
            public void running() {
                LoadingPage.loadingPage(this);
                super.running();
            }

            @Override
            public void succeeded() {
                MainFrame.center.removeAll(MainFrame.center);
                TopButton add = new TopButton("添加演出厅");
                MainFrame.top.add(add);
                List<model.Studio> studios = getValue();
                add.setOnAction(e -> {
                    add.recover();//初始化按钮以及界面 并且恢复上一个按钮的事件以及属性
                    add();//调用添加用户面板
                });

                GridPane centerPane = new GridPane();
                centerPane.setVgap(20*2);
                centerPane.setHgap(30*3);
                centerPane.setPadding(new Insets(30,30,30,90));
                centerPane.add(new Text("演出厅ID"), 0, 0);
                centerPane.add(new Text("演出厅名字"), 1, 0);
                centerPane.add(new Text("座位总数"), 2, 0);

                int row = 1;
                for (model.Studio studio : studios) {
                    centerPane.add(new Text(studio.getId() + ""), 0, row);
                    centerPane.add(new Text(studio.getName()), 1, row);
                    centerPane.add(new Text(studio.getCount() + ""), 2, row);
                    Button mod = new Button("修改"), del = new Button("删除");
                    del.getStyleClass().add("del-button");
                    mod.setOnAction(e -> modify(studio));
                    del.setOnAction(e -> {
                        ConfirmDel.setConfirmDel(del, ee -> delete(studio, del));
                    });
                    centerPane.add(mod, 3, row);
                    centerPane.add(del, 4, row);
			/*		System.out.println("row="+row);
					System.out.println("count="+studio.getCount());*/
                    row = row + 1;

                }
                MainFrame.center.add(centerPane);
            }
        }).start();
    }

    public void add() {

        VBox centerPane = new VBox();
        /*centerPane.setFillWidth(true);*/
        centerPane.prefWidthProperty().bind(MainFrame.centerWidth);
        double width = centerPane.getPrefWidth();
        centerPane.setPadding(new Insets(30, width * 2.0 / 7, 0, width * 2.0 / 7));
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
    	/*Label  count= new Label("座位总数：");
    	TextField Count = new TextField();
    	Count.setPromptText("座位总个数");*/
        Button next = new Button("下一步");
        next.setDefaultButton(true);
        Button cla = new Button("返回");
        cla.setCancelButton(true);
        HBox hBox = new HBox(next, cla);
        hBox.setSpacing(80);
        hBox.setAlignment(Pos.CENTER);
        centerPane.getChildren().addAll(text, name, nameField, row, Row, col, Col, hBox);
        centerPane.setSpacing(30);

        next.setOnAction(e -> {
            if (!nameField.getText().isEmpty() && !Row.getText().isEmpty() && !Col.getText().isEmpty()) {
                try {
                    Integer.parseInt(Row.getText());
                    Integer.parseInt(Col.getText());
                }catch (NumberFormatException n){
                    MainFrame.popupMessage("请检查输入!");
                    return;
                }
                model.Studio studio = new model.Studio();
                studio.setId(-1);
                studio.setName(nameField.getText());
                studio.setRow(Integer.parseInt(Row.getText()));
                studio.setCol(Integer.parseInt(Col.getText()));
                studio.setCount(Integer.parseInt(Row.getText()) * Integer.parseInt(Col.getText()));
                new SeatView().mgtEntry(studio, centerPane);

            } else {
                MainFrame.popupMessage("请检查输入!");
            }

        });
        cla.setOnAction(e -> mgtEntry());

    }

    public void modify(model.Studio studio) {
        MainFrame.center.removeAll(MainFrame.center);
        VBox vBox = new VBox();
        vBox.prefWidthProperty().bind(MainFrame.centerWidth);
        double width = vBox.getPrefWidth();
        vBox.setPadding(new Insets(30, width * 2.0 / 7, 0, width * 2.0 / 7));
        MainFrame.center.add(vBox);
        Text text = new Text("修改演出厅:");
        text.setFill(Color.DARKGRAY);
        text.setFont(new Font(30));
        Label name = new Label("演出厅名字:");
        TextField Name = new TextField(studio.getName());
        Label row = new Label("座位行数:");
        TextField Row = new TextField(studio.getRow() + "");
        Label col = new Label("座位列数:");
        TextField Col = new TextField(studio.getCol() + "");
        Label count = new Label("座位总数");
        Text Count = new Text(studio.getCount() + "");
        Button go = new Button("下一步");
        go.setDefaultButton(true);
        Button rtn = new Button("返回");
        HBox hBox = new HBox(go, rtn);
        hBox.setSpacing(50);
        hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text, name, Name, row, Row, col, Col, count, Count, hBox);
        vBox.setSpacing(30);


        go.setOnAction(e -> {
            model.Studio newStudio = new model.Studio();
            newStudio.copyFrom(studio);
            newStudio.setName(Name.getText());
            int newRow = Integer.valueOf(Row.getText());
            int newCol = Integer.valueOf(Col.getText());
            if (newCol != newStudio.getCol() || newRow != newStudio.getRow()) {
                newStudio.setRow(newRow);
                newStudio.setCol(newCol);
                newStudio.setCount(newRow * newCol);
                newStudio.setSeats(null);
                new SeatView().mgtEntry(newStudio, vBox, studio);
            }else {
                new Thread(new Task<Result>() {
                    @Override
                    protected Result call() {
                        return new SeatSer().fetchAll(newStudio);
                    }

                    @Override
                    protected void running() {
                        LoadingButton.setLoading(go);
                        super.running();
                    }

                    @Override
                    protected void succeeded() {
                        LoadingButton.setNormal(go);
                        new SeatView().mgtEntry(newStudio, vBox, studio);
                        super.succeeded();
                    }

                    @Override
                    protected void failed() {
                        LoadingButton.setNormal(go);
                        MainFrame.popupMessage("座位获取失败: 服务器异常");
                        super.failed();
                    }
                }).start();
            }
        });
        rtn.setOnAction(e -> mgtEntry());

    }

    public boolean delete(model.Studio studio, Button del) {

        new Thread(new Task<Result>() {
            @Override
            public Result call() throws Exception {
                return studioSer.delete(studio);
            }

            @Override
            public void running() {
                LoadingButton.setLoading(del);
            }

            @Override
            public void succeeded() {
                LoadingButton.setNormal(del);
                Result result = getValue();
                if (result.isStatus()) {
                    mgtEntry();
                    MainFrame.popupMessage("删除成功!");
                } else {
                    MainFrame.popupMessage("删除失败: " + result.getReasons());
                }
            }

            @Override
            public void failed() {
                MainFrame.popupMessage("删除失败: 服务器异常!");
            }
        }).start();
        return true;
    }

}
