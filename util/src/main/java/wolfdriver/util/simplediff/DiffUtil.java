package wolfdriver.util.simplediff;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import wolfdriver.util.simplediff.annotation.Diff;
import wolfdriver.util.simplediff.bean.DiffNode;
import wolfdriver.util.simplediff.bean.DiffObject;
import wolfdriver.util.simplediff.enums.DiffEnum;
import wolfdriver.util.util.JodaTimeUtil;
import wolfdriver.util.util.SafesUtil;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Author: wolf_z
 * @Date: 2018/6/20 18:02
 */
@Slf4j
public class DiffUtil {

    /**
     * 两个对象的比较，针对修改操作
     * @param srcObj
     * @param destObj
     * @param <T>
     * @return
     */
    public static <T> String diffUpdateJson(T srcObj, T destObj) {
        if (srcObj == null || destObj == null) {
            return StringUtils.EMPTY;
        }

        Set<Pair<Field, Diff>> pairs = getDiffField(srcObj.getClass());
        Map<String, DiffNode> diffObjectMap = Maps.newHashMapWithExpectedSize(pairs.size());
        for (Pair<Field, Diff> pair : SafesUtil.ofSet(pairs)) {
            DiffNode diffNode = compare(srcObj, pair.getLeft(), destObj, destObj.getClass());;
            if (diffNode != null) {
                String fieldComment = StringUtils.isBlank(pair.getRight().value()) ? pair.getLeft().getName() : pair.getRight().value();
                diffObjectMap.put(fieldComment, diffNode);
            }
        }
        String clazzDiffName = getClassDiffComment(srcObj.getClass());
        return parseFriendlyString(DiffEnum.UPDATE, DiffObject.of(clazzDiffName, diffObjectMap));
    }

    /**
     * 针对删除操作的对比
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String diffDelJson(T obj) {
        return diffAddOrDel(DiffEnum.DELETE, obj);
    }

    /**
     * 针对添加操作的对比
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String diffAddJson(T obj) {
        return diffAddOrDel(DiffEnum.ADD, obj);
    }


    /**
     * 针对新增和删除的对比操作
     * @param diffEnum
     * @param obj
     * @param <T>
     * @return
     */
    private static <T> String diffAddOrDel(DiffEnum diffEnum, T obj) {
        if (obj == null) {
            return StringUtils.EMPTY;
        }
        Set<Pair<Field, Diff>> pairs = getDiffField(obj.getClass());
        Map<String, DiffNode> diffObjectMap = Maps.newHashMapWithExpectedSize(pairs.size());
        for (Pair<Field, Diff> pair : SafesUtil.ofSet(pairs)) {
            DiffNode diffNode = compare(diffEnum, obj, pair.getLeft());
            if (diffNode != null) {
                String fieldComment = StringUtils.isBlank(pair.getRight().value()) ? pair.getLeft().getName() : pair.getRight().value();
                diffObjectMap.put(fieldComment, diffNode);
            }
        }
        String clazzDiffName = getClassDiffComment(obj.getClass());
        return parseFriendlyString(diffEnum, DiffObject.of(clazzDiffName, diffObjectMap));
    }


    /**
     * 对象的比较结果
     * @param srcObj
     * @param srcField
     * @param destObj
     * @param destClazz
     * @param <T>
     * @return
     */
    private static <T> DiffNode compare(T srcObj, Field srcField, T destObj, Class destClazz) {
        try {
            srcField.setAccessible(true);
            Object srcValue = srcField.get(srcObj);
            Field destField = destClazz.getDeclaredField(srcField.getName());

            destField.setAccessible(true);
            Object destValue = destField.get(destObj);
            if (!Objects.equals(srcValue, destValue)) {
                return DiffNode.of(srcValue, destValue);
            }
        } catch (Exception e){
            log.warn("diffUtils.compare update error, e={}", e);
        }
        return null;
    }


