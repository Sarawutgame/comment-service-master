package com.example.commentservice.core.data;

import com.example.commentservice.core.CommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CommentRepository extends MongoRepository<CommentEntity, String> {
//    CommentEntity findByCommentId(String commentId);
    @Query(value = "{'reviewId': ?0}")
    List<CommentEntity> findByReviewId(String reviewId);
}
