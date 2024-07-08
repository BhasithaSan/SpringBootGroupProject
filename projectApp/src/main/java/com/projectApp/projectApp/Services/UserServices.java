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

            userRepository.save(newUser);
            return "User inserted successfully";
        } catch (Exception e) {

            e.printStackTrace();
            return "Error inserting user: " + e.getMessage();
        }
    }

    public Optional<User> getUser(LoginUserDto loginUserDto) {
        try {
            System.out.println(loginUserDto.getEmail());
            System.out.println(loginUserDto.getPassword());
            Optional<User> user= userRepository.findByEmailAndPassword(loginUserDto.getEmail(),loginUserDto.getPassword());


            return user;
        } catch (DataAccessException e) {
            throw new DatabaseException("Error accessing user data", e);
        }
    }



}
