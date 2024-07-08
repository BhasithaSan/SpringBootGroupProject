package com.projectApp.projectApp.Model;



public class UserDto {
    private String contactNo;
    private String email;
    private String name;
    private String password;

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public UserDto(String contactNo, String email, String name, String password) {
//        this.contactNo = contactNo;
//        this.email = email;
//        this.name = name;
//        this.password = password;
//    }
}
