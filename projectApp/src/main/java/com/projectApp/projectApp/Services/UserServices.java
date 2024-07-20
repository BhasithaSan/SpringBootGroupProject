package com.projectApp.projectApp.Services;

import com.projectApp.projectApp.Entity.User;
import com.projectApp.projectApp.Exceptions.DatabaseException;
import com.projectApp.projectApp.Model.LoginUserDto;
import com.projectApp.projectApp.Model.UserDto;
import com.projectApp.projectApp.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    UserRepository userRepository;

    @Autowired

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public String insertUser(UserDto userDto) {
        try {
            User newUser = new User(
                    userDto.getName(),
                    userDto.getEmail(),
                    userDto.getContactNo(),
                    userDto.getPassword()
            );
            Optional<User> optionalUserEmail=userRepository.findByEmail(userDto.getEmail());
            if(optionalUserEmail.isPresent()){
                return "Email is already registered please log in";
            }else{
                userRepository.save(newUser);
                return "User inserted successfully";
            }


        } catch (Exception e) {

            e.printStackTrace();
            return "Error inserting user: " + e.getMessage();
        }
    }

    public String getUser(LoginUserDto loginUserDto) {
        try {



            Optional<User> optionalUserEmail=userRepository.findByEmail(loginUserDto.getEmail());


            if(optionalUserEmail.isPresent()) {

                Optional<User> user = userRepository.findByEmailAndPassword(loginUserDto.getEmail(), loginUserDto.getPassword());

                if(user.isPresent()){
                    return "user found";
                }
                else{
                    return "invalid password";
                }

            }
            else{
                return "user not found in given email";
            }


        } catch (DataAccessException e) {
            throw new DatabaseException("Error accessing user data", e);
        }
    }



}
