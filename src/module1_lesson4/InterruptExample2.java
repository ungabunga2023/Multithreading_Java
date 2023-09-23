package module1_lesson4;

public class InterruptExample2 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new TaskWithInterrupt());
        thread.start();
        Thread.sleep(2_000);
        thread.interrupt();
    }
}

class TaskWithInterrupt implements Runnable {
    private long count = 0;

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        while (!currentThread.isInterrupted()) {
            count++;
            sleep(1000);
        }
        System.out.println(count);
        System.out.println("Я остановился!");
    }

    private void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().isInterrupted()); //false
            Thread.currentThread().interrupt(); //восстанавливаем флаг прерывания
            throw new RuntimeException(e);
        }
    }
}