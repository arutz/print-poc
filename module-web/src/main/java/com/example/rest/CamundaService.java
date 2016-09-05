package com.example.rest;

import com.tsystems.tara.entities.Vehicle;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Achim on 05/09/2016.
 */
@Path("/camunda")
@RequestScoped
public class CamundaService {

    @PersistenceContext(name = "primary")
    protected EntityManager entityManager;

    @GET
    @Path("list")
    public String getDeployments() {
        return "works";
    }

    @POST
    @Path("addVehicle")
    @Transactional
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Vehicle addVehicle(Vehicle vehicle) {
        Vehicle vehicle1 = new Vehicle();

        entityManager.persist(vehicle);
        return vehicle;
    }
}
