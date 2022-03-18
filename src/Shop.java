import java.util.ArrayList;
import java.util.List;

public class Shop {
    Seller seller = new Seller(this);
    List<Car> cars = new ArrayList<>(10);

    public Car buyCar() {
        return seller.buyCar();
    }

    public void acceptCar() {
        seller.acceptCar();
    }

    List<Car> getCars() {
        return cars;
    }
}