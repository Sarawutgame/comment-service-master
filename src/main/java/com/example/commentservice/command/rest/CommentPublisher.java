package com.example.commentservice.command.rest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public String createComment(@RequestBody CreateCommentModel model){
        Object result = rabbitTemplate.convertSendAndReceive("CommentExchange", "ccomment", model);
        return (String) result;
    }

    @PutMapping
    public String updateComment(@RequestBody UpdateCommentModel model){
        Object result = rabbitTemplate.convertSendAndReceive("CommentExchange", "ucomment", model);
        return "Update Complete";
    }
}
