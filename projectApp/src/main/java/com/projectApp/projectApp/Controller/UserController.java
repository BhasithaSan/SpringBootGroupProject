package com.projectApp.projectApp.Controller;


import com.projectApp.projectApp.Exceptions.DatabaseException;
import com.projectApp.projectApp.Model.LoginUserDto;
import com.projectApp.projectApp.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.projectApp.projectApp.Model.UserDto;
import com.projectApp.projectApp.Model.Response;


@RequestMapping("/Api")
@RestController
@CrossOrigin("*")
public class UserController {

    private UserServices userServices;
    @Autowired
    public UserController(UserServices userServices) {
        this.userServices=userServices;
    }




    @PostMapping("/register")
    public ResponseEntity<?>  userRegistration(@RequestBody UserDto userDto) {
        String message = userServices.insertUser(userDto);

        switch (message) {
            case "User inserted successfully":
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new Response("success", message));

            case "Email is already registered please log in":
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new Response("error", message));

            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response("error", "Unexpected error occurred"));
        }
    }



    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserDto loginUserDto) {


        try {

            String message = userServices.getUser(loginUserDto);
            switch (message) {
                case "user found":
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new Response("success", "Login succesfull"));
                case "user not found in given email":
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new Response("error", "User not found for given email"));
                default:
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(new Response("error", "Invalid password"));

            }
        } catch (DatabaseException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Database error", e.getMessage()));
    }}
}
