package module1_lesson4;

public class ThreadSleepExample {

    public static void main(String[] args) {
        Runnable myThread = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(5_000, 500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread thread = new Thread(myThread, "My thread");
        thread.start();
    }
}