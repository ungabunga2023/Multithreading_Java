package module1_lesson8;

public class StarvationExample {
    public static void main(String[] args) {
        System.out.println("Main поток стартанул");
        Thread t1 = new Thread(new MyThread(), "Первый поток");
        Thread t2 = new Thread(new MyThread(), "Второй поток");
        t1.start();
        t2.start();
        System.out.println("Main поток завершился");
    }
}

class MyThread implements Runnable {
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " стартанул");
        synchronized (MyThread.class) {
            System.out.println(threadName + " зашел в критический блок");
            while (true) {

            }
        }
    }
}