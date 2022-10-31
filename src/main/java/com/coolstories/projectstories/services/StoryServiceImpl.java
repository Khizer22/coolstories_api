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
        return storyRepository.findAll();
    }

    @Override
    public Story fetchStoryByID(Integer storyID) throws PSResourceNotFoundException {
        return storyRepository.findByID(storyID);
    }

    @Override
    public Story addStory(Integer userID, String title, String imageURL, String description, String text)
            throws PSBadRequestException {
        
        Integer storyID = storyRepository.create(userID, title, imageURL, description, text);

        return storyRepository.findByID(storyID);
    }

    @Override
    public void UpdateStory(Integer userID,Integer storyID, Story story)
            throws PSBadRequestException {
        storyRepository.update(userID, storyID, story);
    }

    @Override
    public void deleteStory(Integer userID, Integer storyID) throws PSResourceNotFoundException {
        
    }

    @Override
    public void incrementStoryView(Integer storyID) throws PSBadRequestException {
        storyRepository.updateView(storyID);
    }

    @Override
    public void incrementStoryDownload(Integer storyID) throws PSBadRequestException {
        storyRepository.updateDownload(storyID);     
    }
    
}
