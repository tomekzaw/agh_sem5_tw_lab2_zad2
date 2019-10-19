public class Main {
    public static void main(String[] args) {
        ISemaphore sem = new CountingSemaphoreWithQueue(4);
        for (int i = 1; i <= 20; ++i) {
            (new Customer(i, sem)).start();
        }
    }
}
