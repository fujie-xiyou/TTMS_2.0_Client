package tools;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class LoadingModel {
    public static Pane giveMeALoadingModel(){
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        ImageView imageView = new ImageView(new Image("loading3.gif"));
        pane.getChildren().add(imageView);
        return pane;
    }
}
