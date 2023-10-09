package module3_lesson1;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class LinkedTransferQueueExample {
    public static void main(String[] args) {
        TransferQueue<Integer> queue = new LinkedTransferQueue<>();

        new Thread(new CafeHostess(queue)).start();
        new Thread(new CafeWaiter(queue)).start();
    }
}

class CafeHostess implements Runnable {

    private final TransferQueue<Integer> queue;

    public CafeHostess(TransferQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                System.out.println("[Hostess] пригласил гостя №" + i);
                queue.transfer(i);
                System.out.println("[Hostess] свободных столиков : " + queue.getWaitingConsumerCount());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class CafeWaiter implements Runnable {

    private final TransferQueue<Integer> queue;

    public CafeWaiter(TransferQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(300);
                Integer take = queue.take();
                System.out.println("[Waiter] обслужил гостя №" + take);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}