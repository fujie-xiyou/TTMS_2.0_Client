package model;

import model.enums.SEAT_STATUS;

public class Seat {
	public Seat(int id, int roomID, int row, int col, SEAT_STATUS status) {
		super();
		this.id = id;
		this.roomID = roomID;
		this.row = row;
		this.col = col;
		this.status = status;
	}
	private int id;
	private int roomID;
	private int row;//行号
	private int col;//列号
	private SEAT_STATUS status;//状态
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public SEAT_STATUS getStatus() {
		return status;
	}
	public void setStatus(SEAT_STATUS status) {
		this.status = status;
	}
	
}
