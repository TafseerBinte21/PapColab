package com.tafa.PapColab.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tafa.PapColab.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	Comment findByComment(String comment);

	List<Comment> findByUserId(Long userId);

	List<Comment> findByPostId(Long postId);

	Optional<Comment> findById(Long id);

}
