package module3_lesson4;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Phaser;

public class PhaserExample {
    private static final Phaser PHASER = new Phaser(1);//Регистрируем главный поток - поезд
    //Фазы 0 - 6 остановки

    public static void main(String[] args) throws InterruptedException {

        Passenger passenger1 = new Passenger(2, 6, PHASER, "Первый");//создаем пассажиров
        Passenger passenger2 = new Passenger(3, 5, PHASER, "Второй");
        Passenger passenger3 = new Passenger(2, 4, PHASER, "Третий");
        Passenger passenger4 = new Passenger(4, 6, PHASER, "Четвертый");
        Passenger passenger5 = new Passenger(1, 5, PHASER, "Пятый");
        Passenger passenger6 = new Passenger(1, 6, PHASER, "Шестой");
        List<Passenger> passengers = Arrays.asList(passenger1, passenger2, passenger3, passenger4, passenger5, passenger6);


        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    System.out.println("Поезд выехал со станции");
                    PHASER.arrive();
                    break;
                case 6:
                    System.out.println("Поезд приехал на конечную станцию");
                    PHASER.arriveAndDeregister();//Снимаем главный поток, поезд приехал в депо
                    break;
                default:
                    int currentTrainStop = PHASER.getPhase();
                    System.out.println("Остановка № " + currentTrainStop);
                    Thread.sleep(7_000);

                    for (Passenger p : passengers)          //Проверяем, есть ли пассажиры на остановке
                        if (p.getDeparture() == currentTrainStop) {
                            PHASER.register();                   //Регистрируем пассажира, который будет участвовать в фазах
                            new Thread(p, p.getName()).start();  // и запускаем поток
                        }
                    PHASER.arriveAndAwaitAdvance();//Сообщаем о своей готовности
            }
        }
    }
}

class Passenger implements Runnable {
    private int departure;
    private int destination;
    private String name;
    private Phaser phaser;

    public Passenger(int departure, int destination, Phaser phaser, String name) {
        this.phaser = phaser;
        this.departure = departure;
        this.destination = destination;
        this.name = name;
        System.out.println(this + " ждёт на остановке № " + this.departure);
    }

    @Override
    public void run() {
        try {
            System.out.println(this + " на поезд.");

            while (phaser.getPhase() < destination) {//Пока поезд не приедет на нужную остановку (фазу)
                phaser.arriveAndAwaitAdvance(); //заявляем в каждой фазе о готовности и ждем
            }

            Thread.sleep(1000);
            System.out.println(this + " покинул поезд.");
            phaser.arriveAndDeregister();   //Отменяем регистрацию на нужной фазе
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Пассажир{" + departure + " -> " + destination + '}';
    }

    public int getDeparture() {
        return departure;
    }

    public String getName() {
        return name;
    }
}