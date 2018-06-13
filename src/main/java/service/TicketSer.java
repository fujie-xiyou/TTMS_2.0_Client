package service;

import com.google.gson.Gson;
import model.CustomResp;
import model.Order;
import model.Result;
import model.Ticket;

import java.util.List;

public class TicketSer {
    HttpCommon httpCommon = new HttpCommon();
    Gson json = new Gson();
    public Boolean lockedTicked(Ticket ticket){
        CustomResp cr = httpCommon.doHttp("/ticket/lockedTicket",ticket);
        return json.fromJson(cr.getResultJSON(),Result.class).isStatus();
    }
    public Boolean unLockTicked(Ticket ticket){
        CustomResp cr = httpCommon.doHttp("/ticket/unLockTicket",ticket);
        return json.fromJson(cr.getResultJSON(),Result.class).isStatus();
    }
    public Long timeStamp(){
        CustomResp cr = httpCommon.doHttp("/ticket/timeStamp");
        return json.fromJson(cr.getObjectJSON(),Long.class);
    }
    public Order purchaseTickets(List<Ticket> tickets){
        CustomResp cr = httpCommon.doHttp("/ticket/purchaseTicket",tickets);
        //TODO 完了记得删除这个
        System.out.println(cr);
        if(json.fromJson(cr.getResultJSON(),Result.class).isStatus()) {
            return json.fromJson(cr.getObjectJSON(), Order.class);
        }else {
            return null;
        }
    }
    public Result returnTickets(Order order){
        CustomResp cr = httpCommon.doHttp("/ticket/returnTicket",order);
        return json.fromJson(cr.getResultJSON(),Result.class);
    }
    public Ticket fetchByID(int id){
        CustomResp cr = httpCommon.doHttp("/ticket/fetchByID",id);
        return json.fromJson(cr.getObjectJSON(),Ticket.class);
    }
}
