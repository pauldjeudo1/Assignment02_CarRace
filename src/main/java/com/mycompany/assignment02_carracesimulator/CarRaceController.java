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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Paul Djeudo
 */
public class CarRaceController implements Initializable {
    private final CarRaceModel model = new CarRaceModel();
    private final List<CarNode> carNodes = new ArrayList<>();
    private final List<TranslateTransition> transitions = new ArrayList<>();
    private static final double SLIDE_DELAY_SECONDS = 2.5;
    private MediaPlayer mediaPlayer; 

    @FXML private VBox slideshowPane;
    @FXML private Label slideCaption;
    @FXML private ImageView slideImage;
    @FXML private Slider speedSlider;
    @FXML private Label speedLabel;
    @FXML private Label messageTitle;
    @FXML private Label messageBody;
    @FXML private VBox resultsBox;
    @FXML private Pane trackPane;

    @FXML
    private void onStart(ActionEvent event) {
        if (!model.isRaceFinished()) {
            messageTitle.setText("Race in progress...");
            messageBody.setText("The cars are racing to the finish line!");
            for (int i = 0; i < carNodes.size(); i++) {
                if (!model.getFinishOrder().contains(carNodes.get(i).getCar())) {
                    transitions.get(i).play();   // starts, or resumes if paused
                    carNodes.get(i).startSpin();
                }
            }
        }
    }

    @FXML
    private void onPause(ActionEvent event) {
        boolean running = false;
        for (TranslateTransition tt : transitions) {
            if (tt.getStatus() == Animation.Status.RUNNING) {
                tt.pause();
                running = true;
            }
        }
        if (running) {
            carNodes.forEach(CarNode::pauseSpin);
            messageTitle.setText("Race paused");
            messageBody.setText("Press Start to resume.");
        }
    }

    @FXML
    private void onReset(ActionEvent event) {
        transitions.forEach(Animation::stop);
        for (CarNode node : carNodes) {
            node.stopSpin();
            node.setTranslateX(0);
        }
        model.reset();                
        buildTransitions();           
        setSimulationSpeed(speedSlider.getValue());
        resultsBox.getChildren().clear();
        messageTitle.setText("Ready to race!");
        messageBody.setText("Press Start to begin.");
    }
    
    /**
     * Called automatically after the FXML is loaded.
     * Creates one CarNode per car and places it on the track.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model.reset();
        
        for (Car car : model.getCars()) {
            CarNode node = new CarNode(car);
            node.setLayoutX(car.getX());
            node.setLayoutY(car.getY());
            carNodes.add(node);
            trackPane.getChildren().add(node);
        }
        buildTransitions();
        speedSlider.valueProperty().addListener((obs, oldV, newV)
            -> setSimulationSpeed(newV.doubleValue()));
        startSlideshow();
    }
    
    /**
     * Creates one TranslateTransition per car, from the start line to the finish line.
     */
    private void buildTransitions() {
        transitions.clear();
        for (CarNode node : carNodes) {
           Car car = node.getCar();
           TranslateTransition tt = new TranslateTransition(
                   Duration.seconds(model.getRaceDurationSeconds(car)), node);
           tt.setFromX(0);
           tt.setToX(model.getTravelDistance());
           tt.setInterpolator(Interpolator.LINEAR);
           tt.setOnFinished(e -> onCarFinished(node));
           transitions.add(tt);
        }
    }
    
    /**
     * Called when a car crosses the finish line.
     * @param node the car that finished
     */
    private void onCarFinished(CarNode node) {
        model.recordFinish(node.getCar());
        node.stopSpin();
        if (model.isRaceFinished()) {
           showResults();
        }
    }
        
    /**
     * Shows the winner and the final standings in the message panel.
     */
    private void showResults() {
        Car winner = model.getWinner();
        messageTitle.setText("Race Finished!");
        messageBody.setText(String.format("Congratulations! Car #%02d (%s) is the winner!",
               winner.getNumber(), winner.getColorName()));
        
        resultsBox.getChildren().clear();
        resultsBox.getChildren().add(new Label("Final Results:"));
        int place = 1;
        for (Car car : model.getFinishOrder()) {
            resultsBox.getChildren().add(new Label(String.format("%d.  Car #%02d (%s)",
                    place++, car.getNumber(), car.getColorName())));
        }
    }
    
    /**
     * Applies the slider value to the car movement and the wheel spin.
     * @param rate the speed multiplier
     */
    private void setSimulationSpeed(double rate) {
        for (TranslateTransition tt : transitions) {
           tt.setRate(rate);
        }
        for (CarNode node : carNodes) {
           node.setSpinRate(rate);
        }
        speedLabel.setText(String.format("%.1fx", rate));
    }
    
    /**
     * Plays the music and shows each car for a few seconds, then reveals the race screen.
     */
    private void startSlideshow() {
        playMusic();
        Timeline slideshow = new Timeline();
        List<Car> cars = model.getCars();
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            String imageFile = "/images/car" + (i + 1) + ".png";
            slideshow.getKeyFrames().add(new KeyFrame(Duration.seconds(i * SLIDE_DELAY_SECONDS),
                   e -> showSlide(car, imageFile)));
        }
        slideshow.getKeyFrames().add(new KeyFrame(Duration.seconds(cars.size() * SLIDE_DELAY_SECONDS),
                e -> endSlideshow()));
        slideshow.play();
    }

   /**
     * Displays one car image and its caption.
     * @param car the car being shown
     * @param imageFile classpath location of the car's image
     */
    private void showSlide(Car car, String imageFile) {
        slideImage.setImage(new Image(getClass().getResourceAsStream(imageFile)));
        slideCaption.setText(String.format("Car #%02d - %s %s",
                car.getNumber(), car.getColorName(), car.getType()));
    }

    /**
     * Stops the slideshow and shows the race car simulation screen
     */
    private void endSlideshow() {
        if (mediaPlayer != null) {
           mediaPlayer.stop();
        }
        slideshowPane.setVisible(false);
    }

    /**
     * Retrieves the mp3 file from resources and plays the song
     */
    private void playMusic() {
        URL musicUrl = getClass().getResource("/audio/introSong.mp3");
        if (musicUrl != null) {
           mediaPlayer = new MediaPlayer(new Media(musicUrl.toExternalForm()));
           mediaPlayer.play();
        }
    }
    
}
