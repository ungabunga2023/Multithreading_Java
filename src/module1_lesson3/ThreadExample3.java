package module1_lesson3;

public class ThreadExample3 {

    public static void main(String[] args) {

        Runnable ungaBunga = () -> System.out.println("Unga Bunga " + Thread.currentThread());
        new Thread(ungaBunga, "Unga Bunga").start();
    }
}
