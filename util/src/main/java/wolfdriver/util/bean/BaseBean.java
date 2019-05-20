package wolfdriver.util.bean;

import lombok.Data;

/**
 *
 * Bean中共有的常见数据
 *
 * @Author: wolf_z
 * @Date: 2018/6/1 15:36
 */
@Data
public class BaseBean extends ModelBean {
    private static final long serialVersionUID = 6591093218827914764L;
    /**创建者id*/
    private Long creatorId;
    /**创建者名称*/
    private String creatorName;
    /**更新者id*/
    private Long updatorId;
    /**更新者名称*/
    private String updatorName;

}
