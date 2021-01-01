package com.example.taskmanagement.classes;

import java.sql.Time;
import java.util.Date;

public class MainTask {

    private String title;
    private Date date;
    private Time time;

    public MainTask(){

    }

    // Getters
    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
