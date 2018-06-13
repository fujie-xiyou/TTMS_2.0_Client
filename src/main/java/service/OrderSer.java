package service;


import com.google.gson.Gson;
import model.CustomResp;
import model.Order;

public class OrderSer {
    private HttpCommon httpCommon = new HttpCommon();
    private Gson json = new Gson();
    public Order fetchByID(int id){
        CustomResp cr = httpCommon.doHttp("/order/fetchByID",id);
        return json.fromJson(cr.getObjectJSON(),Order.class);
    }
}
