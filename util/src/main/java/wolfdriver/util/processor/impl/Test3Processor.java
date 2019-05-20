package wolfdriver.util.processor.impl;

import wolfdriver.util.processor.AbstractProcessor;
import wolfdriver.util.processor.Context;
import wolfdriver.util.processor.bean.TestQuery;
import wolfdriver.util.processor.bean.TestResult;

public class Test3Processor extends AbstractProcessor<TestQuery, TestResult> {

    @Override
    protected void execute(Context<TestQuery, TestResult> ctx) {
        System.out.println("test3");
        ctx.getData().setT3(true);
    }

    @Override
    public int getPriority() {
        return 30;
    }

}
