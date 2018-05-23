package service;

import com.google.gson.Gson;

import model.Account;
import model.LoginUser;
import model.Result;

public class AccountSer {
	public Result login(Account account) {
		Gson gson = new Gson();
		HttpCommon httpCommon = new HttpCommon();
		CustomResp cr = httpCommon.doHttp("/login", "POST", gson.toJson(account));
		Result result = gson.fromJson(cr.getResultJSON(),Result.class);
		if(result.isStatus()) {
			LoginUser.setLoginUser(gson.fromJson(cr.getObjectJSON(), Account.class));
			view.Account.CurUser = gson.fromJson(cr.getObjectJSON(), Account.class);
		}
		return result;
	}
}
