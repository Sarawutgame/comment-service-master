package com.example.commentservice.command.rest;

import com.example.commentservice.command.CreateCommentCommand;
import com.example.commentservice.command.UpdateCommentCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommandGateway commandGateway;

    @Autowired
    public CommentController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createComment(@RequestBody CreateCommentModel model){
        CreateCommentCommand command = CreateCommentCommand.builder()
                ._id(UUID.randomUUID().toString())
                .user(model.getUser())
                .userid(model.getUserid())
                .rating(model.getRating())
                .recommendMenu(model.getRecommendMenu())
                .description(model.getDescription())
                .imageId(model.getImageId())
                .time(model.getTime())
                .like(model.getLike())
                .reviewId(model.getReviewId())
                .ban(model.isBan())
                .report(model.getReport())
                .build();

        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }


    @PutMapping
    public String updateComment(@RequestBody UpdateCommentModel model){
        UpdateCommentCommand command = UpdateCommentCommand.builder()
                ._id(model.get_id())
                .user(model.getUser())
                .userid(model.getUserid())
                .rating(model.getRating())
                .recommendMenu(model.getRecommendMenu())
                .description(model.getDescription())
                .imageId(model.getImageId())
                .time(model.getTime())
                .like(model.getLike())
                .reviewId(model.getReviewId())
                .ban(model.isBan())
                .report(model.getReport())
                .build();
        String result;
        try{
            String idUpdate = commandGateway.sendAndWait(command);
            result = "Update Complete ID: " + idUpdate;
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }



}
