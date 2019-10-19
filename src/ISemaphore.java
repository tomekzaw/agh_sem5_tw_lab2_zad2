import java.util.function.Consumer;

public interface ISemaphore {
    void P(Consumer<Integer> onComplete);
    void V(Consumer<Integer> onComplete);
}