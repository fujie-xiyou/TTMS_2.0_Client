package model;
import model.enums.TICKET_STATUS;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Schedule {
	//演出计划类型
	private int id;
	private int playID;//剧目id
	private int studioID; //演出厅ID
	private LocalDate date; //演出时间
	private int ticketCount;//剩余座位数量
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
	public Schedule(int id, int playID, int studioID, LocalDate date,int ticketCount) {
		super();
		this.id = id;
		this.playID = playID;
		this.studioID = studioID;
		this.date = date;
		this.ticketCount = ticketCount;
	}
	public int getStudioID() {
		return studioID;
	}
	public void setStudioID(int studioID) {
		this.studioID = studioID;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getTicketCount() {
		return ticketCount;
	}
	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}
	public Ticket[][] getTickets() {
		return tickets;
	}
	public void setTickets(Ticket[][] tickets) {
		this.tickets = tickets;
	}
	public void copyFrom(Schedule schedule){
		this.id = schedule.id;
		this.playID = schedule.playID;
		this.studioID = schedule.studioID;
		this.date = schedule.date;
		this.ticketCount = schedule.ticketCount;
		this.ticketCount = schedule.ticketCount;
	}
	public static List<Schedule> getSchedules(){
		int id = 1;
		List<Schedule> schedules = new LinkedList<>();
		Schedule schedule = new Schedule(1, 1, 1, LocalDate.now(),18);
		Studio studio = schedule.getStudioByID(Studio.getStdios(),schedule.getStudioID());
		schedule.setTicketCount(studio.getCount());
		Play play = schedule.getPlayByID(null, schedule.getPlayID());
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
		schedule = new Schedule(2, 1, 2, LocalDate.now(),18);
		studio = schedule.getStudioByID(Studio.getStdios(),schedule.getStudioID());
		schedule.setTicketCount(studio.getCount());
		play = schedule.getPlayByID(null, schedule.getPlayID());
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

}
