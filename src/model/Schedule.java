package model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import model.enums.TICKET_STATUS;
import service.PlaySer;

public class Schedule {
	//演出计划类型
	private int id;
	private int playID;//剧目id
	private int studioID; //演出厅ID
	private Date date; //演出时间
	private int seatCount;//剩余座位数量
	private Ticket[][] tickets;
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
	public Schedule(int id, int playID, int studioID, Date date) {
		super();
		this.id = id;
		this.playID = playID;
		this.studioID = studioID;
		this.date = date;
	}
	public int getStudioID() {
		return studioID;
	}
	public void setStudioID(int studioID) {
		this.studioID = studioID;
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
	public static List<model.Schedule> getSchedules(){
		int id = 1;
		List<model.Schedule> schedules = new LinkedList<>();
		Schedule schedule = new model.Schedule(1, 1, 1, new Date());
		Studio studio = schedule.getStudioByID(Studio.getStdios(),schedule.getStudioID());
		schedule.setSeatCount(studio.getCount());
		Play play = schedule.getPlayByID(new PlaySer().fetchAll(), schedule.getPlayID());
		Seat[][] seats = studio.getSeats();
		Ticket[][] tickets = new Ticket[studio.getRow()][studio.getCol()];
		for(int i = 0; i < seats.length; i++) {
			for(int j = 0; j < seats[0].length; j++) {
				tickets[i][j] = new Ticket(id, schedule.getId(), studio.getSeats()[i][j].getId(), play.getPrice(), TICKET_STATUS.AVL);
				id++;
			}
		}
		schedule.setTickets(tickets);
		schedules.add(schedule);
		schedule = new model.Schedule(2, 1, 2, new Date());
		studio = schedule.getStudioByID(Studio.getStdios(),schedule.getStudioID());
		schedule.setSeatCount(studio.getCount());
		play = schedule.getPlayByID(new PlaySer().fetchAll(), schedule.getPlayID());
		seats = studio.getSeats();
		tickets = new Ticket[studio.getRow()][studio.getCol()];
		for(int i = 0; i < seats.length; i++) {
			for(int j = 0; j < seats[0].length; j++) {
				tickets[i][j] = new Ticket(id, schedule.getId(), studio.getSeats()[i][j].getId(), play.getPrice(), TICKET_STATUS.AVL);
				id++;
			}
		}
		schedule.setTickets(tickets);
		schedules.add(schedule);
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
	public Ticket[][] getTickets() {
		return tickets;
	}
	public void setTickets(Ticket[][] tickets) {
		this.tickets = tickets;
	}
}
