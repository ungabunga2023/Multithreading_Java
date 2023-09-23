package module1_lesson5;

public class RaceConditionExample {

    //Неправильная работа с потоками
    public static void main(String[] args) throws InterruptedException {
        Incrementer incrementer = new Incrementer();
        for (int i = 0; i < 200; i++) { //создали 200 потоков, каждый увеличил на 1000
            IncrementerThread ct = new IncrementerThread(incrementer);
            new Thread(ct).start();
        }
        Thread.sleep(5_000);
        System.out.println("Counter:" + incrementer.getAmount()); //должно быть 200_000
    }
}

class Incrementer {
    private long amount = 0L;

    public void increaseAmount() {
        amount++;
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
