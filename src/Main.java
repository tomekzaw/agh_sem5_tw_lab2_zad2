import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop(4);
        IntStream.rangeClosed(1, 20).forEach(i -> {
            new Thread(() -> {
                Customer customer = new Customer(i);
                IAction shopping = new GoShoppingAction(customer, shop);
                while (true) {
                    try {
                        shopping.execute();
                    } catch (InterruptedException __) {}
                }
            }).start();
        });
    }
}
