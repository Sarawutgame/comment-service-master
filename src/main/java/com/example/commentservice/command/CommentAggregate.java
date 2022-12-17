package com.example.commentservice.command;

import com.example.commentservice.core.event.CommentCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class CommentAggregate {
    @AggregateIdentifier
    private String _id;
    private String user;
    private String userid;
    private Integer rating;
    private String description;
    private String recommendMenu;
    private String imageId;
    private String time;
    private Integer like;
    private String reviewId;

    public CommentAggregate() {
    }

    @CommandHandler
    public CommentAggregate(CreateCommentCommand createCommentCommand){
        if(createCommentCommand.getDescription() == null || createCommentCommand.getDescription().isBlank()){
            throw new IllegalArgumentException("Pls Enter Comment");
        }
        if(createCommentCommand.getRecommendMenu() == null || createCommentCommand.getRecommendMenu().isBlank()){
            throw new IllegalArgumentException("Please Enter Recommend Menu");
        }
        CommentCreatedEvent commentCreatedEvent = new CommentCreatedEvent();
        BeanUtils.copyProperties(createCommentCommand, commentCreatedEvent);
        AggregateLifecycle.apply(commentCreatedEvent);
    }

    @EventSourcingHandler
    public void on(CommentCreatedEvent commentCreatedEvent){
        this._id = commentCreatedEvent.get_id();
        this.user = commentCreatedEvent.getUser();
        this.userid = commentCreatedEvent.getUserid();
        this.rating = commentCreatedEvent.getRating();
        this.description = commentCreatedEvent.getDescription();
        this.recommendMenu = commentCreatedEvent.getRecommendMenu();
        this.imageId = commentCreatedEvent.getImageId();
        this.time = commentCreatedEvent.getTime();
        this.like = commentCreatedEvent.getLike();
        this.reviewId = commentCreatedEvent.getReviewId();
    }
}
