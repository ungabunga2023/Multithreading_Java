package module1_lesson5;

public class RaceConditionSynchronizedExample {

    //Правильная работа с потоками с помощью synchronized
    public static void main(String[] args) throws InterruptedException {
        SynchronizedIncrementer incrementer = new SynchronizedIncrementer();
        for (int i = 0; i < 200; i++) { //создали 200 потоков, каждый увеличил на 1000
            SynchronizedIncrementerThread ct = new SynchronizedIncrementerThread(incrementer);
            new Thread(ct).start();
        }
        Thread.sleep(3000);
        System.out.println("Counter:" + incrementer.getAmount()); //должно быть 200_000
    }
}

class SynchronizedIncrementer {
    private long amount = 0L;

    public void increaseAmount() {
        synchronized (this) {
            amount++;
        }
    }

    public long getAmount() {
        return amount;
    }
}

class SynchronizedIncrementerThread implements Runnable {
    private SynchronizedIncrementer incrementer;

    public SynchronizedIncrementerThread(SynchronizedIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            incrementer.increaseAmount();
        }
    }
}

