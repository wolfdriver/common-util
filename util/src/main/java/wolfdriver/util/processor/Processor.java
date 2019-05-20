package wolfdriver.util.processor;

public interface Processor<Q, T> {

    /**
     * 处理器执行
     * @param ctx
     */
    void process(Context<Q, T> ctx);


    /**
     * 获取处理器优先级
     *
     * @return 优先级 值越小优先级越高
     */
    int getPriority();

    /**
     * 设置优先级
     *
     * @param priority 优先级 见
     * @return
     */
    void setPriority(int priority);


    /**
     * 是否跳过执行
     * @param ctx
     * @return
     */
    boolean isSkip(Context<Q, T> ctx);

}
