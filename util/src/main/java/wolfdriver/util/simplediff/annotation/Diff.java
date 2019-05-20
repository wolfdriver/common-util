package wolfdriver.util.simplediff.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于diff工具，比较对象的不同
 * @Author: wolf_z
 * @Date: 2018/6/20 17:56
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Diff {

    /** 用于描述字段含义 */
    String value() default "";

}
