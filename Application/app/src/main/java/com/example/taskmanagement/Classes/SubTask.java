package com.example.taskmanagement.Classes;

public class SubTask {
    private int main_id, id, status;
    private String sub_task;

    public SubTask(int main_id, int id) {
        this.main_id = main_id;
        this.id = id;
    }

    public int getMain_id() {
        return main_id;
    }

    public void setMain_id(int main_id) {
        this.main_id = main_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSub_task() {
        return sub_task;
    }

    public void setSub_task(String sub_task) {
        this.sub_task = sub_task;
    }
}
