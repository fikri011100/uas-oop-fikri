package com.bmcc.model;

public class Lencana {
    private String username;
    private String lencana;

    public Lencana(String username, String lencana) {
        this.username = username;
        this.lencana = lencana;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLencana() {
        return lencana;
    }

    public void setLencana(String lencana) {
        this.lencana = lencana;
    }
}
