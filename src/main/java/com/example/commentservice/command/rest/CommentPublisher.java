package com.example.commentservice.command.rest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/comment")
public class CommentPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public String createComment(@RequestBody CreateCommentModel model){
        Object result = rabbitTemplate.convertSendAndReceive("CommentExchange", "ccomment", model);

        String mess = model.getReviewId() + "__" + model.getRating();

        rabbitTemplate.convertAndSend("ReviewExchange", "uprating", mess);
        return (String) result;
    }

    @PutMapping
    public String updateComment(@RequestBody UpdateCommentModel model){
        Object result = rabbitTemplate.convertSendAndReceive("CommentExchange", "ucomment", model);
        String mess = model.getReviewId() + "__" + model.getRating();
        rabbitTemplate.convertAndSend("ReviewExchange", "ratingold", mess);
        return "Update Complete";
    }
}
