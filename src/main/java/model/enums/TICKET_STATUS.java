package model.enums;

public enum TICKET_STATUS{
	NONE(0,"无座位"),
	AVL(1,"待售"),
	SOLD(2,"已售"),
	RESV(3,"预留");
	
	private String name;
	private int index;
	private TICKET_STATUS(int index , String name) {
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