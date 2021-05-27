package com.example.bottom_project.Models;

import java.util.Calendar;

public class MyEvent {
    public String name, place;
    private String date, time;
    public boolean possible = false;

    MyEvent(){}

    public MyEvent(String name, String place, String date, String time) {
        this.name = name;
        this.place = place;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isPossible() {
        return possible;
    }

    public void setPossible(boolean possible) {
        this.possible = possible;
    }
}