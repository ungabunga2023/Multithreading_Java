package com.example.javafx_tasks.module4_lesson4;

import javafx.animation.PauseTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class TrafficLight extends Rectangle {
    private final List<Rectangle> listOfRectangle;
    private boolean redLightOn = true;
    private final Rectangle redLight, greenLight;

    private TrafficLight() {
        super(370, 150, 20, 50);
        this.setFill(Color.BLACK);
        listOfRectangle = new ArrayList<>();
        listOfRectangle.add(this);

        redLight = new Rectangle(375, 155, 10, 10);
        redLight.setFill(Color.RED);
        listOfRectangle.add(redLight);

        greenLight = new Rectangle(375, 175, 10, 10);
        greenLight.setFill(Color.GRAY);
        listOfRectangle.add(greenLight);


        // Таймер для светофора
        PauseTransition lightTimer = new PauseTransition(Duration.seconds(5));
        lightTimer.setOnFinished(event -> {
            if (redLightOn) {
                redLight.setFill(Color.GRAY);
                greenLight.setFill(Color.GREEN);
                redLightOn = false;
            } else {
                redLight.setFill(Color.RED);
                greenLight.setFill(Color.GRAY);
                redLightOn = true;
            }
            lightTimer.playFromStart();
        });
        lightTimer.play();
    }


    public static List<Rectangle> getTrafficLight() {
        return new TrafficLight().listOfRectangle;
    }

}
