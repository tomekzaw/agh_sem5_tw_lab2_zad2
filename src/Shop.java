import java.util.Stack;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Shop {
    private final ISemaphore sem;
    private final Stack<Cart> carts;

    public Shop(int maxCustomersCount) {
        this.sem = new CountingSemaphoreWithQueue(maxCustomersCount); // instead of CountingSemaphore
        this.carts = IntStream.rangeClosed(1, maxCustomersCount)
                .mapToObj(Cart::new)
                .collect(Collectors.toCollection(Stack::new));
    }

    public void welcomeCustomer(Customer customer) {
        this.sem.P(count -> System.out.println("Customer #" + customer.getNumber() + " enters the store. Semaphore value is now " + count + "."));
    }

    public void goodbyeCustomer(Customer customer) {
        this.sem.V(count -> System.out.println("Customer #" + customer.getNumber() + " leaves the store. Semaphore value is now " + count + "."));
    }

    public Cart takeCart(Consumer<Cart> onComplete) {
        Cart cart = this.carts.pop();
        onComplete.accept(cart);
        return cart;
    }

    public void returnCart(Cart cart, Consumer<Cart> onComplete) {
        this.carts.push(cart);
        onComplete.accept(cart);
    }
}
