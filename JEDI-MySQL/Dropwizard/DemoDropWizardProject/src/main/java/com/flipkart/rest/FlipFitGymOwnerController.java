package com.flipkart.rest;

import com.codahale.metrics.annotation.Timed;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.business.FlipFitGymOwnerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/gymowner")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlipFitGymOwnerController {
    private final FlipFitGymOwnerService gymOwnerService = new FlipFitGymOwnerService();

    /**
     * Gym Owner login
     * Note: We keep @QueryParam here because GET requests typically use URL params.
     */
    @GET
    @Path("/login")
    @Timed
    public Response gymOwnerLogin(@QueryParam("userName") String userName, @QueryParam("password") String password) {
        try {
            if (gymOwnerService.loginGymOwner(userName, password)) {
                return Response.ok("{\"message\": \"Login successful\"}").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"message\": \"Invalid credentials\"}")
                        .build();
            }
        } catch (Exception exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }
    }

    /**
     * FIXED: Register a new gym owner
     * Removed all @QueryParam to allow JSON body consumption.
     */
    @POST
    @Path("/register")
    @Timed
    public Response registerGymOwner(FlipFitGymOwner gymOwner) {
        try {
            // Extract values from the bean object sent in the JSON body
            gymOwnerService.registerGymOwner(
                    gymOwner.getUserID(),
                    gymOwner.getUserName(),
                    gymOwner.getPassword(),
                    gymOwner.getEmail(),
                    gymOwner.getPanNumber(),
                    gymOwner.getCardDetails());
            return Response.ok("{\"message\": \"Gym Owner registered successfully\"}").build();
        } catch (Exception e) {
            // Modularized exception handling
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\": \"Registration failed: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Timed
    public Response viewAllGymOwners() {
        try {
            List<FlipFitGymOwner> owners = gymOwnerService.viewAllGymOwners();
            return Response.ok(owners).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/request-approval/{ownerId}")
    @Timed
    public Response requestApproval(@PathParam("ownerId") String ownerId) {
        try {
            gymOwnerService.requestGymOwnerApproval(ownerId);
            return Response.ok("{\"message\": \"Approval request sent\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}