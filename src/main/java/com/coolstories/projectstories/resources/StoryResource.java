package com.coolstories.projectstories.resources;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Encoder.Text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String getAllStories(HttpServletRequest request){
        return "Banana";
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
}
