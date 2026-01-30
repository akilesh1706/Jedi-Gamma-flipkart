package com.flipkart.rest;

import com.codahale.metrics.annotation.Timed;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitGymOwner;
import com.flipkart.business.FlipFitAdminService;
import com.flipkart.business.FlipFitGymCenterService;
import com.flipkart.business.FlipFitGymOwnerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * REST Controller for Admin operations
 */
@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlipFitAdminController {
    private final FlipFitAdminService adminService = new FlipFitAdminService();
    private final FlipFitGymOwnerService gymOwnerService = new FlipFitGymOwnerService();
    private final FlipFitGymCenterService gymCenterService = new FlipFitGymCenterService();

    /**
     * Admin login
     */
    @GET
    @Path("/login")
    @Timed
    public Response adminLogin(@QueryParam("userName") String userName, @QueryParam("password") String password) {
        try {
            if (adminService.isUserValid(userName, password)) {
                return Response.ok("{\"message\": \"Login successful\"}").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("{\"message\": \"Invalid credentials\"}")
                        .build();
            }
        } catch (Exception exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
        }
    }

    /**
     * View pending gym owners
     */
    @GET
    @Path("/gym-owner/pending-list")
    @Timed
    public Response viewPendingGymOwners() {
        try {
            List<FlipFitGymOwner> gymOwnerList = adminService.viewPendingGymOwners();
            return Response.ok(gymOwnerList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * View all gym owners
     */
    @GET
    @Path("/gym-owner/all")
    @Timed
    public Response viewGymOwners() {
        try {
            List<FlipFitGymOwner> gymOwnerList = gymOwnerService.viewAllGymOwners();
            return Response.ok(gymOwnerList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * View all gym centers
     */
    @GET
    @Path("/gym-centre/all")
    @Timed
    public Response viewAllGymCenters() {
        try {
            List<FlipFitGymCenter> gymCenterList = gymCenterService.viewAllGymCenters();
            return Response.ok(gymCenterList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * View pending gym centers
     */
    @GET
    @Path("/gym-centre/pending-list")
    @Timed
    public Response viewPendingGymCentres() {
        try {
            List<FlipFitGymCenter> gymCentreList = adminService.viewPendingGymCentres();
            return Response.ok(gymCentreList).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Approve or reject a gym owner
     */
    @POST
    @Path("/gym-owner/handle")
    @Timed
    public Response handleGymOwner(FlipFitGymApprovalRequest approvalRequest) {
        try {
            adminService.approveGymOwner(approvalRequest.getId(), approvalRequest.getChoice());
            return Response.ok("{\"message\": \"Gym Owner request handled\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"message\": \"Error handling gym owner\"}")
                    .build();
        }
    }

    /**
     * Approve or reject a gym center
     */
    @POST
    @Path("/gym-centre/handle")
    @Timed
    public Response handleGymCentre(FlipFitGymApprovalRequest approvalRequest) {
        try {
            adminService.approveGymCenter(approvalRequest.getId(), approvalRequest.getChoice());
            return Response.ok("{\"message\": \"Gym Centre request handled\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"message\": \"Error handling gym center\"}")
                    .build();
        }
    }
}