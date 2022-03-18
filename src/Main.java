public class Main {

    public static final int CLIENT_AMOUNT = 10;
    public static final int CLIENT_COMING = 2000;

    public static void main(String[] args) {
        final Shop shop = new Shop();


        new Thread(null, shop::acceptCar, "Производитель").start();

        for (int i = 1; i <= CLIENT_AMOUNT; i++) {
            try {
                new Thread(null, shop::buyCar, "Покупатель " + i).start();
                Thread.sleep(CLIENT_COMING);
            } catch (InterruptedException E) {
                E.printStackTrace();
            }
        }
    }
}
