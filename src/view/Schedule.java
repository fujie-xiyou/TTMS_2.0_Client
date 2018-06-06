package view;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Play;
import model.Result;
import model.Studio;
import nodes.TopButton;
import service.ScheduleSer;
import tools.ConfirmDel;
import tools.LoadingPage;

//
public class Schedule{
	private ScheduleSer scheduleSer = new ScheduleSer();
	public void mgtEntry() {
		MainFrame.center.removeAll(MainFrame.center);
		MainFrame.top.removeAll(MainFrame.top);
		new Thread(new Task<List<model.Schedule>>() {
			@Override
			protected List<model.Schedule> call() {
				return scheduleSer.fetchAll();
			}

			@Override
			protected void running() {
				LoadingPage.loadingPage(this);
				super.running();
			}

			@Override
			protected void succeeded() {
				TopButton add = new TopButton("添加");
				MainFrame.top.addAll(add);
				add.setOnAction(e -> {
					add.recover();
					add();
				});
				MainFrame.center.removeAll(MainFrame.center);
				List<model.Schedule> schedules = getValue();
				GridPane centerPane = new GridPane();
				centerPane.setVgap(20);
				centerPane.setHgap(30);
				centerPane.setPadding(new Insets(30));
				centerPane.add(new Text("计划ID"), 0, 0);
				centerPane.add(new Text("剧目名"), 1, 0);
				centerPane.add(new Text("演出厅"), 2, 0);
				centerPane.add(new Text("演出时间"),3,0);
				centerPane.add(new Text("余座"),4,0);
				//centerPane.add(new Text("密码"), 3, 0);
				int row = 1;
				for(model.Schedule schedule : schedules) {
					centerPane.add(new Text(schedule.getId()+""),0 , row);
					centerPane.add(new Text(scheduleSer.getPlay(schedule).getName()),1 , row);
					centerPane.add(new Text(scheduleSer.getStudio(schedule).getName()),2 , row);
					//centerPane.add(new Text(schedule.getPassword()),3 , row);
					centerPane.add(new Text(schedule.getDate().toString()),3,row);
					centerPane.add(new Text(schedule.getTicketCount()+""),4,row);
					Button mod = new Button("修改") , del = new Button("删除");
					del.getStyleClass().add("del-button");
					mod.setOnAction(e -> modify(schedule));
					del.setOnAction(e -> {
						ConfirmDel.setConfirmDel(del, ee -> delete(schedule,del));
					});
					centerPane.add(mod, 4, row);
					centerPane.add(del, 5, row);
					row++;
				}
				MainFrame.center.add(centerPane);
				super.succeeded();
			}
		}).start();


	}

	public boolean add() {
		List<model.Studio> studios= model.Studio.getStdios();
	//	TextField stdio_id,
		GridPane gPane=new GridPane();
		gPane.setHgap(5);
		gPane.setVgap(30);
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
		gPane.add(studioss,1,0);  //演出厅编号下拉框。。。。。。。。。。。。
		gPane.add(studiodate, 0, 1);  //日期label
		gPane.add(studiodatepicker, 1, 1);  //日期选择。。。。。。。。。。。
		gPane.add(studiotime, 0, 2);  //时间label
		gPane.add(studiotimefild, 1, 2);  //填入时间
		Button ok=new Button("确认");
		gPane.add(ok, 1, 3);
		//类型转换
	/*	Studio studiostr =studioss.getValue();
		LocalDate studiodatepickerstr=studiodatepicker.getValue();
		String studiotimefildstr=studiotimefild.getText();*/
		
		
		ok.setOnAction(e->{
			//ok按钮
			if (!studiotimefild.getText().isEmpty()) {     //未判断studioss演出厅id和studiodatepicker日期......
				MainFrame.popupMessage("添加成功!");        //???????????????????????????????????????????????????????????????????
			}
			else {
				MainFrame.popupMessage("请检查输入!");
			}
		});
		return true;
	}


