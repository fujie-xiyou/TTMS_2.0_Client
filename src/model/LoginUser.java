package model;

public class LoginUser {
	private static Account loginUser = null;
	public static Account getLoginUser() {
		return loginUser;
	}
	public static void setLoginUser(Account loginUser) {
		LoginUser.loginUser = loginUser;
	}

}
