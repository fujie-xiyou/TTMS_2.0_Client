package service;

import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import model.Account;
import model.CustomResp;
import model.LoginUser;
import model.Result;

public class AccountSer {
	private HttpCommon httpCommon = new HttpCommon();
	private static List<Account> accounts = null; 
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

	public List<Account> fetchAll(boolean isReload){
		//若isReload为true 则强制从服务器重新获取数据
		if(accounts == null || isReload) {
			CustomResp cr = httpCommon.doHttp("/account/fetchAll", "GET", null);
			accounts = json.fromJson(cr.getObjectJSON(), new TypeToken<List<Account>>(){

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;}.getType());
		}
		return accounts;
	}
	public Result add(Account account) {
		CustomResp cr = httpCommon.doHttp("/account/add", "POST",json.toJson(account));
		Result result = json.fromJson(cr.getResultJSON(), Result.class);
		if(result.isStatus()) {
			accounts.add(json.fromJson(cr.getObjectJSON(), Account.class));
			//添加成功
		}
		return result;
	}
}
