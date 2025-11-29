package com.mege.webseer.controller;

import com.mege.webseer.dtos.RequestCreateUrlByUserDto;
import com.mege.webseer.services.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/url")
@RestController
public class WebController {

    @Autowired
    private WebService webService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> allUrlByUser(@PathVariable("userId")String userId){
        return webService.allWebByUser(userId);
    }

    @PostMapping
    public ResponseEntity<?> saveUrlByUser(@RequestBody RequestCreateUrlByUserDto createUrlByUserDto){
        return webService.addWeb(createUrlByUserDto.userId(), createUrlByUserDto.url(), createUrlByUserDto.mode());
    }
}
