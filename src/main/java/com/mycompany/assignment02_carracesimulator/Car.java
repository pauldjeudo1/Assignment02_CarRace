/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment02_carracesimulator;

/**
 *
 * @author Paul Djeudo
 */
public class Car {
    private final String type;
    private final int number;
    private final String colorName;
    private final String colorHex;
    private final double startX;
    private final double startY;
    private final double baseSpeed;
    
    private double x;
    private double y;
    private double speed;

    public Car(String type, int number, String colorName, String colorHex, double startX, double startY, double baseSpeed) {
        this.type = type;
        this.number = number;
        this.colorName = colorName;
        this.colorHex = colorHex;
        this.startX = startX;
        this.startY = startY;
        this.baseSpeed = baseSpeed;
        reset();
    }
    
    /** Moves the car forward by speed * deltaSeconds. */
    public void advance(double deltaSeconds) {
        x += speed * deltaSeconds;
    }

    /** Puts the car back on the start line. */
    public void reset() {
        x = startX;
        y = startY;
        speed = baseSpeed;
    }
    
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public String getColorName() {
        return colorName;
    }

    public String getColorHex() {
        return colorHex;
    }

    public double getStartX() {
        return startX;
    }

    public double getBaseSpeed() {
        return baseSpeed;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }
    
}
