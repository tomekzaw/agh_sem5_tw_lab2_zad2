package util;

import java.util.LinkedList;
import java.util.Queue;

public class CountingSemaphoreWithQueue extends CountingSemaphore {
    private Queue<Thread> queue = new LinkedList<>();

    public CountingSemaphoreWithQueue(int initialCount) {
        super(initialCount);
    }

    // P — opuszczanie semafora (hol. proberen), powoduje zmniejszenie wartości zmiennej semaforowej
    @Override
    public synchronized void P() {

        while (!(this.count > 0 )) {
            try {
                this.queue.add(Thread.currentThread());
                this.wait();
            } catch (InterruptedException __) {}
        }
        //this.queue.remove();
        --this.count;
    }

    // V — podnoszenie semafora (hol. verhogen). powoduje zwiekszanie wartości zmiennej semaforowej
    @Override
    public synchronized void V() {
        ++this.count;
        if (!queue.isEmpty()) {
            queue.remove().notify();
        }
        //this.notifyAll();
    }
}