	public boolean modify(model.Schedule schedule) {
		// TODO Auto-generated method stub
		VBox vpane=new VBox();
		vpane.prefWidthProperty().bind(MainFrame.centerWidth);//将面板首选宽度与预留面板的宽度绑定
		double width = vpane.getPrefWidth();
		vpane.setPadding(new Insets(30,width*2.0/7,0,width*2.0/7));//面板上左右内边距
		MainFrame.center.add(vpane);
		vpane.setAlignment(Pos.TOP_LEFT);
		vpane.setSpacing(50);
		Text text2=new Text("修改演出计划：");
		text2.setFill(Color.DARKGRAY);
		text2.setFont(new Font(25));
		TextField plan=new TextField();
		plan.setPromptText("演出计划ID：");
		Button ok2=new Button("修改");
		vpane.getChildren().addAll(text2,plan,ok2);
		ok2.setOnAction(e->{
			//具体修改内容
			if(!plan.getText().isEmpty()) {
		
			vpane.setSpacing(20);
			vpane.getChildren().removeAll(text2,plan,ok2);
			TextField play_id=new TextField();
			play_id.setPromptText("请输入剧目ID：");
			List<model.Studio> studios2 = model.Studio.getStdios();
			ComboBox<Studio> studioss2=new ComboBox<>(FXCollections.observableArrayList(studios2));
			studioss2.setPromptText("请选择演出厅：");
			DatePicker play_date=new DatePicker();
			play_date.setPromptText("请选择演出日期：");
			TextField play_time=new TextField();
			play_time.setPromptText("请输入演出时间：");
			Button mod_ok=new Button("确认修改");
			vpane.getChildren().addAll(play_id,studioss2,play_date,play_time,mod_ok);
			
			mod_ok.setOnAction(Event->{   //
				if(!play_id.getText().isEmpty()&&!play_time.getText().isEmpty()) {
					MainFrame.popupMessage("修改成功!");//未判断演出厅ID和演出日期?????????????????????????????????????????????????
				}
				else {
					MainFrame.popupMessage("请检查输入!");
				}
			});
			
			}
			else {
				MainFrame.popupMessage("请检查输入!");
			}
			
		});
		
		return false;
	}


	public boolean delete(model.Schedule schedule ,Button del) {
		// TODO Auto-generated method stub
		List<model.Studio> studios1= model.Studio.getStdios();
		ComboBox<Studio> studioss1=new ComboBox<>(FXCollections.observableArrayList(studios1));
		VBox vBox=new VBox();
		
		MainFrame.center.add(vBox);
	
	    
		vBox.setAlignment(Pos.TOP_LEFT);
		vBox.prefWidthProperty().bind(MainFrame.centerWidth);//将面板首选宽度与预留面板的宽度绑定
		double width = vBox.getPrefWidth();
		vBox.setPadding(new Insets(30,width*2.0/7,0,width*2.0/7));//面板上左右内边距
		vBox.setSpacing(50);
		Text text1=new Text("删除演出计划：");
		text1.setFont(new Font(25));
		text1.setFill(Color.DARKGRAY);
		
		studioss1.setPromptText("请选择演出厅..");
		
		Button ok1=new Button("确定");
		vBox.getChildren().addAll(text1,studioss1,ok1);
		ok1.setOnAction(e->{
			MainFrame.popupMessage("删除成功!");
			/*if() {                                       //未判断??????????????????????????????????????????????????????????????
				
			}
			else {
				
			}*/
		});
		
		
		return false;
	}


	public boolean query(int id) {
		// TODO Auto-generated method stub
		VBox quepane=new VBox();
        MainFrame.center.add(quepane);
        quepane.setAlignment(Pos.TOP_LEFT);
		quepane.prefWidthProperty().bind(MainFrame.centerWidth);//将面板首选宽度与预留面板的宽度绑定
		double width = quepane.getPrefWidth();
		quepane.setPadding(new Insets(30,width*2.0/7,0,width*2.0/7));//面板上左右内边距
		quepane.setSpacing(50);
		Text quetext=new Text("查询演出计划：");
		quetext.setFill(Color.DARKGRAY);
		quetext.setFont(new Font(25));
		List<model.Studio> delstud= model.Studio.getStdios();
		ComboBox<Studio> delstuds=new ComboBox<>(FXCollections.observableArrayList(delstud));
		delstuds.setPromptText("请选择演出厅..");
		Button del_bt=new Button("查询");
		quepane.getChildren().addAll(quetext,delstuds,del_bt);
		del_bt.setOnAction(Event->{      //不能显示所选演出厅剧目信息??????????????????????????????????????????????????????????????
			                             //咋显示出来啊。。。
		});
		return false;
	}


	public List<model.Schedule> listByPlay(Play play) {
		// TODO Auto-generated method stub
		return null;         //what???????????????????????????????????????????????????
	}


	public void listAll() {
		// TODO Auto-generated method stub
		                     //what???????????????????????????????????????????????????
	}

}
