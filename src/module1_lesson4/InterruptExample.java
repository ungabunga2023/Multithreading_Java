package module1_lesson4;

public class InterruptExample {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        new Thread(task).start();

        Thread.sleep(5_000); //главный поток останавливается на 5 секунд
        task.disable();
    }
}

class Task implements Runnable {
    private int count = 0;
    private boolean isActive = true;

    void disable() {
        isActive = false; //флаг остановки
    }

    @Override
    public void run() {
        while (isActive) {
            count++;
            System.out.println(count);
            sleep(1000);
        }
        System.out.println("Я остановился!");
    }

    private void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
