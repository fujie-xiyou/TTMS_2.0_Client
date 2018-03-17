package view;

import service.Seat;

public interface SeatIf {
	//座位管理接口
	
	//座位管理主界面
	public void mgtEntry(int roomID);
	
	public boolean add(Seat seat);
	
	public boolean modify(Seat seat);
	
	public boolean delete(Seat seat);
	
}
