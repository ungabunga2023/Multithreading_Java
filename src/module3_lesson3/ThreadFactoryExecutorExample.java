package module3_lesson3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


public class ThreadFactoryExecutorExample {

    public static void main(String[] args) {
        Runnable task = () -> System.out.println("Я родился! " + Thread.currentThread().getName()); //полезная нагрузка потока

        CustomExecutorThreadFactory customThreadFactory = new CustomExecutorThreadFactory("Фабричный поток"); //шаблон потока
        ExecutorService fixedPool = Executors.newSingleThreadExecutor(customThreadFactory); // создастся один поток

        for (int i = 0; i < 10; i++) {
            fixedPool.submit(task);
        }
        fixedPool.shutdown();
    }
}

class CustomExecutorThreadFactory implements ThreadFactory {

    private String name;

    public CustomExecutorThreadFactory(String name) {
        this.name = name;
    }

    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(name);
        return thread;
    }
}