package com.example.commentservice.core.data;

import com.example.commentservice.core.CommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<CommentEntity, String> {
//    CommentEntity findByCommentId(String commentId);
}
