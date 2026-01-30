package com.flipkart.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Helper class for Admin approval requests
 */
public class FlipFitGymApprovalRequest {
    private String id;
    private boolean choice;

    public FlipFitGymApprovalRequest() {
    }

    public FlipFitGymApprovalRequest(String id, boolean choice) {
        this.id = id;
        this.choice = choice;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty
    public boolean getChoice() {
        return choice;
    }

    @JsonProperty
    public void setChoice(boolean choice) {
        this.choice = choice;
    }
}
