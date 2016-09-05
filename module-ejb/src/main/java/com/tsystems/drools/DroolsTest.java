package com.tsystems.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.tsystems.tara.entities.Vehicle;

public class DroolsTest {
	
	public static void main(String[]args) {
		try {
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");
 
            // go !
            Vehicle vehicle = new Vehicle();
            vehicle.setBmMotor("heavy");
            vehicle.setMassTire1(1.0f);
            vehicle.setMassTire2(1.0f);
            kSession.insert(vehicle);
            System.out.println("facts:" + kSession.getFactHandles().size());
            int rulesApplied = kSession.fireAllRules();
            System.out.println("all rules should be applied! rules applied: " + rulesApplied);
            System.out.println(vehicle.toString());
        } catch (Throwable t) {
            t.printStackTrace();
        }
	}

}