package view;

import java.util.List;

import service.Play;
import service.Seat;
import service.Studio;
import service.Ticket;

public interface SaleIf {
	//入口
	public void mgtEntry(List<Studio> studios,List<service.Play> plays);
	
	//根据剧目ID显示演出计划
	public void showScheduler(List<service.Schedule> schs,List<Studio> studios,List<service.Play> plays,Play play);
	
	//根据计划ID显示演出票
	public void showTicket(List<service.Studio> studios, service.Schedule sch ,List<service.Schedule> schs ,List<service.Play> plays , service.Play play);
	
	//售票
	public boolean sellTicket(List<Ticket> tickets ,List<Seat> seats);
	
	
	//退票
	public void retrurnTicket();

}
