package module2_lesson1;

import java.util.concurrent.atomic.AtomicLong;

public class RaceConditionAtomicExample {
    //Правильная работа с потоками с помощью AtomicLong
    public static void main(String[] args) throws InterruptedException {
        AtomicIncrementer incrementer = new AtomicIncrementer();
        for (int i = 0; i < 200; i++) { //создали 200 потоков, каждый увеличил на 1000
            AtomicIncrementerThread ct = new AtomicIncrementerThread(incrementer);
            new Thread(ct).start();
        }
        Thread.sleep(5_000);
        System.out.println("Counter:" + incrementer.getAmount()); //должно быть 200_000
    }
}

class AtomicIncrementer {
    private AtomicLong amount = new AtomicLong();

    public void increaseAmount() {
        amount.incrementAndGet();
    }

    public AtomicLong getAmount() {
        return amount;
    }
}

class AtomicIncrementerThread implements Runnable {
    private AtomicIncrementer incrementer;

    public AtomicIncrementerThread(AtomicIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            incrementer.increaseAmount();
        }
    }
}

