package com.mycompany.assignment02_carracesimulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.layout.Pane;

/**
 * JavaFX App
 * GitHub Link: https://github.com/pauldjeudo1/Assignment02_CarRace.git
 */
public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        CarRaceModel model = new CarRaceModel();
        CarNode node = new CarNode(model.getCars().get(0));
        node.setLayoutX(50);
        node.setLayoutY(50);
        node.startSpin();
        stage.setScene(new Scene(new Pane(node), 300, 200));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}