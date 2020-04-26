package com.kolll.gui.widget;


import com.kolll.config.ConfigModel;
import com.kolll.config.ConfigurationService;
import com.kolll.datafetch.DataProviderService;
import com.kolll.datafetch.model.CountryData;
import com.kolll.datafetch.model.CovidDataModel;
import com.kolll.datafetch.model.GlobalData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WidgetController implements Initializable {

    @FXML
    public AnchorPane rootPane;
    @FXML
    public Text textGlobalReport, textCountryCode, textCountryReport;
    private ScheduledExecutorService executorService;
    private ConfigModel configModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            configModel = new ConfigurationService().getConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initializeScheduler();
        initializeContextMenu();
        textCountryCode.setText(configModel.getCountryCode());
    }

    private void initializeScheduler() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::loadData, 0, configModel.getRefreshIntervalInSeconds(), TimeUnit.SECONDS);
    }

    private void loadData() {
        DataProviderService dataProviderService = new DataProviderService();
        CovidDataModel covidDataModel = dataProviderService.getData(configModel.getCountryName());
        System.out.println(covidDataModel.getGlobalData().toString());
        Platform.runLater(() -> {
            inflateData(covidDataModel);
        });

    }

    private void inflateData(CovidDataModel covidDataModel) {
        GlobalData globalData = covidDataModel.getGlobalData();
        textGlobalReport.setText(getFormattedData(globalData.getCases(), globalData.getRecovered(), globalData.getDeath()));
        CountryData countryData = covidDataModel.getCountryData();
        textCountryCode.setText(configModel.getCountryCode());
        textCountryReport.setText(getFormattedData(countryData.getCases(), countryData.getRecovered(), countryData.getDeaths()));

        resizeStage(textCountryCode.getScene().getWindow());

    }


    private String getFormattedData(long totalCases, long recoveredCases, long totalDeath) {
        return String.format("Cases: %-8d | Recovered: %-6d | Deaths: %-6d"
                , totalCases, recoveredCases, totalDeath);
    }

    private void resizeStage(Window stage) {
        stage.sizeToScene();

        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(visualBounds.getMaxX() - 25 - textCountryCode.getScene().getWidth());
        stage.setY(visualBounds.getMinY() + 25);
    }

    private void initializeContextMenu() {
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(actionEvent -> {
            System.exit(00);
        });

        MenuItem refreshItem = new MenuItem("Refresh");
        refreshItem.setOnAction(actionEvent -> {
            executorService.schedule(this::loadData, 0, TimeUnit.SECONDS);
        });

        final ContextMenu contextMenu = new ContextMenu(exitItem, refreshItem);
        rootPane.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (mouseEvent.isSecondaryButtonDown()) {
                contextMenu.show(rootPane, mouseEvent.getScreenX(), mouseEvent.getScreenY());
            } else {
                if (contextMenu.isShowing())
                    contextMenu.hide();
            }
        });
    }
}
