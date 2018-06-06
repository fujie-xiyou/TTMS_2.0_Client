package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;

import java.util.List;

public class ScheduleSer {
    private static List<Schedule> schedules;
    HttpCommon httpCommon = new HttpCommon();
    Gson json = new Gson();
    public List<Schedule> fetchAll(){
        if(schedules == null){
            CustomResp cr = httpCommon.doHttp("/schedule/fetchAll");
            schedules =  json.fromJson(cr.getObjectJSON(),new TypeToken<List<Schedule>>(){}.getType());
        }
        return schedules;
    }
    public Result add(Schedule schedule){
        CustomResp cr = httpCommon.doHttp("/schedule/add");
        Result result = json.fromJson(cr.getResultJSON(),Result.class);
        if(result.isStatus()){
            schedules.add(schedule);
        }
        return result;
    }
    public Result delete(Schedule schedule){
        CustomResp cr = httpCommon.doHttp("/schedule/delete");
        Result result = json.fromJson(cr.getResultJSON(),Result.class);
        if(result.isStatus()){
            schedules.remove(schedule);
        }
        return  result;
    }
    public Result modify(Schedule schedule){
        CustomResp cr = httpCommon.doHttp("/schedule/modify");
        Result result = json.fromJson(cr.getResultJSON(),Result.class);
        if(result.isStatus()){

        }
        return result;
    }
    public Play getPlay(Schedule schedule){
        List<Play> plays = new PlaySer().fetchAll();
        for(Play play : plays){
            if(play.getId() == schedule.getPlayID())
                return play;
        }
        return null;
    }
    public Studio getStudio(Schedule schedule){
        List<Studio> studios = new StudioSer().fetchAll();
        for(Studio studio : studios){
            if(studio.getId() == schedule.getStudioID()){
                return studio;
            }
        }
        return null;
    }
}
