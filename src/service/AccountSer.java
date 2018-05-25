package service;

import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import model.Account;
import model.CustomResp;
import model.LoginUser;
import model.Result;

public class AccountSer {
	HttpCommon httpCommon = new HttpCommon();
	Gson json = new Gson();
	public Result login(Account account) {
		CustomResp cr = httpCommon.doHttp("/login", "POST", json.toJson(account));
		Result result = json.fromJson(cr.getResultJSON(),Result.class);
		if(result.isStatus()) {
			LoginUser.setLoginUser(json.fromJson(cr.getObjectJSON(), Account.class));
			view.Account.CurUser = json.fromJson(cr.getObjectJSON(), Account.class);
		}
		return result;
	}
	public List<Account> fetchAll() {
		CustomResp cr = httpCommon.doHttp("/account/fetchAll", "GET", null);
		return json.fromJson(cr.getObjectJSON(), new TypeToken<List<Account>>(){}.getType());
	}
	public Result add(Account account) {
		CustomResp cr = httpCommon.doHttp("/account/add", "POST",json.toJson(account));
		Result result = json.fromJson(cr.getResultJSON(), Result.class);
		if(result.isStatus()) {
			//添加成功
		}
		return result;
	}
}
