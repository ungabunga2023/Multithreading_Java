package module2_lesson3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledFixedDelayExample {

    public static void main(final String[] arguments) throws InterruptedException {
        int count = 0;
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Reminder(), 1, 3, TimeUnit.SECONDS);

        while (count < 10) {
            Thread.sleep(1000);
            count++;
            System.out.println(count);
        }
        scheduler.shutdown();
    }
}

class Reminder implements Runnable {

    public void run() {
        System.out.println("Пора изучать многопоточку!");
    }
}
