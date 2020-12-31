package com.example.taskmanagement;

public class MainTask {

    private int id;
    private String title;
    private String date;
    private  String time;

    public MainTask(int id, String title, String date, String time) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
    }

    // Null Constructor
    public MainTask() {
    }

    // Setters and Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    // To-String? Maybe
    @Override
    public String toString() {
        return "MainTask{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
