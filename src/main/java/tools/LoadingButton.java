package tools;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoadingButton {
	public static void setLoading(Button button) {
		button.setGraphic(new ImageView(new Image("loading2.gif", 12, 12, true, true)));
		button.setDisable(true);
	}
	public static void setNormal(Button button) {
		button.setGraphic(null);
		button.setDisable(false);
	}
}
