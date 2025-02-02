package com.example.javafx_tasks.module4_lesson4;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Crosswalk extends Rectangle {
    private final List<Rectangle> lines;

    private Crosswalk() {
        lines = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Rectangle stripe = new Rectangle(375, 210 + i * 15, 50, 10);
            stripe.setFill(Color.WHITE);
            lines.add(stripe);
        }
    }

    public static List<Rectangle> getCrosswalk() {
        return new Crosswalk().lines;
    }

}
