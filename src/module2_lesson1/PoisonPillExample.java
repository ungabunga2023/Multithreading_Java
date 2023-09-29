package module2_lesson1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PoisonPillExample {
    private static final Integer POISON = -1;

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        new Thread(new HostessPP(queue, POISON), "Hostess").start();
        new Thread(new WaiterPP(queue, POISON), "Waiter").start();
    }

}

class HostessPP implements Runnable {

    private final BlockingQueue<Integer> queue;
    private final Integer POISON;

    public HostessPP(BlockingQueue<Integer> queue, Integer POISON) {
        this.queue = queue;
        this.POISON = POISON;
    }

    @Override
    public void run() {

        try {
            for (int i = 0; i < 20; i++) {
                System.out.println("[Hostess] пригласил гостя №" + i);
                queue.put(i);
                System.out.println("[Hostess] свободных столиков : " + queue.remainingCapacity());
                Thread.sleep(300);
            }
            queue.put(POISON);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class WaiterPP implements Runnable {

    private final BlockingQueue<Integer> queue;
    private final Integer POISON;

    public WaiterPP(BlockingQueue<Integer> queue, Integer POISON) {
        this.queue = queue;
        this.POISON = POISON;
    }

    @Override
    public void run() {

        try {
            while (true) {
                Integer take = queue.take();
                // если это отравленная пилюлю, то завершай работу
                if (POISON.equals(take)) {
                    System.out.println("Официант пошел домой.");
                    break;
                }
                System.out.println("[Waiter] обслужил гостя №" + take);
                Thread.sleep(700);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
