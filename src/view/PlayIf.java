package view;

import java.util.List;

import model.Play;

public interface PlayIf {
	public void mgtEntry(List<Play> plays);
	public boolean add(List<Play> plays);
	public boolean modify(List<Play> plays ,Play play);
	public boolean delece(List<Play> plays ,Play play);
	public boolean query(List<Play> plays,Play play);			
	public void showList(List<Play> plays);
}
