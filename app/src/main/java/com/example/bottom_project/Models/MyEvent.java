package com.example.bottom_project.Models;

import java.sql.Time;
import java.sql.Date;

public class MyEvent {
    public String name;
    public int id = 0;
//    Date date;
//    Time time;
    public boolean possible = false;
    public MyEvent(String name, Date date, Time time){
        this.name = name;
//        this.date = date;
//        this.time = time;
    }
    public MyEvent(String name){
        this.name = name;
        this.id = id;
        id++;
    }
}
