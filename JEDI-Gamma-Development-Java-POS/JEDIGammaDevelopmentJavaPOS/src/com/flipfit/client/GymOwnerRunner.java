package com.flipfit.client;

public class GymOwnerRunner {
    public static void main(String[] args) {
        // Simulating a logged-in owner with a dummy email
        System.out.println("Starting Gym Owner Menu in Standalone Mode...");
        new GymOwnerMenu().displayMenu("test_owner@flipfit.com");
    }
}
