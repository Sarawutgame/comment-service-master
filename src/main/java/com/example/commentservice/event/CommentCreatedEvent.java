package com.example.commentservice.event;


import lombok.Data;

@Data
public class CommentCreatedEvent {
    private String commentId;
    private String user;
    private String userid;
    private Integer rating;
    private String description;
    private String recommendMenu;
    private String imageId;
    private String time;
    private Integer like;
}
