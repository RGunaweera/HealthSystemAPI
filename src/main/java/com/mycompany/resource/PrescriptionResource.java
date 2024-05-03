/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resource;

/**
 *
 * @author Randima
 */

import com.mycompany.dao.PrescriptionDAO;
import com.mycompany.model.Prescription;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/prescriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrescriptionResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionResource.class);

    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    @GET
    @Path("/{id}")
    public Response getPrescriptionById(@PathParam("id") int id) {
        try {
            Prescription prescription = prescriptionDAO.getById(id);
            if (prescription == null) {
                LOGGER.info("Prescription not found with id: " + id);
                return Response.status(Response.Status.NOT_FOUND).entity("Prescription not found with id: " + id).build();
            }
            return Response.ok(prescription).build();
        } catch (Exception e) {
            LOGGER.error("Error getting prescription by id: " + id, e);
            return Response.serverError().entity("Error getting prescription by id: " + id).build();
        }
    }

    @GET
    public Response getAllPrescriptions() {
        try {
            List<Prescription> prescriptions = prescriptionDAO.getAll();
            return Response.ok(prescriptions).build();
        } catch (Exception e) {
            LOGGER.error("Error getting all prescriptions", e);
            return Response.serverError().entity("Error getting all prescriptions").build();
        }
    }

    @POST
    public Response createPrescription(Prescription prescription) {
        try {
            prescriptionDAO.save(prescription);
            return Response.ok("Prescription created successfully").build();
        } catch (Exception e) {
            LOGGER.error("Error creating prescription", e);
            return Response.serverError().entity("Error creating prescription").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePrescription(@PathParam("id") int id, Prescription updatedPrescription) {
        try {
            Prescription existingPrescription = prescriptionDAO.getById(id);
            if (existingPrescription == null) {
                LOGGER.info("Prescription not found with id: " + id);
                return Response.status(Response.Status.NOT_FOUND).entity("Prescription not found with id: " + id).build();
            }
            updatedPrescription.setId(id); // Ensure the ID is set for the updated prescription
            prescriptionDAO.update(updatedPrescription);
            return Response.ok("Prescription updated successfully").build();
        } catch (Exception e) {
            LOGGER.error("Error updating prescription with id: " + id, e);
            return Response.serverError().entity("Error updating prescription with id: " + id).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePrescription(@PathParam("id") int id) {
        try {
            Prescription existingPrescription = prescriptionDAO.getById(id);
            if (existingPrescription == null) {
                LOGGER.info("Prescription not found with id: " + id);
                return Response.status(Response.Status.NOT_FOUND).entity("Prescription not found with id: " + id).build();
            }
            prescriptionDAO.delete(id);
            return Response.ok("Prescription deleted successfully").build();
        } catch (Exception e) {
            LOGGER.error("Error deleting prescription with id: " + id, e);
            return Response.serverError().entity("Error deleting prescription with id: " + id).build();
        }
    }
}
