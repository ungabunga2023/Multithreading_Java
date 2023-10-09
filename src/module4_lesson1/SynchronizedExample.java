package module4_lesson1;

public class SynchronizedExample {

    public static void main(String[] args) throws InterruptedException {
        SynchronizedIncrementer incrementer = new SynchronizedIncrementer();
        for (int i = 0; i < 200; i++) { //создали 200 потоков, каждый увеличил на 1000
            IncrementerThread ct = new IncrementerThread(incrementer);
            new Thread(ct).start();
        }
        Thread.sleep(3000);
        System.out.println("Counter:" + incrementer.getAmount()); //должно быть 200_000
    }
}

class SynchronizedIncrementer {
    private long amount = 0L;

    public synchronized void increaseAmount() {
        amount++;
    }

    public long getAmount() {
        return amount;
    }
}

class IncrementerThread implements Runnable {
    private final SynchronizedIncrementer incrementer;

    public IncrementerThread(SynchronizedIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            incrementer.increaseAmount();
        }
    }
}

