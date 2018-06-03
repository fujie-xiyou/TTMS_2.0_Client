package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.CustomResp;
import model.Result;
import model.Studio;

import java.util.List;

public class StudioSer {
    private static List<Studio> studios = null;
    private HttpCommon httpCommon = new HttpCommon();
    private Gson json = new Gson();
    private static void clearList(){
        StudioSer.studios = null;
    }
    public List<Studio> fetchAll(){
        if(studios == null){
            studios = json.fromJson(httpCommon.doHttp("/studio/fetchAll").getObjectJSON(),new TypeToken<List<Studio>>(){}.getType());
        }
        return studios;
    }
    public Result add(Studio studio){
        CustomResp cr = httpCommon.doHttp("/studio/add",studio);
        Result result = json.fromJson(cr.getResultJSON(),Result.class);
        if(result.isStatus()){
            studio = json.fromJson(cr.getObjectJSON(),Studio.class);
            studios.add(studio);
        }
        return result;
    }
    public Result delete(Studio studio){
        CustomResp cr = httpCommon.doHttp("/studio/delete",studio);
        Result result = json.fromJson(cr.getResultJSON(),Result.class);
        if(result.isStatus()){
            studios.remove(studio);
        }
        return result;
    }
    public Result modify(Studio studio){
        CustomResp cr = httpCommon.doHttp("/studio/modify",studio);
        Result result = json.fromJson(cr.getResultJSON(),Result.class);
        return result;
    }

}
