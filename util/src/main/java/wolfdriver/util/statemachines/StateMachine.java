package wolfdriver.util.statemachines;

import org.springframework.stereotype.Component;
import wolfdriver.util.statemachines.enums.DispatchOrderStatus;
import wolfdriver.util.statemachines.enums.TransportOrderStatus;

import javax.annotation.PostConstruct;
import java.io.IOException;


/**
 * Description:
 *
 * @auther: wolf_z
 * @date: 2017-05-22
 */
@Component
public class StateMachine {

    // 派车单
    private final static String DISPATCH_MACHINE_NAME = "dispatch";
    // 运单
    private final static String TRANSPORT_MACHINE_NAME = "transport";
    // 路线集合单
    private final static String COLLECTION_ORDER_MACHINE_NAME = "logisticsCenterCollectionOrder";

    private StateMachines machines;

    @PostConstruct
    public void init() throws IOException {
        machines = PropertyReader.read("statemachines.json", StateMachines.class);
    }

    public boolean reachable(DispatchOrderStatus from, DispatchOrderStatus to) {
        if (from.equals(to)) {
            return true;
        }
        return machines.permit2Change(DISPATCH_MACHINE_NAME, from.getName(), to.getName());
    }

    public boolean reachable(TransportOrderStatus from, TransportOrderStatus to) {
        if (from.equals(to)) {
            return true;
        }
        return machines.permit2Change(TRANSPORT_MACHINE_NAME, from.getName(), to.getName());
    }

}