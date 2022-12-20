package com.example.commentservice.query.rest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentQueryPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public List<CommentRestModel> getComments(){
        Object result = rabbitTemplate.convertSendAndReceive("CommentExchange", "allcomment", "hello");
        return ((List<CommentRestModel>) result);
    }

    @GetMapping("/findByReviewId")
    public List<CommentRestModel> getCommentByReviewId(){
        Object result = rabbitTemplate.convertSendAndReceive();
        return ;

    }
}
