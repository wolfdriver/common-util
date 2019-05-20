package wolfdriver.util.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 顶层基类
 *
 * @Author: wolf_z
 * @Date: 2018/7/23 17:08
 */
@Data
public class ModelBean implements Serializable {
    private static final long serialVersionUID = 671758469358693926L;
    /** 主键 */
    private Long id;
    /**是否逻辑删除字段，（0：正常，1：删除）*/
    private Integer delFlag;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date updateTime;
}
