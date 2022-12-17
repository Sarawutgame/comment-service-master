package com.example.commentservice.rest;

import lombok.Data;

@Data
public class CreateCommentModel {
    private String user;
    private String userid;
    private Integer rating;
    private String description;
    private String recommendMenu;
    private String imageId;
    private String time;
    private Integer like;
}
