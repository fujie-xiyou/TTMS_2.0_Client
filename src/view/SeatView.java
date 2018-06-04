package view;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Seat;
import model.Studio;
import model.Ticket;
import model.enums.SEAT_STATUS;
import model.enums.TICKET_STATUS;
import nodes.SeatRectangle;

import java.util.LinkedList;
import java.util.List;

public class SeatView {
    public void mgtEntry(Studio studio) {
        if (studio.getSeats() == null) {
            Seat[][] seats = new Seat[studio.getRow()][studio.getCol()];
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    seats[i][j] = new Seat(-1, studio.getId(), i + 1, j + 1, SEAT_STATUS.GOOD);
                }
            }
            studio.setSeats(seats);
        }
        Seat[][] seats = studio.getSeats();
        VBox outer = new VBox();
        MainFrame.center.removeAll(MainFrame.center);
        MainFrame.center.add(outer);
        outer.setAlignment(Pos.CENTER);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        outer.getChildren().add(gridPane);
        List<Seat> chosedSeat = new LinkedList<>();
        for (int i = 0; i < seats.length; i++) {
            gridPane.add(new Text((i + 1) + ""), 0, i + 1);
            for (int j = 0; j < seats[0].length; j++) {
                gridPane.add(new Text((j + 1) + ""), j + 1, 0);
                if (!seats[i][j].getStatus().equals(SEAT_STATUS.BROKEN)) {
                    SeatRectangle seat = new SeatRectangle(seats[i][j], 30, 20);
                    seat.setFill(Color.GREEN);
                    chosed(seat,chosedSeat);
                    gridPane.add(seat, j + 1, i + 1);

                }
            }
        }

    }
    private void chosed(SeatRectangle seat ,List<Seat> chosedSeat) {
        seat.setOnMouseClicked(e -> {
            seat.setFill(Color.LIMEGREEN);
            chosedSeat.add(seat.getSeat());
            calened(seat, chosedSeat);
        });
    }
    private void calened(SeatRectangle seat , List<Seat> chosedSeat) {
        seat.setOnMouseClicked(e -> {
            seat.setFill(Color.WHITE);
            chosedSeat.remove(seat.getSeat());
            chosed(seat, chosedSeat);
        });
    }
}
