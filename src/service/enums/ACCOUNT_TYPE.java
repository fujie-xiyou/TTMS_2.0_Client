package service.enums;

public enum ACCOUNT_TYPE {
	ANOMT(0,"匿名用户"),
	CLERK(1,"售票员"),
	MANG(2,"经理"),
	ADMIN(3,"管理员");
	
	private String name;
	private int index;
	private ACCOUNT_TYPE(int index , String name) {
		this.index = index;
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}
	public String getName() {
		return name;
	}
	public int getIndex() {
		return index;
	}
}
