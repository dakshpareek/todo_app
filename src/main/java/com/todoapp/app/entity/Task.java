package com.todoapp.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class Task {

    @Id
    @GeneratedValue
    private int tid;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private User user;

    @NotBlank( message = "Task name is necessary" )
    private String task_name;

    //@Temporal(TemporalType.DATE)
    //@JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy", timezone = JsonFormat.DEFAULT_TIMEZONE)
    //@CreatedDate
    //@Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime task_creation_date;

    //private Date task_creation_date;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy", timezone = JsonFormat.DEFAULT_TIMEZONE)
    private Date deadline;

    @UpdateTimestamp
    private LocalDateTime task_updated_date;

    private String task_description;

    private boolean taskStatus = false;

    public Task(){

    }


    public Task(User user, @NotBlank(message = "Task name is necessary") String task_name, Date deadline, String task_description,boolean taskStatus) {
        //this.task_creation_date = new Date();
        this.user = user;
        this.task_name = task_name;
        this.deadline = deadline;
        this.task_description = task_description;
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

    public LocalDateTime getTask_creation_date() {
        return task_creation_date;
    }

    public void setTask_creation_date(LocalDateTime task_creation_date) {
        this.task_creation_date = task_creation_date;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getTask_description() {
        return task_description;
    }

    public LocalDateTime getTask_updated_date() {
        return task_updated_date;
    }

    public void setTask_updated_date(LocalDateTime task_updated_date) {
        this.task_updated_date = task_updated_date;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }


    public boolean getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }



    @Override
    public String toString(){

        return "Task{" +
                "tid=" + tid +
                ", name='" + task_name + '\'' +
                ", creation_date='" + task_creation_date + '\'' +
                ", deadline='" + deadline + '\'' +
                ", description='" + task_description + '\'' +
                ", Status='" + taskStatus + '\'' +
                '}';
    }


}
