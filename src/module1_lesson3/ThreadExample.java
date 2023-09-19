package module1_lesson3;

public class ThreadExample {
    public static void main(String[] args) {
        new MyThread("Новый поток").start();
    }
}

class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("This is thread " + Thread.currentThread());
    }
}