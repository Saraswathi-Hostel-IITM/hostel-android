package com.sarasapp.sarasapp.Objects;

/**
 * Created by adarsh on 14/4/16.
 */
public class Contact {
    private String post, name, phone, email;
    private int imageid;

    public Contact() {
    }

    public Contact(String post, String name, String phone, String email, Integer imageid) {
        this.name = name;
        this.post = post;
        this.email = email;
        this.phone = phone;
        this.imageid = imageid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String date) {
        this.post = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Caption) {
        this.email = Caption;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String Description) {
        this.phone = Description;
    }

    public Integer getImageID() {
        return imageid;
    }
}