package service;

public enum PLAY_TYPE {

	FILE(1,"电影"),
	OPEAR(2,"歌剧"),
	CONCERT(3,"音乐会");
	
	private String name;
	private int index;
	private PLAY_TYPE(int index , String name) {
		this.index = index;
		this.name = name;
	}
	@Override
	public  String toString() {
		return name;
	}
	public String getName() {
		return name;
	}
	public int getIndex() {
		return index;
	}
}
