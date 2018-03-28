package view;

import java.util.List;

import service.Play;

public interface PlayIf {
	public void mgtEntry();
	public boolean add(List<Play> plays);
	public boolean modify(Play play);
	public boolean delece(List<Play> plays ,Play play);
	public boolean query(Play play);			
	public void showList(List<Play> plays);
}
