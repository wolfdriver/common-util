package wolfdriver.util.processor;

import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Context<Q, T> implements Serializable {
    private static final long serialVersionUID = 7538668660801630440L;

    protected Logger logger = LoggerFactory.getLogger(Context.class);

    /**
     * 查询条件
     */
    private Q query;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 处理器逻辑
     */
    private List<Processor<Q, T>> processors;

    private Context(Q query, T data, List<Processor<Q, T>> processors) {
        this.query = query;
        this.data = data;
        this.processors = processors;
    }

    public static <Q, T> Builder<Q, T> builder(Q query, T data) {
        return new Builder<Q, T>().init(query, data);
    }



    public Context<Q, T> process() {
        if (CollectionUtils.isEmpty(getProcessors())) {
            return this;
        }

        for (Processor<Q, T> p : getProcessors()) {
            long start = System.currentTimeMillis();
            p.process(this);
            logger.info("process :{} time :{} ", p.getClass().getSimpleName(), System.currentTimeMillis() - start);
        }
        return this;
    }

    public Q getQuery() {
        return query;
    }

    public T getData() {
        return data;
    }

    public List<Processor<Q, T>> getProcessors() {
        return processors;
    }

    public static class Builder<Q, T> {
        /**
         * 查询条件
         */
        private Q query;

        /**
         * 返回数据
         */
        private T data;

        /**
         * 处理器逻辑
         */
        private List<Processor<Q, T>> processors;

        public Builder<Q, T> init(Q query, T data) {
            this.query = query;
            this.data = data;
            return this;
        }

        public Context<Q, T> create() {
            return new Context<>(query, data, processors);
        }

        public Builder<Q, T> addProcessor(List<Processor<Q, T>> processors) {
            this.processors = Lists.newLinkedList(processors);
            this.processors.sort(Comparator.comparingInt(Processor::getPriority));
            this.processors = Collections.unmodifiableList(this.processors);
            return this;
        }

    }

}
