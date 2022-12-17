package com.example.commentservice.rest;

import com.example.commentservice.command.CreateCommentCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .commentId(UUID.randomUUID().toString())
                .user(model.getUser())
                .userid(model.getUserid())
                .rating(model.getRating())
                .description(model.getDescription())
                .imageId(model.getImageId())
                .time(model.getTime())
                .like(model.getLike()).build();

        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }


}
