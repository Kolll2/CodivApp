package com.kolll;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Launch extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene (new StackPane(new Label("JavaFX")),200,200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
