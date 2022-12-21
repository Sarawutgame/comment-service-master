package com.example.commentservice.query.rest;

import com.example.commentservice.core.CommentEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<CommentRestModel> getCommentByReviewId(@RequestParam String reviewId){
        Object result = rabbitTemplate.convertSendAndReceive("CommentExchange", "idcomment", reviewId);
        return (List<CommentRestModel>) result;

    }

    @GetMapping("/findById")
    private CommentEntity getCommentById(@RequestParam String id){
        Object result = rabbitTemplate.convertSendAndReceive("CommentExchange", "commentbyid", id);
        return (CommentEntity) result;
    }

    @GetMapping("/findByUserId")
    public List<CommentRestModel> getCommentByUserId(@RequestParam String userId){
        Object result = rabbitTemplate.convertSendAndReceive("CommentExchange", "commentByUserId", userId);
        return (List<CommentRestModel>) result;
    }
}
