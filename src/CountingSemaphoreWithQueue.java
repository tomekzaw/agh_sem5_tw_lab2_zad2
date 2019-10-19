import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class CountingSemaphoreWithQueue extends CountingSemaphore {
    private Queue<Long> queue = new LinkedList<>();

    public CountingSemaphoreWithQueue(int initialCount) {
        super(initialCount);
    }

    // P — opuszczanie semafora (hol. proberen), powoduje zmniejszenie wartości zmiennej semaforowej
    @Override
    public synchronized void P(Consumer<Integer> onComplete) {
        this.queue.add(Thread.currentThread().getId());
        while (!(this.count > 0 && this.queue.peek() == Thread.currentThread().getId())) {
            try {
                this.wait();
            } catch (InterruptedException __) {}
        }
        this.queue.remove();
        --this.count;
        onComplete.accept(this.count);
    }

    // V — podnoszenie semafora (hol. verhogen). powoduje zwiekszanie wartości zmiennej semaforowej
    @Override
    public synchronized void V(Consumer<Integer> onComplete) {
        ++this.count;
        this.notifyAll();
        onComplete.accept(this.count);
    }
}
