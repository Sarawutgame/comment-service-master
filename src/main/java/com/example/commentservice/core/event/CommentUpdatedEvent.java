package com.example.commentservice.core.event;

import lombok.Data;

@Data
public class CommentUpdatedEvent {
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
    private boolean report;
    private boolean ban;
}
