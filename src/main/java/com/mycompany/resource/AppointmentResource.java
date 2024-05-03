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
import com.mycompany.exception.UserNotFoundException;
import com.mycompany.model.Appointment;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/appointments")
public class AppointmentResource {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentResource.class);

    private AppointmentDAO appointmentDAO = new AppointmentDAO();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        try {
            LOGGER.info("Getting all appointments");
            List<Appointment> appointments = appointmentDAO.getAll();
            return Response.ok().entity(appointments).build();
        } catch (Exception e) {
            LOGGER.error("Failed to retrieve appointments: {}", e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to retrieve appointments. Please try again later.")
                           .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentById(@PathParam("id") int id) {
        try {
            LOGGER.info("Getting appointment by id: {}", id);
            Appointment appointment = appointmentDAO.getById(id);
            if (appointment == null) {
                throw new UserNotFoundException("Appointment with id : " + id + " not found.");
            }
            return Response.ok().entity(appointment).build();
        } catch (UserNotFoundException e) {
            LOGGER.warn("User not found: {}", e.getMessage(), e);
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        } catch (Exception e) {
            LOGGER.error("Failed to retrieve appointment with id {}: {}", id, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to retrieve appointment. Please try again later.")
                           .build();
        }
    }

    @POST
    public Response createAppointment(Appointment appointment) {
        try {
            
            appointmentDAO.save(appointment);
            return Response.status(Response.Status.CREATED)
                    .entity("New appoinement created.")
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("id") int id, Appointment updatedAppointment) {
        try {
            LOGGER.info("Updating appointment with id: {}", id);
            Appointment existingAppointment = appointmentDAO.getById(id);
            if (existingAppointment == null) {
                throw new NotFoundException("Appointment not found with id: " + id);
            }
            updatedAppointment.setId(id); // Ensure the ID is set for the updated appointment
            appointmentDAO.update(updatedAppointment);
            return Response.ok()
                    .entity("Appointment with id: " + id + " updated.")
                    .build();
        } catch (NotFoundException e) {
            LOGGER.warn("Appointment not found: {}", e.getMessage(), e);
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        } catch (Exception e) {
            LOGGER.error("Failed to update appointment with id {}: {}", id, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to update appointment. Please try again later.")
                           .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAppointment(@PathParam("id") int id) {
        try {
            LOGGER.info("Deleting appointment with id: {}", id);
            Appointment existingAppointment = appointmentDAO.getById(id);
            if (existingAppointment == null) {
                throw new NotFoundException("Appointment not found with id: " + id);
            }
            appointmentDAO.delete(id);
            return Response.ok().build();
        } catch (NotFoundException e) {
            LOGGER.warn("Appointment not found: {}", e.getMessage(), e);
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        } catch (Exception e) {
            LOGGER.error("Failed to delete appointment with id {}: {}", id, e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to delete appointment. Please try again later.")
                           .build();
        }
    }
}

