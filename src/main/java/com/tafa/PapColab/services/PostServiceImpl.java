package com.tafa.PapColab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tafa.PapColab.controllers.dto.PostDto;
import com.tafa.PapColab.exception.ResourceNotFoundException;
import com.tafa.PapColab.models.Post;
import com.tafa.PapColab.models.User;
import com.tafa.PapColab.repository.PostRepository;
import com.tafa.PapColab.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.AttributeNotFoundException;
import javax.security.auth.login.AccountNotFoundException;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = null;
        try {
            post = postRepository.findById(id)
                    .orElseThrow(() -> new AttributeNotFoundException("Post not found with id: " + id));
        } catch (AttributeNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertToDto(post);
    }

    @Override
    @Transactional
    public PostDto updatePost(Long id, PostDto postDto) {
        Post existingPost = null;
        try {
            existingPost = postRepository.findById(id)
                    .orElseThrow(() -> new AccountNotFoundException("Post not found with id: " + id));
        } catch (AccountNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        existingPost.setTitle(postDto.getTitle());
        existingPost.setDescription(postDto.getDescription());

        Post updatedPost = postRepository.save(existingPost);
        return convertToDto(updatedPost);
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        Post post = null;
        try {
            post = postRepository.findById(id)
                    .orElseThrow(() -> new AttributeNotFoundException("Post not found with id: " + id));
        } catch (AttributeNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        postRepository.delete(post);
    }

    private PostDto convertToDto(Post post) {
        return new PostDto(post.getId(), post.getTitle(), post.getDescription(), post.getUser());
    }

    private Post convertToEntity(PostDto postDto) {
        return new Post(postDto.getTitle(), postDto.getDescription(), postDto.getUser());
    }

    @Override
    @Transactional
    public PostDto createPost(PostDto postDto) {
        // Convert the PostDto to a Post entity
        Post post = convertToEntity(postDto);

        // Save the post to the database
        Post savedPost = postRepository.save(post);

        // Convert the saved Post entity back to a PostDto
        return convertToDto(savedPost);
    }

    @Override
    public Post save(PostDto postDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Post findpostBytitle(String title) {

        return postRepository.findByTitle(title);
    }

    @Override
    public List<Post> findpostBykeyword(String keyword) {

        return postRepository.findByKeyword(keyword);
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Override
    public Boolean isPostExitsById(long l) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Post> getPostsByUserId(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        return posts;
    }

    @Override
    public Post findPostById(Long id) throws Exception {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id:" + id + " not found"));
        ;
        return post;
    }

    @Override
    public List<Post> getFeed(User user) {
        String collabIds = user.getCollaboratorIds().toString();
        collabIds = collabIds.substring(1, collabIds.length() - 1);
        List<Post> posts = new ArrayList<Post>();
        if (user.getInterest() != null) {
            for (String interest : user.getInterest().split(",")) {
                posts.addAll(postRepository.getFeed(interest.trim(), user.getId(), collabIds));
            }
        }
        return posts;
    }

}
