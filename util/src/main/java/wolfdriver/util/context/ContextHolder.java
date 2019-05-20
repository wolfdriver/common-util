package wolfdriver.util.context;

/**
 * Created by wolf_z on 2016/4/5.
 */
public class ContextHolder<T> {
    private volatile static ContextHolder instance;
    private ContextHolder(){ }
    private final InheritableThreadLocal<T> contextHolder = new InheritableThreadLocal<T>();


    public void set(T sourceType) {
        contextHolder.set(sourceType);
    }
    public T get() {
        return contextHolder.get();
    }
    public void clear() {
        contextHolder.remove();
    }


    public static ContextHolder getInstance(){
        if (instance == null){
            synchronized (ContextHolder.class){
                if (instance == null){
                    instance = new ContextHolder();
                }
            }
        }
        return instance;
    }
}
