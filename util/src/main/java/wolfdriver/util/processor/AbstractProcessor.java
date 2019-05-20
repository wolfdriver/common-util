package wolfdriver.util.processor;

public abstract class AbstractProcessor<Q, T> implements Processor<Q, T> {

    private int priority = 90;

    @Override
    public void process(Context<Q, T> ctx) {
        if (isSkip(ctx)) {
            return;
        }
        execute(ctx);
    }

    protected abstract void execute(Context<Q, T> ctx);




    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean isSkip(Context<Q, T> ctx) {
        return false;
    }
}
