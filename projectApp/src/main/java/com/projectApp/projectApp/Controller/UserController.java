package com.projectApp.projectApp.Controller;


import com.projectApp.projectApp.Entity.User;
import com.projectApp.projectApp.Exceptions.DatabaseException;
import com.projectApp.projectApp.Model.LoginUserDto;
import com.projectApp.projectApp.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.projectApp.projectApp.Model.UserDto;
import com.projectApp.projectApp.Model.ErrorResponse;

import java.util.Optional;


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
    public String userRegistration(@RequestBody UserDto userDto){

        String message =userServices.insertUser(userDto);

        return message;

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserDto loginUserDto) {


        try {
            Optional<User> userOptional = userServices.getUser(loginUserDto);
            if (userOptional.isPresent()) {

                System.out.println("User found: ppppppppppppppppppppppp" );
                System.out.println(userOptional.get());

                return ResponseEntity.ok(userOptional.get());
            } else {
                System.out.println("User not found for: qqqqqqqqqqqqqqqqqqqqqqq" + loginUserDto);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("User not found", "No user found with provided credentials"));
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Database error", e.getMessage()));
    }}
}
