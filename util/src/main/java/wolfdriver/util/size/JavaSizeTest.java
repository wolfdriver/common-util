package wolfdriver.util.size;

import com.carrotsearch.sizeof.RamUsageEstimator;
import wolfdriver.util.bean.CupDTO;
import wolfdriver.util.bean.CupboardUnitDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wolf_z
 * @date 2018/9/25 16:44
 */
public class JavaSizeTest {

    public static void main(String[] args) {
        sizeCup();

    }


    private static void sizeCup() {
        CupDTO cupDTO = createCup();
        System.out.println(RamUsageEstimator.humanSizeOf(cupDTO));


        List<CupDTO> cupDTOList = new ArrayList<>(500);
        System.out.println("ArrayList.add before memory size: " + RamUsageEstimator.humanSizeOf(cupDTOList));
        for (int i=0; i<500; i++) {
            cupDTOList.add(createCup());
        }
        System.out.println("ArrayList.add after memory size: " +RamUsageEstimator.humanSizeOf(cupDTOList));


        CupDTO[] cupDTOArr = new CupDTO[500];
        System.out.println("Array.add before memory size: " +RamUsageEstimator.humanSizeOf(cupDTOArr));
        for (int i=0; i<500; i++) {
            cupDTOArr[i] = createCup();
        }
        System.out.println("Array.add after memory size: " +RamUsageEstimator.humanSizeOf(cupDTOArr));


        List<CupDTO> cupDTOLinked = new LinkedList<>();
        System.out.println("LinkedList.add before memory size: " +RamUsageEstimator.humanSizeOf(cupDTOLinked));
        for (int i=0; i<500; i++) {
            cupDTOLinked.add(createCup());
        }
        System.out.println("LinkedList.add after memory size: " +RamUsageEstimator.humanSizeOf(cupDTOLinked));
    }


    private static void sizeUnit() {
        CupboardUnitDTO unitDTO = createUnit();
        System.out.println(RamUsageEstimator.humanSizeOf(unitDTO));


        List<CupboardUnitDTO> unitDTOList = new ArrayList<>(500);
        System.out.println("ArrayList.add before memory size: " + RamUsageEstimator.humanSizeOf(unitDTOList));
        for (int i=0; i<500; i++) {
            unitDTOList.add(createUnit());
        }
        System.out.println("ArrayList.add after memory size: " +RamUsageEstimator.humanSizeOf(unitDTOList));


        CupboardUnitDTO[] unitDTOArr = new CupboardUnitDTO[500];
        System.out.println("Array.add before memory size: " +RamUsageEstimator.humanSizeOf(unitDTOArr));
        for (int i=0; i<500; i++) {
            unitDTOArr[i] = createUnit();
        }
        System.out.println("Array.add after memory size: " +RamUsageEstimator.humanSizeOf(unitDTOArr));


        List<CupboardUnitDTO> unitDTOLinked = new LinkedList<>();
        System.out.println("LinkedList.add before memory size: " +RamUsageEstimator.humanSizeOf(unitDTOLinked));
        for (int i=0; i<500; i++) {
            unitDTOLinked.add(createUnit());
        }
        System.out.println("LinkedList.add after memory size: " +RamUsageEstimator.humanSizeOf(unitDTOLinked));
    }


    private static CupDTO createCup() {
        CupDTO cup = new CupDTO();
        cup.setParam1(1);
        cup.setParam2(20000000000L);
        cup.setParam3(3);
        cup.setParam4(40000000000L);
        cup.setParam5(5.5D);
        cup.setParam6(66666666666L);
        cup.setParam7(7.7F);
        cup.setParam8('x');
        return cup;
    }


    private static CupboardUnitDTO createUnit() {
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
        return unitDTO;
    }

}
