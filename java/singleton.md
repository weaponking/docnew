```
public class Singleton {

    private static class SingletonHolder {

        public static Singleton INSTANCE = new Singleton();

    }

    public static Singleton getInstance() {

        return SingletonHolder.INSTANCE;
    }
}
```

```
public class DoubleCheckSingleton {

    //volatile 禁止重排序
    private static volatile DoubleCheckSingleton INSTANCE = null;

    public static DoubleCheckSingleton getInstance() {

        if(Objects.isNull(INSTANCE)) {

            synchronized (DoubleCheckSingleton.class) {

                if(Objects.isNull(INSTANCE)) {
                    INSTANCE = new DoubleCheckSingleton();
                }
            }
        }

        return INSTANCE;
    }
}
```
