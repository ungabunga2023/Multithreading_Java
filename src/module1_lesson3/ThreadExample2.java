package module1_lesson3;

public class ThreadExample2 {

    public static void main(String[] args) {
        new Thread(new MyCustomThread(), "Unga Bunga").start();
    }
}

class MyCustomThread implements Runnable {

    @Override
    public void run() {
        System.out.println("This is thread " + Thread.currentThread());
    }
}
