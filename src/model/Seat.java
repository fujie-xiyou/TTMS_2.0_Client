package model;

import model.enums.SEAT_STATUS;

public class Seat implements Cloneable{
	private int id;
	private int studioID;
	private int row;//行号
	private int col;//列号
	private SEAT_STATUS status;//状态

	public Seat(int id, int studioID, int row, int col, SEAT_STATUS status) {
		super();
		this.id = id;
		this.studioID = studioID;
		this.row = row;
		this.col = col;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getstudioID() {
		return studioID;
	}
	public void setstudioID(int studioID) {
		this.studioID = studioID;
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
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Seat seat = (Seat) super.clone();
		return seat;
	}
	public Seat copy(){
		return new Seat(this.getId(),this.getstudioID(),this.getRow(),this.getCol(),this.getStatus());
	}
}
