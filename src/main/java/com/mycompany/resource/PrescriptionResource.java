/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resource;

/**
 *
 * @author HP
 */

import com.mycompany.dao.PrescriptionDAO;
import com.mycompany.model.Prescription;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/prescriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrescriptionResource {

    private final PrescriptionDAO prescriptionDAO; // Inject the DAO interface for Prescription

    public PrescriptionResource() {
        this.prescriptionDAO = new PrescriptionDAO();
    }

    @GET
    @Path("/{id}")
    public Prescription getPrescriptionById(@PathParam("id") int id) {
        Prescription prescription = prescriptionDAO.getById(id);
        if (prescription == null) {
            throw new NotFoundException("Prescription not found with id: " + id);
        }
        return prescription;
    }

    @GET
    public List<Prescription> getAllPrescriptions() {
        return prescriptionDAO.getAll();
    }

    @POST
    public void createPrescription(Prescription prescription) {
        prescriptionDAO.save(prescription);
    }

    @PUT
    @Path("/{id}")
    public void updatePrescription(@PathParam("id") int id, Prescription updatedPrescription) {
        Prescription existingPrescription = prescriptionDAO.getById(id);
        if (existingPrescription == null) {
            throw new NotFoundException("Prescription not found with id: " + id);
        }
        updatedPrescription.setId(id); // Ensure the ID is set for the updated prescription
        prescriptionDAO.update(updatedPrescription);
    }

    @DELETE
    @Path("/{id}")
    public void deletePrescription(@PathParam("id") int id) {
        Prescription existingPrescription = prescriptionDAO.getById(id);
        if (existingPrescription == null) {
            throw new NotFoundException("Prescription not found with id: " + id);
        }
        prescriptionDAO.delete(id);
    }
}

