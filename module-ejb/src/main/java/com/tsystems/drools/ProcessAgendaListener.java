package com.tsystems.drools;

import org.drools.core.spi.Activation;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by arutz on 06.09.2016.
 */
public class ProcessAgendaListener extends DefaultAgendaEventListener {

    private String processExecutionId;
    private String processExecutionElementId;
    private EntityManager entityManager;

    public ProcessAgendaListener(String processExecutionId, String processExecutionElementId, EntityManager entityManager) {
        this.processExecutionId = processExecutionId;
        this.processExecutionElementId = processExecutionElementId;
        this.entityManager = entityManager;
    }

    private static Logger log = Logger.getLogger(ProcessAgendaListener.class.getName());

    private List<Activation> activationList = new ArrayList<>();

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
