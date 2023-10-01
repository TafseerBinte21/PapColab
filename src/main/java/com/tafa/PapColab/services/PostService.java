package com.tafa.PapColab.services;

import java.util.List;

import com.tafa.PapColab.controllers.dto.PostDto;
import com.tafa.PapColab.models.Post;
import com.tafa.PapColab.models.User;

public interface PostService {

	Post save(PostDto postDto);

	Post findpostBytitle(String title);

	Post findPostById(Long Id) throws Exception;

	List<Post> findpostBykeyword(String keyword);

	List<Post> findAll();

	Boolean isPostExitsById(long l);

	PostDto updatePost(Long id, PostDto postDto);

	void deletePost(Long id);

	PostDto createPost(PostDto postDto);

	PostDto getPostById(Long id);

	List<PostDto> getAllPosts();

	List<Post> getPostsByUserId(Long userId);

	List<Post> getFeed(User user);

}
