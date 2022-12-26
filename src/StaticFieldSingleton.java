import java.io.Serial;
import java.io.Serializable;

public class StaticFieldSingleton implements Serializable {
    private static final StaticFieldSingleton INSTANCE = new StaticFieldSingleton();

    private StaticFieldSingleton() {
    }

    public static StaticFieldSingleton getInstance() {
        return INSTANCE;
    }

    @Serial
    public Object readResolve() {
        return INSTANCE;
    }
}
