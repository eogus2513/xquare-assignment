package com.xquare.assignment.domain.post.domain.repository;

import com.xquare.assignment.domain.post.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("select p from Post p join fetch p.auth order by p.createdAt")
    List<Post> findAllByJoinFetch(Pageable pageable);
}
