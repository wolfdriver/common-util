package wolfdriver.util.util;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * 用于返回安全对象（及转换NULL为空对象）的工具类
 *
 * @Author: wolf_z
 * @Date: 2018/5/21 12:31
 */
public class SafesUtil {

    private SafesUtil() {
    }

    /**
     * 返回空List对象
     * @param value
     * @return
     */
    public static <T> List<T> ofList(List<T> value) {
        return CollectionUtils.isEmpty(value) ? Collections.emptyList() : value;
    }

    /**
     * 返回空Set对象
     * @param value
     * @param <T>
     * @return
     */
    public static <T> Set<T> ofSet(Set<T> value) {
        return CollectionUtils.isEmpty(value) ? Collections.emptySet() : value;
    }

    /**
     * 返回空Map对象
     * @param value
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> ofMap(Map<K, V> value) {
        return MapUtils.isEmpty(value) ? Collections.emptyMap() : value;
    }

}
