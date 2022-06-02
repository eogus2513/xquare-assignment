package com.xquare.assignment.domain.post.domain.repository;

import com.xquare.assignment.domain.post.domain.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAll();
}
