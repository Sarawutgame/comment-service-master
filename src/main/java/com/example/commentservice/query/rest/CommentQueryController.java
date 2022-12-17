package com.example.commentservice.query.rest;

import com.example.commentservice.query.FindCommentsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentQueryController {
    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<CommentRestModel> getComments(){
        FindCommentsQuery findCommentsQuery = new FindCommentsQuery();
        List<CommentRestModel> comments = queryGateway
                .query(findCommentsQuery, ResponseTypes.multipleInstancesOf(CommentRestModel.class)).join();
        return comments;
    }
}
