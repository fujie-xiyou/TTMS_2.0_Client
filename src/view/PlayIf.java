package view;

import java.util.List;

import service.Play;

public interface PlayIf {
	public boolean add();
	public boolean modify(int id);
	public boolean delece(int id);
	public boolean query(int id);
	public void showList(List<Play> plays);
	public void mgtEntry();
}
