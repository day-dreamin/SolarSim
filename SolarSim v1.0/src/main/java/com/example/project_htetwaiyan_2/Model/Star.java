package com.example.project_htetwaiyan_2.Model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.ArrayList;

public class Star extends CelestialBody {
    String name;
    Color color;
    double mass;
    ArrayList<CelestialBody> CelestialObjects;
    double radius;
    public Star() {
        super();
    }
    public Star(String name, Color color, double x, double y, double radius, double mass) {
        super(name, color, x, y, radius, mass);
        this.radius = radius;
    }
    public void move() {
        for (CelestialBody object: CelestialObjects) {
            if (object != this) {
                double angle = calculateAngle(object, this);
            }
        }
    }
    public String click() {
        return "No Velocity";
    }
}
