/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment02_carracesimulator;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Paul Djeudo
 */
public class CarRaceController implements Initializable {
    private final CarRaceModel model = new CarRaceModel();
    private final List<CarNode> carNodes = new ArrayList<>();

    @FXML private BorderPane racePane;
    @FXML private Button startButton;
    @FXML private Button pauseButton;
    @FXML private Button resetButton;
    @FXML private Slider speedSlider;
    @FXML private Label speedLabel;
    @FXML private HBox messagePanel;
    @FXML private Label messageTitle;
    @FXML private Label messageBody;
    @FXML private VBox resultsBox;
    @FXML private Pane trackPane;
    @FXML private Label startZone;
    @FXML private Label arrivalZone;

    @FXML
    private void onStart(ActionEvent event) {
    }

    @FXML
    private void onPause(ActionEvent event) {
    }

    @FXML
    private void onReset(ActionEvent event) {
    }
    
    /**
    * Called automatically after the FXML is loaded.
    * Creates one CarNode per car and places it on the track.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Car car : model.getCars()) {
            CarNode node = new CarNode(car);
            node.setLayoutX(car.getX());
            node.setLayoutY(car.getY());
            carNodes.add(node);
            trackPane.getChildren().add(node);
        }
    }
    
}
