package module4_lesson2;

/*
Решение студентов
 */
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//гендер
enum SEX {
    MEN,
    WOMEN,
}

public class ShowerProblem {

    public static void main(String[] args) throws InterruptedException {
        Shower shower = new Shower();
        var random = new Random();

        for (int i = 0; i < 10; i++) {
            new Thread(new Person(shower, random.nextInt(0, 2) == 0 ? SEX.MEN : SEX.WOMEN), String.valueOf(i)).start();
        }
    }
}

class Shower {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private final AtomicInteger inShower = new AtomicInteger(0);
    private SEX showerState;

    public void exitShower() {
        //    последний сообщает что в душе пусто
        if (inShower.decrementAndGet() == 0) {
            lock.lock();
            try {
                showerState = null;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    public void enterToShower(SEX sex) {
        lock.lock();
        try {
            //если душ свободен, то изменить статус душа на пол указанный в первом зашедшем в метод треде
            if (inShower.get() == 0) {
                System.out.println("Душ свободен, можно заходить! Заходят " + sex);
                showerState = sex;
                inShower.incrementAndGet();
            } else {
                // если душ не свободен, то проверить соответствует ли пол входящего тому кто внутри
                if (showerState != sex) {
                    //если не соответствует, то отправить тред ждать пока душевая не освободиться
                    System.out.println("Душ занят " +  showerState + "! В душевой " + inShower + " "  + showerState);
                    while (inShower.get() != 0) {
                        condition.await();
                    }
                }
                else {
                    inShower.incrementAndGet();
                    System.out.println("В душевой " + inShower + " " + showerState);
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}

class Person implements Runnable {
    private final Shower shower;
    private final SEX sex;
    private final Random random = new Random();

    public Person(Shower shower, SEX state) {
        this.shower = shower;
        this.sex = state;
    }

    @Override
    public void run() {
        try {
            shower.enterToShower(sex);
            int timeout = random.nextInt(2, 6);
            System.out.println(Thread.currentThread().getName() + " " + sex + "  моется " + timeout + " минут");
            TimeUnit.SECONDS.sleep(timeout);
            System.out.println(Thread.currentThread().getName() + " " + sex + " закончил мыться!");
            shower.exitShower();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
