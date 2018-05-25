package iview;

import java.util.List;

import model.Play;
import model.Seat;
import model.Studio;
import model.Ticket;

public interface SaleIf {
	//入口
	public void mgtEntry(List<Studio> studios,List<model.Play> plays);
	
	//根据剧目ID显示演出计划
	public void showScheduler(List<model.Schedule> schs,List<Studio> studios,List<model.Play> plays,Play play);
	
	//根据计划ID显示演出票
	public void showTicket(List<model.Studio> studios, model.Schedule sch ,List<model.Schedule> schs ,List<model.Play> plays , model.Play play);
	
	//售票
	public boolean sellTicket(List<Ticket> tickets ,List<Seat> seats);
	
	
	//退票
	public void retrurnTicket();

}
