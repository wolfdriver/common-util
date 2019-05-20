package wolfdriver.util.simplediff.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @Author: wolf_z
 * @Date: 2018/6/21 14:17
 */
public enum DiffEnum {


    ADD(1, "新增"),
    UPDATE(2, "修改"),
    DELETE(3, "删除");

    private int code;
    private String  desc;

    final static Map<Integer, DiffEnum> cache = Maps.newHashMapWithExpectedSize(values().length);

    static {
        for (DiffEnum val : values()) {
            cache.put(val.code, val);
        }
    }

    DiffEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


}
