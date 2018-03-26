package view;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
		          all = new TopButton("显示全部");
		MainFrame.top.addAll(add,mod,del,que,all);
//      添加事件
		add.setOnAction(e -> {
			add.recover();
			add(-1);
		});
		//修改
		mod.setOnAction(e -> {
			mod.recover();
			modify(-1);}
		);
		//删除
		del.setOnAction(e->{
			del.recover();
			delete(-1);
		});
		//查询演出计划
		que.setOnAction(e->{
			que.recover();
			query(-1);
		});
		//显示所有演出计划信息
		all.setOnAction(e->{
			all.recover();
			listAll();
		});
		
	}
	@Override
	public boolean add(int playID) {
		// TODO Auto-generated method stub
	//	TextField stdio_id,
		GridPane gPane=new GridPane();
		gPane.setHgap(5);
		gPane.setVgap(5);
		gPane.setAlignment(Pos.CENTER);
		//设置边距
		
	
		MainFrame.center.add(gPane);getClass();
		Label playid=new Label("演出厅编号：");
		Label playdate=new Label("演出日期：");
		Label playtime=new Label("演出时间：");
		gPane.add(playid, 0, 0);
		gPane.add(new TextField(), 1, 0);
		gPane.add(playdate, 0, 1);
		gPane.add(new DatePicker(), 1, 1);
		gPane.add(playtime, 0, 2);
		gPane.add(new TextField(), 1, 2);
		Button ok=new Button("确认");
		ok.getStyleClass().add("my-button");
		gPane.add(ok, 1, 3);
		String playids =playid.getText();
		String playdates=playdate.getText();
		String playtimes=playtime.getText();
		
		
		ok.setOnAction(e->{
			//ok按钮
			if (!playids.isEmpty()&&!playdates.isEmpty()&&!playtimes.isEmpty()) {
				MainFrame.popupMessage("添加成功!");
			}
			else {
				MainFrame.popupMessage("请检查输入!");
			}
		});
		return true;
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
