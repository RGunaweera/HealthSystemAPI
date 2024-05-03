/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resource;

/**
 *
 * @author Randima
 */

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    // As the Person class being abstract and PersonDAO being an interface implies that Person is a base class or interface 
    // for more specialized classes like Patient and Doctor.
    // This class is empty as we do not have any specific operations or endpoints that deal directly with the Person class itself 
    // This class can act as a placeholder or a potential extension point for future functionalities
    
}
