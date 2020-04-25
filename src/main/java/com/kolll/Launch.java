package com.kolll;

import com.kolll.datafetch.DataProviderService;
import com.kolll.datafetch.model.CovidDataModel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class Launch extends Application {

    private double xOffset;
    private double yOffset;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setOpacity(0);
        primaryStage.show();

        Stage secondaryStage = new Stage();
        secondaryStage.initStyle(StageStyle.UNDECORATED);
        secondaryStage.initOwner(primaryStage);

        Parent root = FXMLLoader.load(getClass().getResource("/com/kolll/gui/widget/widget.fxml"));
        Scene scene = new Scene (root);
        secondaryStage.setScene(scene);
        secondaryStage.show();

//        aligned right-top
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        secondaryStage.setX(visualBounds.getMaxX() - 25 - scene.getWidth());
        secondaryStage.setY(visualBounds.getMinY() + 25);

//        Drag and Move

        scene.setOnMousePressed(mouseEvent -> {
            xOffset =secondaryStage.getX() - mouseEvent.getScreenX();
            yOffset = secondaryStage.getY() - mouseEvent.getScreenY();
        });

        scene.setOnMouseDragged( mouseEvent ->{
            secondaryStage.setX(mouseEvent.getScreenX() + xOffset);
            secondaryStage.setY(mouseEvent.getScreenY() + yOffset);
        });
    }
}
