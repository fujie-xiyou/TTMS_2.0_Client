package service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/*enum PLAY_TYPE {
	FILE(1,"电影"),
	OPEAR(2,"歌剧"),
	CONCERT(3,"音乐会");
	
	private String name;
	private int index;
	private PLAY_TYPE(int index , String name) {
		this.index = index;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public int getIndex() {
		return index;
	}
}*/

/*enum PLAY_RATING{
	//演出级别
	CHILD,//儿童可观看
	TEENAGE,//青少年可观看
	ADULT //成人可观看
}*/

public class Play {
	private int id;
	private String name;
	private PLAY_TYPE type;
	private String area; //来源地区
	private PLAY_RATING rating;//级别
	private int duration;//演出时长(分钟)
	private LocalDate startDate;//开始日期
	private LocalDate endDate;//结束日期
	private int price;
	
	public Play(int id , String name, PLAY_TYPE type , String area,PLAY_RATING rating, int duration,
			LocalDate startDate,LocalDate endDate,int price) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.area = area;
		this.rating = rating;
		this.duration = duration;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PLAY_TYPE getType() {
		return type;
	}
	public void setType(PLAY_TYPE type) {
		this.type = type;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public PLAY_RATING getRating() {
		return rating;
	}
	public void setRating(PLAY_RATING rating) {
		this.rating = rating;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<Play> getPlays() {
		List<Play> plays = new LinkedList<>();
		
		plays.add(new Play(1, "捉妖记1", PLAY_TYPE.FILE, "中国", PLAY_RATING.CHILD, 120, LocalDate.of(2018, 03, 25) ,LocalDate.of(2018,03,30) , 35));
		plays.add(new Play(2, "捉妖记2", PLAY_TYPE.FILE, "中国", PLAY_RATING.CHILD, 125, LocalDate.of(2018, 04, 25) ,LocalDate.of(2018,04,30) , 35));
		plays.add(new Play(3, "摔跤吧，爸爸", PLAY_TYPE.FILE, "泰国", PLAY_RATING.CHILD, 140, LocalDate.of(2018, 05, 25) ,LocalDate.of(2018,05,30) , 33));
		plays.add(new Play(4, "寻梦环游记", PLAY_TYPE.FILE, "美国", PLAY_RATING.CHILD, 145, LocalDate.of(2018, 06, 25) ,LocalDate.of(2018,06,30) , 40));
		plays.add(new Play(2, "奇迹男孩", PLAY_TYPE.FILE, "美国", PLAY_RATING.CHILD, 137, LocalDate.of(2018, 07, 25) ,LocalDate.of(2018,07,30) , 38));

		return plays;
	}
	
}
