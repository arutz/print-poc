package com.tsystems.drools;

import org.drools.core.audit.WorkingMemoryConsoleLogger;
import org.drools.core.event.DebugAgendaEventListener;
import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.tsystems.tara.entities.Vehicle;
import org.kie.internal.logger.KnowledgeRuntimeLogger;
import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;

public class DroolsTest {
	
	public static void main(String[]args) {
		try {
            // load up the knowledge base
            // setup the audit logging


            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");
            // go !
            kSession.addEventListener(new CustomAgendaListener());
            Vehicle vehicle = new Vehicle();
            vehicle.setBmVehicle("Astra");
            vehicle.setBmMotor("heavy");
            vehicle.setMassTire1(1.0f);
            vehicle.setMassTire2(1.0f);
            vehicle.setCocState(1);
            vehicle.setObjectId(0);
            vehicle.setId(1);
            kSession.insert(vehicle);
            int rulesApplied = kSession.fireAllRules();
            System.out.println("all rules should be applied! rules applied: " + rulesApplied);
            System.out.println(vehicle.toString());
        } catch (Throwable t) {
            t.printStackTrace();
        }
	}

}
