package nodes;

import javafx.scene.shape.Rectangle;
import service.Seat;
import service.Ticket;

public class SeatRectangle extends Rectangle {
	private Seat seat;
	private Ticket ticket;
	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	public SeatRectangle(Seat seat ,double w, double h) {
		super(w, h);
		this.seat = seat;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
}
