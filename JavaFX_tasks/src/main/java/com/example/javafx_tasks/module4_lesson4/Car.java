package com.example.javafx_tasks.module4_lesson4;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Car extends Rectangle {

    public Car(Color color, int y, int speed) {
        super(50, 30);
        this.setX(100);
        this.setY(y);
        this.setFill(color);

        // Анимация для машин
        var movement = new TranslateTransition(Duration.seconds(speed), this);
        movement.setFromX(0);
        movement.setToX(600);
        movement.setCycleCount(TranslateTransition.INDEFINITE);
        movement.setAutoReverse(true);
        movement.play();
    }
}
