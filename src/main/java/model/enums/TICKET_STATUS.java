package model.enums;

public enum TICKET_STATUS{
	AVL(0,"待售"),
	SOLD(1,"已售"),
	RESV(2,"预留");
	
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