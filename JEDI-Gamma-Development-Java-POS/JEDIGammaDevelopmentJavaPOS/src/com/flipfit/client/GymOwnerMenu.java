package com.flipfit.client;

import com.flipfit.bean.GymCentre;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.Slot;
import com.flipfit.business.GymOwnerBusiness;
import com.flipfit.business.GymOwnerBusinessImpl;

import java.util.List;
import java.util.Scanner;

public class GymOwnerMenu {

    private final GymOwnerBusiness ownerBusiness = new GymOwnerBusinessImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu(String ownerEmail) {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("      FLIPFIT GYM OWNER DASHBOARD       ");
            System.out.println("========================================");
            System.out.println("Owner logged in: " + ownerEmail);
            System.out.println("1. Register a New Gym Centre");
            System.out.println("2. Add a Workout Slot");
            System.out.println("3. View My Registered Gym Centres");
            System.out.println("4. View All Bookings (by Centre)");
            System.out.println("5. Logout");
            System.out.print("\nPlease enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (choice) {
                case 1:
                    registerGymCentre(ownerEmail);
                    break;
                case 2:
                    addSlotToCentre();
                    break;
                case 3:
                    viewMyCentres(ownerEmail);
                    break;
                case 4:
                    viewCentreBookings();
                    break;
                case 5:
                    System.out.println("Logging out of FlipFit... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please select between 1-5.");
            }
        }
    }

    private void registerGymCentre(String ownerEmail) {
        GymCentre centre = new GymCentre();
        System.out.print("Enter Centre ID (e.g., C01): ");
        centre.setCentreId(scanner.nextLine());
        System.out.print("Enter Centre Name: ");
        centre.setCentreName(scanner.nextLine());
        System.out.print("Enter Address: ");
        centre.setAddress(scanner.nextLine());
        System.out.print("Enter City: ");
        centre.setCity(scanner.nextLine());
        centre.setOwnerEmail(ownerEmail);
        centre.setApproved(false); // Defaulting to false as per Admin approval logic

        ownerBusiness.addCentre(centre);
    }

    private void addSlotToCentre() {
        Slot slot = new Slot();
        System.out.print("Enter Centre ID for the slot: ");
        slot.setCentreId(scanner.nextLine());
        System.out.print("Enter Slot ID (e.g., S01): ");
        slot.setSlotId(scanner.nextLine());
        System.out.print("Enter Slot Time (e.g., 6AM-7AM): ");
        slot.setTime(scanner.nextLine());
        System.out.print("Enter Maximum Capacity: ");
        slot.setCapacity(scanner.nextInt());

        if (ownerBusiness.addSlot(slot)) {
            System.out.println("Slot added successfully!");
        }
    }

    private void viewMyCentres(String ownerEmail) {
        List<GymCentre> centres = ownerBusiness.viewMyCentres(ownerEmail);
        if (centres.isEmpty()) {
            System.out.println("No gym centres found under your account.");
        } else {
            System.out.println("\n--- Your Registered Gym Centres ---");
            centres.forEach(c -> {
                String status = c.isApproved() ? "[APPROVED]" : "[PENDING APPROVAL]";
                System.out.println("ID: " + c.getCentreId() + " | Name: " + c.getCentreName() + 
                                   " | Loc: " + c.getCity() + " | Status: " + status);
            });
        }
    }

    private void viewCentreBookings() {
        System.out.print("Enter Centre ID to view bookings: ");
        String centreId = scanner.nextLine();
        // viewBookings logic currently returns empty list in the business layer
        ownerBusiness.viewBookings(centreId);
        System.out.println("Displaying bookings for " + centreId + "...");
    }
}