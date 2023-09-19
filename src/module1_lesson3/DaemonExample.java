package module1_lesson3;

public class DaemonExample {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Counter());
        thread.setDaemon(true);
        thread.start();

        Thread.sleep(10_000);
        System.out.println(Thread.currentThread() + " finished!");
    }
}

class Counter implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
