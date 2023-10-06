package module3_lesson1;

/*
Пример имеет проблему
 */
public class ReadyExample {
    private static boolean RUNNING;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!RUNNING) {
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        number = 42;
        Thread.sleep(5000);
        RUNNING = true;
    }
}