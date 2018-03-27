package service;

import java.util.Date;

public class Schedule {
	//演出计划类型
	private int id;
	private int playID;//剧目id
	private int StudioID; //演出厅ID
	private Date date; //演出时间
	private int seatCount;//座位数量
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlayID() {
		return playID;
	}
	public void setPlayID(int playID) {
		this.playID = playID;
	}
	public Schedule(int id, int playID, int studioID, Date date, int seatCount) {
		super();
		this.id = id;
		this.playID = playID;
		StudioID = studioID;
		this.date = date;
		this.seatCount = seatCount;
	}
	public int getStudioID() {
		return StudioID;
	}
	public void setStudioID(int studioID) {
		StudioID = studioID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}
	
	
}
