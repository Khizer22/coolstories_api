package com.coolstories.projectstories.services;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolstories.projectstories.domain.User;
import com.coolstories.projectstories.exceptions.PSAuthException;
import com.coolstories.projectstories.repositories.UserRepository;

@Service
@Transactional 
public class UserServiceImpl implements UserService  {
    
    //Using an interface, decoupling accross tiers
    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws PSAuthException {
        
        if (email != null)
            email.toLowerCase();

        return userRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public User registerUser(String email, String password) throws PSAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        
        if (email != null)
            email.toLowerCase();

        if (!pattern.matcher(email).matches())
            throw new PSAuthException("Invalid email format");
        
        Integer emailInUse = userRepository.getCountByEmail(email);

        if (emailInUse > 0)
            throw new PSAuthException("Email already in use.");
        
        Integer userID = userRepository.create(email,password);
        
        return userRepository.findByID(userID);
    }
}
