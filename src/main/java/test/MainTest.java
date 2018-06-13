package test;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Account;
import model.LoginUser;
import model.Result;
import model.enums.ACCOUNT_TYPE;
import service.HttpCommon;
import view.MainFrame;

public class MainTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginUser.setLoginUser(new Account(-1,ACCOUNT_TYPE.CLERK,"假的","00"));
        new MainFrame().mainFrame();
    }
    public static void main(String[] args){
        if(!new Gson().fromJson(new HttpCommon().doHttp("/login").getResultJSON(), Result.class).isStatus()) {
            HttpCommon.url = "http://fujie.bid/TTMS";
        }
        System.out.println("使用服务器："+HttpCommon.url);
        launch(args);
    }
}
