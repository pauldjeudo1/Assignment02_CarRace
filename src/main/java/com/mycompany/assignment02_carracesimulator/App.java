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
        FXMLLoader loader = new FXMLLoader(App.class.getResource("CarRace.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Car Race Simulation");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}