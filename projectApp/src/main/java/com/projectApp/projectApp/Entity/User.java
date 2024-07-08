package com.projectApp.projectApp.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String name;

    private String email;
    @Column(name = "contact_no")
    private  String contactNo;

    private String password;

    public User(String name, String email, String contactNo, String password) {
            this.name=name;
            this.email=email;
            this.contactNo=contactNo;
            this.password=password;
    }
    public User(){

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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
