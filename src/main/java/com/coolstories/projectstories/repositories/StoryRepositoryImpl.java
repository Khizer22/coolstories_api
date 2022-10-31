package com.coolstories.projectstories.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.coolstories.projectstories.domain.Story;
import com.coolstories.projectstories.exceptions.PSBadRequestException;
import com.coolstories.projectstories.exceptions.PSResourceNotFoundException;

@Repository
public class StoryRepositoryImpl implements StoryRepository{

    private static final String SQL_FIND_BY_ID = "SELECT STORY_ID, USER_ID, IMAGEURL, TITLE , DESCRIPTION, TEXT, VIEWS, DOWNLOADS FROM PS_STORIES WHERE STORY_ID = ?";
    private static final String SQL_CREATE = "INSERT INTO PS_STORIES (STORY_ID, USER_ID, TITLE, IMAGEURL, DESCRIPTION, TEXT, VIEWS, DOWNLOADS) " 
    + "VALUES(NEXTVAL('PS_STORIES_SEQ'), ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_FIND_ALL = "SELECT STORY_ID, USER_ID, IMAGEURL, TITLE , DESCRIPTION, TEXT, VIEWS, DOWNLOADS FROM PS_STORIES ORDER BY VIEWS DESC";
    private static final String SQL_UPDATE = "UPDATE PS_STORIES SET IMAGEURL = ?, TITLE = ?, DESCRIPTION = ?, TEXT = ? WHERE USER_ID = ? AND STORY_ID = ?";
    private static final String SQL_UPDATE_VIEW = "UPDATE PS_STORIES SET VIEWS = VIEWS + 1 WHERE STORY_ID = ?";
    private static final String SQL_UPDATE_DOWNLOAD = "UPDATE PS_STORIES SET DOWNLOADS = DOWNLOADS + 1 WHERE STORY_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Story> findAll() throws PSResourceNotFoundException {
        
        return jdbcTemplate.query(SQL_FIND_ALL, storyRowMapper, new Object[]{});
    }

    @Override
    public Story findByID(Integer storyID) throws PSResourceNotFoundException {
        
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, storyRowMapper, new Object[]{storyID});
        } catch (Exception e) {
            throw new PSResourceNotFoundException("Story not found");
        }
    }

    @Override
    public Integer create(Integer userID, String title, String imageURL, String description, String text)
            throws PSBadRequestException {
        
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userID);
                ps.setString(2, title);
                ps.setString(3, imageURL);
                ps.setString(4, description);
                ps.setString(5, text);
                ps.setInt(6, 1);
                ps.setInt(7,0);
                return ps;
            }, keyHolder);

            return (Integer) keyHolder.getKeys().get("STORY_ID");

        } catch (Exception e) {
            throw new PSBadRequestException(e.toString());
        }
    }

    @Override
    public void update(Integer userID, Integer storyID, Story story)
            throws PSBadRequestException {
        
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{story.getImageURL(), story.getTitle(), story.getDescription(), story.getText(), userID, storyID});
        } catch (Exception e) {
           throw new PSBadRequestException("Invalid Request");
        }
    }

    @Override
    public void updateView(Integer storyID)
            throws PSBadRequestException {
        
        try {
            jdbcTemplate.update(SQL_UPDATE_VIEW, new Object[]{storyID});
        } catch (Exception e) {
           throw new PSBadRequestException("Invalid Request");
        }
    }

    @Override
    public void updateDownload(Integer storyID)
            throws PSBadRequestException {
        
        try {
            jdbcTemplate.update(SQL_UPDATE_DOWNLOAD, new Object[]{storyID});
        } catch (Exception e) {
           throw new PSBadRequestException("Invalid Request");
        }
    }

    @Override
    public void removeByID(Integer userID, Integer storyID) {
        
    }

    private RowMapper<Story> storyRowMapper = ((rs,rowNum) -> {
        return new Story(
            rs.getInt("STORY_ID"),
            rs.getInt("USER_ID"),
            rs.getString("TITLE"),
            rs.getString("IMAGEURL"),
            rs.getString("DESCRIPTION"),
            rs.getString("TEXT"),
            rs.getInt("VIEWS"),
            rs.getInt("DOWNLOADS")
        );
    });    
}