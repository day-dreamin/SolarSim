package com.example.project_htetwaiyan_2.Model;
import javafx.scene.shape.Circle;

public class Camera {
    private double xPos, yPos, width, height;
    public Camera(double width, double height, double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }
    /*public void move(double xVal, double yVal) {
        xPos += xVal;
        yPos += yVal;
    }*/
    public void focus(Circle circle) {
        xPos = ((double)circle.getCenterX() - width/2);
        yPos = ((double)circle.getCenterY() - height/2);
    }
    /*public void focus(double xPos2, double yPos2) {
        xPos = (xPos2 - width/2);
        yPos = (yPos2 - height/2);
    }*/

    public double getxPos() {
        return xPos;
    }
    public double getyPos() {
        return yPos;
    }
    /*public void setxPos(double xPos) {
        this.xPos = xPos;
    }
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }*/
}