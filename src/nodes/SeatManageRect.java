package nodes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Seat;
import model.enums.SEAT_STATUS;
import java.util.Set;


public class SeatManageRect extends Rectangle {
    private Seat seat;
    private SEAT_STATUS oldStatus;
    private Set<Seat> chosedSeats;
    public Seat getSeat() {
        return seat;
    }
    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    public SeatManageRect(Seat seat , Set<Seat> chosedSeats, double w, double h) {
        super(w, h);
        this.seat = seat;
        this.chosedSeats = chosedSeats;
        this.oldStatus = seat.getStatus();
        this.setColor();
        this.setOnMouseClicked(e -> {
            setSeatStatus();
        });

    }
    public void setSeatStatus(){
        int index = seat.getStatus().getIndex();
        SEAT_STATUS[] statuses = SEAT_STATUS.values();
        int count = statuses.length;
        seat.setStatus(statuses[(index + 1) % count]);
        if(seat.getStatus().equals(oldStatus)) chosedSeats.remove(seat);
        else chosedSeats.add(seat);
        this.setColor();
    }
    public void setColor(){
        if(seat.getStatus().equals(SEAT_STATUS.GOOD)){
            this.setFill(Color.GREEN);
        }else if(seat.getStatus().equals(SEAT_STATUS.BROKEN)){
            this.setFill(Color.RED);
        }else {
            this.setFill(Color.GRAY);
        }
    }


}
