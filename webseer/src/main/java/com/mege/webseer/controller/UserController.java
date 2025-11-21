package com.mege.webseer.controller;

import com.mege.webseer.dtos.RequestLoginUserDto;
import com.mege.webseer.dtos.RequestRegisterUserDto;
import com.mege.webseer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> allUsers(){
        return userService.allUsers();
    }

    @PostMapping()
    public ResponseEntity<?> loginUser(@RequestBody RequestLoginUserDto loginUserDto){
        return userService.login(loginUserDto.name(), loginUserDto.password());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RequestRegisterUserDto registerUserDto){
        return userService.register(registerUserDto.name(), registerUserDto.password());
    }
}
