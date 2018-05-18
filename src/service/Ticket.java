package service;

import service.enums.TICKET_STATUS;

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
	public Ticket(int id, int scheduleID, int seatID, int price, TICKET_STATUS status) {
		super();
		this.id = id;
		this.scheduleID = scheduleID;
		this.seatID = seatID;
		this.price = price;
		this.status = status;
	}
	
	
	
}
