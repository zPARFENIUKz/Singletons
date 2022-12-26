import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException, ClassNotFoundException {
        test(DoubleCheckLockSingleton.class);
        test(EnumSingleton.class);
        test(OnDemandHolderSingleton.class);
        test(StaticFieldSingleton.class);
        test(SynchronizedAccessorSingleton.class);
    }

    private static void test(Class clazz) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        printTestInfo(clazz, singletonTest(clazz));
    }

    private static void printTestInfo(Class clazz, boolean result) {
        StringBuilder sb = new StringBuilder(clazz.getName());
        if (result) {
            sb.append(" test passed\n");
        } else {
            sb.append(" test failed!\n");
        }
        System.out.print(sb);
    }
    private static boolean singletonTest(Class clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, ClassNotFoundException {
        if (clazz == null) throw new NullPointerException("clazz variable is null");
        //System.out.println("Testing class " + clazz.getName());
        Method getInstance = clazz.getMethod("getInstance");
        var instance = clazz.cast(getInstance.invoke(clazz));
        // Serializing singleton
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(instance);
        }
        // Deserializing singleton

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            final var deserializedSingleton = clazz.cast(ois.readObject());
            // Comparing by link
            if (deserializedSingleton == instance) return true;
        }
        return false;
    }
}


