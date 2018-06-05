package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Seat;
import model.Studio;
import model.enums.SEAT_STATUS;
import nodes.SeatManageRect;
import service.StudioSer;

import java.util.HashSet;
import java.util.Set;
public class SeatView {
    StudioSer studioSer = new StudioSer();
    public void mgtEntry(Studio studio) {
        Seat[][] seats = studio.getSeats();
        if (seats == null) {
            seats = new Seat[studio.getRow()][studio.getCol()];
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    seats[i][j] = new Seat(-1, studio.getId(), i + 1, j + 1, SEAT_STATUS.GOOD);
                }
            }
            //       studio.setSeats(seats);
        }
        Seat[][] seats1 = seats;
        //直接在后面使用seats会报错(在lambda中只能使用类final值???)
        VBox outer = new VBox();
        MainFrame.center.removeAll(MainFrame.center);
        MainFrame.center.add(outer);
        outer.setAlignment(Pos.CENTER);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);
        gridPane.setHgap(30);
        outer.getChildren().add(gridPane);
        Set<Seat> chosedSeats = new HashSet<>();
        for (int i = 0; i < seats.length; i++) {
            gridPane.add(new Text((i + 1) + ""), 0, i + 1);
            for (int j = 0; j < seats[0].length; j++) {
                gridPane.add(new Text((j + 1) + ""), j + 1, 0);
                if (seats[i][j] == null) {
                    seats[i][j] = new Seat(-1, studio.getId(), i, j, SEAT_STATUS.NONE);
                }
                SeatManageRect seat = new SeatManageRect(seats[i][j], chosedSeats, 50, 35);
                gridPane.add(seat, j + 1, i + 1);
            }
        }
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(100);
        outer.setSpacing(50);
        outer.getChildren().add(hBox);
        Button ok = new Button("提交");
        ok.setDefaultButton(true);
        Button cal = new Button("返回");
        cal.setCancelButton(true);
        ok.setOnAction(e -> {
            if(studio.getSeats() == null){
                addAll(seats1,studio);
            }else {
                modify(chosedSeats ,studio);
            }
        });
        cal.setOnAction(e -> {
            return;
        });
        hBox.getChildren().addAll(cal, ok);
    }

    private void addAll(Seat[][] seats,Studio studio) {
        studio.setSeats(seats);
        studioSer.add(studio);
    }

    private void modify(Set<Seat> chosedSeats,Studio studio) {
        studioSer.modify(studio);


    }
}