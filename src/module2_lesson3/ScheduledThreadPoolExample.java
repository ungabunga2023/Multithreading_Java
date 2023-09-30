package module2_lesson3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExample {
    public static void main(final String[] arguments) throws InterruptedException {

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(new Alarm(), 5, TimeUnit.SECONDS); // через 10 секунд сработает будильник
        scheduler.shutdown();
    }
}

class Alarm implements Runnable {

    public void run() {
        System.out.println("Дзинь-дзинь");
    }
}