    private static <T> DiffNode compare(DiffEnum diffEnum, T obj, Field field) {
        try {
            field.setAccessible(true);
            Object fieldValue = field.get(obj);
            if (DiffEnum.ADD.getCode().equals(diffEnum.getCode())) {
                return DiffNode.of(null, fieldValue);
            }
            else if(DiffEnum.DELETE.getCode().equals(diffEnum.getCode())) {
                return DiffNode.of(fieldValue, null);
            }

        } catch (Exception e) {
            log.warn("diffUtils.compare insert or delete error, e={}", e);
        }
        return null;
    }


    /**
     * 获取拥有Diff注解的所有字段
     * @param clazz
     * @return
     */
    private static Set<Pair<Field, Diff>> getDiffField(Class clazz) {
        // 判断是否有Field
        Field[] fieldArr = clazz.getDeclaredFields();
        if (fieldArr == null || fieldArr.length <= 0) {
            return Collections.emptySet();
        }

        Set<Pair<Field, Diff>> result = Sets.newHashSet();
        // 获取有@Diff注解的Field
        for (Field field : fieldArr) {
            Diff diffAnnotation = field.getAnnotation(Diff.class);
            if (diffAnnotation == null) {
                continue;
            }
            result.add(Pair.of(field, diffAnnotation));
        }
        return result;
    }


    /**
     * 解析出对人友好的字符串信息
     * @param diffObject
     * @return
     */
    private static String parseFriendlyString(DiffEnum diffEnum, DiffObject diffObject) {
        if (diffObject == null || MapUtils.isEmpty(diffObject.getDiffNodeMap())) {
            return StringUtils.EMPTY;
        }
        StringBuilder stringBuilder = new StringBuilder(diffObject.getClazzDiffName());
        stringBuilder.append("<").append(diffEnum.getDesc()).append(">").append(":").append(" {");
        diffObject.getDiffNodeMap().forEach((key, diffNode) -> {

            Pair<String, String> pair = parseDiffNode(diffNode);
            if (DiffEnum.DELETE.getCode().equals(diffEnum.getCode())) {
                stringBuilder.append(key).append(":")
                        .append(pair.getLeft())
                        .append("; ");
            } else if (DiffEnum.ADD.getCode().equals(diffEnum.getCode())) {
                stringBuilder.append(key).append(":")
                        .append(pair.getRight())
                        .append("; ");
            } else if (DiffEnum.UPDATE.getCode().equals(diffEnum.getCode())) {
                stringBuilder.append(key).append(":")
                        .append(pair.getLeft())
                        .append("->")
                        .append(pair.getRight())
                        .append("; ");
            }
        });
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(";"));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    /**
     * 解析还原DiffNode中的属性信息
     * @param diffNode
     * @return
     */
    private static Pair<String, String> parseDiffNode(DiffNode diffNode) {
        String srcObjValue;
        String destObjValue;
        if (diffNode.getSrcObjValue() != null && diffNode.getSrcObjValue() instanceof Date) {
            srcObjValue = JodaTimeUtil.dateToString((Date)diffNode.getSrcObjValue());
        } else {
            srcObjValue = String.valueOf(diffNode.getSrcObjValue());
        }

        if (diffNode.getDestObjValue() != null && diffNode.getDestObjValue() instanceof Date) {
            destObjValue = JodaTimeUtil.dateToString((Date)diffNode.getDestObjValue());
        } else {
            destObjValue = String.valueOf(diffNode.getDestObjValue());
        }
        return Pair.of(srcObjValue, destObjValue);
    }

    /**
     * 获取类上的Diff注解信息
     * @param clazz
     * @return
     */
    private static String getClassDiffComment(Class clazz) {
        Diff clazzAnnotation = (Diff)clazz.getAnnotation(Diff.class);
        if (clazzAnnotation == null) {
            return clazz.getName();
        }
        return StringUtils.isBlank(clazzAnnotation.value()) ? clazz.getName() : clazzAnnotation.value();
    }

}
