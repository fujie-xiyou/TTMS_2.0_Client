package view;

import java.util.List;

import nodes.TopButton;
import service.Play;

public class Schedule implements ScheduleIf{
	
	@Override
	public void mgtEntry(int playID) {
		// TODO Auto-generated method stub
		TopButton add = new TopButton("添加"),
				  mod = new TopButton("修改"),
				  del = new TopButton("删除"),
				  que = new TopButton("查询"),
		          allplay = new TopButton("全部");
		MainFrame.top.addAll(add,mod,del,que,allplay);
//		sch.setOnAction(e->{
//			sch.recover();
//			ScheduleIf sche=new Schedule();
//			sche.mgtEntry(0);
//		});

	}

	@Override
	public boolean add(int playID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modify(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean query(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<service.Schedule> listByPlay(Play play) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void listAll() {
		// TODO Auto-generated method stub
		
	}

}
