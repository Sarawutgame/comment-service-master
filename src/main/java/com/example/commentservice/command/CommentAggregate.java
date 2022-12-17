package com.example.commentservice.command;

import com.example.commentservice.event.CommentCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class CommentAggregate {
    @AggregateIdentifier
    private String commentId;
    private String user;
    private String userid;
    private Integer rating;
    private String description;
    private String recommendMenu;
    private String imageId;
    private String time;
    private Integer like;

    public CommentAggregate() {
    }

    @CommandHandler
    public CommentAggregate(CreateCommentCommand createCommentCommand){
        if(createCommentCommand.getDescription() == null || createCommentCommand.getDescription().isBlank()){
            throw new IllegalArgumentException("Pls Enter Comment");
        }
        CommentCreatedEvent commentCreatedEvent = new CommentCreatedEvent();
        BeanUtils.copyProperties(createCommentCommand, commentCreatedEvent);
        AggregateLifecycle.apply(commentCreatedEvent);
    }

    @EventSourcingHandler
    public void on(CommentCreatedEvent commentCreatedEvent){
        this.commentId = commentCreatedEvent.getCommentId();
        this.user = commentCreatedEvent.getUser();
        this.userid = commentCreatedEvent.getUserid();
        this.rating = commentCreatedEvent.getRating();
        this.description = commentCreatedEvent.getDescription();
        this.recommendMenu = commentCreatedEvent.getRecommendMenu();
        this.imageId = commentCreatedEvent.getImageId();
        this.time = commentCreatedEvent.getTime();
        this.like = commentCreatedEvent.getLike();
    }
}
