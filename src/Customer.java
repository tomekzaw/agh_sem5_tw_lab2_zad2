public class Customer extends Thread {
    private int number;
    private CountingSemaphore sem;

    public Customer(int number, CountingSemaphore sem) {
        this.number = number;
        this.sem = sem;
        // this.setPriority(Thread.NORM_PRIORITY);
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.sem.P(count -> System.out.println("Customer #" + this.number + " enters the store. Semaphore value is now " + count + "."));
                Thread.sleep((long) (Math.random() * 1000));
                this.sem.V(count -> System.out.println("Customer #" + this.number + " leaves the store. Semaphore value is now " + count + "."));
                Thread.sleep((long) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}