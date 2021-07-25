package com.bmcc.model;

import com.bmcc.view.EventCount;

public class Event implements EventCount {
    private String id;
    private String name;
    private String sport;
    private String level;
    private String place;
    private String tanggal;
    private int min;
    private int max;
    private int price;
    private int ordered;
    private String status;
    private String usernameOrganizer;

    public Event(String id, String name, String sport, String level, String place,String tanggal, int min, int max, int price,int ordered, String status, String usernameOrganizer) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.level = level;
        this.place = place;
        this.min = min;
        this.max = max;
        this.tanggal = tanggal;
        this.price = price;
        this.ordered = ordered;
        this.status = status;
        this.usernameOrganizer = usernameOrganizer;
    }

    public int getOrdered() {
        return ordered;
    }

    public void setOredered(int ordered) {
        this.ordered = ordered;
    }

    public String getUsernameOrganizer() {
        return usernameOrganizer;
    }

    public void setUsernameOrganizer(String usernameOrganizer) {
        this.usernameOrganizer = usernameOrganizer;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void increaseCount() {
        ordered++;
    }

    @Override
    public void decreaseCount() {
        ordered--;
    }
}
