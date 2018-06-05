package test;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Account;
import model.LoginUser;
import model.Result;
import model.Studio;
import model.enums.ACCOUNT_TYPE;
import service.HttpCommon;
import view.MainFrame;
import view.SeatView;

public class SeatTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginUser.setLoginUser(new Account(-1, ACCOUNT_TYPE.ADMIN, "假的", "00"));
        new MainFrame().mainFrame();
        new SeatView().mgtEntry(new Studio(-1,"测试",5,7,35),null);
    }

    public static void main(String[] args) {
        if (!new Gson().fromJson(new HttpCommon().doHttp("/login").getResultJSON(), Result.class).isStatus()) {
            HttpCommon.url = "http://fujie.bid/TTMS";
        }
        System.out.println("使用服务器：" + HttpCommon.url);
        launch(args);
    }
}
