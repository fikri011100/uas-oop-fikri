package com.bmcc.model;

import com.bmcc.view.MatchCount;

public class Player extends User implements MatchCount {

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

    public String getLevel(int match) {
        String result;
        if (match > 1 && match < 5) {
            result = "Beginner";
        } else if (match > 6 && match < 10) {
            result = "Intermediate";
        } else {
            result = "Advance";
        }
        return result;
    }

    @Override
    public void increaseMatch() {
        jumlahPertandingan++;
    }

    @Override
    public void decreaseMatch() {
        jumlahPertandingan--;
    }
}
