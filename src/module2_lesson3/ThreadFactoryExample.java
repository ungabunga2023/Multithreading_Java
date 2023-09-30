package module2_lesson3;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryExample {

    public static void main(String[] args) {
        Runnable task = () -> System.out.println("Я родился! " + Thread.currentThread().getName());
        CustomThreadFactory customThreadFactory = new CustomThreadFactory("Фабричный поток");

        customThreadFactory.newThread(task).start();
        customThreadFactory.newThread(task).start();
        customThreadFactory.newThread(task).start();
    }
}

class CustomThreadFactory implements ThreadFactory {

    private String name;

    public CustomThreadFactory(String name) {
        this.name = name;
    }

    public Thread newThread(Runnable r) {
        Thread thread = Executors.defaultThreadFactory().newThread(r);
        thread.setName(name);
        thread.setPriority(7);
        return thread;
    }
}