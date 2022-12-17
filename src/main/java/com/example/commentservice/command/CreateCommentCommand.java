package com.example.commentservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class CreateCommentCommand {

    @TargetAggregateIdentifier
    private final String commentId;
    private final String user;
    private final String userid;
    private final Integer rating;
    private final String description;
    private final String recommendMenu;
    private final String imageId;
    private final String time;
    private final Integer like;
}
