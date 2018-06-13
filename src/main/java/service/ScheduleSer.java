package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;

import java.util.List;

public class ScheduleSer {
    private static List<Schedule> schedules;
    HttpCommon httpCommon = new HttpCommon();
    Gson json = new Gson();
    public static void clearList(){
        schedules = null;
    }
    public List<Schedule> fetchAll(){
        if(schedules == null){
            CustomResp cr = httpCommon.doHttp("/schedule/fetchAll");
            schedules =  json.fromJson(cr.getObjectJSON(),new TypeToken<List<Schedule>>(){}.getType());
        }
        return schedules;
    }
    public List<Schedule> fetchByPlay(Play play){
        CustomResp cr = httpCommon.doHttp("/schedule/fetchByPlay",play);
        return json.fromJson(cr.getObjectJSON(),new TypeToken<List<Schedule>>(){}.getType());
    }
    public Result add(Schedule schedule){
        CustomResp cr = httpCommon.doHttp("/schedule/add",schedule);
        Result result = json.fromJson(cr.getResultJSON(),Result.class);
        if(result.isStatus()){
            schedules.add(json.fromJson(cr.getObjectJSON(),Schedule.class));
        }
        return result;
    }
    public Result delete(Schedule schedule){
        CustomResp cr = httpCommon.doHttp("/schedule/delete",schedule);
        Result result = json.fromJson(cr.getResultJSON(),Result.class);
        if(result.isStatus()){
            schedules.remove(schedule);
        }
        return  result;
    }
    public Result modify(Schedule schedule){
        CustomResp cr = httpCommon.doHttp("/schedule/modify",schedule);
        Result result = json.fromJson(cr.getResultJSON(),Result.class);
        if(result.isStatus()){

        }
        return result;
    }
    public void getTickets(Schedule schedule){
        CustomResp cr = httpCommon.doHttp("/schedule/getTickets",schedule);
        schedule.setTickets(json.fromJson(cr.getObjectJSON(),Ticket[][].class));
    }
    public Schedule fetchByID(int id){
        CustomResp cr = httpCommon.doHttp("/schedule/fetchByID",id);
        return json.fromJson(cr.getObjectJSON(),Schedule.class);
    }
}
