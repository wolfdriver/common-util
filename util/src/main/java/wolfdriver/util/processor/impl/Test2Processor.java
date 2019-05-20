package wolfdriver.util.processor.impl;

import wolfdriver.util.processor.AbstractProcessor;
import wolfdriver.util.processor.Context;
import wolfdriver.util.processor.bean.TestQuery;
import wolfdriver.util.processor.bean.TestResult;

public class Test2Processor extends AbstractProcessor<TestQuery, TestResult> {

    @Override
    protected void execute(Context<TestQuery, TestResult> ctx) {
        System.out.println("test2");
        ctx.getData().setT2(true);
    }

    @Override
    public boolean isSkip(Context<TestQuery, TestResult> ctx) {
        return true;
    }

    @Override
    public int getPriority() {
        return 20;
    }
}
