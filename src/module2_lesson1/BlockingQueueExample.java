package module2_lesson1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        new Thread(new Hostess(queue)).start();
        new Thread(new Waiter(queue)).start();
    }
}

class Hostess implements Runnable {

    private final BlockingQueue<Integer> queue;

    public Hostess(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                System.out.println("[Hostess] пригласил гостя №" + i);
                queue.put(i);
                System.out.println("[Hostess] еще есть свободных столиков : " + queue.remainingCapacity());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Waiter implements Runnable {

    private final BlockingQueue<Integer> queue;

    public Waiter(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                Integer take = queue.take();
                System.out.println("[Waiter] обслужил гостя №" + take);
                System.out.println("[Waiter] еще есть свободных столиков : " + queue.remainingCapacity());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}