package com.example.project_htetwaiyan_2.Model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
public abstract class CelestialBody extends Circle implements Selectable, HasDistance {
    String name;
    Color color;
    double xVelocity;
    double yVelocity;
    double mass;
    ArrayList<CelestialBody> CelestialObjects;
    ArrayList<Point2D> trail = new ArrayList<>();
    double radius;


    public CelestialBody() {
        super();
    }
    public CelestialBody(String name, Color color, double x, double y, double radius, double mass) {
        super(x, y, radius);
        this.name = name;
        this.color = color;
        this.mass = mass;

        this.xVelocity = 0;
        this.yVelocity = 0;

    }
    public abstract void move();
    public void update(GraphicsContext gc, Camera c) {
        double xOffset = c.getxPos();
        double yOffset = c.getyPos();
        gc.save();
        gc.setFill(color);
        gc.fillOval(getCenterX()-getRadius()-xOffset, getCenterY()-getRadius()-yOffset, getRadius()*2, getRadius()*2);
        gc.restore();
    }
    public double calculateAngle(Circle body1, Circle body2) {
        double angle;
        double xDiff = body1.getCenterX() - body2.getCenterX();
        double yDiff = body1.getCenterY() - body2.getCenterY();
        angle = Math.atan2(xDiff, yDiff);
        angle -= Math.PI / 2;

        return angle;
    }
    public double calculateForce(CelestialBody otherObject) {
        final double G = 1;
        double mass = otherObject.mass;
        double distance = calculateDistance(this, otherObject);
        double weightForce = (G * mass) / (distance * distance);
        return weightForce;
    }
    public double calculateDistance(Circle body1, Circle body2) {

        double xDistance = body1.getCenterX() - body2.getCenterX();
        double yDistance = body1.getCenterY() - body2.getCenterY();
        double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
        return distance;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public double getMass() {
        return mass;
    }
    public void setMass(double mass) {
        this.mass = mass;
    }
    public void setCelestialObjects(ArrayList<CelestialBody> CelestialObjects) {
        this.CelestialObjects = CelestialObjects;
    }
    public ArrayList<Point2D> getTrail() {
        return trail;
    }
}
