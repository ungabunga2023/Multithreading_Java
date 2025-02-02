package com.example.javafx_tasks.module4_lesson4;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Road extends Rectangle {

    public Road() {
        super(700, 100);
        this.setFill(Color.GRAY);
        this.setX(50);
        this.setY(200);
    }
}
