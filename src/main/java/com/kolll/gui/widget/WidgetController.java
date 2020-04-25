package com.kolll.gui.widget;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class WidgetController implements Initializable {

    @FXML
    public AnchorPane rootPane;
    @FXML
    public Text textGlobalReport, textCountryCode, textCountryReport;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
