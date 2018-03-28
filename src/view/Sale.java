package view;

import java.util.List;
import javafx.scene.layout.FlowPane;
import service.Seat;
import service.Ticket;

public class Sale implements SaleIf {

	@Override
	public void mgtEntry(List<Play> plays, List<Schedule> schs) {
		// TODO Auto-generated method stub
		FlowPane centerPane = new FlowPane();
		
	}

	@Override
	public void showScheduler(int playID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showTicket(int schID) {
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
