package wolfdriver.util.simplediff.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: wolf_z
 * @Date: 2018/6/21 15:27
 */
public class DiffObject implements Serializable {

    private String clazzDiffName;

    private Map<String, DiffNode> diffNodeMap;

    private DiffObject(String clazzDiffName, Map<String, DiffNode> diffNodeMap) {
        this.clazzDiffName = clazzDiffName;
        this.diffNodeMap = diffNodeMap;
    }

    public static DiffObject of(String clazzDiffName, Map<String, DiffNode> diffNodeMap) {
        return new DiffObject(clazzDiffName, diffNodeMap);
    }

    public String getClazzDiffName() {
        return clazzDiffName;
    }

    public void setClazzDiffName(String clazzDiffName) {
        this.clazzDiffName = clazzDiffName;
    }

    public Map<String, DiffNode> getDiffNodeMap() {
        return diffNodeMap;
    }

    public void setDiffNodeMap(Map<String, DiffNode> diffNodeMap) {
        this.diffNodeMap = diffNodeMap;
    }
}
