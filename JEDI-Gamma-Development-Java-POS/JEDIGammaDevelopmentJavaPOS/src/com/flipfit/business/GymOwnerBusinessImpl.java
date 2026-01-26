package com.flipfit.business;

import com.flipfit.bean.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GymOwnerBusinessImpl implements GymOwnerBusiness {

    // Hardcoded In-Memory Storage
    private static List<GymOwner> owners = new ArrayList<>();
    private static List<GymCentre> centres = new ArrayList<>();
    private static List<Slot> slots = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();

    @Override
    public void addCentre(GymCentre centre) {
        centres.add(centre);
        System.out.println("Success: Gym Centre '" + centre.getCentreName() + "' added to FlipFit.");
    }

    @Override
    public boolean addSlot(Slot slot) {
        slots.add(slot);
        System.out.println("Success: Slot added for Centre ID: " + slot.getCentreId());
        return true;
    }

    @Override
    public List<GymCentre> viewMyCentres(String ownerEmail) {
        return centres.stream()
                .filter(c -> c.getOwnerEmail().equalsIgnoreCase(ownerEmail))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> viewBookings(String centreId) {
        return bookings.stream()
                .filter(b -> b.getCentreId().equalsIgnoreCase(centreId))
                .collect(Collectors.toList());
    }

    @Override
    public GymOwner createGymOwner(String name, String email, String password, String phone, String pan, String address) {
        GymOwner newOwner = new GymOwner();
        newOwner.setOwnerName(name);
        newOwner.setOwnerEmailAddress(email);
        newOwner.setPassword(password);
        newOwner.setOwnerPhone(phone);
        newOwner.setOwnerPanNum(pan);
        newOwner.setOwnerAddress(address);
        newOwner.setApproved(false); // Initially pending Admin approval

        owners.add(newOwner);
        return newOwner;
    }

    @Override
    public boolean login(String email, String password) {
        return owners.stream()
                .anyMatch(o -> o.getOwnerEmailAddress().equalsIgnoreCase(email) 
                          && o.getPassword().equals(password));
    }
}