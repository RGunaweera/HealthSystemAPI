/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resource;

/**
 *
 * @author Randima
 */

import com.mycompany.dao.BillingDAO;
import com.mycompany.model.Billing;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/billing")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillingResource.class);
    private BillingDAO billingDAO = new BillingDAO();

    
    @GET
    public Response getAllBilling() {
        try {
            List<Billing> billings = billingDAO.getAll();
            return Response.ok(billings).build();
        } catch (Exception e) {
            LOGGER.error("Error getting all billing records", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error getting all billing records")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getBillingById(@PathParam("id") int id) {
        try {
            Billing billing = billingDAO.getById(id);
            if (billing == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Billing not found with id: " + id)
                        .build();
            }
            return Response.ok(billing).build();
        } catch (Exception e) {
            LOGGER.error("Error getting billing record with ID " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error getting billing record with ID " + id)
                    .build();
        }
    }

    @POST
    public Response createBilling(Billing billing) {
        try {
            billingDAO.save(billing);
            return Response.status(Response.Status.CREATED).build();
            
        } catch (IllegalArgumentException e) {
            LOGGER.error( "Invalid billing data provided", e);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            LOGGER.error( "Error creating billing record", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error creating billing record")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateBilling(@PathParam("id") int id, Billing updatedBilling) {
        try {
            Billing existingBilling = billingDAO.getById(id);
            if (existingBilling == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Billing not found with id: " + id)
                        .build();
            }
            updatedBilling.setId(id); // Ensure the ID is set for the updated billing
            billingDAO.update(updatedBilling);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            LOGGER.error( "Invalid billing data provided for update", e);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            LOGGER.error("Error updating billing record with ID " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error updating billing record with ID " + id)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBilling(@PathParam("id") int id) {
        try {
            Billing existingBilling = billingDAO.getById(id);
            if (existingBilling == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Billing not found with id: " + id)
                        .build();
            }
            billingDAO.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            LOGGER.error("Error deleting billing record with ID " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting billing record with ID " + id)
                    .build();
        }
    }
}