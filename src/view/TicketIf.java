package view;

import service.Schedule;

public interface TicketIf {
	public boolean update(int id);
	
	public boolean query(int id);
	
	public void list();
	
	//根据ID打印票
	public void print(int id);
	
	public void listBySch(Schedule schedule);
}
