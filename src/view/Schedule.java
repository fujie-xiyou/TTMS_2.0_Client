package view;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
		ok2.getStyleClass().add("my-button");
		vpane.getChildren().addAll(text2,plan,ok2);
		ok2.setOnAction(e->{
			//具体修改内容
			if(!plan.getText().isEmpty()) {
		
			vpane.setSpacing(20);
			vpane.getChildren().removeAll(text2,plan,ok2);
			TextField play_id=new TextField();
			play_id.setPromptText("请输入剧目ID：");
			List<service.Studio> studios2=new service.Studio().getStdios();
			ComboBox<Studio> studioss2=new ComboBox<>(FXCollections.observableArrayList(studios2));
			studioss2.setPromptText("请选择演出厅：");
			DatePicker play_date=new DatePicker();
			play_date.setPromptText("请选择演出日期：");
			TextField play_time=new TextField();
			play_time.setPromptText("请输入演出时间：");
			Button mod_ok=new Button("确认修改");
			mod_ok.getStyleClass().add("my-button");
			vpane.getChildren().addAll(play_id,studioss2,play_date,play_time,mod_ok);
			
			mod_ok.setOnAction(Event->{   //历史遗留问题，先粗糙判断一下？？？？？？？？？？？？？？？？？？？？？？？？？？？？
				if(!play_time.getText().isEmpty()) {
					MainFrame.popupMessage("修改成功!");
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

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		List<service.Studio> studios1=new service.Studio().getStdios();
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
		ok1.getStyleClass().add("my-button");
		vBox.getChildren().addAll(text1,studioss1,ok1);
		ok1.setOnAction(e->{
			MainFrame.popupMessage("删除成功!");
			/*if() {  //??????????????????????????????????????????????????????????????
				
			}
			else {
				
			}*/
		});
		
		
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
