package service;

import java.security.Provider.Service;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.naming.LinkLoopException;

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
	public static List<service.Schedule> getSchedules(){
		List<service.Schedule> schedules = new LinkedList<>();
		schedules.add(new service.Schedule(1, 1, 1, new Date(), 0));
		return schedules;
	}
	
	public Studio getStudioByID(List<Studio> studios ,int id) {
		//根据ID获取演出厅
		for(Studio studio : studios) {
			if(studio.getId() == id)
				return studio;
		}
		return null;
	}
	public Play getPlayByID(List<Play> plays ,int id) {
		for(Play play : plays) {
			if(play.getId() == id)
				return play;
		}
		return null;
	}
}
