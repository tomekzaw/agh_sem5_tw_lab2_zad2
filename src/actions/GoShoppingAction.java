package actions;

import objects.Cart;
import objects.Customer;
import places.Shop;

public class GoShoppingAction implements IAction {
    private final Customer customer;
    private final Shop shop;

    public GoShoppingAction(Customer customer, Shop shop) {
        this.customer = customer;
        this.shop = shop;
    }

    @Override
    public void execute() throws InterruptedException {
        // customer enters shop
        this.shop.getIn();
        this.log("Customer #" + this.customer.getNumber() + " enters the shop.");

        // customer takes cart
        Cart cart = this.shop.takeCart();
        this.log("Customer #" + this.customer.getNumber() + " takes cart #" + cart.getNumber() + ".");

        // customer does shopping
        // Thread.sleep(3000);
        Thread.sleep((long) (Math.random() * 5000));

        // customer returns cart
        this.shop.returnCart(cart);
        this.log("Customer #" + this.customer.getNumber() + " returns cart #" + cart.getNumber() + ".");

        // customer leaves shop
        this.shop.getOut();
        this.log("Customer #" + this.customer.getNumber() + " leaves the shop.");

        // customer does something else
        // Thread.sleep((long) (Math.random() * 1000));
    }

    private void log(String message) {
        System.out.println("[" + System.nanoTime() + "] " + message);
    }
}
