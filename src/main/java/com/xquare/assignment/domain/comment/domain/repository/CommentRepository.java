package com.xquare.assignment.domain.comment.domain.repository;

import com.xquare.assignment.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Query("select c from Comment c join fetch c.client where c.post.id = :postId order by c.createdAt")
    List<Comment> findAllByJoinFetch(Long postId);
}
