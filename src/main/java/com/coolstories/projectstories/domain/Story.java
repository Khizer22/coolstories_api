package com.coolstories.projectstories.domain;

public class Story {

    private Integer storyID;
    private Integer userID;
    private String imageURL;
    private String title;
    private String description;
    private String text;
    private Integer views;
    private Integer downloads;

    public Story(){}

    public Story(Integer storyID, Integer userID, String title, String imageURL, String description, String text, Integer views,
    Integer downloads) {
        this.storyID = storyID;
        this.userID = userID;
        this.imageURL = imageURL;
        this.title = title;
        this.description = description;
        this.text = text;
        this.views = views;
        this.downloads = downloads;
    }
    
    public Integer getStoryID() {
        return storyID;
    }

    public void setStoryID(Integer storyID) {
        this.storyID = storyID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
    
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

}
