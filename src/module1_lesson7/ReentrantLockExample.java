package module1_lesson7;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    public static void main(String[] args) throws InterruptedException {
        Incrementer incrementer = new Incrementer();

        for (int i = 0; i < 200; i++) { //создали 200 потоков, каждый увеличил на 1000
            Thread ct = new Thread(new IncrementerThread(incrementer), "Thread" + i);
            ct.start();
        }
        Thread.sleep(5000);
        System.out.println("Counter:" + incrementer.getAmount()); //должно быть 200_000
    }
}

class Incrementer {
    private ReentrantLock locker = new ReentrantLock(); // создаем объект ReentrantLock
    private long amount = 0L;

    public void increaseAmount() {
        locker.lock(); // устанавливаем блокировку
        System.out.println("HoldCount - " + locker.getHoldCount()); //количество удержаний блокировки текущим потоком
        System.out.println("QueueLength - " + locker.getQueueLength()); //длина очереди ожидания

        try {
            amount++;
        } finally {
            locker.unlock(); // непременно снять блокировку, даже если генерируется исключение
        }
    }

    public long getAmount() {
        return amount;
    }
}

class IncrementerThread implements Runnable {
    private Incrementer incrementer;

    public IncrementerThread(Incrementer incrementer) {
        this.incrementer = incrementer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            incrementer.increaseAmount();
        }
    }
}