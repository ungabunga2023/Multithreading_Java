package module3_lesson3;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ExceptionHandlerExample {
    private static final Runnable task = () -> {
        throw new RuntimeException("Exception выброшено из " + Thread.currentThread());
    };

    public static void main(String[] args) throws InterruptedException {
        ThreadFactory threadFactory = new CustomExceptionThreadFactory("Новый поток", new ExceptionHandler());
        ExecutorService threadPool = Executors.newFixedThreadPool(2, threadFactory);

        threadPool.execute(task);
        threadPool.shutdown();
    }
}

class CustomExceptionThreadFactory implements ThreadFactory {
    private final Thread.UncaughtExceptionHandler handler;
    private final String name;

    public CustomExceptionThreadFactory(String name, Thread.UncaughtExceptionHandler handler) {
        this.name = name;
        this.handler = handler;
    }

    @Override
    public Thread newThread(Runnable run) {
        Thread thread = Executors.defaultThreadFactory().newThread(run);
        thread.setName(name);
        thread.setUncaughtExceptionHandler(handler);
        return thread;
    }
}

class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable t) {
        System.out.printf("Uncaught exception is detected! \n" +
                "%s st: %s%n", t, Arrays.toString(t.getStackTrace()));
    }
}