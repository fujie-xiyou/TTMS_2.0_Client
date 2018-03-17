package service;

enum TICKET_STATUS{
	AVL(1,"待售"),
	SOLD(2,"已售"),
	RESV(3,"预留");
	
	private String name;
	private int index;
	private TICKET_STATUS(int index , String name) {
		this.index = index;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public int getIndex() {
		return index;
	}
}

public class Ticket {
	private int id;
	private int scheduleID;
	private int seatID;
	private int price;
	private TICKET_STATUS status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}
	public int getSeatID() {
		return seatID;
	}
	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public TICKET_STATUS getStatus() {
		return status;
	}
	public void setStatus(TICKET_STATUS status) {
		this.status = status;
	}
	
	
}
