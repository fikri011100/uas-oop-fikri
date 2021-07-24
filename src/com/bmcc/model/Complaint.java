package com.bmcc.model;

public class Complaint {
    private String username;
    private String complaint;

    public Complaint(String username, String complaint) {
        this.username = username;
        this.complaint = complaint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
