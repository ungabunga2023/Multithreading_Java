package module3_lesson2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableExample {

    public static void main(String []args) throws Exception {
        Callable<Long> task = () -> {
            Thread.sleep(5_000);
            return System.currentTimeMillis();
        };

        FutureTask<Long> future = new FutureTask<>(task);
        new Thread(future, "My Thread").start();

        System.out.println(future.get());
        System.out.println(Thread.currentThread() + " finished!");
    }
}