package service;
enum SEAT_STATUS{
	NONE(1,"空位"),
	GOOD(2,"有座位"),
	BROKEN(3,"损坏");
	
	private String name;
	private int index;
	private SEAT_STATUS(int index , String name) {
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

public class Seat {
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
