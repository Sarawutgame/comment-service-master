package com.example.commentservice.command.rest;

import lombok.Data;

@Data
public class CreateCommentModel {
    private String user;
    private String userid;
    private int rating;
    private String description;
    private String recommendMenu;
    private String imageId;
    private String time;
    private int like;
    private String reviewId;
    private boolean report;
    private boolean ban;
}
