package wolfdriver.util.simplediff.bean;

/**
 * @Author: wolf_z
 * @Date: 2018/6/21 9:47
 */
public class DiffNode {

    private Object srcObjValue;
    private Object destObjValue;

    public DiffNode(Object srcObjValue, Object destObjValue) {
        this.srcObjValue = srcObjValue;
        this.destObjValue = destObjValue;
    }

    public static DiffNode of(Object srcObjValue, Object destObjValue) {
        return new DiffNode(srcObjValue, destObjValue);
    }

    public Object getSrcObjValue() {
        return srcObjValue;
    }

    public void setSrcObjValue(Object srcObjValue) {
        this.srcObjValue = srcObjValue;
    }

    public Object getDestObjValue() {
        return destObjValue;
    }

    public void setDestObjValue(Object destObjValue) {
        this.destObjValue = destObjValue;
    }
}
