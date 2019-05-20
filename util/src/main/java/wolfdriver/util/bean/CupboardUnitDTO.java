package wolfdriver.util.bean;

import lombok.Data;

import java.util.Set;


/**
 * 柜子的存储单元
 * 
 * @author wolf_z
 * @date 2018-07-11 16:48:14
 */
@Data
public class CupboardUnitDTO extends BaseBean {
	private static final long serialVersionUID = 1L;

	/**格子单元编码*/
	private String unitCode;
	/**柜子编码*/
	private String cupboardCode;
	/**格子显示编号*/
	private String displayCode;
	/**类型*/
	private Integer type;
	/**闲置状态*/
	private Integer idleStatus;
	/**状态*/
	private Integer status;

	private Set<String> cupboardCodes;
}
