package model;
import model.enums.TICKET_STATUS;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Schedule {
	//演出计划类型
	private int id;
	private Play play;//剧目id
	private Studio studio; //演出厅ID
	private LocalDate date; //演出时间
	private int ticketCount;//剩余座位数量
	private Ticket[][] tickets;
	public int getId() {
		return id;
	}

	public Play getPlay() {
		return play;
	}

	public void setPlay(Play play) {
		this.play = play;
	}

	public Studio getStudio() {
		return studio;
	}

	public void setStudio(Studio studio) {
		this.studio = studio;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Schedule(int id, Play play, Studio studio, LocalDate date,int ticketCount) {
		super();
		this.id = id;
		this.play = play;
		this.studio = studio;
		this.date = date;
		this.ticketCount = ticketCount;
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
		this.play = schedule.play;
		this.studio = schedule.studio;
		this.date = schedule.date;
		this.ticketCount = schedule.ticketCount;
		this.ticketCount = schedule.ticketCount;
	}
//	public static List<Schedule> getSchedules(){
//		int id = 1;
//		List<Schedule> schedules = new LinkedList<>();
//		Schedule schedule = new Schedule(1, 1, 1, LocalDate.now(),18);
//		Studio studio = schedule.getStudioByID(Studio.getStdios(),schedule.getStudio());
//		schedule.setTicketCount(studio.getCount());
//		Play play = schedule.getPlayByID(null, schedule.getPlayID());
//		Seat[][] seats = studio.getSeats();
//		Ticket[][] tickets = new Ticket[studio.getRow()][studio.getCol()];
//		for(int i = 0; i < seats.length; i++) {
//			for(int j = 0; j < seats[0].length; j++) {
//				tickets[i][j] = new Ticket(id, schedule.getId(), studio.getSeats()[i][j].getId(), play.getPrice(), TICKET_STATUS.AVL);
//				id++;
//			}
//		}
//		schedule.setTickets(tickets);
//		schedules.add(schedule);
//		schedule = new Schedule(2, 1, 2, LocalDate.now(),18);
//		studio = schedule.getStudioByID(Studio.getStdios(),schedule.getStudioID());
//		schedule.setTicketCount(studio.getCount());
//		play = schedule.getPlayByID(null, schedule.getPlayID());
//		seats = studio.getSeats();
//		tickets = new Ticket[studio.getRow()][studio.getCol()];
//		for(int i = 0; i < seats.length; i++) {
//			for(int j = 0; j < seats[0].length; j++) {
//				tickets[i][j] = new Ticket(id, schedule.getId(), studio.getSeats()[i][j].getId(), play.getPrice(), TICKET_STATUS.AVL);
//				id++;
//			}
//		}
//		schedule.setTickets(tickets);
//		schedules.add(schedule);
//		return schedules;
//	}

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
