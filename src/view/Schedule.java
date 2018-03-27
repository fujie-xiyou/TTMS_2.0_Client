package view;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nodes.TopButton;
import service.Play;
import service.Studio;

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
//      添加事件···1
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
		List<service.Studio> studios=new service.Studio().getStdios();
		
		// TODO Auto-generated method stub
	//	TextField stdio_id,
		GridPane gPane=new GridPane();
		gPane.setHgap(5);
		gPane.setVgap(5);
		gPane.setAlignment(Pos.CENTER);
		//设置边距
		
	
		MainFrame.center.add(gPane);
		Label studioid=new Label("演出厅编号：");
		
		Label studiodate=new Label("演出日期：");
		
		Label studiotime=new Label("演出时间：");
		
		DatePicker studiodatepicker=new DatePicker();
		
		TextField studiotimefild=new TextField();
		
		
		
		ComboBox<Studio> studioss=new ComboBox<>(FXCollections.observableArrayList(studios));
		
		studioss.setPromptText("请选择演出厅..");
		gPane.add(studioid, 0, 0); //编号label
		gPane.add(studioss,1,0);  //编号下拉框
		gPane.add(studiodate, 0, 1);  //日期label
		gPane.add(studiodatepicker, 1, 1);  //日期选择
		gPane.add(studiotime, 0, 2);  //时间label
		gPane.add(studiotimefild, 1, 2);  //填入时间
		Button ok=new Button("确认");
		ok.getStyleClass().add("my-button");
		gPane.add(ok, 1, 3);
		//类型转换
	/*	Studio studiostr =studioss.getValue();
		LocalDate studiodatepickerstr=studiodatepicker.getValue();
		String studiotimefildstr=studiotimefild.getText();*/
		
		
		ok.setOnAction(e->{
			//ok按钮
			if (!studiotimefild.getText().isEmpty()) {     //解决不了的问题......
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
