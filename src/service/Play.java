package service;

import java.util.Date;

enum PLAY_TYPE {
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
}

enum PLAY_RATING{
	//演出级别
	CHILD,//儿童可观看
	TEENAGE,//青少年可观看
	ADULT //成人可观看
}

public class Play {
	private int id;
	private String name;
	private PLAY_TYPE type;
	private String area; //来源地区
	private PLAY_RATING rating;//级别
	private int duration;//演出时长(分钟)
	private Date startDate;//开始日期
	private Date endDate;//结束日期
	private int price;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
