package tools;


import javafx.animation.FadeTransition;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.util.Duration;


public class ConfirmDel {
    public static void setConfirmDel(Button del, EventHandler<ActionEvent> event) {
        String old = del.getText();
        del.setText("确认" + old);
        del.setOnAction(event);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), del);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() {
                        try {
                            Thread.sleep(700);
                        } catch (InterruptedException e) {
                            // e.printStackTrace();
                            return null;
                        }
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        del.setOnMouseExited(null);
                        del.setOnMouseEntered(null);
                        del.setText(old);
                        del.setOnAction(ee -> setConfirmDel(del, event));
                        fadeTransition.play();
                        super.succeeded();
                    }

                    @Override
                    protected void cancelled() {
                        super.cancelled();
                    }
                };
            }
        };


        del.setOnMouseExited(e -> {
            service.restart();
        });
        del.setOnMouseEntered(e -> service.cancel());


    }
}
