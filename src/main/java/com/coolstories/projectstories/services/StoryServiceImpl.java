package com.coolstories.projectstories.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolstories.projectstories.domain.Story;
import com.coolstories.projectstories.exceptions.PSBadRequestException;
import com.coolstories.projectstories.exceptions.PSResourceNotFoundException;
import com.coolstories.projectstories.repositories.StoryRepository;

@Service
@Transactional
public class StoryServiceImpl implements StoryService{

    @Autowired
    StoryRepository storyRepository;

    @Override
    public List<Story> fetchAllStories() {
        return null;
    }

    @Override
    public Story fetchStoryByID(Integer storyID) throws PSResourceNotFoundException {
        return null;
    }

    @Override
    public Story addStory(Integer userID, String title, String imageURL, String description, String text)
            throws PSBadRequestException {
        
        Integer storyID = storyRepository.create(userID, title, imageURL, description, text);

        return storyRepository.findByID(storyID);
    }

    @Override
    public void UpdateStory(Integer userID, String title, String imageURL, String description, String text)
            throws PSBadRequestException {
        
    }

    @Override
    public void deleteStory(Integer userID, Integer storyID) throws PSResourceNotFoundException {
        
    }
    
}
