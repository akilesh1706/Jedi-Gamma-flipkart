package com.flipfit.bean;

public class Slot {
    private int slotId;
    private int centreId;
    private int startTime;
    private int availableSeats;
    private int maximumSeats;
    private WaitingList waitingList;

    public Slot(int slotId, int startTime, int capacity) {
        this.slotId = slotId;
        this.startTime = startTime;
        this.maximumSeats = capacity;
        this.availableSeats = capacity;
        this.waitingList = new WaitingList(slotId, slotId); // Interpreting waitListId as slotId for now
    }

    public Slot() {}

    public int getSlotId() { return slotId; }
    public void setSlotId(int slotId) { this.slotId = slotId; }
    public int getCentreId() { return centreId; }
    public void setCentreId(int centreId) { this.centreId = centreId; }

    public int getCapacity() { return maximumSeats; }
    public void setCapacity(int capacity) { this.maximumSeats = capacity; }
    
    public int getStartTime() { return startTime; }
    public void setStartTime(int startTime) { this.startTime = startTime; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public int getMaximumSeats() { return maximumSeats; }
    public void setMaximumSeats(int maximumSeats) { this.maximumSeats = maximumSeats; }

    public WaitingList getWaitingList() { return waitingList; }
    public void setWaitingList(WaitingList waitingList) { this.waitingList = waitingList; }
}