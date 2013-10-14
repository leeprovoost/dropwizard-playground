package com.leeprovoost.dropwizard_playground.resources;

import com.leeprovoost.dropwizard_playground.core.Registration;
import com.leeprovoost.dropwizard_playground.service.RegistrationNotFoundException;
import com.leeprovoost.dropwizard_playground.service.RegistrationService;
import com.wordnik.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(value = "/registration")
@Api(value = "/registration", description = "Operations on Registrations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistrationResource {

    private RegistrationService registrationService;

    public RegistrationResource(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GET
    @Path("/{id}")
    @ApiOperation(
    		value = "Get a Registration object for a given ObjectId ",
    		notes = "<p><strong>Service Name</strong>: getRegistration (internal)</p>", 
            response = Registration.class)
    @ApiResponses(value = {
    		  @ApiResponse(code = 200, message = "Registration found"),
    		  @ApiResponse(code = 404, message = "Registration not found") 
    		})
    public Response getRegistration(
    		@ApiParam(value = "Registration ObjectId", required = true, allowMultiple = false) @QueryParam("id") 
    				String id) {	 	
        try {
            Registration registration = registrationService.findById(id);
            return Response.ok(registration).build();
        } catch (RegistrationNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @ApiOperation(value = "Create a new Registration",
    notes = "<p><strong>Service Name</strong>: addRegistration (internal)</p>")
    @ApiResponses(value = {
  		  @ApiResponse(code = 201, message = "New Registration created")
  		})
    // TODO deal with errors
    public Response addRegistration(@ApiParam(value = "New Registration JSON Object", required = true) 
    										Registration registration) {
        Registration createdRegistration = registrationService.create(registration);
        return Response.status(Response.Status.CREATED).entity(createdRegistration).build();
    }

}
