/**
 * Copyright (c) 2018 Wormpex.com. All Rights Reserved.
 */
package wolfdriver.util.statemachines;

import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 状态机
 * 
 * @author wolf_z
 * @version 1.0
 */
public class StateMachines {

    private Map<String, Map<String, List<String>>> statemachines = Maps.newHashMap();

    public Map<String, Map<String, List<String>>> getStatemachines() {
        return statemachines;
    }

    public void setStatemachines(Map<String, Map<String, List<String>>> statemachines) {
        this.statemachines = statemachines;
    }

    public boolean permit2Change(String machine, String from, String to) {

        if (StringUtils.isEmpty(machine) || StringUtils.isEmpty(from) || StringUtils.isEmpty(to)) {
            return false;
        }

        if (MapUtils.isEmpty(statemachines)) {
            return false;
        }

        if (!statemachines.containsKey(machine)) {
            return false;
        }

        Map<String, List<String>> instance = statemachines.get(machine);

        if (MapUtils.isEmpty(instance)) {
            return false;
        }

        if (!instance.containsKey(from)) {
            return false;
        }

        List<String> candidates = instance.get(from);

        if (CollectionUtils.isEmpty(candidates)) {
            return false;
        }

        return candidates.contains(to);
    }
}