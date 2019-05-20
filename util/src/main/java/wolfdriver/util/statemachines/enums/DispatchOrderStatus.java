package wolfdriver.util.statemachines.enums;

/**
 * @author wolf_z
 * @date 2018/8/24 13:56
 */
public enum  DispatchOrderStatus {

    WAIT_FETCH(10, "待取箱"),
    CANCEL_DONE(20, "取消已完成"),
    CANCEL_WAIT_PAY(30, "已取消待支付"),
    RENTING(40, "租用中"),
    PREP_RETURN(50, "已预约待归还"),
    RETURNED(60, "已归还待支付"),
    CHECKING(70, "核损中"),
    COMPLETE(80, "已完成"),
    CANCEL_PAY(90, "已取消支付完成");

    private int code;
    private String name;


    DispatchOrderStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
