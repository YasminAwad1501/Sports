package com.example.sports;

import java.io.Serializable;
import java.time.LocalTime;

public class Item implements Serializable {
    private String name, location, days, hours;
    private int image;
    private boolean favorite;

    public Item(String name, String location, String days, String hours, int image, boolean favorite) {

        this.name = name;
        this.location = location;
        this.days = days;
        this.hours = hours;
        this.image = image;
        this.favorite = false;

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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}


