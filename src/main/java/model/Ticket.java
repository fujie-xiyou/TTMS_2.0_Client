package model;

import model.enums.TICKET_STATUS;


public class Ticket {
	public static final int lockTime = 60 * 1;
	private int id;
	private int scheduleID;
	private int price;
	private int seat_row;
	private int seat_col;
	private TICKET_STATUS status;
	private int lockedUID;
	private long lockedTime;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public static int getLockTime() {
		return lockTime;
	}

	public int getSeat_row() {
		return seat_row;
	}

	public void setSeat_row(int seat_row) {
		this.seat_row = seat_row;
	}

	public int getSeat_col() {
		return seat_col;
	}

	public void setSeat_col(int seat_col) {
		this.seat_col = seat_col;
	}

	public TICKET_STATUS getStatus() {
		return status;
	}

	public int getLockedUID() {
		return lockedUID;
	}

	public void setLockedUID(int lockedUID) {
		this.lockedUID = lockedUID;
	}

	public long getLockedTime() {
		return lockedTime;
	}

	public void setLockedTime(long lockedTime) {
		this.lockedTime = lockedTime;
	}

	public void setStatus(TICKET_STATUS status) {
		this.status = status;
	}
	public Ticket(int id, int scheduleID, int price, TICKET_STATUS status,int lockedUID ,long lockedTime) {
		super();
		this.id = id;
		this.scheduleID = scheduleID;
		this.price = price;
		this.status = status;
		this.setLockedUID(lockedUID);
		this.setLockedTime(lockedTime);
	}
}