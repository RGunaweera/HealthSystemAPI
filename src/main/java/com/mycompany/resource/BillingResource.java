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

@Path("/billing")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillingResource {

    private final BillingDAO billingDAO;

    public BillingResource() {
        this.billingDAO = new BillingDAO();
    }

    @GET
    public List<Billing> getAllBilling() {
        return billingDAO.getAll();
    }

    @GET
    @Path("/{id}")
    public Billing getBillingById(@PathParam("id") int id) {
        Billing billing = billingDAO.getById(id);
        if (billing == null) {
            throw new NotFoundException("Billing not found with id: " + id);
        }
        return billing;
    }

    @POST
    public Response createBilling(Billing billing) {
        billingDAO.save(billing);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public void updateBilling(@PathParam("id") int id, Billing updatedBilling) {
        Billing existingBilling = billingDAO.getById(id);
        if (existingBilling == null) {
            throw new NotFoundException("Billing not found with id: " + id);
        }
        updatedBilling.setId(id); // Ensure the ID is set for the updated billing
        billingDAO.update(updatedBilling);
    }

    @DELETE
    @Path("/{id}")
    public void deleteBilling(@PathParam("id") int id) {
        Billing existingBilling = billingDAO.getById(id);
        if (existingBilling == null) {
            throw new NotFoundException("Billing not found with id: " + id);
        }
        billingDAO.delete(id);
    }
}
