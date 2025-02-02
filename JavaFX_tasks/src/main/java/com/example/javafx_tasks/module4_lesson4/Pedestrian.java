package com.example.javafx_tasks.module4_lesson4;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Pedestrian extends Rectangle {
    public Pedestrian(Color color, int x) {
        super(20, 40);
        this.setX(x);
        this.setY(100);
        this.setFill(color);

        // Анимация движения пешеходов по переходу
        TranslateTransition movePedestrian = new TranslateTransition(Duration.seconds(6), this);
        movePedestrian.setFromY(100);
        movePedestrian.setToY(250);
        movePedestrian.setCycleCount(TranslateTransition.INDEFINITE);
        movePedestrian.setAutoReverse(true);
        movePedestrian.play();
    }
}
