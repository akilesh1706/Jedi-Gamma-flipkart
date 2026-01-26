package com.flipfit.bean;


import java.util.ArrayList;
import java.util.List;

public class GymCentre {
    private int centreId;
    private String centreName;
    private String ownerEmail;
    private String address;
    private String city;
    private boolean isApproved;
    private int price;
    private List<Slot> slots = new ArrayList<>();

    public GymCentre(int centreId, String centreName, String address, int price) {
        this.centreId = centreId;
        this.centreName = centreName;
        this.address = address;
        this.price = price;
    }

    public GymCentre() {}

    // Getters and Setters
    public int getCentreId() { return centreId; }
    public void setCentreId(int centreId) { this.centreId = centreId; }
    public String getCentreName() { return centreName; }
    public void setCentreName(String centreName) { this.centreName = centreName; }
    public String getOwnerEmail() { return ownerEmail; }
    public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public boolean isApproved() { return isApproved; }
    public void setApproved(boolean approved) { isApproved = approved; }
    
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public List<Slot> getSlots() { return slots; }
    public void setSlots(List<Slot> slots) { this.slots = slots; }
    
    public void addSlot(Slot slot) {
        this.slots.add(slot);
    }
}