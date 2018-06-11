package nodes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Seat;
import model.Studio;
import model.enums.SEAT_STATUS;

import java.util.Set;

public class SeatManageImg extends ImageView {
    private Seat seat;
    private SEAT_STATUS oldStatus;
    private Set<Seat> chosedSeats;
    private static Image[] images = {
            new Image("file:Resource/seat/white_none.png",35,35,true,true),
            new Image("file:Resource/seat/green.png",35,35,true,true),
            new Image("file:Resource/seat/red.png",35,35,true,true),
    };
    public SeatManageImg(Studio studio,Seat seat , Set<Seat> chosedSeats){
        this.seat = seat;
        this.oldStatus = seat.getStatus();
        this.chosedSeats = chosedSeats;
        this.setOnMouseClicked(e -> setSeatStatus(studio));
        this.setImage();
    }

    public Seat getSeat() {
        return seat;
    }
    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    public void setSeatStatus(Studio studio) {
        int index = seat.getStatus().getIndex();
        SEAT_STATUS[] statuses = SEAT_STATUS.values();
        int count = statuses.length;
        if (index == SEAT_STATUS.GOOD.getIndex()) studio.setCount(studio.getCount() - 1);
        seat.setStatus(statuses[(++index) % count]);
        if (index == SEAT_STATUS.GOOD.getIndex()) studio.setCount(studio.getCount() + 1);
        if (seat.getStatus().equals(oldStatus)) chosedSeats.remove(seat);
        else chosedSeats.add(seat);
        this.setImage();
    }

    public void setImage(){
        this.setImage(images[seat.getStatus().getIndex()]);
    }

}
