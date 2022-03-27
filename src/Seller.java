import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Seller {

    private Shop shop;
    private static final int RECEIVE_TIME = 1500;
    private static final int CAR_INPUT = 2000;
    private final int CARS = 10;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public Seller(Shop shop) {
        this.shop = shop;
    }

    public Car buyCar() {
        System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
        try {
            lock.lock();
            while (shop.cars.size()==0) {
                System.out.println("Машин нет");
                condition.await();
            }
            Thread.sleep(RECEIVE_TIME);
            System.out.println("Покупатель " + Thread.currentThread().getName() + " уехал на новеньком авто");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return shop.cars.remove(0);
    }

    public void acceptCar () {
        for (int i = 0; i < CARS; i++) {
            try {
                System.out.println("Производитель toyota выпустил 1 авто");
                Thread.sleep(CAR_INPUT);
                lock.lock();
                shop.cars.add(new Car());
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
