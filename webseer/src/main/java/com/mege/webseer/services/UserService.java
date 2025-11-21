package com.mege.webseer.services;

import com.mege.webseer.models.User;
import com.mege.webseer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> allUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<?> login(String name, String password){
        Optional<User> foundedUser = userRepository.loginUser(name, password);
        if(foundedUser.isEmpty()) return ResponseEntity.notFound().build();

        User user = foundedUser.get();
        return ResponseEntity.ok().body(user.getId());
    }

    public ResponseEntity<?> register(String name, String password){
        if(name.isBlank() || password.isBlank()) return ResponseEntity.badRequest().build();

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userRepository.save(user);
        return ResponseEntity.status(201).build();
    }
}