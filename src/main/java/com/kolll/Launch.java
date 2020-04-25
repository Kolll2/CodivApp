package com.kolll;

import com.kolll.datafetch.DataProviderService;
import com.kolll.datafetch.model.CovidDataModel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launch extends Application {
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
//
//        CovidDataModel dataModel = new DataProviderService().getData("Russia");
//        System.out.println(dataModel.toString());
    }
}
