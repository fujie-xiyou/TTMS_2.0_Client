package service;

import com.google.gson.Gson;
import model.CustomResp;
import model.Result;
import model.Seat;

import java.util.List;

public class SeatSer {
    //好像不需要
    //谁说不需要

    HttpCommon httpCommon = new HttpCommon();
    Gson json = new Gson();
    public Result modifyAll(List<Seat> seats){
        CustomResp cr = httpCommon.doHttp("/seat/modifyAll",seats);
        Result result = json.fromJson(cr.getResultJSON(),Result.class);
        if(result.isStatus()){

        }
        return result;
    }
}
