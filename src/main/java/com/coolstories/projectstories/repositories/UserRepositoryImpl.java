package com.coolstories.projectstories.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.coolstories.projectstories.domain.User;
import com.coolstories.projectstories.exceptions.PSAuthException;


@Repository
public class UserRepositoryImpl implements UserRepository{

    private static final String SQL_CREATE = "INSERT INTO PS_USERS(USER_ID, EMAIL, PASSWORD) VALUES(NEXTVAL('PS_USERS_SEQ'), ?, ?)";
    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM PS_USERS WHERE EMAIL = ?";
    private static final String SQL_FIND_BY_ID = "SELECT USER_ID, EMAIL, PASSWORD FROM PS_USERS WHERE USER_ID = ?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT USER_ID, EMAIL, PASSWORD FROM PS_USERS WHERE EMAIL = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String email, String password) throws PSAuthException {
       
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        
        try {
           
            KeyHolder keyHolder = new GeneratedKeyHolder();
            
            jdbcTemplate.update(connection -> {
                
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, email);
                ps.setString(2, hashedPassword);

                return ps;

            }, keyHolder);

            return (Integer) keyHolder.getKeys().get("USER_ID");
            
        } catch (Exception e) {
            throw new PSAuthException("Invalid details. Failed to create account.");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws PSAuthException {
        
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, userRowMapper,new Object[]{email});
        
            if (BCrypt.checkpw(password, user.getPassword()) == false)
                throw new PSAuthException("Invalid email or password");
            
            return user;

        } catch (Exception e) {
            throw new PSAuthException("Invalid email or password");
        }
    }

    @Override
    public Integer getCountByEmail(String email) {
        
        //return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email}, Integer.class);
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, Integer.class,new Object[]{email});
    }

    @Override
    public User findByID(Integer userID) {
        
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, userRowMapper,new Object[]{userID});
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(
            rs.getInt("USER_ID"), 
            rs.getString("EMAIL"), 
            rs.getString("PASSWORD")       
            );
    });
    
}
