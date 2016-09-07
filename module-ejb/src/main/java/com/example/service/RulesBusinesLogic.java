package com.example.service;

import com.tsystems.drools.ProcessAgendaListener;
import com.tsystems.tara.entities.Vehicle;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.kie.api.KieServices;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.Stateless;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by Achim on 05/09/2016.
 */
@Stateless
@Named(value = "rulesEngine")
public class RulesBusinesLogic {

	@PersistenceContext(name = "primary")
    public EntityManager entityManager;
	
    private Logger logger = LoggerFactory.getLogger(RulesBusinesLogic.class);

    private KieServices ks;
    private KieContainer kContainer;
    private KieSession kSession;

    @PostConstruct
    public void init() {
        ks = KieServices.Factory.get();
        kContainer = ks.getKieClasspathContainer();
        kSession = kContainer.newKieSession("ksession-rules");
    }

    public int applyRules(DelegateExecution delegateExecution){
        Vehicle vehicle = (Vehicle) delegateExecution.getVariable(Vehicle.PROP_NAME);
        ProcessAgendaListener agendaListener = new ProcessAgendaListener(
        		delegateExecution.getActivityInstanceId(),
        		delegateExecution.getCurrentActivityName(), entityManager);
        kSession.addEventListener(agendaListener);
        kSession.insert(vehicle);
        int appliedRules = kSession.fireAllRules();
        logger.info(MessageFormat.format("applied {0} rules for process instance {1}"
                ,appliedRules,delegateExecution.getParentId()));
        delegateExecution.setVariable(Vehicle.PROP_NAME,vehicle);
        return appliedRules;
    }

}
