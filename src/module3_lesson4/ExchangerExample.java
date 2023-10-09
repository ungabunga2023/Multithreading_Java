package module3_lesson4;

import java.util.Random;
import java.util.concurrent.Exchanger;

import static java.lang.String.valueOf;

public class ExchangerExample {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(new Cook(exchanger)).start();
        new Thread(new Waiter(exchanger)).start();
    }
}

class Cook implements Runnable {
    private Exchanger<String> exchanger;

    public Cook(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                int randomNumber = new Random().nextInt(100);
                System.out.println("Повар: Я приготовил блюдо под номером " + randomNumber + "! Забирай!");
                exchanger.exchange(valueOf(randomNumber));
                Thread.sleep(1_000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

class Waiter implements Runnable {
    private Exchanger<String> exchanger;
    public String message = "";

    public Waiter(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                String gettingMessage = exchanger.exchange(message);
                System.out.println("Официант: Я готов унести блюдо под номером: " + gettingMessage);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}