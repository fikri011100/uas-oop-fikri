package com.bmcc.model;

public class Player extends User{

    private int status;
    private int jumlahPertandingan;
    private String nama;

    public Player(String username, String password) {
        super(username, password);
    }

    public Player(String username, String password, int status, int jumlahPertandingan, String nama) {
        super(username, password);
        this.status = status;
        this.jumlahPertandingan = jumlahPertandingan;
        this.nama = nama;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getJumlahPertandingan() {
        return jumlahPertandingan;
    }

    public void setJumlahPertandingan(int jumlahPertandingan) {
        this.jumlahPertandingan = jumlahPertandingan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
