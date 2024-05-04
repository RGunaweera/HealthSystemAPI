/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resource;

/**
 *
 * @author Randima
 */

import com.mycompany.dao.PatientDAO;
import com.mycompany.exception.UserNotFoundException;
import com.mycompany.model.Patient;
import com.mycompany.model.Person;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientResource.class);

    private final PatientDAO patientDAO;

    public PatientResource() {
        this.patientDAO = new PatientDAO();
    }
    
    @GET
    @Path("/{id}")
    public Response getPatientById(@PathParam("id") int id) {
        try {
            LOGGER.info("Getting patient by id: {}", id);
            Patient patient = (Patient) patientDAO.getById(id);
            if (patient == null) {
                throw new UserNotFoundException("Patient not found with id: " + id);
            }
            return Response.ok().entity(patient).build();
        } catch (UserNotFoundException e) {
            LOGGER.warn("Patient not found: {}", e.getMessage(), e);
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        } catch (Exception e) {
            LOGGER.error("Failed to retrieve patient with id {}: {}", id, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to retrieve patient. Please try again later.")
                           .build();
        }
    }

    @GET
    public Response getAllPatients() {
        try {
            LOGGER.info("Getting all patients");
            List<Person> patients = patientDAO.getAll();
            return Response.ok().entity(patients).build();
        } catch (Exception e) {
            LOGGER.error("Failed to retrieve patients: {}", e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to retrieve patients. Please try again later.")
                           .build();
        }
    }

    @POST
    public Response createPatient(Patient patient) {
        try {
            LOGGER.info("Creating new patient");          

            patientDAO.save(patient);
            return Response.status(Response.Status.CREATED)
                    .entity("A New Patient added.")
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        
        } catch (Exception e) {
            LOGGER.error("Failed to create patient: {}", e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to create patient. Please try again later.")
                           .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePatient(@PathParam("id") int id, Patient updatedPatient) {
        try {
            LOGGER.info("Updating patient with id: {}", id);
            Patient existingPatient = (Patient) patientDAO.getById(id);
            if (existingPatient == null) {
                throw new UserNotFoundException("Patient not found with id: " + id);
            }
            
            updatedPatient.setId(id); // Ensure the ID is set for the updated patient
            patientDAO.update(updatedPatient);
            return Response.ok()
                    .entity("Patient with id: " + id + " updated.")
                    .build();
        } catch (UserNotFoundException e) {
            LOGGER.warn("Patient not found: {}", e.getMessage(), e);
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        } catch (Exception e) {
            LOGGER.error("Failed to update patient with id {}: {}", id, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to update patient. Please try again later.")
                           .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePatient(@PathParam("id") int id) {
        try {
            LOGGER.info("Deleting patient with id: {}", id);
            Patient existingPatient = (Patient) patientDAO.getById(id);
            if (existingPatient == null) {
                throw new UserNotFoundException("Patient not found with id: " + id);
            }
            patientDAO.delete(id);
            return Response.ok()
                    .entity("Patient with id: " + id + " deleted.")
                    .build();
        } catch (UserNotFoundException e) {
            LOGGER.warn("Patient not found: {}", e.getMessage(), e);
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        } catch (Exception e) {
            LOGGER.error("Failed to delete patient with id {}: {}", id, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to delete patient. Please try again later.")
                           .build();
        }
    }
}
