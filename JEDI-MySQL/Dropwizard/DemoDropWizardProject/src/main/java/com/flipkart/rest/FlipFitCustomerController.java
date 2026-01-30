package com.flipkart.rest;

import com.codahale.metrics.annotation.Timed;
import com.flipkart.bean.FlipFitBooking;
import com.flipkart.bean.FlipFitCustomer;
import com.flipkart.bean.FlipFitGymCenter;
import com.flipkart.bean.FlipFitSlot;
import com.flipkart.business.FlipFitCustomerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * REST Controller for Customer operations
 */
@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlipFitCustomerController {
    private final FlipFitCustomerService customerService = new FlipFitCustomerService();

    /**
     * Customer login
     */
    @GET
    @Path("/login")
    @Timed
    public Response customerLogin(@QueryParam("userName") String userName, @QueryParam("password") String password) {
        try {
            if (customerService.isUserValid(userName, password)) {
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
     * Register a new customer
     */
    @POST
    @Path("/register")
    @Timed
    public Response registerCustomer(@QueryParam("userName") String userName,
            @QueryParam("password") String password,
            @QueryParam("email") String email,
            @QueryParam("phoneNumber") String phoneNumber,
            @QueryParam("cardNumber") String cardNumber) {
        try {
            customerService.registerCustomer(userName, password, email, phoneNumber, cardNumber);
            return Response.ok("{\"message\": \"Customer registered successfully\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"message\": \"Registration failed\"}")
                    .build();
        }
    }

    /**
     * View customer profile
     */
    @GET
    @Path("/profile/{userName}")
    @Timed
    public Response viewMyProfile(@PathParam("userName") String userName) {
        try {
            FlipFitCustomer customer = customerService.viewMyProfile(userName);
            return Response.ok(customer).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\": \"User not found\"}").build();
        }
    }

    /**
     * View gym centers by city
     */
    @GET
    @Path("/gym-centers")
    @Timed
    public Response getGymCentersByCity(@QueryParam("city") String city) {
        try {
            List<FlipFitGymCenter> centers = customerService.getAllGymCenterDetailsByCity(city);
            return Response.ok(centers).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * View available slots
     */
    @GET
    @Path("/available-slots")
    @Timed
    public Response getAvailableSlots(@QueryParam("centerId") String centerId) {
        try {
            // Using current date for testing
            List<FlipFitSlot> slots = customerService.getAvailableSlots(centerId, new Date());
            return Response.ok(slots).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Book a slot
     */
    @POST
    @Path("/book-slot")
    @Timed
    public Response bookSlot(@QueryParam("userName") String userName,
            @QueryParam("centerId") String centerId,
            @QueryParam("slotId") String slotId) {
        try {
            boolean success = customerService.bookSlot(userName, new Date(), slotId, centerId);
            if (success) {
                return Response.ok("{\"message\": \"Slot booked successfully\"}").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("{\"message\": \"Booking failed\"}").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * View customer bookings
     */
    @GET
    @Path("/bookings/{userName}")
    @Timed
    public Response getCustomerBookings(@PathParam("userName") String userName) {
        try {
            List<FlipFitBooking> bookings = customerService.getCustomerBookings(userName);
            return Response.ok(bookings).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
