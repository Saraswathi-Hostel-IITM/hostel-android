package com.sarasapp.sarasapp.Objects;

/**
 * Created by adarsh on 14/4/16.
 */
public class DiscussionTopic {
    private String name, caption,description, idname, date;

    public DiscussionTopic() {
    }

    public DiscussionTopic(String name, String caption, String description) {
        this.name = name;
        this.caption = caption;
        this.description = description;
        this.idname = "";
        this.date = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String Caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        this.description = description;
    }
}