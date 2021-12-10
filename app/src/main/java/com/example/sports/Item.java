package com.example.sports;

import java.time.LocalTime;

public class Item {
    private String name, location, days, hours;

    public Item(String name, String location, String days, String hours) {

        this.name = name;
        this.location = location;
        this.days = days;
        this.hours = hours;
    }
    public Item(){}
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

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }


}


