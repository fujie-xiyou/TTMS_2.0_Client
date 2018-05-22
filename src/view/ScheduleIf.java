package view;

import java.util.List;

import model.Play;
import model.Schedule;

public interface ScheduleIf {
	//演出计划接口
	
	
	static final int PAGE_SIZE = 5;

	//演出计划管理界面，演出计划按照剧目进行管理，以剧目ID号为输入
	public void mgtEntry(int playID);
	
	//使用剧目ID添加一条演出计划
	public boolean add(int playID);
	
	//修改演出计划
	public boolean modify(int id);
	
	public boolean delete(int id);
	
	//查询演出计划
	public boolean query(int id);
	
	
	//以列表模式显示给定剧目的演出计划信息
	public List<Schedule> listByPlay(Play play);
	
	//显示所有演出计划信息
	public void listAll();
	
}
