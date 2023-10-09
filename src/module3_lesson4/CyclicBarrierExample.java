package module3_lesson4;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierExample {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(3, new Ship()); //вместимость 3 контейнера
        for (int i = 0; i < 60; i++) {
            new Thread(new Cargo(i, barrier)).start();
            Thread.sleep(1_000);
        }
    }
}

//Задача, который будет выполняться при достижении сторонами барьера
class Ship implements Runnable {

    @Override
    public void run() {
        System.out.println("Отгрузка контейнеров произошла. Корабль отплывает!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Cargo implements Runnable {
    private final int number;
    private final CyclicBarrier barrier;

    public Cargo(int number, CyclicBarrier barrier) {
        this.number = number;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Груз номер " + number + " загружен на корабль. " +
                    "Осталось загрузить " + (barrier.getParties() - barrier.getNumberWaiting()) + " контейнеров.");
            barrier.await(10L, TimeUnit.SECONDS);
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}