package view;

import java.util.List;

import service.Seat;
import service.Ticket;

public interface SaleIf {
	//入口
	public void mgtEntry();
	
	//根据剧目ID显示演出计划
	public void showScheduler(int playID);
	
	//根据计划ID显示演出票
	public void showTicket(int schID);
	
	//售票
	public boolean sellTicket(List<Ticket> tickets ,List<Seat> seats);
	
	
	//退票
	public void retrurnTicket();
}
