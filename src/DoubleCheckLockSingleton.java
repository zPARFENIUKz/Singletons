import java.io.Serial;
import java.io.Serializable;

public class DoubleCheckLockSingleton implements Serializable {
    private static volatile DoubleCheckLockSingleton INSTANCE;

    private DoubleCheckLockSingleton() {}

    public static DoubleCheckLockSingleton getInstance() {
        DoubleCheckLockSingleton localInstance = INSTANCE;
        if (localInstance == null) {
            synchronized (DoubleCheckLockSingleton.class) {
                localInstance = INSTANCE;
                if (localInstance == null) {
                    INSTANCE = localInstance = new DoubleCheckLockSingleton();
                }
            }
        }
        return localInstance;
    }

    @Serial
    public Object readResolve() {
        return INSTANCE;
    }
}
