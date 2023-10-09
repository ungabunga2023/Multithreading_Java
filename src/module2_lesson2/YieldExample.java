package module2_lesson2;

public class YieldExample {

    public static void main(String[] args) {
        new Thread(new CustomThread(), "Первый поток").start();
        new Thread(new CustomThread(), "Второй поток").start();
        new Thread(new CustomThread(), "Третий поток").start();
    }
}

class CustomThread implements Runnable {

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " выводит число " + i);
            Thread.yield();
        }
    }
}
