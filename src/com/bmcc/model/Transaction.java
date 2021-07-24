package com.bmcc.model;

public class Transaction {
    private String usernamePlayer;
    private String idEvent;
    private String status;

    public Transaction(String usernamePlayer, String idEvent, String status) {
        this.usernamePlayer = usernamePlayer;
        this.idEvent = idEvent;
        this.status = status;
    }

    public String getUsernamePlayer() {
        return usernamePlayer;
    }

    public void setUsernamePlayer(String usernamePlayer) {
        this.usernamePlayer = usernamePlayer;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
