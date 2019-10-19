package places;

import objects.Cart;
import util.CountingSemaphoreWithQueue;
import util.ISemaphore;

import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Shop {
    private final ISemaphore sem;
    private final Stack<Cart> carts;

    public Shop(int maxCustomersCount) {
        this.sem = new CountingSemaphoreWithQueue(maxCustomersCount); // instead of util.CountingSemaphore
        this.carts = IntStream.rangeClosed(1, maxCustomersCount)
                .mapToObj(Cart::new)
                .collect(Collectors.toCollection(Stack::new));
    }

    public void getIn() {
        this.sem.P();
    }

    public void getOut() {
        this.sem.V();
    }

    public Cart takeCart() {
        return this.carts.pop();
    }

    public void returnCart(Cart cart) {
        this.carts.push(cart);
    }
}
