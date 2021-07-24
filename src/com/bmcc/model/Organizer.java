package com.bmcc.model;

public class Organizer extends User{

    private int status;
    private String nama;

    public Organizer(String username, String password) {
        super(username, password);
    }

    public Organizer(String username, String password, int status, String nama) {
        super(username, password);
        this.status = status;
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
