package com.mege.webseer.services;

import com.mege.webseer.models.User;
import com.mege.webseer.models.Web;
import com.mege.webseer.repositories.UserRepository;
import com.mege.webseer.repositories.WebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class WebService {

    @Autowired
    private WebRepository webRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> allWebByUser(String userId){
        return ResponseEntity.ok(webRepository.findAllWebByUserId(userId));
    }

    public ResponseEntity<?> addWeb(String userId, String url){
        Optional<Web> foundedWeb = webRepository.findByUrl(url);
        Optional<User> foundedUser = userRepository.findById(userId);
        if(foundedUser.isEmpty()) return ResponseEntity.notFound().build();

        User user = foundedUser.get();

        if(foundedWeb.isPresent()){
            Web web = foundedWeb.get();
            web.setNumberOfAccess(web.getNumberOfAccess() + 1);
            web.setLastAccess(Instant.now());

            webRepository.save(web);
            return ResponseEntity.ok().build();
        }

        Web web = new Web();
        web.setNumberOfAccess(1);
        web.setUrl(url);
        web.setLastAccess(Instant.now());

        user.getWebList().add(web);
        web.setUser(user);

        webRepository.save(web);
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
