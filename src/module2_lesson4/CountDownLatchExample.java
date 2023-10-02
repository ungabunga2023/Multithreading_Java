package module2_lesson4;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(15); //максимальный набор в группу 15 человек
        for (int i = 1; i < 16; i++) {     // создадим 15 желающих
            new Thread(new Student(i, countDownLatch)).start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


class Student implements Runnable {
    private final CountDownLatch countDownLatch;
    private final int number;

    public Student(int number, CountDownLatch countDownLatch) {
        this.number = number;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        countDownLatch.countDown();
        System.out.println("Добавился новый студент под номером "+number+". Осталось набрать " + countDownLatch.getCount());
        try {
            // здесь студен приостанавливается и ждет, пока доберется группа больше 15 человек
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Студент " + number + " начал занятия");
    }
}