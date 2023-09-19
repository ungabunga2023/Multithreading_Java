package module1_lesson4;

public class ThreadStopExample {
    public static void main(String[] args) throws InterruptedException {
        Runnable myThread = () -> System.out.println(Thread.currentThread());

        Thread thread = new Thread(myThread, "My thread");
        thread.start();
        Thread.sleep(1000);
        thread.stop(); //deprecated, так делать не надо
    }
}
