import java.util.function.Consumer;

public class CountingSemaphore implements ISemaphore {
    protected int count;

    public CountingSemaphore(int initialCount) {
        if (initialCount < 0) {
            throw new IllegalArgumentException("Initial count must be non-negative");
        }
        this.count = initialCount;
    }

    // P — opuszczanie semafora (hol. proberen), powoduje zmniejszenie wartości zmiennej semaforowej
    @Override
    public synchronized void P(Consumer<Integer> onComplete) {
        while (this.count == 0) {
            try {
                this.wait();
            } catch (InterruptedException __) {}
        }
        --this.count;
        onComplete.accept(this.count);
    }

    // V — podnoszenie semafora (hol. verhogen). powoduje zwiekszanie wartości zmiennej semaforowej
    @Override
    public synchronized void V(Consumer<Integer> onComplete) {
        ++this.count;
        this.notify();
        onComplete.accept(this.count);
    }
}
