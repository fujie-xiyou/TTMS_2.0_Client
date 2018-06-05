package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.CustomResp;
import model.Result;
import model.Seat;
import model.Studio;

import java.util.List;
import java.util.ListIterator;

public class SeatSer {
    //好像不需要
    //谁说不需要

    HttpCommon httpCommon = new HttpCommon();
    Gson json = new Gson();
    public Result addAll(List<Seat> seats){
        CustomResp cr = httpCommon.doHttp("/seat/addAll");
        Result result = json.fromJson(cr.getResultJSON(),Result.class);
        if(result.isStatus()){
            List<Seat> seats1 = json.fromJson(cr.getObjectJSON(),new TypeToken<List<Seat>>(){}.getType());
            ListIterator<Seat> iterator = seats.listIterator();
            ListIterator<Seat> iterator1 = seats1.listIterator();
            while (iterator.hasNext() && iterator1.hasNext()){
                iterator.next().setId(iterator1.next().getId());
            }
        }else {

        }
        return  result;
    }
    public Result modifyAll(List<Seat> seats){
        CustomResp cr = httpCommon.doHttp("/seat/modifyAll",seats);
        Result result = json.fromJson(cr.getResultJSON(),Result.class);
        if(result.isStatus()){

        }
        return result;
    }
    public Result deleteAll(Studio studio){
        CustomResp cr = httpCommon.doHttp("/seat/deleteAll",studio);
        Result result = json.fromJson(cr.getResultJSON(),Result.class);
        if(result.isStatus()){

        }
        return result;
    }
}
