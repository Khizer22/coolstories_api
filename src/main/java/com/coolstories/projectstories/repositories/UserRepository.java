package com.coolstories.projectstories.repositories;

import com.coolstories.projectstories.domain.User;
import com.coolstories.projectstories.exceptions.PSAuthException;

public interface UserRepository {
    
    Integer create(String email, String password) throws PSAuthException;

    User findByEmailAndPassword(String email, String password) throws PSAuthException;

    Integer getCountByEmail(String email);

    User findByID(Integer userID);
}
