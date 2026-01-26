package com.flipfit.business;

import com.flipfit.bean.GymCentre;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.Slot;
import com.flipfit.bean.Booking;
import java.util.List;

public interface GymOwnerBusiness {
    void addCentre(GymCentre centre);
    boolean addSlot(Slot slot);
    List<GymCentre> viewMyCentres(String ownerEmail);
    List<Booking> viewBookings(String centreId);
    GymOwner createGymOwner(String name, String email, String password, String phone, String pan, String address);
    boolean login(String email, String password);
}