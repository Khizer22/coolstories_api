package com.coolstories.projectstories.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coolstories.projectstories.domain.Story;
import com.coolstories.projectstories.services.StoryService;

@RestController
@RequestMapping("/api/stories")
public class StoryResource {

    @Autowired
    StoryService storyService;
    
    @GetMapping("")
    public ResponseEntity<List<Story>> getAllStories(HttpServletRequest request){
        List<Story> stories = storyService.fetchAllStories();

        return new ResponseEntity<>(stories, HttpStatus.OK);
    }

    @GetMapping("/{storyID}")
    public ResponseEntity<Story> getStoryByID(HttpServletRequest request, @PathVariable("storyID") Integer storyID){

        Story story = storyService.fetchStoryByID(storyID);

        return new ResponseEntity<>(story, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Story> addStory(HttpServletRequest request, @RequestBody Map<String, Object> storyMap){
        
        //int userID = (Integer) request.getAttribute("userID");
        Integer userID = (Integer) storyMap.get("userID");
        String title = (String) storyMap.get("title");
        String imageURL = (String) storyMap.get("imageURL");
        String description = (String) storyMap.get("description");
        String text = (String) storyMap.get("text");

        Story story = storyService.addStory(userID, title, imageURL, description, text);

        return new ResponseEntity<>(story, HttpStatus.CREATED);
    }

    @PutMapping("/{storyID}")
    public ResponseEntity<Map<String,Boolean>> updateStory (HttpServletRequest request, @PathVariable("storyID") Integer storyID, @RequestBody Story story){

        storyService.UpdateStory(story.getUserID(), storyID, story);

        Map<String,Boolean> map = new HashMap<>();
        map.put("Success", true);

        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @PutMapping("/{storyID}/incview")
    public ResponseEntity<Map<String,Boolean>> incrementStoryView (HttpServletRequest request, @PathVariable("storyID") Integer storyID){

        storyService.incrementStoryView(storyID);

        Map<String,Boolean> map = new HashMap<>();
        map.put("Success", true);

        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @PutMapping("/{storyID}/incdownload")
    public ResponseEntity<Map<String,Boolean>> incrementStoryDownload (HttpServletRequest request, @PathVariable("storyID") Integer storyID){

        storyService.incrementStoryDownload(storyID);

        Map<String,Boolean> map = new HashMap<>();
        map.put("Success", true);

        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
