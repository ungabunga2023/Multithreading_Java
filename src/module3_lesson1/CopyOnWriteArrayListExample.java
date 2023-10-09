package module3_lesson1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {

    public static void main(String[] args) throws InterruptedException {
        Incrementer incrementer = new Incrementer();
        for (int i = 0; i < 20; i++) { //создали 20 потоков, каждый увеличил на 1000
            IncrementerThread ct = new IncrementerThread("Thread " +i, incrementer);
            ct.start();
        }
        Thread.sleep(10_000);
        System.out.println("Counter:" + incrementer.getAmount()); //должно быть 20_000
    }
}

class Incrementer {
    //private List<Object> list =  new ArrayList<>(); //это плохой пример, так делать не надо
    private List<Object> list = new CopyOnWriteArrayList(new ArrayList<>());


    public void increaseObjectAmount() {
        list.add(new Object());
    }

    public long getAmount() {
        return list.size();
    }

    public void remove(){ // выбрасывает java.lang.UnsupportedOperationException
        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()){
            iterator.remove();
        }
    }
}

class IncrementerThread extends Thread {
    private Incrementer incrementer;

    public IncrementerThread(String name, Incrementer incrementer) {
        super(name);
        this.incrementer = incrementer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            incrementer.increaseObjectAmount();
        }
    }
}