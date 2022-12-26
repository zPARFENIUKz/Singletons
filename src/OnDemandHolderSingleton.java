import java.io.Serial;
import java.io.Serializable;

public class OnDemandHolderSingleton implements Serializable{
    private static class InstanceHolder implements Serializable {
        public static final OnDemandHolderSingleton INSTANCE_HOLDER = new OnDemandHolderSingleton();

        @Serial
        public Object readResolve() {
            return InstanceHolder.INSTANCE_HOLDER;
        }
    }

    private OnDemandHolderSingleton() {}

    public static OnDemandHolderSingleton getInstance() {
        return InstanceHolder.INSTANCE_HOLDER;
    }

    @Serial
    public Object readResolve() {
        return InstanceHolder.INSTANCE_HOLDER;
    }
}
