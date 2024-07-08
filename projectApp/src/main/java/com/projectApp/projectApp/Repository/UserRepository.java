package com.projectApp.projectApp.Repository;

import com.projectApp.projectApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE email = :email AND password = :password")
    Optional<User>findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
    Optional<User> findByEmail(String email);
}
