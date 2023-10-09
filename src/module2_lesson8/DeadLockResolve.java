package module2_lesson8;

public class DeadLockResolve {

    static class Philosopher {
        public void eat(Object less, Object bigger){
            synchronized (less) {
                String name = Thread.currentThread().getName();
                System.out.println("Поток " + name + ": Удерживает блокировку less");
                sleep(1000);
                synchronized (bigger) {
                    System.out.println("Поток " + name + ": Удерживает блокировку bigger");
                }
            }
        }

        private void sleep(long mills){
            try {
                Thread.sleep(mills);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Object less = new Object();
        Object bigger = new Object();

        Philosopher philosopher1 = new Philosopher();
        Philosopher philosopher2 = new Philosopher();

        new Thread(() -> philosopher1.eat(less, bigger)).start();
        new Thread(() -> philosopher2.eat(less, bigger)).start();

    }
}