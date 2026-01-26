package com.flipfit.bean;

public class Booking {
    private int bookingId;
    private int userId;
    private int slotId;
    private int centreId;
    private int numberOfMembers;
    private double totalCost;
    private String status; // e.g., Confirmed, Pending, Cancelled

    public Booking(int bookingId, int userId, int slotId, int centreId, int numberOfMembers, double totalCost) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.slotId = slotId;
        this.centreId = centreId;
        this.numberOfMembers = numberOfMembers;
        this.totalCost = totalCost;
        this.status = "Confirmed";
    }

    public Booking() {}

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getSlotId() { return slotId; }
    public void setSlotId(int slotId) { this.slotId = slotId; }
    public int getCentreId() { return centreId; }
    public void setCentreId(int centreId) { this.centreId = centreId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public int getNumberOfMembers() { return numberOfMembers; }
    public void setNumberOfMembers(int numberOfMembers) { this.numberOfMembers = numberOfMembers; }
}