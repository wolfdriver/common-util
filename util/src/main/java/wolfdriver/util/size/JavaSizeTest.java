package wolfdriver.util.size;

import com.carrotsearch.sizeof.RamUsageEstimator;
import wolfdriver.util.bean.CupboardUnitDTO;

import java.util.Date;

/**
 * @author wolf_z
 * @date 2018/9/25 16:44
 */
public class JavaSizeTest {

    public static void main(String[] args) {
        CupboardUnitDTO unitDTO = new CupboardUnitDTO();
        unitDTO.setCupboardCode("CU165563a3ca30004");
        unitDTO.setUnitCode("UNCU165563a3ca30004Z0101");
        unitDTO.setDisplayCode("Z0101");
        unitDTO.setType(10);
        unitDTO.setIdleStatus(20);
        unitDTO.setStatus(20);
        unitDTO.setDelFlag(10);
        unitDTO.setCreatorId(0L);
        unitDTO.setCreatorName("system");
        unitDTO.setUpdatorId(0L);
        unitDTO.setUpdatorName("system");
        unitDTO.setCreateTime(new Date());
        unitDTO.setUpdateTime(new Date());
        unitDTO.setId(1L);

        System.out.println(RamUsageEstimator.humanSizeOf(unitDTO));
    }

}
