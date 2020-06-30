package com.todoapp.app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private int tid;

    @ManyToOne
    @JoinColumn()
    private User user;

    @NotBlank( message = "Task name is necessary" )
    private String task_name;

    private long task_creation_date;

    private long deadline;

    private String task_description;

    private String task_status;

    public Task(){

    }

    public Task(User user, @NotBlank(message = "Task name is necessary") String task_name) {
        this.task_creation_date = System.currentTimeMillis();
        this.user = user;
        this.task_name = task_name;
        this.task_status = "Pending";
    }

    public Task(User user, @NotBlank(message = "Task name is necessary") String task_name, long deadline) {
        this.task_creation_date = System.currentTimeMillis();
        this.user = user;
        this.task_name = task_name;
        this.deadline = deadline;
        this.task_status = "Pending";
    }

    public Task(User user, @NotBlank(message = "Task name is necessary") String task_name, String task_description) {
        this.task_creation_date = System.currentTimeMillis();
        this.user = user;
        this.task_name = task_name;
        this.task_description = task_description;
        this.task_status = "Pending";
    }

    public Task(User user, @NotBlank(message = "Task name is necessary") String task_name, long deadline, String task_description) {
        this.task_creation_date = System.currentTimeMillis();
        this.user = user;
        this.task_name = task_name;
        this.deadline = deadline;
        this.task_description = task_description;
        this.task_status = "Pending";
    }


    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public long getTask_creation_date() {
        return task_creation_date;
    }

    public void setTask_creation_date(long task_creation_date) {
        this.task_creation_date = task_creation_date;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    @Override
    public String toString(){

        return "Task{" +
                "tid=" + tid +
                ", name='" + task_name + '\'' +
                ", creation_date='" + task_creation_date + '\'' +
                ", deadline='" + deadline + '\'' +
                ", description='" + task_description + '\'' +
                ", Status='" + task_status + '\'' +
                '}';
    }


}
