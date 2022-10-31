package com.coolstories.projectstories.resources;

import org.springframework.web.bind.annotation.RestController;

import com.coolstories.projectstories.domain.User;
import com.coolstories.projectstories.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Integer>> loginUser(@RequestBody Map<String,Object> userMap){
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User user = userService.validateUser(email, password);

        Map<String, Integer> map = new HashMap<>();
        map.put("userID", user.getUserID());

        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Integer>> registerUser(@RequestBody Map<String,Object> userMap){
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User user = userService.registerUser(email, password);

        Map<String, Integer> map = new HashMap<>();
        map.put("userID", user.getUserID());

        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    
}
