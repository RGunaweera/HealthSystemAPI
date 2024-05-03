/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resource;

/**
 *
 * @author Randima
 */


import com.mycompany.dao.MedicalRecordDAO;
import com.mycompany.model.MedicalRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/medical-records")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicalRecordResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordResource.class);
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();

    @GET
    @Path("/{id}")
    public Response getMedicalRecordById(@PathParam("id") int id) {
        try {
            MedicalRecord medicalRecord = medicalRecordDAO.getById(id);
            if (medicalRecord == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Medical record not found with id: " + id)
                        .build();
            }
            return Response.ok(medicalRecord).build();
        } catch (Exception e) {
            LOGGER.error("Error retrieving medical record with ID " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving medical record with ID " + id)
                    .build();
        }
    }

    @GET
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordDAO.getAll();
    }

    @GET
    @Path("/patients/{patientId}/medical-records")
    public Response getMedicalRecordsByPatientId(@PathParam("patientId") int patientId) {
        try {
            List<MedicalRecord> medicalRecords = medicalRecordDAO.getByPatientId(patientId);
            if (medicalRecords.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No medical records found for patient with ID: " + patientId)
                        .build();
            } else {
                return Response.ok(medicalRecords).build();
            }
        } catch (Exception e) {
            LOGGER.error("Error retrieving medical records for patient with ID " + patientId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving medical records for patient with ID " + patientId)
                    .build();
        }
    }

    @POST
    public Response createMedicalRecord(MedicalRecord medicalRecord) {
        try {
            if (medicalRecord == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Medical record object cannot be null")
                        .build();
            }
            medicalRecordDAO.save(medicalRecord);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            LOGGER.error("Invalid medical record data provided", e);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            LOGGER.error("Error creating medical record", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error creating medical record")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateMedicalRecord(@PathParam("id") int id, MedicalRecord updatedMedicalRecord) {
        try {
            MedicalRecord existingMedicalRecord = medicalRecordDAO.getById(id);
            if (existingMedicalRecord == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Medical record not found with id: " + id)
                        .build();
            }
            updatedMedicalRecord.setId(id);
            medicalRecordDAO.update(updatedMedicalRecord);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            LOGGER.error("Error updating medical record with ID " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error updating medical record with ID " + id)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMedicalRecord(@PathParam("id") int id) {
        try {
            MedicalRecord existingMedicalRecord = medicalRecordDAO.getById(id);
            if (existingMedicalRecord == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Medical record not found with id: " + id)
                        .build();
            }
            medicalRecordDAO.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            LOGGER.error("Error deleting medical record with ID " + id, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting medical record with ID " + id)
                    .build();
        }
    }
}
