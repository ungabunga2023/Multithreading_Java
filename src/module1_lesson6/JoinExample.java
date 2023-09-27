package module1_lesson6;

public class JoinExample {

    public static void main(String[] args) throws InterruptedException {
        Thread firstThread = new Thread(new MyThread(), "First");
        Thread secondThread = new Thread(new MyThread(), "Second");
        Thread thirdThread = new Thread(new MyThread(), "Third");

        firstThread.start();
        firstThread.join();

        secondThread.start(); //потоки не начнут работу, пока firstThread не закончит свое выполнение
        thirdThread.start();
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " is running! " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(name + " completed!!");
    }
}