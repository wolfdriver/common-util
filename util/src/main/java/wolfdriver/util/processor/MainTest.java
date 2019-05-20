package wolfdriver.util.processor;

import com.alibaba.fastjson.JSON;
import wolfdriver.util.processor.bean.TestQuery;
import wolfdriver.util.processor.bean.TestResult;

public class MainTest {

    public static void main(String[] args) {
        TestQuery query = new TestQuery();
        TestResult result = new TestResult();
        Context<TestQuery, TestResult> context = Context.builder(query, result)
                .addProcessor(ProcessorFactory.getTestProcessors()).create();
        context.process();
        System.out.println(JSON.toJSONString(result));
    }

}
