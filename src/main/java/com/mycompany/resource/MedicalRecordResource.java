/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resource;

/**
 *
 * @author HP
 */


import com.mycompany.dao.MedicalRecordDAO;
import com.mycompany.model.MedicalRecord;

import javax.inject.Inject;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentResource.class);

    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();

    @GET
    @Path("/{id}")
    public Response getMedicalRecordById(@PathParam("id") int id) {
        MedicalRecord medicalRecord = medicalRecordDAO.getById(id);
        if (medicalRecord == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Medical record not found with id: " + id)
                    .build();
        }
        return Response.ok(medicalRecord).build();
    }

    @GET
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordDAO.getAll();
    }
    
    @GET
    @Path("/patients/{patientId}/medical-records")
    public Response getMedicalRecordsByPatientId(@PathParam("patientId") int patientId) {
        List<MedicalRecord> medicalRecords = medicalRecordDAO.getByPatientId(patientId);
        if (medicalRecords.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No medical records found for patient with ID: " + patientId)
                    .build();
        } else {
            return Response.ok(medicalRecords).build();
        }
    }

    @POST
    public Response createMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecord == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Medical record object cannot be null")
                    .build();
        }
        medicalRecordDAO.save(medicalRecord);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateMedicalRecord(@PathParam("id") int id, MedicalRecord updatedMedicalRecord) {
        MedicalRecord existingMedicalRecord = medicalRecordDAO.getById(id);
        if (existingMedicalRecord == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Medical record not found with id: " + id)
                    .build();
        }
        updatedMedicalRecord.setId(id); 
        medicalRecordDAO.update(updatedMedicalRecord);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMedicalRecord(@PathParam("id") int id) {
        MedicalRecord existingMedicalRecord = medicalRecordDAO.getById(id);
        if (existingMedicalRecord == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Medical record not found with id: " + id)
                    .build();
        }
        medicalRecordDAO.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
