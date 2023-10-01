package com.tafa.PapColab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tafa.PapColab.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	Post findByTitle(String title);

	@Query(value = "select * from post s where s.title like :keyword%", nativeQuery = true)
	List<Post> findByKeyword(@Param("keyword") String keyword);

	List<Post> findByUserId(Long userId);

	@Query(value = "select * from post p where (p.title like %:interest% or p.description like %:interest%) and p.user_id != :userId and p.user_id not in (:collabIds) limit 50", nativeQuery = true)
	List<Post> getFeed(@Param("interest") String interest, @Param("userId") Long userId,
			@Param("collabIds") String collabIds);

}
