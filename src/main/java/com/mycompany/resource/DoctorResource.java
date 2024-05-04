/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resource;

/**
 *
 * @author Randima
 */

import com.mycompany.dao.AppointmentDAO;
import com.mycompany.dao.DoctorDAO;
import com.mycompany.exception.UserNotFoundException;
import com.mycompany.model.Doctor;
import com.mycompany.model.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/doctors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoctorResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorResource.class);

    private final DoctorDAO doctorDAO;
    //private final AppointmentDAO appointmentDAO;

    public DoctorResource() {        
        this.doctorDAO = new DoctorDAO(new AppointmentDAO());
    }

    @GET
    @Path("/{id}")
    public Response getDoctorById(@PathParam("id") int id) {
        try {
            LOGGER.info("Getting doctor by id: {}", id);
            Doctor doctor = (Doctor) doctorDAO.getById(id);
            if (doctor == null) {
                throw new UserNotFoundException("Doctor not found with id: " + id);
            }
            return Response.ok().entity(doctor).build();
        } catch (UserNotFoundException e) {
            LOGGER.warn("Doctor not found: {}", e.getMessage(), e);
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        } catch (Exception e) {
            LOGGER.error("Failed to retrieve doctor with id {}: {}", id, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to retrieve doctor. Please try again later.")
                           .build();
        }
    }

    @GET
    public Response getAllDoctors() {
        try {
            LOGGER.info("Getting all doctors");
            List<Person> doctors = doctorDAO.getAll();
            return Response.ok().entity(doctors).build();
        } catch (Exception e) {
            LOGGER.error("Failed to retrieve doctors: {}", e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to retrieve doctors. Please try again later.")
                           .build();
        }
    }
    
    @GET
    @Path("/{id}/appointments")
    public Response getAppointmentsScheduled(@PathParam("id") int id) {
        try {
            LOGGER.info("Getting appointments scheduled for doctor with id: {}", id);
            int appointmentsScheduled = doctorDAO.getNumberOfScheduledAppointments(id);
            return Response.ok().entity("Appointments scheduled for doctor with id " + id + ": " + appointmentsScheduled).build();
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Failed to get appointments scheduled: {}", e.getMessage(), e);
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        } catch (Exception e) {
            LOGGER.error("Failed to get appointments scheduled for doctor with id {}: {}", id, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to get appointments scheduled for doctor. Please try again later.")
                           .build();
        }
    }

    @GET
    @Path("/{id}/available-appointments")
    public Response getAvailableAppointments(@PathParam("id") int id) {
        try {
            LOGGER.info("Getting available appointments for doctor with id: {}", id);
            int availableAppointments = doctorDAO.calculateAvailableAppointments(id);            
            return Response.ok().entity("Available appointments for doctor with id " + id + ": " + availableAppointments).build();
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Failed to get available appointments: {}", e.getMessage(), e);
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        } catch (Exception e) {
            LOGGER.error("Failed to get available appointments for doctor with id {}: {}", id, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to get available appointments for doctor. Please try again later.")
                           .build();
        }
    }

    @POST
    public Response createDoctor(Doctor doctor) {
        try {
            LOGGER.info("Creating new doctor");           
            doctorDAO.save(doctor);
            return Response.status(Response.Status.CREATED)
                    .entity("A New Doctor added.")
                    .build();
        } catch (IllegalArgumentException e) {
            LOGGER.warn(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        }
        
        catch (Exception e) {
            LOGGER.error("Failed to create doctor: {}", e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to create doctor. Please try again later.")
                           .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateDoctor(@PathParam("id") int id, Doctor updatedDoctor) {
        try {
            LOGGER.info("Updating doctor with id: {}", id);
            Doctor existingDoctor = (Doctor) doctorDAO.getById(id);
            if (existingDoctor == null) {
                throw new UserNotFoundException("Doctor not found with id: " + id);
            }
           
            updatedDoctor.setId(id); // Ensure the ID is set for the updated doctor
            doctorDAO.update(updatedDoctor);
            return Response.ok()
                    .entity("Doctor with id: " + id + " updated.")
                    .build();
        } catch (UserNotFoundException e) {
            LOGGER.warn("Doctor not found: {}", e.getMessage(), e);
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        } catch (Exception e) {
            LOGGER.error("Failed to update doctor with id {}: {}", id, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to update doctor. Please try again later.")
                           .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDoctor(@PathParam("id") int id) {
        try {
            LOGGER.info("Deleting doctor with id: {}", id);
            Doctor existingDoctor = (Doctor) doctorDAO.getById(id);
            if (existingDoctor == null) {
                throw new UserNotFoundException("Doctor not found with id: " + id);
            }
            doctorDAO.delete(id);
            return Response.ok()
                    .entity("Doctor with id: " + id + " deleted.")
                    .build();
        } catch (UserNotFoundException e) {
            LOGGER.warn("Doctor not found: {}", e.getMessage(), e);
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        } catch (Exception e) {
            LOGGER.error("Failed to delete doctor with id {}: {}", id, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to delete doctor. Please try again later.")
                           .build();
        }
    }
}

