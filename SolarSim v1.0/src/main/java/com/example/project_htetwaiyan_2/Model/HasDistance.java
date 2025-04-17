package com.example.project_htetwaiyan_2.Model;

import javafx.scene.shape.Circle;

public interface HasDistance {
    public default double calculateDistance(Circle body1, Circle body2) {
        return 0;
    }
}
