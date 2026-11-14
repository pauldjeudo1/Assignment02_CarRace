/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment02_carracesimulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
public class CarRaceController {

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
    @FXML private Label arrivalZone1;
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
    
}
