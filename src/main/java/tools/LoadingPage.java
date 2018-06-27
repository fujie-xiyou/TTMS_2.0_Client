package tools;

import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import view.MainFrame;

public class LoadingPage {
    public static void loadingPage(Task task) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        Button cal = new Button("取消");
        cal.setOnAction(e -> {
            task.cancel();
            MainFrame.center.removeAll(MainFrame.center);
        });
        vBox.getChildren().addAll(new ImageView("loading1.gif"), cal);
        MainFrame.center.add(vBox);
    }
}
