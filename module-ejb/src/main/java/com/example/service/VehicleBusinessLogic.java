package com.example.service;

import com.tsystems.tara.entities.Vehicle;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.Stateless;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by Achim on 05/09/2016.
 */
@Stateless
@Named(value = "vehicleBusinessLogic")
public class VehicleBusinessLogic {

    private static Logger LOGGER = Logger.getLogger(VehicleBusinessLogic.class.getName());

    @PostConstruct
    public void init() {
    }

    @PersistenceContext(name = "primary")
    public EntityManager entityManager;

    public void persistVehicle(DelegateExecution delegateExecution){
        // Create new order instance
        Vehicle vehicle = null;

        // Get all process variables
        Map<String, Object> variables = delegateExecution.getVariables();

        // get vehicle
        vehicle = (Vehicle) variables.get(Vehicle.PROP_NAME);

        // Persist order instance and flush. After the flush the
        // id of the order instance is set.
        persist(vehicle);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void persist(Vehicle vehicle){
        entityManager.persist(vehicle);
        entityManager.flush();
    }

    public void removeVehicle(DelegateExecution delegateExecution) {
        // Create new order instance
        Vehicle vehicle = null;

        // Get all process variables
        Map<String, Object> variables = delegateExecution.getVariables();

        // get vehicle
        vehicle = (Vehicle) variables.get(Vehicle.PROP_NAME);

        // remove order instance and flush. After the flush the
        // id of the order instance is removed.
        remove(vehicle);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void remove(Vehicle vehicle) {
        entityManager.remove(vehicle);
        entityManager.flush();
    }

    public Collection<Vehicle> listVehicles() {
        Query query = entityManager.createQuery("SELECT v from Vehicle v");
        return (Collection<Vehicle>) query.getResultList();
    }

}
