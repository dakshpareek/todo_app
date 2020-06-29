package com.todoapp.app.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int uid;

    @NotBlank(message = "Name can not be empty")
    private String name;

    @Email(message = "Email should be valid")
    @Column(unique=true)
    private String email;

    @NotBlank(message = "Password can not be empty")
    @Size(min = 6, max = 15, message = "Password must be between 6 and 15 characters")
    private String password;

    @Size(min = 10, max = 10, message = "Phone must contain 10 digits")
    private String phone;


    public User()
    {

    }


    public User(@NotBlank(message = "Name can not be empty") String name, @Email(message = "Email should be valid") String email, @NotBlank(message = "Password can not be empty") @Size(min = 6, max = 15, message = "Password must be between 6 and 15 characters") String password, @Size(min = 10, max = 10, message = "Phone must contain 10 digits") String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
