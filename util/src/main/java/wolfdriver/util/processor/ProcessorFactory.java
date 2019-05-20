package wolfdriver.util.processor;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import wolfdriver.util.processor.bean.TestQuery;
import wolfdriver.util.processor.bean.TestResult;
import wolfdriver.util.processor.impl.Test1Processor;
import wolfdriver.util.processor.impl.Test2Processor;
import wolfdriver.util.processor.impl.Test3Processor;

import java.util.List;

public class ProcessorFactory {

    private static Test1Processor pro1 = new Test1Processor();
    private static Test2Processor pro2 = new Test2Processor();
    private static Test3Processor pro3 = new Test3Processor();

    private static List<Processor<TestQuery, TestResult>> testProcessors = Lists.newArrayList();
    static {
        testProcessors.add(pro1);
        testProcessors.add(pro2);
        testProcessors.add(pro3);
    }

    public static List<Processor<TestQuery, TestResult>> getTestProcessors() {
        return ImmutableList.copyOf(testProcessors);
    }


}
