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
        this.shop.welcomeCustomer(customer);

        // customer takes cart
        Cart cart = this.shop.takeCart(c -> System.out.println("Customer #" + customer.getNumber() + " took cart #" + c.getNumber() + "."));

        // customer does shopping
        // Thread.sleep(3000);
        Thread.sleep((long) (Math.random() * 5000));

        // customer returns cart
        this.shop.returnCart(cart, c -> System.out.println("Customer #" + customer.getNumber() + " returned cart #" + c.getNumber() + "."));

        // customer leaves shop
        this.shop.goodbyeCustomer(customer);

        // customer does something else
        // Thread.sleep((long) (Math.random() * 1000));
    }
}
