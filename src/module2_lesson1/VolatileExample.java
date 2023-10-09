package module2_lesson1;

public class VolatileExample {
    static volatile boolean RUNNING = true; // правильная работа программы с volatile

    static class MyThread implements Runnable {
        @Override
        public void run() {
            long count = 0;
            while (RUNNING){
                count ++;
            }
            System.out.println(count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        MyThread work = new MyThread();
        new Thread(work, "My Thread").start();

        Thread.sleep(10_000);
        System.out.println("stop");
        RUNNING = false;
    }

}