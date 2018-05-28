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
		CustomResp cr = httpCommon.doHttp("/login",account);
		Result result = json.fromJson(cr.getResultJSON(),Result.class);
		if(result.isStatus()) {
			LoginUser.setLoginUser(json.fromJson(cr.getObjectJSON(), Account.class));
			view.Account.CurUser = LoginUser.getLoginUser();
		}
		return result;
	}
	public static void cleanList() {
		accounts = null;
	}
	public List<Account> fetchAll(){
		//若isReload为true 则强制从服务器重新获取数据
		if(accounts == null) {
			CustomResp cr = httpCommon.doHttp("/account/fetchAll");
			accounts = json.fromJson(cr.getObjectJSON(), new TypeToken<List<Account>>(){

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;}.getType());
		}
		return accounts;
	}
	public Result add(Account account) {
		CustomResp cr = httpCommon.doHttp("/account/add",account);
		Result result = json.fromJson(cr.getResultJSON(), Result.class);
		if(result.isStatus()) {
			accounts.add(json.fromJson(cr.getObjectJSON(), Account.class));
			//添加成功
		}
		return result;
	}
	public Result delete(Account account) {
		CustomResp cr = httpCommon.doHttp("/account/delete", account);
		Result result = json.fromJson(cr.getResultJSON(), Result.class);
		if(result.isStatus()) {
			accounts.remove(account);
		}
		return result;
	}
}
