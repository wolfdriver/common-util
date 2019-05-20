package wolfdriver.util.processor.impl;

import wolfdriver.util.processor.AbstractProcessor;
import wolfdriver.util.processor.Context;
import wolfdriver.util.processor.bean.TestQuery;
import wolfdriver.util.processor.bean.TestResult;

public class Test1Processor extends AbstractProcessor<TestQuery, TestResult> {

    @Override
    protected void execute(Context<TestQuery, TestResult> ctx) {
        System.out.println("test1");
        ctx.getData().setT1(true);
    }

    @Override
    public int getPriority() {
        return 10;
    }
}
