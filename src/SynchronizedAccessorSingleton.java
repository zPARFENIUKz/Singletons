import java.io.Serial;
import java.io.Serializable;

public class SynchronizedAccessorSingleton implements Serializable {
    private static SynchronizedAccessorSingleton INSTANCE;

    private SynchronizedAccessorSingleton() {}

    public static synchronized SynchronizedAccessorSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SynchronizedAccessorSingleton();
        }
        return INSTANCE;
    }

    @Serial
    public Object readResolve() {
        return INSTANCE;
    }

}
