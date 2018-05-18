package service.enums;

public enum SEAT_STATUS{
	NONE(0,"空位"),
	GOOD(1,"有座位"),
	BROKEN(2,"损坏");
	
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
	@Override
	public String toString() {
		return name();
	}
}