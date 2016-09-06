package com.example.rest;

import com.example.service.VehicleBusinessLogic;
import com.tsystems.tara.entities.Vehicle;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.runtime.ProcessInstance;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Achim on 05/09/2016.
 */
@Path("/print-poc")
@RequestScoped
public class PrintPocService {

    @Inject()
    VehicleBusinessLogic vehicleBusinessLogic;

    @POST
    @Path("addVehicle")
    @Produces(MediaType.APPLICATION_XML)
    public Vehicle addNewVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicleBusinessLogic.persist(vehicle);
        return vehicle;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String listVehicles() {
        Collection<Vehicle> vehicles = vehicleBusinessLogic.listVehicles();
        StringBuilder stringBuilder = new StringBuilder();
        for(Vehicle vehicle : vehicles) {
            stringBuilder.append(vehicle.toString() + "</br>");
        }
        return stringBuilder.toString();
    }

    @GET
    @Path("startByKey")
    public String startProcessByKey(@DefaultValue("SimpleProcess") @QueryParam("key")String key) throws Exception {
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance instance = defaultProcessEngine.getRuntimeService().startProcessInstanceByKey(key);
        return instance.getId();
    }

    @POST
    @Path("startVehicleExampleSync")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Vehicle startVehicleExampleSync(Vehicle vehicle) throws Exception {
        /*ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance instance = defaultProcessEngine.getRuntimeService().startProcessInstanceByKey(key);
        return instance.getId();*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        Map<String, Object> properties = new HashMap<>();
        properties.put(Vehicle.PROP_NAME,vehicle);
        ProcessInstance instance = null;
        instance =  defaultProcessEngine.
                getRuntimeService().startProcessInstanceByMessage("NewVehicle",properties);
        return vehicle;
    }

    @POST
    @Path("startVehicleExampleSync10Times")
    @Consumes(MediaType.APPLICATION_XML)
    public Response startVehicleExampleSync10Times(Vehicle vehicle) throws Exception {
        /*ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance instance = defaultProcessEngine.getRuntimeService().startProcessInstanceByKey(key);
        return instance.getId();*/
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        Map<String, Object> properties = new HashMap<>();
        properties.put(Vehicle.PROP_NAME,vehicle);
        ProcessInstance instance = null;
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Collection<ProcessExecution> executions = new ArrayList<>(10);
        for(int i = 0; i < 10; i++){
            executions.add(new ProcessExecution(properties,defaultProcessEngine));
        }
        executorService.invokeAll(executions);
        return Response.ok().build();
    }

    public static class ProcessExecution implements Callable<Object> {

        private Map<String, Object> properties;
        private ProcessEngine defaultProcessEngine;

        public ProcessExecution(Map<String, Object> properties, ProcessEngine defaultProcessEngine) {
            this.properties = properties;
            this.defaultProcessEngine = defaultProcessEngine;
        }

        @Override
        public Object call() throws Exception {
            return defaultProcessEngine.
                    getRuntimeService().startProcessInstanceByMessage("NewVehicle",properties);
        }
    }
}
