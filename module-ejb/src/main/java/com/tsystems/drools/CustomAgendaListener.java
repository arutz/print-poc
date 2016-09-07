package com.tsystems.drools;

import org.drools.core.common.*;
import org.drools.core.definitions.rule.impl.RuleImpl;
import org.drools.core.event.AfterActivationFiredEvent;
import org.drools.core.event.rule.impl.ActivationEventImpl;
import org.drools.core.reteoo.LeftTuple;
import org.drools.core.rule.GroupElement;
import org.drools.core.spi.Activation;
import org.drools.core.spi.Consequence;
import org.drools.core.spi.PropagationContext;
import org.drools.core.util.LinkedList;
import org.drools.core.util.LinkedListEntry;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.runtime.rule.FactHandle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by arutz on 06.09.2016.
 */
public class CustomAgendaListener extends DefaultAgendaEventListener {

    private static Logger log = Logger.getLogger(CustomAgendaListener.class.getName());

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        super.afterMatchFired(event);
        Rule rule = event.getMatch().getRule();
        String ruleName = rule.getName();
        Map<String, Object> ruleMetaDataMap = rule.getMetaData();
        StringBuilder sb = new StringBuilder("Rule fired: " + ruleName);

        if (ruleMetaDataMap.size() > 0) {
            sb.append("\n  With [" + ruleMetaDataMap.size() + "] meta-data:");
            for (String key : ruleMetaDataMap.keySet()) {
                sb.append("\n    key=" + key + ", value="
                        + ruleMetaDataMap.get(key));
            }
        }

        log.info(sb.toString());
    }

}
