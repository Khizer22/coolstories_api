package com.coolstories.projectstories.services;

import java.util.List;

import com.coolstories.projectstories.domain.Story;
import com.coolstories.projectstories.exceptions.PSBadRequestException;
import com.coolstories.projectstories.exceptions.PSResourceNotFoundException;

public interface StoryService {

    List<Story> fetchAllStories();
    
    Story fetchStoryByID(Integer storyID) throws PSResourceNotFoundException;

    Story addStory(Integer userID, String title,  String imageURL, String description, String text) throws PSBadRequestException;

    void UpdateStory(Integer userID, String title,  String imageURL, String description, String text) throws PSBadRequestException;

    void deleteStory(Integer userID, Integer storyID) throws PSResourceNotFoundException;
}