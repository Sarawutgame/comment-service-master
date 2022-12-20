package com.example.commentservice.query.rest;

import com.example.commentservice.query.FindCommentByReviewIdQuery;
import com.example.commentservice.query.FindCommentsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
//@RequestMapping("/comment")
@Service
public class CommentQueryController {
    @Autowired
    QueryGateway queryGateway;

//    @GetMapping
    @RabbitListener(queues = "GetAllComment")
    public List<CommentRestModel> getComments(String messange){
        FindCommentsQuery findCommentsQuery = new FindCommentsQuery();
        List<CommentRestModel> comments = queryGateway
                .query(findCommentsQuery, ResponseTypes.multipleInstancesOf(CommentRestModel.class)).join();
        return comments;
    }

    @RabbitListener(queues = "GetCommentByReviewId")
    public List<CommentRestModel> getCommentByReviewId(@RequestParam String reviewId){
        List<CommentRestModel> comments = queryGateway
                .query(new FindCommentByReviewIdQuery(reviewId), ResponseTypes.multipleInstancesOf(CommentRestModel.class)).join();
        return comments;
    }
}
