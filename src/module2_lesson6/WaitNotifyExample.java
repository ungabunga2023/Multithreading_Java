package module2_lesson6;

public class WaitNotifyExample {
    public static void main(String[] args) {
        Message message = new Message();
        Thread threadCook = new Thread(new Cook(message), "Повар");
        Thread threadWaiter = new Thread(new Waiter(message), "Официант");
        threadCook.start();
        threadWaiter.start();
    }
}

class Message {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

class Cook implements Runnable {
    private Message msg;

    public Cook(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Повар начал готовить");
            sleep(2000);
                  /*
                    Перед msg.notify(); нужно захватить msg в монопольное использование,
                    чтобы убедиться, что никто кроме этого потока не имеет доступа к объекту.
                     */
            synchronized (msg) {
                msg.setMsg("Блюда готовы, можно забирать");
                msg.notify();
            }
        }
    }

    private void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Waiter implements Runnable {

    private Message msg;

    public Waiter(Message m) {
        this.msg = m;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (msg) {
                try {
                    System.out.println("Официант ждет, пока повар начнет готовить");
                    msg.wait();
                    System.out.println("Сообщение от повара получено: " + msg.getMsg());
                    System.out.println("___________________________");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}