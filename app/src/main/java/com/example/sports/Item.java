package com.example.sports;

public class Item {
    private String name, location, days;
    private int hours;

    public Item(String description, int resid, boolean isHappy, int amount) {
        this.name = name;
        this.location = location;
        this.days = days;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
