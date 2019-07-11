package wolfdriver.util.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class TemplateUtils {
    private static final Logger log = LoggerFactory.getLogger(TemplateUtils.class);

    public static final void stringNotEmptyAndThenExecute(String source, Runnable runnable) {
        if (StringUtils.isNotEmpty(source)) {
            try {
                runnable.run();
            } catch (Exception e) {
                log.error("string empty and then execute cause an exception.", e);
            }
        }
    }

    public static final String stringEmptyAndThenExecute(String source, Callable<String> callable) {
        if (StringUtils.isEmpty(source)) {
            try {
                return callable.call();
            } catch (Exception e) {
                log.error("string empty and then execute cause an exception.", e);
            }
        }
        return source.trim();
    }

    public static final String stringBlankAndThenExecute(String source, Callable<String> callable) {
        if (StringUtils.isBlank(source)) {
            try {
                return callable.call();
            } catch (Exception e) {
                log.error("string empty and then execute cause an exception.", e);
            }
        }
        return source.trim();
    }

    public static void main(String[] args) {
        String namespaceTmp = null;
        namespaceTmp = TemplateUtils.stringBlankAndThenExecute(namespaceTmp, () -> {
            return "tmp";
        });
        System.out.println(namespaceTmp);
    }

}
