package com.example.commentservice.query.rest;

import lombok.Data;

@Data
public class CommentRestModel {
    private String _id;
    private String user;
    private String userid;
    private int rating;
    private String description;
    private String recommendMenu;
    private String imageId;
    private String time;
    private int like;
    private String reviewId;
    private int report;
    private boolean ban;
}
