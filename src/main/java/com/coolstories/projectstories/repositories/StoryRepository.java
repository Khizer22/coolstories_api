package com.coolstories.projectstories.repositories;

import java.util.List;

import com.coolstories.projectstories.domain.Story;
import com.coolstories.projectstories.exceptions.PSBadRequestException;
import com.coolstories.projectstories.exceptions.PSResourceNotFoundException;

public interface StoryRepository {
    
    List<Story> findAll() throws PSResourceNotFoundException;

    Story findByID(Integer storyID) throws PSResourceNotFoundException;

    Integer create(Integer userID, String title, String imageURL, String description, String text) throws PSBadRequestException;

    void update(Integer userID, Integer storyID, Story story) throws PSBadRequestException;

    void updateView(Integer storyID) throws PSBadRequestException;

    void updateDownload(Integer storyID) throws PSBadRequestException;

    void removeByID(Integer userID, Integer storyID);
}
