package module4_lesson1;

public class SynchronizedVolatileExample {
    //todo код содержит ошибку
    public static void main(String[] args) throws InterruptedException {
        VolatileIncrementer incrementer = new VolatileIncrementer();
        for (int i = 0; i < 200; i++) { //создали 200 потоков, каждый увеличил на 1000
            MyThread ct = new MyThread(incrementer);
            new Thread(ct).start();
        }
        Thread.sleep(3000);
        System.out.println("Counter:" + incrementer.getAmount()); //должно быть 200_000
    }
}

class VolatileIncrementer {
    //неправильно
    private volatile long amount = 0L;

    public void increaseAmount() {
        amount++;
    }

    public long getAmount() {
        return amount;
    }
}

class MyThread implements Runnable {
    private final VolatileIncrementer incrementer;

    public MyThread(VolatileIncrementer incrementer) {
        this.incrementer = incrementer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            incrementer.increaseAmount();
        }
    }
}
