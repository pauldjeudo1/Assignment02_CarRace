/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment02_carracesimulator;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author Paul Djeudo
 */
public class CarNode extends Group {

    private static final double[] WHEEL_CENTERS_X = {25, 95};
    private static final double WHEEL_CENTER_Y = 44;
    private static final double WHEEL_RADIUS = 9;

    private final Car car;
    private final Timeline wheelSpin = new Timeline();

    /**
     * Builds the shapes for the given car.
     * @param car the car that this node represents
     */
    public CarNode(Car car) {
        this.car = car;
        Color color = Color.web(car.getColorHex());

        Polygon body = new Polygon(0, 30, 10, 18, 
                110, 18, 120, 30, 120, 
                42, 0, 42);
        body.setFill(color);
        Polygon roof = new Polygon(15, 18, 30, 2,
                70, 2, 90, 18);
        roof.setFill(color.darker());

        Rectangle window1 = new Rectangle(32, 5, 16, 11);
        Rectangle window2 = new Rectangle(52, 5, 16, 11);
        window1.setFill(Color.LIGHTSKYBLUE);
        window2.setFill(Color.LIGHTSKYBLUE);

        Rectangle plate = new Rectangle(44, 22, 32, 15);
        plate.setFill(Color.WHITE);
        Text number = new Text(48, 34, String.format("%02d", car.getNumber()));
        number.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        getChildren().addAll(body, roof, window1, window2, plate, number);

        for (double centerX : WHEEL_CENTERS_X) {
            addWheel(centerX);
        }
        wheelSpin.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * Adds a wheel plus a spoke (center to edge) that rotates so the spin is visible.
     * @param centerX x coordinate of the wheel center
     */
    private void addWheel(double centerX) {
        Circle wheel = new Circle(centerX, WHEEL_CENTER_Y, WHEEL_RADIUS, Color.DARKGRAY);
        wheel.setStroke(Color.BLACK);
        wheel.setStrokeWidth(3);

        Line spoke = new Line(centerX, WHEEL_CENTER_Y, centerX + WHEEL_RADIUS - 2, WHEEL_CENTER_Y);
        Rotate rotate = new Rotate(0, centerX, WHEEL_CENTER_Y);
        spoke.getTransforms().add(rotate);

        wheelSpin.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5),
                new KeyValue(rotate.angleProperty(), 360, Interpolator.LINEAR)));
        getChildren().addAll(wheel, spoke);
    }

    public Car getCar() {
        return car;
    }

    /**
     * Starts the animation of the spinning car wheels
     */
    public void startSpin() {
        wheelSpin.play();
    }

    /**
     * Pauses the animation of the spinning car wheels
     */
    public void pauseSpin() {
        wheelSpin.pause();
    }

    /**
     * Stops the animation of the spinning car wheels
     */
    public void stopSpin() {
        wheelSpin.stop();
    }

    /**
     * Changes the speed of the wheel spinning animation using a speed multiplier
     */
    public void setSpinRate(double rate) {
        wheelSpin.setRate(rate);
    }
    
}
