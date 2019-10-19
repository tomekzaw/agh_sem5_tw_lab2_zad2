package util;

import java.util.function.Consumer;

public interface ISemaphore {
    void P();
    void V();
}