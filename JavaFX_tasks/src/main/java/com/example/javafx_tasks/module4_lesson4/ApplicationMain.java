package com.example.javafx_tasks.module4_lesson4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ApplicationMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Создаем панель, которая будет служить холстом для отрисовки
        Pane pane = new Pane();
        //Создаем дорогу
        Road road = new Road();
        pane.getChildren().add(road);
        //Добавляем пешеходный переход
        pane.getChildren().addAll(Crosswalk.getCrosswalk());
        //Добавляем светофор
        pane.getChildren().addAll(TrafficLight.getTrafficLight());

        //Создаем две машины
        Car car1 = new Car(Color.RED, 220, 5);
        Car car2 = new Car(Color.BLUE, 260, 4);
        pane.getChildren().addAll(car1, car2);

        //Добавляем пешеходов
        Pedestrian pedestrian1 = new Pedestrian(Color.BROWN, 360);
        Pedestrian pedestrian2 = new Pedestrian(Color.ORANGE, 400);
        pane.getChildren().addAll(pedestrian1, pedestrian2);

        // Настроим сцену
        Scene scene = new Scene(pane, 800, 400);
        stage.setTitle("Две машины, светофор и пешеходы");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
