/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment02_carracesimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Paul Djeudo
 */
public class CarRaceModel {
    public static final double TRACK_WIDTH = 900;
    public static final double ZONE_WIDTH = 55;
    public static final double LANE_HEIGHT = 75;
    public static final double CAR_LENGTH = 120;

    private final List<Car> cars = new ArrayList<>();
    private final List<Car> finishOrder = new ArrayList<>();
    private final Random random = new Random();

    public CarRaceModel() {
        addCar("Sedan", 7, "Blue", "#0D2D6B", 0, 100);
        addCar("Wagon", 23, "Maroon", "#7A0000", 1, 100);
        addCar("SUV", 71, "Gray", "#535353", 2, 100);
        addCar("Sports Car", 95, "Red", "#EB0D18", 3, 100);
    }

    /**
     * Adds a car
     * @param type the car type
     * @param number the car number
     * @param colorName the car color
     * @param colorHex the hex code for the car's color
     * @param lane the designated lane for the car
     * @param baseSpeed the car's base speed
     */
    private void addCar(String type, int number, String colorName,
            String colorHex, int lane, double baseSpeed) {
        double y = lane * LANE_HEIGHT + 10;
        cars.add(new Car(type, number, colorName, colorHex, ZONE_WIDTH, y, baseSpeed));
    }

    /**
     * @return the distance a car must travel from the start line to the finish line. 
     */
    public double getTravelDistance() {
        return TRACK_WIDTH - 2 * ZONE_WIDTH - CAR_LENGTH;
    }

    /**
     * @param the car
     * @return the seconds the car needs to finish at its current speed. 
     */
    public double getRaceDurationSeconds(Car car) {
        return getTravelDistance() / car.getSpeed();
    }

    /** 
     * Resets every car and gives each a slightly random speed. 
     */
    public void reset() {
        finishOrder.clear();
        for (Car car : cars) {
            car.reset();
            car.setSpeed(car.getBaseSpeed() * (0.85 + 0.3 * random.nextDouble()));
        }
    }

    /**
     * Adds the car to the finishing order (ignored if already recorded).
     * @param the car
     */
    public void recordFinish(Car car) {
        if (!finishOrder.contains(car)) {
            finishOrder.add(car);
        }
    }

    /**
     * @return true if the race is finished (finishOrder contains all the cars,
     * false if otherwise
     */
    public boolean isRaceFinished() {
        return finishOrder.size() == cars.size();
    }

    /** First car in the finishing order, or null if nobody has finished. */
    public Car getWinner() {
        return finishOrder.isEmpty() ? null : finishOrder.get(0);
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public List<Car> getFinishOrder() {
        return Collections.unmodifiableList(finishOrder);
    }
    
}
