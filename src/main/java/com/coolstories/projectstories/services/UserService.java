package com.coolstories.projectstories.services;

import com.coolstories.projectstories.domain.User;
import com.coolstories.projectstories.exceptions.PSAuthException;

public interface UserService {
    User validateUser(String email, String password) throws PSAuthException;
    
    User registerUser(String email, String password) throws PSAuthException;

    
}
