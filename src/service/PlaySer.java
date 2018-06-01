package service;

import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import model.CustomResp;
import model.Result;
import view.Play;

public class PlaySer {
	private static List<model.Play> plays = null;
	private HttpCommon httpCommon = new HttpCommon();
	private Gson json = new Gson();

	public static void cleanList() {
		PlaySer.plays = null;
	}
	
	
	
	public Result add(model.Play play) {
		CustomResp cr = httpCommon.doHttp("/play/add",play);
		Result result = json.fromJson(cr.getResultJSON(), Result.class);
		if(result.isStatus()) {
			plays.add(json.fromJson(cr.getObjectJSON(), model.Play.class));
		}
		return result;
	}
	public Result delete(model.Play play) {
		CustomResp cr = httpCommon.doHttp("/play/delete",play);
		Result result = json.fromJson(cr.getResultJSON(), Result.class);
		if(result.isStatus()) {
			plays.remove(play);
		}
		return result;
	}
	public Result modify(Play play) {
		CustomResp cr = httpCommon.doHttp("/play/modify");
		return json.fromJson(cr.getResultJSON(), Result.class);
		
	}
	public List<model.Play> fetchAll() {
		if (plays == null) {
			CustomResp cr = httpCommon.doHttp("/play/fetchAll");
			plays = json.fromJson(cr.getObjectJSON(), new TypeToken<List<model.Play>>() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
			}.getType());
		}
		return plays;
	}

}
