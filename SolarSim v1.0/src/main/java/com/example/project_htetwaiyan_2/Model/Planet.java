package com.example.project_htetwaiyan_2.Model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
public class Planet extends CelestialBody {
    String name;
    Color color;
    double xVelocity;
    double yVelocity;
    double mass;
    double radius;


    public Planet() {
        super();
    }
    public Planet(String name, Color color, double x, double y, double radius, double mass, double velocity, double direction) {
        super(name, color, x, y, radius, mass);
        double directionInRadians = Math.toRadians(direction);
        this.xVelocity = velocity * Math.cos(directionInRadians);
        this.yVelocity = velocity * Math.sin(directionInRadians);
        this.radius = radius;
    }
    public void updatePosition() {
        move();
        setCenterX(getCenterX() + xVelocity);
        setCenterY(getCenterY() + yVelocity);

        trail.add(new Point2D(getCenterX(), getCenterY()));
        if (trail.size() > 100) {
            trail.remove(0);
        }
    }
    public void move() {
        for (CelestialBody object: super.CelestialObjects) {
            if (object != this) {
                double angle = calculateAngle(object, this);
                double force = calculateForce(object);
                setxVelocity(getxVelocity() + force*Math.cos(angle));
                setyVelocity(getyVelocity() - force*Math.sin(angle));
            }
        }
    }
    public double getxVelocity() {
        return xVelocity;
    }
    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }
    public double getyVelocity() {
        return yVelocity;
    }
    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public String click() {
        String str = String.format("X-Velocity: %.8f\nY-Velocity: %.8f", xVelocity, yVelocity);
        return str;
    }
}
